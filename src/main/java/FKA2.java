import java.util.Scanner;

class FKA2 {

    public static double fahrkartenbestellungErfassen() {
        double[] ticketpreise = {2.90, 3.30, 3.60, 1.90, 8.60, 9.00, 9.60, 23.50, 24.30, 24.90};
        int[] anzahlTickets = new int[ticketpreise.length];

        double gesamtPreis = 0.0;
        int auswahl;
        Scanner tastatur = new Scanner(System.in);

        System.out.println(" Fahrkartenbestellvorgang:");
        System.out.println(" =========================");
        do {
            auswahl = fahrkartenMenue();

            if (auswahl == 0)
                for (int i = 0; i < ticketpreise.length; i++)
                    gesamtPreis += ticketpreise[i] * anzahlTickets[i];
            else
                erfasseTicketAnzahl(anzahlTickets, auswahl);

        } while (auswahl != 0);

        return gesamtPreis;

    }

    public static void erfasseTicketAnzahl(int[] anzahlTickets, int auswahl) {
        Scanner tastatur = new Scanner(System.in);
        System.out.print("\n  Anzahl der Tickets: ");
        anzahlTickets[auswahl - 1] += tastatur.nextInt();
    }

    public static int fahrkartenMenue() {
        String[] eintraege = {"Einzelfahrschein Berlin AB ",
                "Einzelfahrschein Berlin BC ",
                "Einzelfahrschein Berlin ABC",
                "Kurzstrecke",
                "Tageskarte Berlin AB",
                "Tageskarte Berlin BC",
                "Tageskarte Berlin ABC",
                "Kleingruppen-Tageskarte Berlin AB",
                "Kleingruppen-Tageskarte Berlin BC ",
                "Kleingruppen-Tageskarte Berlin ABC "
        };
        int auswahl;
        boolean istEingabeKorrekt = false;
        Scanner tastatur = new Scanner(System.in);

        do {
            System.out.println("\n  Wählen Sie :");
            for (int i = 0; i < eintraege.length; i++)
                System.out.println("    " + eintraege[i] + " (" + (i + 1) + ")");
            System.out.println("    Bezahlen (0)\n");
            System.out.print("  Ihre Wahl: ");
            auswahl = tastatur.nextInt();
            if (auswahl < eintraege.length && auswahl >= 0)
                istEingabeKorrekt = true;
            else
                System.out.println("  >>falsche Eingabe<<");


        } while (!istEingabeKorrekt);
        return auswahl;
    }

    public static double fahrkartenBezahlen(double zuZahlenderBetrag) {
        double eingeworfeneMuenze;
        double eingezahlterGesamtbetrag = 0.0;
        Scanner tastatur = new Scanner(System.in);

        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            System.out.format("  Noch zu zahlen: %4.2f € %n", (zuZahlenderBetrag - eingezahlterGesamtbetrag));
            System.out.print("  Eingabe (mind. 5Ct, höchstens 10 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();
            eingezahlterGesamtbetrag += eingeworfeneMuenze;
        }
        return eingezahlterGesamtbetrag - zuZahlenderBetrag;
    }

    public static void fahrkartenAusgeben() {
        System.out.println("\n  Fahrschein wird ausgegeben");
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            warte(255);
        }
        System.out.println("\n");
    }

    public static void warte(int milisekunde) {
        try {
            Thread.sleep(milisekunde);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void rueckgeldAusgeben(double rueckgabebetrag) {

        if (rueckgabebetrag > 0.0) {
            System.out.format("  Der Rückgabebetrag in Höhe von %4.2f € %n", rueckgabebetrag);
            System.out.println("  wird in folgenden Münzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) {// 2 EURO-Münzen
                muenzeAusgeben(2, "EURO");
                rueckgabebetrag -= 2.0;
            }
            while (rueckgabebetrag >= 1.0) {// 1 EURO-Münzen
                muenzeAusgeben(1, "EURO");
                rueckgabebetrag -= 1.0;
            }
            while (rueckgabebetrag >= 0.5) // 50 CENT-Münzen
            {
                muenzeAusgeben(50, "CENT");
                rueckgabebetrag -= 0.5;
            }
            while (rueckgabebetrag >= 0.2) // 20 CENT-Münzen
            {
                muenzeAusgeben(20, "CENT");
                rueckgabebetrag -= 0.2;
            }
            while (rueckgabebetrag >= 0.1) // 10 CENT-Müzen
            {
                muenzeAusgeben(10, "CENT");
                rueckgabebetrag -= 0.1;
            }
            while (rueckgabebetrag >= 0.05)// 5 CENT-Münzen
            {
                muenzeAusgeben(5, "CENT");
                rueckgabebetrag -= 0.05;
            }
        }
    }

    public static void muenzeAusgeben(int betrag, String einheit) {

        System.out.println("                 * * *        ");
        System.out.println("               *       *      ");
        System.out.format("              *    %-2s   *     %n", betrag);
        System.out.format("              *   %4s  *     %n", einheit);
        System.out.println("               *       *      ");
        System.out.println("                 * * *        ");

    }

    public static void main(String[] args) {

        double zuZahlenderBetrag;
        double rueckgabebetrag;
        do {

            zuZahlenderBetrag = fahrkartenbestellungErfassen();
            rueckgabebetrag = fahrkartenBezahlen(zuZahlenderBetrag);
            fahrkartenAusgeben();
            rueckgeldAusgeben(rueckgabebetrag);

            System.out.println("\n  Vergessen Sie nicht, den Fahrschein\n" + "  vor Fahrtantritt entwerten zu lassen!\n"
                    + "  Wir wünschen Ihnen eine gute Fahrt.\n\n");
        } while (true);

    }
}