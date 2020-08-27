import java.util.Scanner;

/**
 * Beispielcode für den Fahrkartenautomaten
 *
 * @author Hafezi
 */
class Fahrkartenautomat {
    public static void main(String[] args) {
        Scanner tastatur = new Scanner(System.in);
        // Achtung!!!!
        // Hier wird deutlich, warum double nicht für Geldbeträge geeignet ist.
        // =======================================
        double zuZahlenderBetrag;
        double eingezahlterGesamtbetrag;
        double eingeworfeneMuenze;
        double rueckgabebetrag;
        // Die Eingabe erfolgt anwenderfreudlich mit Dezimalpunkt: just testing
        double eingegebenerBetrag;

        // Den zu zahlenden Betrag ermittelt normalerweise der Automat
        // aufgrund der gewählten Fahrkarte(n).
        // -----------------------------------
        System.out.print("Zu zahlender Betrag (EURO): ");
        zuZahlenderBetrag = tastatur.nextDouble();

        // Geldeinwurf
        // -----------
        eingezahlterGesamtbetrag = 0.0;
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            System.out.println("Noch zu zahlen: " + (zuZahlenderBetrag - eingezahlterGesamtbetrag));
            System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();
            eingezahlterGesamtbetrag += eingeworfeneMuenze;
        }

        // Fahrscheinausgabe
        // -----------------
        System.out.println("\nFahrschein wird ausgegeben");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");

        // Rückgeldberechnung und -Ausgabe
        // -------------------------------
        rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
        if (rueckgabebetrag > 0.0) {
            System.out.println("Der Rückgabebetrag in Höhe von " + rueckgabebetrag + " EURO");
            System.out.println("wird in folgenden Münzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) // 2 EURO-Münzen
            {
                System.out.println("2 EURO");
                rueckgabebetrag -= 2.0;
            }
            while (rueckgabebetrag >= 1.0) // 1 EURO-Münzen
            {
                System.out.println("1 EURO");
                rueckgabebetrag -= 1.0;
            }
            while (rueckgabebetrag >= 0.5) // 50 CENT-Münzen
            {
                System.out.println("50 CENT");
                rueckgabebetrag -= 0.5;
            }
            while (rueckgabebetrag >= 0.2) // 20 CENT-Münzen
            {
                System.out.println("20 CENT");
                rueckgabebetrag -= 0.2;
            }
            while (rueckgabebetrag >= 0.1) // 10 CENT-Münzen
            {
                System.out.println("10 CENT");
                rueckgabebetrag -= 0.1;
            }
            while (rueckgabebetrag >= 0.05)// 5 CENT-Münzen
            {
                System.out.println("5 CENT");
                rueckgabebetrag -= 0.05;
            }
        }

        System.out.println("\nVergessen Sie nicht, den Fahrschein\n" +
                "vor Fahrtantritt entwerten zu lassen!\n" +
                "Wir wünschen Ihnen eine gute Fahrt.");
    }
}