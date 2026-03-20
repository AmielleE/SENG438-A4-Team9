package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.createNumberArray()
// This is a regular test because it operates on a primitive array and does not require mocking

public class CreateNumberArrayTest {

    // Testing: normal behaviour with a valid array of doubles
    // Partition: Valid input array
    // Expected: Returns a Number array with the same values
    @Test
    void testCreateNumberArray_ValidInput_ShouldReturnCorrectArray() {
        double[] input = {1.0, 2.0, 3.0};

        Number[] result = DataUtilities.createNumberArray(input);

        assertArrayEquals(new Number[]{1.0, 2.0, 3.0}, result);
    }

    // Testing: null input
    // Partition: Invalid input (null array)
    // Expected: IllegalArgumentException thrown
    @Test
    void testCreateNumberArray_NullInput_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.createNumberArray(null);
        });
    }

    // new test for an empty array
    @Test
    void testCreateNumberArray_EmptyArray() {
        double[] input = {};

        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(0, result.length);
    }

    // new test for negative numbers
    @Test
    void testCreateNumberArray_NegativeValues() {
        double[] input = {-1.0, -2.5};

        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(-1.0, result[0]);
        assertEquals(-2.5, result[1]);
    }

    // new test for mixed values
    @Test
    void testCreateNumberArray_MixedValues() {
        double[] input = {0.0, -3.0, 8.0};

        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(0.0, result[0]);
        assertEquals(-3.0, result[1]);
        assertEquals(8.0, result[2]);
    }

    // new test for large boundary values
    @Test
    void testCreateNumberArray_LargeValues() {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE};

        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(Double.MAX_VALUE, result[0]);
        assertEquals(Double.MIN_VALUE, result[1]);
    }

    // added for PIT: checks boundary case when array length is exactly one
    @Test
    void testCreateNumberArray_LengthOne() {
        double[] input = {4.2};

        Number[] result = DataUtilities.createNumberArray(input);

        assertEquals(1, result.length);
        assertEquals(4.2, result[0].doubleValue(), 0.000000001d);
    }
}