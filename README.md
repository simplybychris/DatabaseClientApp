# Android Database Client App (MySQL)

## Spis treści
* [Opis aplikacji](#opis-aplikacji)
* [Wykorzystane narzędzia](#wykorzystane-narzędzia)
* [Interfejs aplikacji](#interfejs-aplikacji)
* [Instrukcja użycia](#instrukcja-użycia)
* [Podsumowanie](#podsumowanie)

## Opis aplikacji
Aplikacja Database Client pozwala połączyć się z bazą danych MySQL poprzez telefon lub tablet z systemen Android, nawigować pomiędzy tabelami oraz widokami oraz zarządzać ich danymi (dodawanie, edycja, usuwanie, kopiowanie). 

## Wykorzystane narzędzia
* Java - obiektowy język programowania ogólnego zastosowania
* zestaw programistyczny dla systemu Android - pozwala pisać aplikacje na systemy Android
* MySQL Connector Java - oficjalny sterownik JDBC do bazy MySQL
* Gradle - narzędzie do automatyzacji, kompilacji, do tworzenia oprogramowania w wielu językach
* Safe Args - gradle plugin, który generuje proste klasy obiektów i konstruktorów w celu zapewnienia bezpiecznego dostępu do argumentów określonych dla miejsc docelowych i akcji

## Interfejs aplikacji
Widoki są generowane dynamicznie dla wybranych tabel zależnie od ilości kolumn, dlatego mogą delikatnie różnić się od przykładów.

### Widok tabel

<img src="https://user-images.githubusercontent.com/59068947/104658684-a3b39980-56c3-11eb-9a29-670c1a12258c.png" width="30%">

### Widok rekordów

<img src="https://user-images.githubusercontent.com/59068947/104658691-a7dfb700-56c3-11eb-9fc8-16e07b5ec0cd.png" width="30%">

### Widok opcji

<img src="https://user-images.githubusercontent.com/59068947/104658704-ab733e00-56c3-11eb-9b65-0068af1b4783.png" width="30%">

### Widok dodawania/edycji rekordów

<img src="https://user-images.githubusercontent.com/59068947/104658705-aca46b00-56c3-11eb-9379-2cfd09b863f5.png" width="30%">

<img src="https://user-images.githubusercontent.com/59068947/104658706-aca46b00-56c3-11eb-83f1-bd3e809a0b85.png" width="30%">

## Instrukcja użycia

By uruchomić projekt pobierz go na swój lokalny używając komendy:<br>
`git clone`<br>
Następnie uruchom projekt w AndroidStudio.<br>
Pobierz wymagane dependencje za pomocą Gradle.<br>
W pliku data/Async.java skonfiguruj ustawienia pod swoją bazę:

    private String host = "host";  
    private String port = "port";  
    private String database = "nazwa bazy";  
    private String user = "użytkownik";  
    private String password = "hasło";

Po konfiguracji możesz przejść do uruchomienia i użytkowania aplikacji.

## Podsumowanie

Aplikacja mobilna do zarządzania bazą danych z poziomu urządzenia wyposażonego w system Android w prosty i przejrzysty sposób pozwala na podgląd oraz zarządzanie danymi w bazie. Użytkownik bez znania języka SQL w przystępny sposób przy użyciu interaktywnego interfejsu może wykonywać zmiany w bazie.
