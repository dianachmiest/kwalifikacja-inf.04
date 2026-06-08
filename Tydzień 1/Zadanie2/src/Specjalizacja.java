public enum Specjalizacja {
    INF02, INF03, INF04;
    public String pobierzSformatowanaNazwe() {
        if (this == INF02) return "INF.02";
        if (this == INF03) return "INF.03";
        if (this == INF04) return "INF.04";
        return "";
    }
}
