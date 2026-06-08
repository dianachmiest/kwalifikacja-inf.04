void main() {
    System.out.println("Licznik przed tworzeniem: " + Egzaminator.getLicznikInstancji());

    System.out.println("--- Tworzę egzaminatora #1 ---");
    Egzaminator egz1 = new Egzaminator("Anna", "Nowak", Specjalizacja.INF02);
    System.out.println("Dane: " + egz1.wyswietlDane());
    System.out.println("Licznik teraz: " + Egzaminator.getLicznikInstancji());

    System.out.println("--- Tworzę egzaminatora #2 ---");
    Egzaminator egz2 = new Egzaminator("Piotr", "Lisowski", Specjalizacja.INF04);
    System.out.println("Dane: " + egz2.wyswietlDane());
    System.out.println("Licznik teraz: " + Egzaminator.getLicznikInstancji());

    System.out.println("--- Tworzę egzaminatora #3 ---");
    Egzaminator egz3 = new Egzaminator("Ewa", "Wąsowska", Specjalizacja.INF03);
    System.out.println("Dane: " + egz3.wyswietlDane());
    System.out.println("Licznik teraz: " + Egzaminator.getLicznikInstancji());

    System.out.println("Licznik po zakończeniu: " + Egzaminator.getLicznikInstancji());

    int roznica = egz2.getId() - egz1.getId();
    System.out.println("Różnica ID: obj2 - obj1 = " + roznica);
}
