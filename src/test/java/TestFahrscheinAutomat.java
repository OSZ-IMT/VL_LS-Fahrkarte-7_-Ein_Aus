import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TestFahrscheinAutomat {

    private PrintStream originalOut;
    private ByteArrayOutputStream bos;

    @Before
    public void InitPrintStream(){
        //bind the system
        originalOut = System.out;
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    @After
    public void EndPrintStream(){
        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testAufgabe1()
    {

        // action
        Ausgabenformatierung.Aufgabe1();

        // assertion
        assertTrue(bos.toString().startsWith("   **   "));
        assertTrue(bos.toString().contains("*      *"));

    }

    @Test
    public void testAufgabe2()
    {

        // action
        Ausgabenformatierung.Aufgabe2();

        // assertion
        assertTrue(bos.toString().contains("0!   =                   =   1"));
        assertTrue(bos.toString().contains("1!   = 1                 =   1"));
        assertTrue(bos.toString().contains("2!   = 1 * 2             =   2"));
        assertTrue(bos.toString().contains("3!   = 1 * 2 * 3         =   6"));
        assertTrue(bos.toString().contains("4!   = 1 * 2 * 3 * 4     =  24"));
        assertTrue(bos.toString().contains("5!   = 1 * 2 * 3 * 4 * 5 = 120"));

    }

    @Test
    public void testAufgabe3Head()
    {

        // action
        Ausgabenformatierung.Aufgabe3();

        // assertion
        assertTrue(bos.toString().contains("Fahrenheit  |  Celsius"));

    }

    @Test
    public void testAufgabe3Table()
    {

        // action
        Ausgabenformatierung.Aufgabe3();

        // assertion
        assertTrue(bos.toString().contains("----------------------"));
        assertTrue(bos.toString().contains("-20         |   -28.89"));
        assertTrue(bos.toString().contains("-10         |   -23.33"));
        assertTrue(bos.toString().contains("+0          |   -17.78"));
        assertTrue(bos.toString().contains("20          |    -6.67"));
        assertTrue(bos.toString().contains("30          |    -1.11"));

    }
}
