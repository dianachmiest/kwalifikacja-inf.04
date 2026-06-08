public class Produkt {
    private String nazwa;
    private double cena;
    private int ilosc;
    private String kodPartii;

    public Produkt(String nazwa, double cena, int ilosc, String kodPartii) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
        this.kodPartii = kodPartii;
    }
    public int obliczSumeKontrolna() {
        int[] wagi = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int suma = 0;
        for (int i = 0; i < 10; i++) {
            int cyfra = Character.getNumericValue(kodPartii.charAt(i));
            suma += cyfra * wagi[i];
        }
        return suma;
    }
    public boolean czyKodPoprawny() {
        if (kodPartii.length() != 11) {
            return false;
        }
        int suma = obliczSumeKontrolna();
        int reszta = suma % 10;
        int wyliczonaCyfraKontrolna;

        if (reszta == 0) {
            wyliczonaCyfraKontrolna = 0;
        } else {
            wyliczonaCyfraKontrolna = 10 - reszta;
        }

        int rzeczywistaCyfraKontrolna = Character.getNumericValue(kodPartii.charAt(10));
        return wyliczonaCyfraKontrolna == rzeczywistaCyfraKontrolna;
    }
    public String generujEtykiete() {
        String tekst = this.nazwa;
        StringBuilder bezDuplikatow = new StringBuilder();
        if (tekst.length() > 0) {
            bezDuplikatow.append(tekst.charAt(0));
            for (int i = 1; i < tekst.length(); i++) {
                if (tekst.charAt(i) != tekst.charAt(i - 1)) {
                    bezDuplikatow.append(tekst.charAt(i));
                }
            }
        }

        String wynik = bezDuplikatow.toString();
        String samogloski = "aaeéiiouuyąąęęóóAĄEĘIÓOUU";
        StringBuilder koncowy = new StringBuilder();

        for (int i = 0; i < wynik.length(); i++) {
            char znak = wynik.charAt(i);
            if (samogloski.indexOf(znak) != -1) {
                koncowy.append('*');
            } else {
                koncowy.append(znak);
            }
        }
        return koncowy.toString();
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKodPartii() {
        return kodPartii;
    }

    public double getCena() {
        return cena;
    }

    public int getIlosc() {
        return ilosc;
    }
}

