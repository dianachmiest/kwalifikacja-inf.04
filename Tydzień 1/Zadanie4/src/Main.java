void main() {
    Konto[] konta = new Konto[3];
    konta[0] = new Konto("PL001", 1000.00);
    konta[1] = new KontoOszczednosciowe("PL002", 1000.00);
    konta[2] = new KontoPremium("PL003", 1000.00, 500.00);


    //4.1
    for (Konto k : konta) {
        System.out.println(k.toString());
    }
    System.out.println();

    //4.2
    for (Konto k : konta) {
        if (k instanceof KontoOszczednosciowe) {
            System.out.println("--- KontoOszczednosciowe " + k.numerKonta + " ---");
            k.wplac(1000);
            System.out.println("Wpłata 1000 zł (+1%) -> saldo: " + String.format("%.2f", k.saldo) + " zł");
            k.wyplac(1200);
            System.out.println("Wypłata 1200 zł (+5 zł prowizji) -> saldo: " + String.format("%.2f", k.saldo) + " zł");

        }
        else if (k instanceof KontoPremium) {
            System.out.println("--- KontoPremium " + k.numerKonta + " ---");
            k.wplac(1000);
            System.out.println("Wpłata 1000 zł -> saldo: " + String.format("%.2f", k.saldo) + " zł");
            k.wyplac(1200);
            System.out.println("Wypłata 1200 zł -> saldo: " + String.format("%.2f", k.saldo) + " zł");
            ((KontoPremium) k).naliczOdsetkiKarne();
        }
        else {
            System.out.println("--- Konto " + k.numerKonta + " ---");
            k.wplac(1000);
            System.out.println("Wpłata 1000 zł -> saldo: " + String.format("%.2f", k.saldo) + " zł");
            k.wyplac(1200);
            System.out.println("Wypłata 1200 zł -> saldo: " + String.format("%.2f", k.saldo) + " zł");

        }
        System.out.println("Końcowe saldo: " + String.format("%.2f", k.saldo) + " zł");
        System.out.println();
    }
}
