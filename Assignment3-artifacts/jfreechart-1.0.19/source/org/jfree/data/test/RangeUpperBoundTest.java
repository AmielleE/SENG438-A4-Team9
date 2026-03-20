package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class RangeUpperBoundTest {

    // Partition: normal range
    // Expected: returns the upper bound
    @Test
    void getUpperBound_NormalRange() {
        Range r = new Range(-1, 5);
        assertEquals(5.0, r.getUpperBound(), 1e-9);
    }

    // Partition: zero-length range
    // Expected: returns the same value
    @Test
    void getUpperBound_ZeroRange() {
        Range r = new Range(0, 0);
        assertEquals(0.0, r.getUpperBound(), 1e-9);
    }

    // Partition: both bounds negative
    // Expected: returns the upper bound
    @Test
    void getUpperBound_NegativeRange() {
        Range r = new Range(-10, -2);
        assertEquals(-2.0, r.getUpperBound(), 1e-9);
    }

    // Partition: invalid range
    // Expected: constructor throws exception
    @Test
    void getUpperBound_InvalidRange_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(5, 2);
        });
    }
}