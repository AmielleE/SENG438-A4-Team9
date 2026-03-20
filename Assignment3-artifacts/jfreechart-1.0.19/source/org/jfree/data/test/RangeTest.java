package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeTest {

    private Range exampleRange;
    private static final double EPS = 1e-9;

    @BeforeEach
    void setUp() {
        exampleRange = new Range(-1, 1);
    }

    //------------------Constructor--------------------

    @Test
    void constructor_ValidRange_ShouldCreateRange() {
        Range r = new Range(2.0, 5.0);
        assertEquals(2.0, r.getLowerBound(), EPS);
        assertEquals(5.0, r.getUpperBound(), EPS);
    }

    @Test
    void constructor_LowerGreaterThanUpper_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(5.0, 2.0);
        });
    }

    //------------------getLowerBound------------------

    @Test
    void getLowerBound_ShouldReturnLower() {
        assertEquals(-1.0, exampleRange.getLowerBound(), EPS);
    }

    @Test
    void getLowerBound_PositiveRange() {
        Range r = new Range(5, 10);
        assertEquals(5.0, r.getLowerBound(), EPS);
    }

    @Test
    void getLowerBound_NegativeRange() {
        Range r = new Range(-10, -2);
        assertEquals(-10.0, r.getLowerBound(), EPS);
    }

    @Test
    void getLowerBound_ZeroRange() {
        Range r = new Range(0, 5);
        assertEquals(0.0, r.getLowerBound(), EPS);
    }

    //------------------getUpperBound------------------

    @Test
    void getUpperBound_ShouldReturnUpper() {
        assertEquals(1.0, exampleRange.getUpperBound(), EPS);
    }

    @Test
    void getUpperBound_PositiveRange() {
        Range r = new Range(5, 10);
        assertEquals(10.0, r.getUpperBound(), EPS);
    }

    @Test
    void getUpperBound_NegativeRange() {
        Range r = new Range(-10, -2);
        assertEquals(-2.0, r.getUpperBound(), EPS);
    }

    @Test
    void getUpperBound_ZeroUpperBound() {
        Range r = new Range(-5, 0);
        assertEquals(0.0, r.getUpperBound(), EPS);
    }

    //------------------getLength----------------------

    @Test
    void getLength_NormalRange() {
        assertEquals(2.0, exampleRange.getLength(), EPS);
    }

    @Test
    void getLength_ZeroLength() {
        Range r = new Range(5.0, 5.0);
        assertEquals(0.0, r.getLength(), EPS);
    }

    @Test
    void getLength_NegativeRange() {
        Range r = new Range(-10, -2);
        assertEquals(8.0, r.getLength(), EPS);
    }

    @Test
    void getLength_LargeRange() {
        Range r = new Range(-100, 100);
        assertEquals(200.0, r.getLength(), EPS);
    }

    @Test
    void getters_WithDifferentRange() {
        Range r = new Range(10, 20);
        assertEquals(10.0, r.getLowerBound(), EPS);
        assertEquals(20.0, r.getUpperBound(), EPS);
        assertEquals(10.0, r.getLength(), EPS);
    }

    @Test
    void getLength_SmallRange() {
        Range r = new Range(1, 2);
        assertEquals(1.0, r.getLength(), EPS);
    }

    @Test
    void getLength_DecimalRange() {
        Range r = new Range(0.5, 2.5);
        assertEquals(2.0, r.getLength(), EPS);
    }

    @Test
    void getLength_DecimalValues() {
        Range r = new Range(1.5, 4.5);
        assertEquals(3.0, r.getLength(), EPS);
    }

    @Test
    void getLength_NegativeToPositiveRange() {
        Range r = new Range(-3, 7);
        assertEquals(10.0, r.getLength(), EPS);
    }

    //------------------getCentralValue----------------

    @Test
    void getCentralValue_NormalRange() {
        assertEquals(0.0, exampleRange.getCentralValue(), EPS);
    }

    @Test
    void getCentralValue_PositiveRange() {
        Range r = new Range(2, 6);
        assertEquals(4.0, r.getCentralValue(), EPS);
    }

    //------------------contains-----------------------

    @Test
    void contains_ValueInsideRange() {
        assertTrue(exampleRange.contains(0.0));
    }

    @Test
    void contains_ValueOutsideRange() {
        assertFalse(exampleRange.contains(2.0));
    }

    @Test
    void contains_ValueOnBoundary() {
        assertTrue(exampleRange.contains(-1.0));
        assertTrue(exampleRange.contains(1.0));
    }

    @Test
    void contains_ValueBelowLowerBound() {
        Range r = new Range(5, 10);
        assertFalse(r.contains(4));
    }

    //------------------constrain----------------------

    @Test
    void constrain_ValueInsideRange() {
        assertEquals(0.5, exampleRange.constrain(0.5), EPS);
    }

    @Test
    void constrain_ValueBelowRange() {
        assertEquals(-1.0, exampleRange.constrain(-5.0), EPS);
    }

    @Test
    void constrain_ValueAboveRange() {
        assertEquals(1.0, exampleRange.constrain(5.0), EPS);
    }

    @Test
    void constrain_ValueExactlyLowerBound() {
        Range r = new Range(0, 10);
        assertEquals(0.0, r.constrain(0.0), EPS);
    }

    @Test
    void constrain_ValueExactlyUpperBound() {
        Range r = new Range(0, 10);
        assertEquals(10.0, r.constrain(10.0), EPS);
    }

    //Constrain Test after pit, survived mutants.
    @Test
    void constrain_ValueEqualUpperBound_ShouldReturnUpper() {
        // Added after PIT to check exact upper bound behavior
        Range r = new Range(2.0, 10.0);
        assertEquals(10.0, r.constrain(10.0));
    }

    @Test
    void constrain_ValueEqualLowerBound_ShouldReturnLower() {
        // Added after PIT to check exact lower bound behavior
        Range r = new Range(2.0, 10.0);
        assertEquals(2.0, r.constrain(2.0));
    }

    @Test
    void constrain_ValueAboveUpper_ShouldClampToUpper() {
        // Added after PIT to ensure values above the range clamp to upper bound
        Range r = new Range(2.0, 10.0);
        assertEquals(10.0, r.constrain(15.0));
    }

    @Test
    void constrain_ValueBelowLower_ShouldClampToLower() {
        // Added after PIT to ensure values below the range clamp to lower bound
        Range r = new Range(2.0, 10.0);
        assertEquals(2.0, r.constrain(-5.0));
    }

    //------------------intersects(double,double)-----

    @Test
    void intersects_Overlap() {
        assertTrue(exampleRange.intersects(0.5, 2.0));
    }

    @Test
    void intersects_NoOverlap() {
        assertFalse(exampleRange.intersects(2.0, 3.0));
    }

    @Test
    void intersects_EndEqualsLowerBound_ShouldReturnFalse() {
        // Added after PIT to test a boundary case in intersects
        Range r = new Range(5.0, 10.0);
        assertFalse(r.intersects(2.0, 5.0));
    }

    @Test
    void intersects_StartEqualsUpperBound_ShouldReturnFalse() {
        // Added after PIT to test when the interval starts exactly at upper bound
        Range r = new Range(0.0, 10.0);
        assertFalse(r.intersects(10.0, 12.0));
    }

    @Test
    void intersects_StartGreaterThanUpper_ShouldReturnFalse() {
        // Added after PIT to test a clearly non-overlapping case above the range
        Range r = new Range(0.0, 10.0);
        assertFalse(r.intersects(11.0, 12.0));
    }

    //------------------intersects(Range)--------------

    @Test
    void intersects_RangeOverlap_ShouldReturnTrue() {
        Range r1 = new Range(0, 10);
        Range r2 = new Range(5, 15);
        assertTrue(r1.intersects(r2));
    }

    @Test
    void intersects_RangeDisjoint_ShouldReturnFalse() {
        Range r1 = new Range(0, 10);
        Range r2 = new Range(20, 30);
        assertFalse(r1.intersects(r2));
    }

    @Test
    void intersects_ExactMatch() {
        Range r = new Range(0, 10);
        assertTrue(r.intersects(0, 10));
    }

    //------------------combine------------------------

    @Test
    void combine_BothNull_ShouldReturnNull() {
        assertNull(Range.combine(null, null));
    }

    @Test
    void combine_FirstNull() {
        Range r = new Range(0, 5);
        Range result = Range.combine(null, r);
        assertEquals(r, result);
    }

    @Test
    void combine_SecondNull() {
        Range r = new Range(0, 5);
        Range result = Range.combine(r, null);
        assertEquals(r, result);
    }

    @Test
    void combine_NormalRanges() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(3, 10);

        Range result = Range.combine(r1, r2);

        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    //------------------combineIgnoringNaN-------------

    @Test
    void combineIgnoringNaN_FirstNullSecondNaN_ShouldReturnNull() {
        // Added after PIT to cover the null + NaN-only branch
        Range r2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(null, r2);
        assertNull(result);
    }

    @Test
    void combineIgnoringNaN_SecondNullFirstNaN_ShouldReturnNull() {
        // Added after PIT to cover the symmetric NaN-only branch
        Range r1 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(r1, null));
    }

    @Test
    void combineIgnoringNaN_FirstNullSecondValid_ShouldReturnSameObject() {
        // Added after PIT to ensure a valid range is returned directly
        Range r2 = new Range(2.0, 8.0);
        assertSame(r2, Range.combineIgnoringNaN(null, r2));
    }

    @Test
    void combineIgnoringNaN_SecondNullFirstValid_ShouldReturnSameObject() {
        // Added after PIT to ensure the same valid range object is returned
        Range r1 = new Range(1.0, 5.0);
        assertSame(r1, Range.combineIgnoringNaN(r1, null));
    }

    @Test
    void combineIgnoringNaN_WithNaNRange() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(1, 5);

        Range result = Range.combineIgnoringNaN(r1, r2);

        assertEquals(r2, result);
    }

    @Test
    void combineIgnoringNaN_SecondNaNRange() {
        Range r1 = new Range(1, 5);
        Range r2 = new Range(Double.NaN, Double.NaN);

        Range result = Range.combineIgnoringNaN(r1, r2);

        assertEquals(r1, result);
    }

    @Test
    void combineIgnoringNaN_BothValidRanges() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(3, 8);

        Range result = Range.combineIgnoringNaN(r1, r2);

        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(8.0, result.getUpperBound(), EPS);
    }

    @Test
    void combineIgnoringNaN_NormalRanges() {
        Range r1 = new Range(1, 3);
        Range r2 = new Range(2, 6);

        Range result = Range.combineIgnoringNaN(r1, r2);

        assertEquals(1.0, result.getLowerBound(), EPS);
        assertEquals(6.0, result.getUpperBound(), EPS);
    }

    @Test
    void combineIgnoringNaN_FirstNull() {
        Range r2 = new Range(2, 8);

        Range result = Range.combineIgnoringNaN(null, r2);

        assertEquals(r2, result);
    }

    @Test
    void combineIgnoringNaN_SecondNull() {
        Range r1 = new Range(1, 5);

        Range result = Range.combineIgnoringNaN(r1, null);

        assertEquals(r1, result);
    }

    @Test
    void combineIgnoringNaN_FirstLowerSecondLower() {
        Range r1 = new Range(1, 3);
        Range r2 = new Range(5, 7);

        Range result = Range.combineIgnoringNaN(r1, r2);

        assertEquals(1.0, result.getLowerBound(), EPS);
        assertEquals(7.0, result.getUpperBound(), EPS);
    }

    //------------------expand--------------------------

    @Test
    void expand_NormalExpansion() {
        Range r = new Range(0, 10);
        Range expanded = Range.expand(r, 0.1, 0.1);

        assertEquals(-1.0, expanded.getLowerBound(), EPS);
        assertEquals(11.0, expanded.getUpperBound(), EPS);
    }

    @Test
    void expand_NegativeMargin() {
        Range r = new Range(0, 10);

        Range result = Range.expand(r, -0.1, -0.1);

        assertEquals(1.0, result.getLowerBound(), EPS);
        assertEquals(9.0, result.getUpperBound(), EPS);
    }

    @Test
    void expand_NullRange_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Range.expand(null, 0.1, 0.2);
        });
    }

    @Test
    void expand_CollapseRange_WhenMarginsTooLarge() {
        Range r = new Range(2, 4);
        Range expanded = Range.expand(r, -0.6, -0.6);

        assertEquals(3.0, expanded.getLowerBound(), EPS);
        assertEquals(3.0, expanded.getUpperBound(), EPS);
    }

    @Test
    void expand_ZeroMargins_ShouldReturnSameRange() {
        Range r = new Range(2, 6);

        Range result = Range.expand(r, 0.0, 0.0);

        assertEquals(2.0, result.getLowerBound(), EPS);
        assertEquals(6.0, result.getUpperBound(), EPS);
    }

    //------------------expandToInclude--------------------------

    @Test
    void expandToInclude_ValueEqualUpperBound_ShouldReturnSameObject() {
        // Added after PIT to check that no new object is created at the boundary
        Range r = new Range(0, 10);
        Range result = Range.expandToInclude(r, 10);
        //So added assertSame to make the test stricter.
        assertSame(r, result);
    }

    @Test
    void expandToInclude_NullRange_ShouldCreateRange() {
        Range result = Range.expandToInclude(null, 5.0);

        assertEquals(5.0, result.getLowerBound(), EPS);
        assertEquals(5.0, result.getUpperBound(), EPS);
    }

    @Test
    void expandToInclude_ValueBelowRange() {
        Range r = new Range(0, 10);

        Range result = Range.expandToInclude(r, -2);

        assertEquals(-2.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    @Test
    void expandToInclude_ValueAboveRange() {
        Range r = new Range(0, 10);

        Range result = Range.expandToInclude(r, 15);

        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(15.0, result.getUpperBound(), EPS);
    }

    @Test
    void expandToInclude_ValueInsideRange() {
        Range r = new Range(0, 10);

        Range result = Range.expandToInclude(r, 5);

        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    @Test
    void expandToInclude_ValueEqualLowerBound() {
        Range r = new Range(0, 10);

        Range result = Range.expandToInclude(r, 0);

        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    @Test
    void expandToInclude_ValueEqualUpperBound() {
        Range r = new Range(0, 10);

        Range result = Range.expandToInclude(r, 10);

        assertEquals(0.0, result.getLowerBound(), EPS);
        assertEquals(10.0, result.getUpperBound(), EPS);
    }

    //------------------scale---------------------------

    @Test
    void scale_PositiveFactor() {
        Range r = new Range(1, 3);
        Range scaled = Range.scale(r, 2.0);

        assertEquals(2.0, scaled.getLowerBound(), EPS);
        assertEquals(6.0, scaled.getUpperBound(), EPS);
    }

    @Test
    void scale_NegativeFactor_ShouldThrowException() {
        Range r = new Range(1, 3);

        assertThrows(IllegalArgumentException.class, () -> {
            Range.scale(r, -1);
        });
    }

    //------------------shift---------------------------

    @Test
    void shift_NormalShift() {
        Range r = new Range(0, 5);
        Range shifted = Range.shift(r, 2);

        assertEquals(2.0, shifted.getLowerBound(), EPS);
        assertEquals(7.0, shifted.getUpperBound(), EPS);
    }

 
    @Test
    void shift_NoZeroCrossing_ShouldClampAtZero() {
        Range r = new Range(-5, -1);

        Range shifted = Range.shift(r, 10, false);

        assertEquals(0.0, shifted.getLowerBound(), EPS);
        assertEquals(9.0, shifted.getUpperBound(), EPS);
    }

    @Test
    void shift_AllowZeroCrossing() {
        Range r = new Range(-5, -1);

        Range shifted = Range.shift(r, 10, true);

        assertEquals(5.0, shifted.getLowerBound(), EPS);
        assertEquals(9.0, shifted.getUpperBound(), EPS);
    }

    @Test
    void shift_ZeroShift() {
        Range r = new Range(1, 5);

        Range shifted = Range.shift(r, 0);

        assertEquals(1.0, shifted.getLowerBound(), EPS);
        assertEquals(5.0, shifted.getUpperBound(), EPS);
    }

    //------------------isNaNRange----------------------

    @Test
    void isNaNRange_AllNaN_ShouldReturnTrue() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }

    @Test
    void isNaNRange_NormalRange_ShouldReturnFalse() {
        Range r = new Range(0, 5);
        assertFalse(r.isNaNRange());
    }

    @Test
    void combineIgnoringNaN_BothNaN_ShouldReturnNull() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(Double.NaN, Double.NaN);

        Range result = Range.combineIgnoringNaN(r1, r2);

        assertNull(result);
    }

    //------------------equals--------------------------

    @Test
    void equals_SameRange() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(0, 5);

        assertTrue(r1.equals(r2));
    }

    @Test
    void equals_DifferentRange() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(1, 6);

        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_NullObject_ShouldReturnFalse() {
        Range r = new Range(0, 5);
        assertFalse(r.equals(null));
    }

    //------------------hashCode------------------------

    @Test
    void hashCode_EqualObjectsHaveSameHash() {
        Range r1 = new Range(0, 5);
        Range r2 = new Range(0, 5);

        assertEquals(r1.hashCode(), r2.hashCode());
    }

    //------------------toString------------------------

    @Test
    void toString_ShouldContainBounds() {
        Range r = new Range(1, 3);
        String str = r.toString();

        assertTrue(str.contains("1.0"));
        assertTrue(str.contains("3.0"));
    }
}