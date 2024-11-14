package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    Plateau plateau = new Plateau(new PlateauSize(5,5));

    @Test
    void test_ValidPosition(){
       Boolean result = plateau.isWithinBounds(2,2);
       assertEquals(result,true);
    }

    @Test
    void test_InvalidPosition(){
        Boolean result1 = plateau.isWithinBounds(6,2);
        Boolean result2 = plateau.isWithinBounds(-1,3);
        assertEquals(result1,false);
        assertEquals(result2,false);
    }

}