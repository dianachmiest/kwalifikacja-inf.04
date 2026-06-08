public class KontoPremium extends Konto {
    private double limitDebetu;
    public KontoPremium(String numerKonta, double saldo, double limitDebetu) {
        super(numerKonta, saldo);
        this.limitDebetu = limitDebetu;
    }

    @Override
    public boolean wyplac(double kwota) {
        if (this.saldo - kwota >= -limitDebetu) {
            this.saldo -= kwota;
            return true;
        }
        System.out.println("Przekroczono limit debetu na koncie " + numerKonta);
        return false;
    }

    public void naliczOdsetkiKarne() {
        if (this.saldo < 0) {
            double odsetki = Math.abs(this.saldo) * 0.10;
            this.saldo -= odsetki;
            System.out.println("Naliczono odsetki karne: " + String.format("%.2f", odsetki) + " zł");
        } else {
            System.out.println("Odsetki karne: 0,00 zł (saldo dodatnie)");
        }
    }
    @Override
    public String toString() {
        return "[KontoPremium] " + numerKonta + "| saldo: " + String.format("%.2f", saldo) + " zł limit: " + String.format("%.2f", limitDebetu) + " zł";
    }
}
