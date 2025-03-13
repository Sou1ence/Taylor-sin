package org.example.taylor;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;


/**
 * Application for visualization of approximate calculation of sin(x)
 * using Taylor series expansion.
 *
 * @author Kostiantyn Feniuk [s29919]
 */
public class TaylorSinFX extends Application {

    /* Main method */
    public static void main (String [] args) { launch(args); }


    /**
     * Start method for JavaFX application.
     *
     * @param stage primary stage
     */
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        // INPUT FORM
        HBox inputForm = new HBox(10);
        inputForm.getStyleClass().add("input-form");

        // CALCULATE BUTTON
        Button calculateButton = new Button("Calculate");

        TextField angleField = new TextField();
        angleField.setPromptText("Enter angle");
        angleField.setOnAction(e -> { calculateButton.fire(); });

        // UNIT SELECTOR
        ComboBox<String> unitCombo = new ComboBox<>();
        unitCombo.getItems().addAll("Degrees", "Radians");
        unitCombo.setValue("Degrees");


        // LABELS
        Label angleLabel = new Label("Angle:");
        Label unitLabel = new Label("Unit:");

        inputForm.getChildren().addAll(angleLabel, angleField, unitLabel, unitCombo, calculateButton);


        // CHART
        NumberAxis xAxis = new NumberAxis(1, 10, 1); // (lowerBound, upperBound, tickUnit)
        NumberAxis yAxis = new NumberAxis(); // auto-ranging

        xAxis.setLabel("Number of row members");
        yAxis.setLabel("Value of sin(x)");

        LineChart<Number, Number> ch = new LineChart<>(xAxis, yAxis);
        ch.setTitle("Taylor Series for sin(x)");
        ch.setLegendVisible(true);
        ch.setCreateSymbols(true);
        ch.setAnimated(true);

        ch.getData().add(new XYChart.Series<>());

        // ADDITIONAL INFO
        VBox additInfoBox = new VBox(10);
        additInfoBox.getStyleClass().add("info-box");

        Label header = new Label("Approximation accuracy:");
        header.getStyleClass().add("info-header");

        Label infoLabel = new Label("Enter an angle and press 'Calculate'");
        infoLabel.getStyleClass().add("info-content");

        additInfoBox.getChildren().addAll(header, infoLabel);

        Label myLabel = new Label("s29919");
        myLabel.getStyleClass().add("personal-label");
        myLabel.setTranslateX(350);

        root.getChildren().addAll(inputForm, ch, additInfoBox, myLabel);


        calculateButton.setOnAction(e -> {
            try {
                double angle = Double.parseDouble(angleField.getText());
                String unit = unitCombo.getValue();
                updateChart(ch, infoLabel, angle, unit);
            } catch (NumberFormatException ex) {
                infoLabel.setText("Invalid input. Please enter a correct number.");
            }
        });

        Scene sc = new Scene(root, 800, 700);
        sc.getStylesheets().add("style.css");

        stage.setTitle("Taylor Series for sin(x)");
        stage.setScene(sc);
        stage.show();

    }


    /**
     * Updates the graph based on the entered angle value and selected units.
     *
     * @param ch Chart object
     * @param infoLabel Label for displaying accuracy information
     * @param angle User entered angle
     * @param unit Units of measurement (degrees or radians)
     */
    private void updateChart (LineChart <Number, Number> ch, Label infoLabel, double angle, String unit) {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Approximation");

        // MATH LOGIC

        // Convert angle to radians if it is in degrees
        double xRad = unit.equals("Degrees") ? Math.toRadians(angle) : angle;

        // Angle normalization
        xRad = xRad % (2 * Math.PI); // removing useless full circles
        if (xRad < 0) xRad += 2 * Math.PI; // negative angles to positive

        // Sine symmetry with respect to the Y-axis
        int sign = 1;
        if (xRad > Math.PI) {
            xRad = 2 * Math.PI - xRad;
            sign = -1;
        }

        if (xRad > Math.PI / 2)
            xRad = Math.PI - xRad;

        // Exact value of sin(x)
        double ref = Math.sin(xRad);


        // CHART UPDATE
        for (int i = 1; i <= 10; i++) {
            double approx = sign * calculateTaylorSin(xRad, i);
            series.getData().add(new XYChart.Data<>(i, approx));
        }

        ch.getData().clear();
        ch.getData().add(series);

        // Calculation info update
        StringBuilder infoText = new StringBuilder();
        infoText.append(String.format("Real value sin(%s): %.10f\n", formatAngle(angle, unit), sign * ref));

        double finalApprox = sign * calculateTaylorSin(xRad, 10);
        double error = Math.abs(finalApprox - sign * ref);

        infoText.append(String.format("Approximation with 10 members: %.10f\n", finalApprox));
        infoText.append(String.format("Absolute error: %.10f\n", error));
        infoText.append(String.format("Relative error: %.10f%%", error / Math.abs(sign * ref) * 100));

        infoLabel.setText(infoText.toString());
    }


    /**
     * Calculates the approximation of sin(x) using Taylor series.
     *
     * The Taylor series for sin(x) looks like this:
     * sin(x) = x - x³/3! + x⁵/5! - x⁷/7! + ...
     *
     * Formula for each term of the series:
     * term(n) = (-1)^n * x^(2n+1) / (2n+1)!
     *
     * @param x Value in radians
     * @param terms Number of members of the series
     * @return Approximate value of sin(x)
     */
    private double calculateTaylorSin(double x, int terms) {
        double sum = 0.0;
        double term = x; // first member of the series - just x

        for (int n = 0; n < terms; n++) {
            sum += term;
            term = -term * x * x / ((2 * n + 2) * (2 * n + 3));
        }
        return sum;
    }

    /**
     * Formats the angle value with the specified unit for user output.
     *
     * @param angle Angle value
     * @param unit Unit of measurement
     * @return Formatted angle string
     */
    private String formatAngle(double angle, String unit) {
        return unit.equals("Degrees") ?
                String.format("%.2f°", angle) :
                String.format("%.2f rad", angle);
    }

}