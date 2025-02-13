package org.MarsRover;

import org.MarsRover.Input.PlateauSize;
import org.MarsRover.logic.Plateau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    @Test
    void testValidPlateau() {
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        assertEquals(5, plateau.getPlateauSize().x());
        assertEquals(5, plateau.getPlateauSize().y());
    }

    @Test
    void testNegativePlateauSize() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Plateau(new PlateauSize(-5,5));
        });
        assertEquals("Plateau size must be non negative", exception.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Plateau(new PlateauSize(5,-5));
        });
        assertEquals("Plateau size must be non negative", exception2.getMessage());
    }

    @Test
    void test_ValidPosition(){
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        Boolean result = plateau.isWithinBounds(2,2);
        assertEquals(result,true);
    }

    @Test
    void test_InvalidPosition(){
        Plateau plateau = new Plateau(new PlateauSize(5,5));
        Boolean result1 = plateau.isWithinBounds(6,2);
        Boolean result2 = plateau.isWithinBounds(-1,3);
        assertEquals(result1,false);
        assertEquals(result2,false);
    }

}