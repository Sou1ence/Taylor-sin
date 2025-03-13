# TaylorSinFX - Wizualizacja szeregu Taylora dla funkcji sin(x)

## Spis treści
- [Wprowadzenie](#wprowadzenie)
- [Funkcjonalność](#funkcjonalność)
- [Wymagania systemowe](#wymagania-systemowe)
- [Instalacja](#instalacja)
- [Instrukcja użytkowania](#instrukcja-użytkowania)
- [Opis matematyczny](#opis-matematyczny)
- [Szczegóły implementacji](#szczegóły-implementacji)
- [Autor](#autor)
- [Licencja](#licencja)

## Wprowadzenie

TaylorSinFX to interaktywna aplikacja edukacyjna napisana w JavaFX, która wizualizuje obliczanie przybliżonej wartości funkcji sinus (sin(x)) przy użyciu rozwinięcia w szereg Taylora. Aplikacja ta została stworzona w celu ułatwienia zrozumienia koncepcji szeregów potęgowych i ich zastosowania w obliczeniach trygonometrycznych.

Aplikacja prezentuje wyniki w formie interaktywnego wykresu, który pokazuje, jak dokładność przybliżenia wzrasta wraz z uwzględnieniem kolejnych wyrazów szeregu. Jest to doskonałe narzędzie dydaktyczne dla studentów matematyki, inżynierii i nauk pokrewnych.

![изображение](https://github.com/user-attachments/assets/94eecc26-d283-447b-a8a6-6f5cbc94d1a1)


## Funkcjonalność

- **Interaktywny interfejs użytkownika** z intuicyjnym formularzem wejściowym
- **Wybór jednostek**: stopnie lub radiany
- **Wizualizacja w czasie rzeczywistym** przybliżenia funkcji sin(x)
- **Wykres liniowy** pokazujący dokładność przybliżenia w zależności od liczby wyrazów szeregu
- **Szczegółowe informacje o dokładności** obliczeń
- **Estetyczny, nowoczesny design** z ciemnym motywem
- **Optymalizacja obliczeń** dzięki wykorzystaniu właściwości symetrii funkcji sinus

## Wymagania systemowe

- Java 11 lub nowsza
- JavaFX 11 lub nowsza
- 64 MB RAM
- System operacyjny: Windows, macOS, Linux

## Instalacja

1. Upewnij się, że masz zainstalowaną Javę w wersji 11 lub nowszej
2. Pobierz pliki projektu z repozytorium
3. Skompiluj projekt za pomocą Maven:

```bash
mvn clean package
```

4. Uruchom aplikację:

```bash
java -jar target/taylor-sin-fx.jar
```

Alternatywnie, można uruchomić aplikację bezpośrednio z IDE, uruchamiając klasę `org.example.taylor.TaylorSinFX`.

## Instrukcja użytkowania

1. **Wprowadź wartość kąta** w polu tekstowym "Angle"
2. **Wybierz jednostkę** (stopnie lub radiany) z rozwijanej listy
3. **Kliknij przycisk "Calculate"** lub naciśnij Enter
4. **Obserwuj wykres** pokazujący, jak zmienia się przybliżona wartość sin(x) wraz z uwzględnieniem kolejnych wyrazów szeregu
5. **Sprawdź szczegółowe informacje** o dokładności przybliżenia w sekcji poniżej wykresu

## Opis matematyczny

Szereg Taylora dla funkcji sin(x) w punkcie x = 0 ma postać:

sin(x) = x - x³/3! + x⁵/5! - x⁷/7! + ...

Ogólna formuła dla n-tego wyrazu szeregu:

term(n) = (-1)ⁿ * x^(2n+1) / (2n+1)!

Dokładność przybliżenia rośnie wraz z uwzględnieniem większej liczby wyrazów szeregu. W praktyce, dla większości zastosowań, uwzględnienie kilku pierwszych wyrazów daje już bardzo dobre przybliżenie.

Aplikacja wykorzystuje również właściwości symetrii funkcji sinus, aby zoptymalizować obliczenia:
- sin(x + 2π) = sin(x) - periodyczność
- sin(-x) = -sin(x) - funkcja nieparzysta
- sin(π - x) = sin(x) - symetria względem x = π/2

## Szczegóły implementacji

Aplikacja została zaimplementowana w języku Java z wykorzystaniem biblioteki JavaFX. Główne komponenty aplikacji:

### Struktura projektu

```
src/
├── main/
│   ├── java/org/example/taylor/
│   │   └── TaylorSinFX.java
│   └── resources/
│       └── style.css
```

### Kluczowe metody

- `calculateTaylorSin(double x, int terms)` - Oblicza przybliżenie funkcji sin(x) przy użyciu określonej liczby wyrazów szeregu
- `updateChart(LineChart<Number, Number> ch, Label infoLabel, double angle, String unit)` - Aktualizuje wykres i informacje o dokładności na podstawie wprowadzonych danych
- `formatAngle(double angle, String unit)` - Formatuje kąt do wyświetlenia z odpowiednią jednostką

### Interfejs użytkownika

Interfejs użytkownika został zaprojektowany z myślą o prostocie i estetyce. Składa się z następujących elementów:
- Formularz wejściowy (pole tekstowe, lista rozwijana, przycisk)
- Wykres liniowy prezentujący wyniki
- Sekcja informacyjna z danymi o dokładności
- Etykieta z identyfikatorem autora (numer studenta)

### Stylizacja

Aplikacja korzysta z niestandardowego arkusza stylów CSS, który definiuje estetyczny ciemny motyw. Główne elementy stylizacji to:
- Gradient tła
- Zaokrąglone narożniki elementów
- Niestandardowe kolory kontrolek
- Stylizowany wykres z siatką
- Efekty hover dla elementów interaktywnych

## Autor

Aplikacja została stworzona przez Kostiantyna Feniuka, studenta o numerze indeksu s29919.

## Licencja

Copyright (c) 2025 Kostiantyn Feniuk. Wszelkie prawa zastrzeżone.

---

*Ten projekt został stworzony w celach edukacyjnych jako demonstracja wykorzystania szeregów Taylora w obliczeniach numerycznych oraz tworzenia interaktywnych aplikacji w JavaFX.*
