void main() {
    ArrayList<Produkt> produkty = new ArrayList<>();

    try (BufferedReader czytnik = new BufferedReader(new FileReader("produkty.txt"))) {
        String linia;
        while ((linia = czytnik.readLine()) != null) {
            String[] czesci = linia.split(";");
            String nazwa = czesci[0];
            String cenaStr = czesci[1];
            int ilosc = Integer.parseInt(czesci[2]);
            String kod = czesci[3];

            if (cenaStr.equals("BRAK")) {
                System.out.println("[POMINIĘTO] " + nazwa + " błędna cena: \"BRAK\"");
                continue;
            }

            double cena = Double.parseDouble(cenaStr);
            produkty.add(new Produkt(nazwa, cena, ilosc, kod));
        }
    } catch (IOException e) {
        System.out.println("Błąd odczytu pliku!");
    }
    System.out.println();
    System.out.println("Wczytano " + produkty.size() + " produktów.");

    ArrayList<Produkt> poprawne = new ArrayList<>();
    ArrayList<String> bledneNazwy = new ArrayList<>();

    for (Produkt p : produkty) {
        if (p.czyKodPoprawny()) {
            poprawne.add(p);
        } else {
            bledneNazwy.add(p.getNazwa());
        }
    }

    System.out.println("Produkty z POPRAWNYM kodem (" + poprawne.size() + "):");
    for (Produkt p : poprawne) {
        System.out.println(p.getNazwa() + " | " + p.getCena() + " zł | " + p.getIlosc() + " szt | kod: " + p.getKodPartii());
    }
    System.out.println();
    System.out.print("Produkty z BŁĘDNYM kodem (" + bledneNazwy.size() + "):\n");
    System.out.println(String.join(", ", bledneNazwy));
    System.out.println();

    System.out.println("Etykiety produktów (poprawny kod):");
    for (Produkt p : poprawne) {
        System.out.println(p.getNazwa() + " -> " + p.generujEtykiete());
    }
}
