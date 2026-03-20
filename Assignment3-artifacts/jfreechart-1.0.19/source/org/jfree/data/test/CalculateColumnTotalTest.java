package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.calculateColumnTotal()
// This is a mock test because Values2D is an interface, so we simulate it using Mockito.

public class CalculateColumnTotalTest {

    private Values2D values;

    @BeforeEach
    void setUp() {
        values = mock(Values2D.class);
    }

    // Testing: normal behaviour where column contains valid numbers
    // Partition: Valid data, normal column index
    // Expected: Sum of column values
    @Test
    void testCalculateColumnTotal_ValidData_ShouldReturnSum() {
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(1, 0)).thenReturn(3.0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(8.0, result, 0.0001);
    }

    @Test
    void testCalculateColumnTotal_NullData_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.calculateColumnTotal(null, 0);
        });
    }

    // new test for a data set with 0 rows
    @Test
    void testCalculateColumnTotal_ZeroRows_ShouldReturnZero() {
        when(values.getRowCount()).thenReturn(0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(0.0, result, 0.0001);
    }

    // new test for if a column has null values
    @Test
    void testCalculateColumnTotal_NullValues_ShouldIgnoreNulls() {
        when(values.getRowCount()).thenReturn(3);

        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(1, 0)).thenReturn(null);
        when(values.getValue(2, 0)).thenReturn(2.0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(7.0, result, 0.0001);
    }

    // new test for negative values
    @Test
    void testCalculateColumnTotal_NegativeValues() {
        when(values.getRowCount()).thenReturn(2);

        when(values.getValue(0, 0)).thenReturn(-4.0);
        when(values.getValue(1, 0)).thenReturn(-6.0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(-10.0, result, 0.0001);
    }

    // added for PIT: checks boundary case with exactly one row
    @Test
    void testCalculateColumnTotal_SingleRow() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(5.0, "R1", "C1");

        double result = DataUtilities.calculateColumnTotal(data, 0);

        assertEquals(5.0, result, 0.000000001d);
    }

    // added for PIT: checks that the last valid row is included in the total
    @Test
    void testCalculateColumnTotal_LastValidRowIncluded() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(2.0, "R1", "C1");
        data.addValue(3.0, "R2", "C1");

        double result = DataUtilities.calculateColumnTotal(data, 0);

        assertEquals(5.0, result, 0.000000001d);
    }

 // new test for a mix of positive and negative values
    @Test
    void testCalculateColumnTotal_MixedValues() {
        when(values.getRowCount()).thenReturn(3);

        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(1, 0)).thenReturn(-5.0);
        when(values.getValue(2, 0)).thenReturn(3.0);

        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(8.0, result, 0.0001);
    }
}