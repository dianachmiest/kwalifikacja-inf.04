import java.util.ArrayList;

void main() {
    ArrayList<Pojazd> flota = new ArrayList<>();

    flota.add(new Pojazd("WZ12345", "osobowy", 2018, 15230.0));
    flota.add(new Pojazd("KR99887", "dostawczy", 2014, 48120.5));
    flota.add(new Pojazd("WA00001", "motocykl", 1999, 2300.0));
    flota.add(new Pojazd("GD44321", "osobowy", 2019, 21050.0));
    flota.add(new Pojazd("PO55555", "osobowy", 2023, 5000.0));
    flota.add(new Pojazd("WR11122", "motocykl", 2022, 12000.0));

    System.out.println(flota.get(0) + " - SERWIS: " + (flota.get(0).czyWymagaSerwisu() ? "TAK" : "NIE"));
    System.out.println(flota.get(1) + "- SERWIS: " + (flota.get(1).czyWymagaSerwisu() ? "TAK" : "NIE"));
    System.out.println(flota.get(2) + "- SERWIS: " + (flota.get(2).czyWymagaSerwisu() ? "TAK" : "NIE"));
    System.out.println();

    wypiszRaportSerwisowy(flota, 2025);
}

//     Wypisuje raport pojazdów, które wymagają serwisu
public static void wypiszRaportSerwisowy(ArrayList<Pojazd> listaPojazdow, int aktualnyRok) {
    ArrayList<Pojazd> wymagajaceSerwisu = new ArrayList<>();

    for (Pojazd p : listaPojazdow) {
        if (p.czyWymagaSerwisu()) {
            wymagajaceSerwisu.add(p);
        }
    }
    int ileWymaga = wymagajaceSerwisu.size();
    int calaFlota = listaPojazdow.size();
    int procent = (ileWymaga * 100) / calaFlota;

    System.out.println("=== RAPORT SERWISOWY " + aktualnyRok + " ===");
    System.out.println("Pojazdy wymagające serwisu (" + ileWymaga + " z " + calaFlota + ", " + procent + "%):");
    for (Pojazd p : wymagajaceSerwisu) {
        System.out.println(p);
    }
}
