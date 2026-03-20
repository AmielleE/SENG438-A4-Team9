package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;


import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

// Test class for Range.shift(Range base, double delta, boolean allowZeroCrossing)

public class ShiftAllowZeroCrossingTest {
    private static final double EPS = 1e-9;

    // Testing: allowZeroCrossing=true shifts bounds normally across zero
    // Partition: allowZeroCrossing is true
    // Expected: [1, 3]
    @Test
    void shift_AllowZeroCrossingTrue_ShouldCrossZeroNormally() {
        Range base = new Range(-1, 1);

        Range shifted = Range.shift(base, 2, true);

        assertNotNull(shifted);
        assertEquals(1.0, shifted.getLowerBound(), EPS);
        assertEquals(3.0, shifted.getUpperBound(), EPS);
    }

    // Testing: allowZeroCrossing = false with zero crossing constraint
    // Partition: allowZeroCrossing = false (zero crossing constraint)
    // Expected: Lower bound should not be negative after shift
    @Test
    void shift_AllowZeroCrossingFalse_ShouldNotCrossZero() {
        Range base = new Range(-1, 1);

        Range shifted = Range.shift(base, -2, false);

        assertNotNull(shifted);
        assertEquals(-3.0, shifted.getLowerBound(), EPS);
        assertEquals(0.0, shifted.getUpperBound(), EPS);
    }
    
    // new test shifting a positive range down, but not crossing zero (allowZeroCrossing=false)
    @Test
    void shift_PositiveRangeNegativeDelta_NoZeroCross() {
        Range base = new Range(2, 5);
        Range shifted = Range.shift(base, -1, false);

        assertNotNull(shifted);
        assertEquals(1.0, shifted.getLowerBound(), EPS);
        assertEquals(4.0, shifted.getUpperBound(), EPS);
    }

    // new test for shifting a negative range down (more negative) with allowZeroCrossing=false
    @Test
    void shift_NegativeRangeNegativeDelta_AllowZeroCrossFalse() {
        Range base = new Range(-5, -2);
        Range shifted = Range.shift(base, -3, false);

        assertNotNull(shifted);
        assertEquals(-8.0, shifted.getLowerBound(), EPS);
        assertEquals(-5.0, shifted.getUpperBound(), EPS);
    }

    // new test for shifting a zero range
    @Test
    void shift_ZeroRange() {
        Range base = new Range(0, 0);
        Range shifted = Range.shift(base, 5, true);

        assertNotNull(shifted);
        assertEquals(5.0, shifted.getLowerBound(), EPS);
        assertEquals(5.0, shifted.getUpperBound(), EPS);
    }

    // new test for shifting by zero delta
    @Test
    void shift_ByZeroDelta() {
        Range base = new Range(1, 4);
        Range shifted = Range.shift(base, 0, true);

        assertNotNull(shifted);
        assertEquals(1.0, shifted.getLowerBound(), EPS);
        assertEquals(4.0, shifted.getUpperBound(), EPS);
    }
}