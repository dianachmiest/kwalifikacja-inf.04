using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Zadanie5
{
    public enum Specjalizacja
    {
        INF02,
        INF03,
        INF04
    }

    public class Egzaminator
    {
        private static int licznikInstancji = 0;
        private readonly int id;

        public int Id { get { return id; } }
        public string Imie { get; set; }
        public string Nazwisko { get; set; }
        public Specjalizacja Kwalifikacja { get; set; }

        public Egzaminator(string imie, string nazwisko, Specjalizacja kwalifikacja)
        {
            licznikInstancji++;
            this.id = licznikInstancji;
            this.Imie = imie;
            this.Nazwisko = nazwisko;
            this.Kwalifikacja = kwalifikacja;
        }

        public string SformatowanaSpecjalizacja
        {
            get
            {
                if (Kwalifikacja == Specjalizacja.INF02) return "INF.02";
                if (Kwalifikacja == Specjalizacja.INF03) return "INF.03";
                if (Kwalifikacja == Specjalizacja.INF04) return "INF.04";
                return "";
            }
        }

        public override string ToString()
        {
            return string.Format("[ID: {0:D3}] {1} {2} | {3}", id, Imie, Nazwisko, SformatowanaSpecjalizacja);
        }
    }

    public partial class MainWindow : Window
    {
        private List<Egzaminator> listaEgzaminatorow = new List<Egzaminator>();

        public MainWindow()
        {
            InitializeComponent();

            cmbSpecjalizacja.Items.Add(Specjalizacja.INF02);
            cmbSpecjalizacja.Items.Add(Specjalizacja.INF03);
            cmbSpecjalizacja.Items.Add(Specjalizacja.INF04);
            cmbSpecjalizacja.SelectedIndex = 0;

            listaEgzaminatorow.Add(new Egzaminator("Anna", "Nowak", Specjalizacja.INF02));
            listaEgzaminatorow.Add(new Egzaminator("Piotr", "Lisowski", Specjalizacja.INF04));
            listaEgzaminatorow.Add(new Egzaminator("Ewa", "Wąsowska", Specjalizacja.INF03));
            listaEgzaminatorow.Add(new Egzaminator("Jan", "Kowalski", Specjalizacja.INF02));
            listaEgzaminatorow.Add(new Egzaminator("Tomasz", "Bąk", Specjalizacja.INF04));

            OdswiezListe();
        }

        private void OdswiezListe()
        {
            lstEgzaminatorzy.Items.Clear();
            foreach (Egzaminator e in listaEgzaminatorow)
            {
                lstEgzaminatorzy.Items.Add(e.ToString());
            }
        }

        private void PoleTekstowe_TextChanged(object sender, TextChangedEventArgs e)
        {
            btnDodaj.IsEnabled = !string.IsNullOrWhiteSpace(txtImie.Text) && !string.IsNullOrWhiteSpace(txtNazwisko.Text);
        }

        private void BtnDodaj_Click(object sender, RoutedEventArgs e)
        {
            Specjalizacja wybrana = (Specjalizacja)cmbSpecjalizacja.SelectedItem;
            Egzaminator nowy = new Egzaminator(txtImie.Text, txtNazwisko.Text, wybrana);
            listaEgzaminatorow.Add(nowy);

            txtImie.Clear();
            txtNazwisko.Clear();
            OdswiezListe();
        }

        private void BtnSzukaj_Click(object sender, RoutedEventArgs e)
        {
            int szukaneId;
            if (!int.TryParse(txtSzukajId.Text, out szukaneId))
            {
                lblWynikSzukania.Text = "Błąd: Wprowadzono niepoprawny format ID!";
                lblWynikSzukania.Foreground = Brushes.Orange;
                return;
            }

            Egzaminator znaleziony = null;
            foreach (Egzaminator egs in listaEgzaminatorow)
            {
                if (egs.Id == szukaneId)
                {
                    znaleziony = egs;
                    break;
                }
            }

            if (znaleziony != null)
            {
                lblWynikSzukania.Text = znaleziony.Imie + " " + znaleziony.Nazwisko + " - " + znaleziony.SformatowanaSpecjalizacja;
                lblWynikSzukania.Foreground = Brushes.DarkGreen;
            }
            else
            {
                lblWynikSzukania.Text = "Błąd: Egzaminator o ID [" + szukaneId + "] nie widnieje w systemie.";
                lblWynikSzukania.Foreground = Brushes.Red;
            }
        }
    }
}