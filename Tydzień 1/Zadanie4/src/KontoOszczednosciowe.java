public class KontoOszczednosciowe extends Konto{
    public KontoOszczednosciowe(String numerKonta, double saldo) {
        super(numerKonta, saldo);
    }
    @Override
    public void wplac(double kwota) {
        if (kwota > 0) {
            double bonus = kwota * 0.01;
            this.saldo += (kwota + bonus);
        }
    }
    @Override
    public boolean wyplac(double kwota) {
        double kosztCalkowity = kwota + 5.0;
        if (this.saldo - kosztCalkowity >= 0) {
            this.saldo -= kosztCalkowity;
            return true;
        }
        System.out.println("Brak środków (wypłata + prowizja) na koncie " + numerKonta);
        return false;
    }
    @Override
    public String toString() {
        return "[KontoOszczednosciowe] " + numerKonta + " | saldo: " + String.format("%.2f", saldo) + " zł";
    }
}
