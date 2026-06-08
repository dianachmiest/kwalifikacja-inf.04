import java.time.LocalDate;
public class Pojazd {
    private String numerRejestracyjny;
    private String rodzajPojazdu;
    private int rokProdukcji;
    private double przebieg;


//     Konstruktor inicjalizujący i walidujący dane pojazdu.
    public Pojazd(String numerRejestracyjny, String rodzajPojazdu, int rokProdukcji, double przebieg) {
        this.numerRejestracyjny = numerRejestracyjny;

        if (rodzajPojazdu.equals("osobowy") || rodzajPojazdu.equals("dostawczy") || rodzajPojazdu.equals("motocykl")) {
            this.rodzajPojazdu = rodzajPojazdu;
        } else {
            System.out.println("Błąd: Niepoprawny rodzaj pojazdu!");
            this.rodzajPojazdu = "osobowy";
        }

        int aktualnyRok = LocalDate.now().getYear();
        if (rokProdukcji >= 1900 && rokProdukcji <= aktualnyRok) {
            this.rokProdukcji = rokProdukcji;
        } else {
            System.out.println("Błąd: Niepoprawny rok produkcji!");
            this.rokProdukcji = 1900;
        }

        if (przebieg >= 0) {
            this.przebieg = przebieg;
        } else {
            System.out.println("Błąd: Przebieg nie może być ujemny!");
            this.przebieg = 0;
        }
    }


//     Metoda sprawdzająca, czy pojazd wymaga serwisu.
    public boolean czyWymagaSerwisu() {
        int aktualnyRok = LocalDate.now().getYear();
        int wiek = aktualnyRok - this.rokProdukcji;
        return wiek > 5 || this.przebieg > 20000;
    }
    @Override
    public String toString() {
        return "[" + numerRejestracyjny + "] " + rodzajPojazdu + ", " + rokProdukcji + "r., " + String.format("%.1f", przebieg) + " km";
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public String getRodzajPojazdu() {
        return rodzajPojazdu;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public double getPrzebieg() {
        return przebieg;
    }
    public void setPrzebieg(double przebieg) {
        if (przebieg >= 0) {
            this.przebieg = przebieg;
        } else {
            System.out.println("Błąd: Przebieg nie może być ujemny!");
        }
    }
}
