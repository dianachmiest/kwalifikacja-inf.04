public class Egzaminator {
    private static int licznikInstancji = 0;
    private final int id;
    private String imie;
    private String nazwisko;
    private Specjalizacja specjalizacja;

    public Egzaminator(String imie, String nazwisko, Specjalizacja specjalizacja) {
        licznikInstancji++;
        this.id = licznikInstancji;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.specjalizacja = specjalizacja;
    }

    public String wyswietlDane() {
        return String.format("[ID: %03d] %s %s | %s", id, imie, nazwisko, specjalizacja.pobierzSformatowanaNazwe());
    }
    public static int getLicznikInstancji() {
        return licznikInstancji;
    }
    public int getId() {
        return id;
    }
}
