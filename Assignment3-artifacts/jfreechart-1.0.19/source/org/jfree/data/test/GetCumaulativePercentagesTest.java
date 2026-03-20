package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test class for DataUtilities.getCumulativePercentages()
// This is a mock test because KeyedValues is an interface, so we simulate it using Mockito.

public class GetCumulativePercentagesTest {

    private KeyedValues values;

    @BeforeEach
    void setUp() {
        values = mock(KeyedValues.class);
    }

    // Testing: normal behaviour with valid KeyedValues
    // Partition: Valid data, normal keys and values
    // Expected: Cumulative percentages calculated correctly
    @Test
    void testGetCumulativePercentages_ValidData_ShouldReturnCorrectPercentages() {
        when(values.getItemCount()).thenReturn(3);
        when(values.getKey(0)).thenReturn(0);
        when(values.getKey(1)).thenReturn(1);
        when(values.getKey(2)).thenReturn(2);

        when(values.getValue(0)).thenReturn(5.0);
        when(values.getValue(1)).thenReturn(9.0);
        when(values.getValue(2)).thenReturn(2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0.3125, result.getValue(0));
        assertEquals(0.875, result.getValue(1));
        assertEquals(1.0, result.getValue(2));
    }

    // Testing: null input
    // Partition: Invalid input
    // Expected: IllegalArgumentException thrown
    @Test
    void testGetCumulativePercentages_NullData_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.getCumulativePercentages(null);
        });
    }

    // new test for when there are null values
    @Test
    void testGetCumulativePercentages_WithNullValues_ShouldIgnoreNulls() {
        when(values.getItemCount()).thenReturn(3);

        when(values.getKey(0)).thenReturn("A");
        when(values.getKey(1)).thenReturn("B");
        when(values.getKey(2)).thenReturn("C");

        when(values.getValue(0)).thenReturn(5.0);
        when(values.getValue(1)).thenReturn(null);
        when(values.getValue(2)).thenReturn(5.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(0.5, result.getValue(0).doubleValue(), 0.0000001);
        assertEquals(0.5, result.getValue(1).doubleValue(), 0.0000001);
        assertEquals(1.0, result.getValue(2).doubleValue(), 0.0000001);
    }

    // new test for when the total equals 0
    @Test
    void testGetCumulativePercentages_AllZeros() {
        when(values.getItemCount()).thenReturn(2);

        when(values.getKey(0)).thenReturn("A");
        when(values.getKey(1)).thenReturn("B");

        when(values.getValue(0)).thenReturn(0.0);
        when(values.getValue(1)).thenReturn(0.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertTrue(Double.isNaN(result.getValue(0).doubleValue()));
        assertTrue(Double.isNaN(result.getValue(1).doubleValue()));
    }

    // new test for a data set with only 1 item
    @Test
    void testGetCumulativePercentages_SingleItem() {
        when(values.getItemCount()).thenReturn(1);

        when(values.getKey(0)).thenReturn("A");
        when(values.getValue(0)).thenReturn(10.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(1.0, result.getValue(0).doubleValue(), 0.0000001);
    }

    // new test for negative numbers
    @Test
    void testGetCumulativePercentages_NegativeValues() {
        when(values.getItemCount()).thenReturn(2);

        when(values.getKey(0)).thenReturn("A");
        when(values.getKey(1)).thenReturn("B");

        when(values.getValue(0)).thenReturn(-5.0);
        when(values.getValue(1)).thenReturn(10.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(values);

        assertEquals(-1.0, result.getValue(0).doubleValue(), 0.0000001);
        assertEquals(1.0, result.getValue(1).doubleValue(), 0.0000001);
    }

    // added for PIT: checks loop boundary and ensures the last cumulative value becomes 1.0
    @Test
    void testGetCumulativePercentages_TwoItems_LastItemIsOne() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 1.0);
        data.addValue("B", 3.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.25, result.getValue(0).doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue(1).doubleValue(), 0.000000001d);
    }

    // added for PIT: checks the single-item boundary for cumulative percentages
    @Test
    void testGetCumulativePercentages_SingleItem_ShouldBeOne() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 5.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue(0).doubleValue(), 0.000000001d);
    }
}