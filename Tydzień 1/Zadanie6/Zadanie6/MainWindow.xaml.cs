using System;
using System.Collections.ObjectModel;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Zadanie6
{
    public partial class MainWindow : Window
    {
        public class Produkt
        {
            public string Nazwa { get; set; }
            public double Cena { get; set; }
            public int Ilosc { get; set; }
            public string KodPartii { get; set; }
            public string StatusKodu { get; set; }

            public Produkt(string nazwa, double cena, int ilosc, string kodPartii)
            {
                this.Nazwa = nazwa;
                this.Cena = cena;
                this.Ilosc = ilosc;
                this.KodPartii = kodPartii;
                this.StatusKodu = CzyKodPoprawny() ? "Poprawny" : "Błędny";
            }

            private bool CzyKodPoprawny()
            {
                if (KodPartii.Length != 11) return false;

                int[] wagi = { 1, 3, 7, 9, 1, 3, 7, 9, 1, 3 };
                int suma = 0;
                for (int i = 0; i < 10; i++)
                {
                    int cyfra = int.Parse(KodPartii[i].ToString());
                    suma += cyfra * wagi[i];
                }

                int reszta = suma % 10;
                int wyliczona;
                if (reszta == 0) wyliczona = 0;
                else wyliczona = 10 - reszta;

                int rzeczywista = int.Parse(KodPartii[10].ToString());
                return wyliczona == rzeczywista;
            }

            public string GenerujEtykiete()
            {
                string tekst = Nazwa;
                string bezDuplikatow = "";

                if (tekst.Length > 0)
                {
                    bezDuplikatow += tekst[0];
                    for (int i = 1; i < tekst.Length; i++)
                    {
                        if (tekst[i] != tekst[i - 1])
                        {
                            bezDuplikatow += tekst[i];
                        }
                    }
                }

                string samogloski = "aaeéiiouuyąąęęóóAĄEĘIÓOUU";
                string wynik = "";
                for (int i = 0; i < bezDuplikatow.Length; i++)
                {
                    if (samogloski.Contains(bezDuplikatow[i].ToString()))
                    {
                        wynik += "*";
                    }
                    else
                    {
                        wynik += bezDuplikatow[i];
                    }
                }
                return wynik;
            }
        }
        private ObservableCollection<Produkt> listaProduktow = new ObservableCollection<Produkt>();

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            string sciezka = "produkty.txt";

            if (!File.Exists(sciezka))
            {
                txtPasekStatusu.Text = "Błąd: Brak pliku produkty.txt w katalogu aplikacji!";
                return;
            }

            int poprawneKody = 0;
            try
            {
                string[] linie = File.ReadAllLines(sciezka);
                foreach (string linia in linie)
                {
                    string[] czesci = linia.Split(';');
                    if (czesci.Length == 4)
                    {
                        string nazwa = czesci[0];
                        string cenaStr = czesci[1];
                        int ilosc = int.Parse(czesci[2]);
                        string kod = czesci[3];

                        if (cenaStr == "BRAK") continue;

                        double cena = double.Parse(cenaStr, System.Globalization.CultureInfo.InvariantCulture);
                        Produkt prod = new Produkt(nazwa, cena, ilosc, kod);

                        if (prod.StatusKodu == "Poprawny")
                        {
                            poprawneKody++;
                        }

                        listaProduktow.Add(prod);
                    }
                }

                lsvProdukty.ItemsSource = listaProduktow;
                txtPasekStatusu.Text = "Załadowano " + listaProduktow.Count + " produktów — " + poprawneKody + " z poprawnym kodem.";
            }
            catch (Exception)
            {
                txtPasekStatusu.Text = "Wystąpił krytyczny błąd podczas odczytu struktury pliku.";
            }
        }

        private void LsvProdukty_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Produkt wybrany = lsvProdukty.SelectedItem as Produkt;
            if (wybrany != null)
            {
                pnlSzczegoly.Visibility = Visibility.Visible;
                txtPelnaNazwa.Text = "Nazwa: " + wybrany.Nazwa;
                txtStatusKodu.Text = "Kod: " + wybrany.StatusKodu;

                if (wybrany.StatusKodu == "Poprawny")
                {
                    txtStatusKodu.Foreground = Brushes.Green;
                    txtEtykieta.Text = wybrany.GenerujEtykiete();
                }
                else
                {
                    txtStatusKodu.Foreground = Brushes.Red;
                    txtEtykieta.Text = "Brak etykiety - błędny kod";
                }
            }
        }
    }
}