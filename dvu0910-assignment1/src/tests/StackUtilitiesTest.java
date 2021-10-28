/**
 *  Assignment 1
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import programs.StackUtilities;

import org.junit.jupiter.api.Test;

/**
 * method to test .
 * 
 * @author duy
 * @version 2021
 *
 */
class StackUtilitiesTest {
    /**
     * Test method for {@link programs.StackUtilities#decimalToBinary(integer n)}.
     */
    @Test
    void testDecimalToBinary() {
        assertEquals(StackUtilities.decimalToBinary(5), "101");

    }
}
