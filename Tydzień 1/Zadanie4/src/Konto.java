public class Konto {
    protected String numerKonta;
    protected double saldo;

    public Konto(String numerKonta, double saldo) {
        this.numerKonta = numerKonta;
        this.saldo = saldo;
    }
    public void wplac(double kwota) {
        if (kwota > 0) {
            this.saldo += kwota;
        }
    }
    public boolean wyplac(double kwota) {
        if (this.saldo - kwota >= 0) {
            this.saldo -= kwota;
            return true;
        }
        System.out.println("Brak środków na koncie " + numerKonta);
        return false;
    }
    @Override
    public String toString() {
        return "[Konto] " + numerKonta + "| saldo: " + String.format("%.2f", saldo) + " zł";
    }
}
