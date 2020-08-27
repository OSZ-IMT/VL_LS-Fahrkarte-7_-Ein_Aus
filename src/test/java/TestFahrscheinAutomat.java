import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestFahrscheinAutomat {

    // backup System.out to restore it later
    private PrintStream originalOut;
    private ByteArrayOutputStream bos;

    // backup System.in to restore it later
    private InputStream sysInBackup;
    private ByteArrayInputStream in;

    private int version;

    @Before
    public void InitPrintStream(){
        //bind the system
        originalOut = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        sysInBackup = System.in; // backup System.in to restore it later
    }

    @After
    public void EndPrintStream(){
        // undo the binding in System
        System.setOut(originalOut);

        // reset System.in to its original
        System.setIn(sysInBackup);
    }

    /**
     * Helpermethod for inputs
     * @param args
     */
    private void SetTestData(String... args) {
        in = new ByteArrayInputStream(String.join(System.lineSeparator(), args).getBytes());
        System.setIn(in);
    }

    /**
     * Helpermethod to check outputs
     * @param check
     */
    private void CheckTestData(String check) {
        try {
            assertTrue(bos.toString().contains(check));
        } catch (Throwable t) {
            //print exact program
            assertEquals(check,bos.toString());
        }
    }

    @Test
    public void testKomma10Zahlen() {

        SetTestData("1","3","2","2");

        // action
        Fahrkartenautomat.main(null);

        // assertion
        CheckTestData("Noch zu zahlen: 1.00 Euro");
    }

    @Test
    public void testKomma05Zahlen() {

        SetTestData("1,05","3","2","2");

        // action
        Fahrkartenautomat.main(null);

        // assertion
        CheckTestData("Noch zu zahlen: 1.15 Euro");
    }

    @Test
    public void testKomma10Rueckgabe() {

        SetTestData("1","3","2","2");

        // action
        Fahrkartenautomat.main(null);

        // assertion
        //CheckTestData("Noch zu zahlen: 1.00 €");
        CheckTestData("he von 1.00 Euro");
    }

    @Test
    public void testKomma05Rueckgabe() {

        SetTestData("1,05","3","2","2");

        // action
        Fahrkartenautomat.main(null);

        // assertion
        CheckTestData("he von 1.15 Euro");
        //CheckTestData("Noch zu zahlen: 0.95");
    }

    @Test
    public void testAnzahlTickets() {

        SetTestData("1","3","2","2");

        // action
        Fahrkartenautomat.main(null);

        // assertion
        CheckTestData("Anzahl der Tickets:");
        CheckTestData("Noch zu zahlen: 3.00 Euro");
    }

}
