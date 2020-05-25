
package FunctionLayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 Tester metoderne i Svg-klassen, som genererer 2D-tegninger af garager.
 */
public class SvgTest {
    Svg svg;

    /**
     Skaber et nyt Svg-objekt
     */
    @Before
    public void setUp() throws Exception {
        svg = new Svg(800, 600, "0,0,800,600", 0,0);
    }

    /**
     Metoden, der tilføjer et rektangel til tegningen, testes.
     */
    @Test
    public void addRect01Test() {
        svg.addRect(0,0,100,100);
        String expectedRect = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"600\" width=\"800\" viewBox=\"0,0,800,600\" preserveAspectRatio=\"xMinYMin\"><rect x=\"0\" y=\"0\" height=\"100\" width=\"100\" style=\"stroke:#000000; fill: #ffffff\" /></svg>";
        assertEquals(svg.toString(),expectedRect);
    }

    /**
     Endnu et rektangel testes, dog med andre mål.
     */
    @Test
    public void addRect02Test() {
        svg.addRect(0,0,99,99);
        String expectedRect = "<rect x=\"0\" y=\"0\" height=\"99\" width=\"99\" style=\"stroke:#000000; fill: #ffffff\" />";
        assertThat(svg.toString(), containsString(expectedRect));
    }
    @Test

    /**
     Test af tilføjelse af stropper til tegningen
     */
    public void addStrapTest(){
        svg.addStrap(0, 0, 95, 95);
        String expectedStrap = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"600\" width=\"800\" viewBox=\"0,0,800,600\" preserveAspectRatio=\"xMinYMin\"><rect x=\"0\" y=\"0\" height=\"95\" width=\"95\" style=\"stroke:#000000; fill: #ffffff\" /></svg>";
        assertEquals(svg.toString(), expectedStrap);
    }

    /**
     Der testes, om et kryds kan tilføjes
     */
    @Test public void addCrossTest(){
        svg.addCross(0, 0, 10, 15);
        String expectedCross = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"600\" width=\"800\" viewBox=\"0,0,800,600\" preserveAspectRatio=\"xMinYMin\"><line x1=\"0\" y1=\"0\" x2=\"10\" y2=\"15\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" /></svg>";
        assertEquals(svg.toString(), expectedCross);
    }
}
