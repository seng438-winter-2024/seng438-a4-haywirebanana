package org.jfree.data.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range exampleRange1;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
        exampleRange1 = new Range(1, 1);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD getLowerBound()

    @Test
    public void lowerBoundShouldBeNegativeOne() {
        assertEquals("The lower boundary of -1 and 1 should be -1",
                -1, exampleRange.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundShouldBeOne() {
        // testing the method of a range of just 1
        assertEquals("The lower boundary of 1 and 1 should be 1",
                1, exampleRange1.getLowerBound(), .000000001d);
    }

    @Test
    public void lowerBoundShouldBeMinimumValue() {
        // testing range with min integer value
        Range range = new Range(Integer.MIN_VALUE, 100);
        assertEquals("The lower boundary should be Integer.MIN_VALUE",
                Integer.MIN_VALUE, range.getLowerBound(), 0);
    }

    @Test
    public void lowerBoundShouldBeNaN() {
        // testing range with NaN value
        Range range = new Range(Double.NaN, 100);
        assertTrue("The lower boundary should be NaN",
                Double.isNaN(range.getLowerBound()));
    }

    @Test
    public void lowerBoundIsLargestNeg() {
        // test with lower bound as a large negative value
        Range range = new Range(-Double.MAX_VALUE, 10);
        assertEquals("The lower bound should be -Double.MAX_VALUE", -Double.MAX_VALUE, range.getLowerBound(), 0);
    }

    @Test
    public void lowerBoundWorstCaseTesting() {
        // test with a large range where both bounds are at the maximum representable
        // value
        Range range = new Range(Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals("the Lower bound should be Double.MAX_VALUE", Double.MAX_VALUE, range.getLowerBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lowerBoundOfInvalidRange() {
        Range range = new Range(2, 1);
        double result = range.getLowerBound();
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD getUpperBound()

    @Test
    public void upperBoundTestOfDistinctRange() {
        // testing a range of different mixed values
        assertEquals("The upper boundary of -1 and 1 should be 1",
                1, exampleRange.getUpperBound(), .000000001d);
    }

    @Test
    public void upperBoundShouldBeOne() {
        // testing is the methods works for a range of just 1
        assertEquals("The upper boundary of 1 and 1 should be 1",
                1, exampleRange1.getUpperBound(), .000000001d);
    }

    @Test
    public void upperBoundIsLargestVal() {
        // testing with upper bound as the largest representable value
        Range range = new Range(0, Double.MAX_VALUE);
        assertEquals("Upper bound should be Double.MAX_VALUE", Double.MAX_VALUE, range.getUpperBound(), 0);
    }

    @Test
    public void upperBoundShouldBeZero() {
        // testing with the upper bound as 0 (with a range of (0,0))
        Range range = new Range(0, 0);
        assertEquals("The upper bound of 1 should be returned.", 0, range.getUpperBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void upperBoundOfInvalidRange() {
        // testing if exception is throw when method is evoked or when constructor is
        // evoked
        Range range = new Range(2, 1);
        double result = range.getUpperBound();
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD getLength()

    @Test
    public void lengthShouldBePositiveForNonEmptyRange() {
        // testing if length is positive
        Range range = new Range(-1, 1);
        assertTrue("The length of a non-empty range should be positive",
                range.getLength() > 0);
    }

    @Test
    public void lengthShouldBeZero() {
        // testing is length is 0
        assertEquals("The length of 1 and 1 should be 0",
                0, exampleRange1.getLength(), .000000001d);
    }

    @Test
    public void lengthWithNaNLower() {
        // invalid length
        Range range = new Range(Double.NaN, 10);
        assertTrue("The length should be NaN", Double.isNaN(range.getLength()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void upperLengthOfInvalidRange() {
        // invalid length
        Range range = new Range(2, 1);
        double result = range.getLength();
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD intersects()

    @Test
    public void intersectLower() {
        // testing the intersection of the lower bound
        assertTrue("The range of -2 and 0 intersects -1 and 1",
                exampleRange.intersects(-2, 1));
    }

    @Test
    public void intersectUpper() {
        // testing the intersection of the upper bound
        boolean result = exampleRange.intersects(0, 2);
        System.out.println("Actual Result: " + result);
        assertTrue("The range of 0 and 2 intersects -1 and 1", result);
    }

    @Test
    public void intersectAll() {
        // testing the intersection of the whole range
        assertTrue("The range of -2 and 2 intersects -1 and 1",
                exampleRange.intersects(-2, 2));
    }

    @Test
    public void intersectSame() {
        // testing the intersection of the same range
        assertTrue("The range of -1 and 1 intersects -1 and 1",
                exampleRange.intersects(-1, 1));
    }

    @Test
    public void intersectNone() {
        // testing the intersection of a distinct range
        assertFalse("The range of -4 and -2 does not intersects -1 and 1",
                exampleRange.intersects(-4, -2));
    }

    @Test
    public void intersectNaN() {
        // testing is a NaN range intersects range
        assertFalse("The range of NaN and NaN does not intersect -1 and 1",
                exampleRange.intersects(Double.NaN, Double.NaN));
    }

    @Test
    public void intersectGivenRange() {
        // testing is a NaN range intersects range
        Range range = new Range(-4, -2);
        assertFalse("The range of -4 and -2 does not intersect -1 and 1",
                exampleRange.intersects(range));
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD contains()

    @Test
    public void containsValue() {
        // testing if range contains 0
        assertTrue("The range of -1 and 1 contains the value 0",
                exampleRange.contains(0));
    }

    @Test
    public void containsUpper() {
        // testing if range contains upper bound
        assertTrue("The range of -1 and 1 contains the value 1",
                exampleRange.contains(1));
    }

    @Test
    public void containsLower() {
        // testing if range contains lower bound
        assertTrue("The range of -1 and 1 contains the value -1",
                exampleRange.contains(-1));
    }

    @Test
    public void doesNotContainValueAbove() {
        // checking if range contains unique value
        assertFalse("The range of -1 and 1 should not contain the value 5",
                exampleRange.contains(5));
    }

    @Test
    public void doesNotContainNaNValue() {
        // test that range does not contain NaN
        assertFalse("The range of -1 and 1 should not contain NaN",
                exampleRange.contains(Double.NaN));
    }

    @Test
    public void doesNotContainValueBelow() {
        // testing that range does not contain negative value
        assertFalse("The range of -1 and 1 should not contain the value -2",
                exampleRange.contains(-2));
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD getCentralValue()

    @Test
    public void getCentralValueOfPositiveRange() {
        // testing the central value of a positive range
        Range range = new Range(1, 3);
        assertEquals("The central value of range (1, 3) should be 2", 2, range.getCentralValue(), 0);
    }

    @Test
    public void getCentralValueOfNegativeRange() {
        // testing the central value of a negative range
        Range range = new Range(-3, -1);
        assertEquals("The central value of range (-3, -1) should be -2", -2, range.getCentralValue(), 0);
    }

    @Test
    public void getCentralValueOfZeroRange() {
        // testing the central value of a zero range
        Range range = new Range(0, 0);
        assertEquals("The central value of range (0, 0) should be 0", 0, range.getCentralValue(), 0);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD constrain()

    @Test
    public void constraineOfPositiveRangeBelow() {
        Range range = new Range(1, 3);
        assertEquals("The return value should be 1", 1, range.constrain(0), 0);
    }

    @Test
    public void constraineOfPositiveRangeAbove() {
        Range range = new Range(1, 3);
        assertEquals("The return value should be 3", 3, range.constrain(4), 0);
    }

    @Test
    public void constrainOfZeroRange() {
        Range range = new Range(0, 0);
        assertEquals("The return value should be 0", 0, range.constrain(0), 0);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD combine()

    @Test
    public void testCombineValidRanges() {
        // testing the combine of positive ranges
        Range range1 = new Range(1, 5);
        Range range2 = new Range(6, 10);
        Range combinedRange = Range.combine(range1, range2);
        assertNotNull(combinedRange);
        assertEquals("The return value should be 1", 1, combinedRange.getLowerBound(), 0);
        assertEquals("The return value should be 10", 10, combinedRange.getUpperBound(), 0);

    }

    @Test
    public void testCombineRange1Null() {
        // testing the combine of a null and positive range
        Range range1 = null;
        Range range2 = new Range(6, 10);
        Range combinedRange = Range.combine(range1, range2);
        assertNotNull(combinedRange);
        assertEquals("The return value should be 6", 6, combinedRange.getLowerBound(), 0);
        assertEquals("The return value should be 10", 10, combinedRange.getUpperBound(), 0);
    }

    @Test
    public void testCombineRange2Null() {
        // testing the combine of a positive range and null range
        Range range1 = new Range(1, 5);
        Range range2 = null;
        Range combinedRange = Range.combine(range1, range2);
        assertNotNull(combinedRange);
        assertEquals("The return value should be 1", 1, combinedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, combinedRange.getUpperBound(), 0);
    }

    @Test
    public void testCombineBothRangesNull() {
        // testing the combine of null ranges
        Range range1 = null;
        Range range2 = null;
        Range combinedRange = Range.combine(range1, range2);
        assertNull(combinedRange);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD combineIgnoringNaN()

    @Test
    public void testCombineIgnoringNaN_ValidRanges() {
        // testing a valid range for combining
        Range range1 = new Range(1, 5);
        Range range2 = new Range(6, 10);
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(combinedRange);
        assertEquals("The return value should be 1", 1, combinedRange.getLowerBound(), 0);
        assertEquals("The return value should be 10", 10, combinedRange.getUpperBound(), 0);
    }

    @Test
    public void testCombineIgnoringNaN_Range1Null() {
        // testing that the combine method ignores the null range
        Range range1 = null;
        Range range2 = new Range(Double.NaN, 10.0);
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(combinedRange);
        assertEquals("The return value should be NaN", Double.NaN, combinedRange.getLowerBound(), 0);
        assertEquals("The return value should be 10", 10, combinedRange.getUpperBound(), 0);
    }

    @Test
    public void testCombineIgnoringNaN_Range2Null() {
        // testing that the combine methods ignores the null range for the second range
        Range range1 = new Range(Double.NaN, 5.0);
        Range range2 = null;
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(combinedRange);
        assertEquals("The return value should be NaN", Double.NaN, combinedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, combinedRange.getUpperBound(), 0);
    }

    @Test
    public void testCombineIgnoringNaN_BothRangesNull() {
        // check that two null ranges assert null
        Range range1 = null;
        Range range2 = null;
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNull(combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_OneRangeNull() {
        // testing that a null and NaN range returns null
        Range range1 = null;
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNull(combinedRange);
    }

    @Test
    public void testCombineIgnoringNaN_SecondRangeNull() {
        // again testing that a NaN and null range (reverse) returns null
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = null;
        Range combinedRange = Range.combineIgnoringNaN(range1, range2);
        assertNull(combinedRange);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD expandToInclude()

    @Test
    public void testExpandToInclude_ValueInsideRange() {
        // testing expand method with a value already in the range
        Range range = new Range(1, 5);
        double value = 3;
        Range expandedRange = Range.expandToInclude(range, value);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 1", 1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpandToInclude_ValueLessThanRange() {
        // testing the expand method with a value that is not inside the range (below)
        Range range = new Range(3, 5);
        double value = 1;
        Range expandedRange = Range.expandToInclude(range, value);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 1", 1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpandToInclude_ValueGreaterThanRange() {
        // testing the expand method with a value that is not inside the range (above)
        Range range = new Range(1, 3);
        double value = 5;
        Range expandedRange = Range.expandToInclude(range, value);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 1", 1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpandToInclude_NullRange() {
        // testing the expand method with a null value (should not effect range)
        Range range = null;
        double value = 5;
        Range expandedRange = Range.expandToInclude(range, value);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 5", 5, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, expandedRange.getUpperBound(), 0);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD expand()

    @Test
    public void testExpand_ValidMargins() {
        // expanding a positive range
        Range range = new Range(0, 10);
        double lowerMargin = 0.1;
        double upperMargin = 0.2;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be -1", -1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 12", 12, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpand_ZeroMargins() {
        // expanding a range with 0 margins
        Range range = new Range(0, 10);
        double lowerMargin = 0.0;
        double upperMargin = 0.0;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 0", 0, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 10", 10, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpand_MarginsGreaterThanRange() {
        // testing the expand for bigger margins
        Range range = new Range(0, 10);
        double lowerMargin = 0.5;
        double upperMargin = 0.6;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be -5", -5, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 16", 16, expandedRange.getUpperBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    // testing is illegal argument exception is thrown when range is null
    public void testExpand_NullRange() {
        Range range = null;
        double lowerMargin = 0.1;
        double upperMargin = 0.2;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD shift()

    @Test
    public void testShift_PositiveDelta() {
        // testing a shift in a positive range
        Range base = new Range(1, 5);
        double delta = 2;
        Range shiftedRange = Range.shift(base, delta);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be 3", 3, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 7", 7, shiftedRange.getUpperBound(), 0);
    }

    @Test
    public void testShift_NegativeDeltaWithoutZeroCrossing() {
        // testing a shift with a negative delta, ie negative shift
        Range base = new Range(1, 5);
        double delta = -2;
        Range shiftedRange = Range.shift(base, delta);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be 0", 0, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 3", 3, shiftedRange.getUpperBound(), 0);
    }

    @Test
    public void testShift_ZeroDelta() {
        // testing a shift of zero, the range should stay the same
        Range base = new Range(1, 5);
        double delta = 0;
        Range shiftedRange = Range.shift(base, delta);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be 1", 1, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 5", 5, shiftedRange.getUpperBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShift_NullBaseRange() {
        // testing to see if methods throws an exception if null range
        Range base = null;
        double delta = 2.0;
        Range shiftedRange = Range.shift(base, delta);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD shift()
    // this allows zero crossing

    @Test
    public void testShift_NegativeDelta_AllowZeroCrossing() {
        // testing shift with negative delta and true zero crossing
        Range base = new Range(1, 5);
        double delta = -2;
        boolean allowZeroCrossing = true;
        Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be -1", -1, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 3", 3, shiftedRange.getUpperBound(), 0);
    }

    @Test
    public void testShift_PositiveDelta_NoZeroCrossing() {
        // testing shift with a positive delta and true zero crossing
        Range base = new Range(1, 5);
        double delta = 2;
        boolean allowZeroCrossing = false;
        Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be 3", 3, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 7", 7, shiftedRange.getUpperBound(), 0);
    }

    @Test
    public void testShift_NegativeRange() {
        // testing a negative range with a positive delta
        Range base = new Range(-2, -1);
        double delta = 2;
        boolean allowZeroCrossing = false;
        Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be 0", 0, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 0", 0, shiftedRange.getUpperBound(), 0);
    }

    @Test
    public void testShift_ZeroDeltaAndRange() {
        // testing a zero range with a zero delta
        Range base = new Range(0, 0);
        double delta = 0;
        boolean allowZeroCrossing = false;
        Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
        assertNotNull(shiftedRange);
        assertEquals("The return value should be 0", 0, shiftedRange.getLowerBound(), 0);
        assertEquals("The return value should be 0", 0, shiftedRange.getUpperBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShift_NullBaseRangeWithZeroCrossing() {
        // testing a shift in the null range, should throw an exception
        Range base = null;
        double delta = 2;
        boolean allowZeroCrossing = true;
        Range shiftedRange = Range.shift(base, delta, allowZeroCrossing);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD scale()
    @Test
    public void testScale_PositiveFactor() {
        // scaling with a positive range and factor
        Range base = new Range(1, 5);
        double factor = 2;
        Range range = Range.scale(base, factor);
        assertNotNull(range);
        assertEquals("The return value should be 2", 2, range.getLowerBound(), 0);
        assertEquals("The return value should be 10", 10, range.getUpperBound(), 0);
    }

    @Test
    public void testScale_ZeroFactor() {
        // testing for scaling with factor of zero
        Range base = new Range(1, 5);
        double factor = 0;
        Range range = Range.scale(base, factor);
        assertNotNull(range);
        assertEquals("The return value should be 0", 0, range.getLowerBound(), 0);
        assertEquals("The return value should be 0", 0, range.getUpperBound(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScale_NegativeFactor() {
        // testing that a exception is thrown for a negative scaling
        Range base = new Range(1.0, 5.0);
        double factor = -2.0;
        Range range = Range.scale(base, factor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScale_NullBaseRange() {
        // testing that an exception is thrown for null range
        Range base = null;
        double factor = 2.0;
        Range range = Range.scale(base, factor);
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD equals()
    @Test
    public void testEquals_SameObject() {
        // testing that the same range equals itself
        Range range = new Range(1.0, 5.0);
        assertTrue(range.equals(range));
    }

    @Test
    public void testEquals_EqualObjects() {
        // testing that 2 equal ranges equal each other
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 5.0);
        assertTrue(range1.equals(range2));
    }

    @Test
    public void testEquals_CompareWithNull() {
        // testing that null does not equal a range
        Range range = new Range(1.0, 5.0);
        assertFalse(range.equals(null));
    }

    @Test
    public void testEquals_CompareWithDifferentType() {
        // testing that a string does not equal a range
        Range range = new Range(1.0, 5.0);
        assertFalse(range.equals("String"));
    }

    @Test
    public void testEquals_CompareWithDifferentValues() {
        // testing that ranges with different values do not equal each other
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 6.0);
        assertFalse(range1.equals(range2));
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD isNaNRange()

    @Test
    public void testIsNaNRange_BothBoundsNaN() {
        // testing a NaN range is a NaN range
        Range range = new Range(Double.NaN, Double.NaN);
        assertTrue(range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_LowerBoundNaN() {
        // testing a range of NaN and a number is not a NaN range
        Range range = new Range(Double.NaN, 5.0);
        assertFalse(range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_UpperBoundNaN() {
        // testing a range of NaN and a number is not a NaN range (lower bound)
        Range range = new Range(1.0, Double.NaN);
        assertFalse(range.isNaNRange());
    }

    @Test
    public void testIsNaNRange_NeitherBoundNaN() {
        // testing that a range of 1 and 5 is not a NaN Range
        Range range = new Range(1.0, 5.0);
        assertFalse(range.isNaNRange());
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD hashCode()

    @Test
    public void testHashCode_EqualObjects() {
        // testing that the hashcodes of equal objs are the same
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 5.0);
        assertEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_UnequalObjects() {
        // testing that the hashcodes of different objs are not the same
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 6.0);
        assertNotEquals(range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_Consistent() {
        Range range = new Range(1.0, 5.0);
        int initialHashCode = range.hashCode();
        for (int i = 0; i < 1000; i++) {
            assertEquals(initialHashCode, range.hashCode());
        }
    }

    // --------------------------------------------------------
    // THESE TESTS ARE FOR THE METHOD toString()

    @Test
    public void testToString_ValidRange() {
        // testing that range is made to a string
        Range range = new Range(1.0, 5.0);
        assertEquals("Range[1.0,5.0]", range.toString());
    }

    @Test
    public void testToString_RangeWithNaN() {
        // testing that range with NaN is made to a string
        Range range = new Range(Double.NaN, 5.0);
        assertEquals("Range[NaN,5.0]", range.toString());
    }

    @Test
    public void testToString_NegativeRange() {
        // testing that negative range is made to a string
        Range range = new Range(-2.5, -1.0);
        assertEquals("Range[-2.5,-1.0]", range.toString());
    }

    @Test
    public void testToString_ZeroRange() {
        // testing that zero range is made to a string
        Range range = new Range(0.0, 0.0);
        assertEquals("Range[0.0,0.0]", range.toString());
    }

    @Test
    public void testToString_PositiveNegativeRange() {
        // testing that mixed range is made to a string
        Range range = new Range(-3.0, 3.0);
        assertEquals("Range[-3.0,3.0]", range.toString());
    }
    
	// 	New Mutation strengthening tests --------------------------- ASSIGNMENT 4
	//	toString()

    // Kill a++, a-- in lower and upper bound
    @Test
    public void testToString_LowerBoundIncrement() {
        Range range = new Range(1.0, 5.0);
        // Use function range.toString(), but then assert lower and upper bound
        String result = range.toString();
        assertEquals(range.getLowerBound(), 1.0, 0);
    }

    @Test
    public void testToString_UpperBoundDecrement() {
        Range range = new Range(1.0, 5.0);
        // Use function range.toString(), but then assert lower and upper bound
        String result = range.toString();
        assertEquals(range.getUpperBound(), 5.0, 0);
    }

    // intersects()
    // Kill a++, a-- in lower and upper bound
    @Test
    public void testIntersects_LowerBoundIncrement() {
        Range range = new Range(1.0, 5.0);
        boolean result = range.intersects(1.0, 5.0);
        assertEquals(range.getLowerBound(), 1.0, 0);
    }

    @Test
    public void testIntersects_UpperBoundDecrement() {
        Range range = new Range(1.0, 5.0);
        boolean result = range.intersects(1.0, 5.0);
        assertEquals(range.getUpperBound(), 5.0, 0);
    }

    @Test
    public void testIntersects_LowerBoundIncrement1() {
        Range range = new Range(5.0, 10.0);
        boolean result = range.intersects(6.0, 10.0);
        assertEquals(range.getLowerBound(), 5.0, 0);
    }

    @Test
    public void testIntersects_UpperBoundDecrement1() {
        Range range = new Range(5.0, 10.0);
        boolean result = range.intersects(6.0, 10.0);
        assertEquals(range.getUpperBound(), 10.0, 0);
    }

    @Test
    public void testIntersectAtLowerBoundary() {
        assertTrue("The range should intersect at its lower boundary",
                exampleRange.intersects(-1.0, 0.0));
    }

    @Test
    public void testIntersectJustOutsideLowerBoundary() {
        assertFalse("The range should not intersect just outside its lower boundary",
                exampleRange.intersects(-1.1, -1.01));
    }

    @Test
    public void testIntersectAtUpperBoundary() {
        assertTrue("The range should intersect at its upper boundary",
                exampleRange.intersects(0.0, 1.0));
    }


    @Test
    public void testIntersectJustOutsideUpperBoundary() {
        assertFalse("The range should not intersect just outside its upper boundary",
                exampleRange.intersects(1.01, 1.1));
    }

    @Test
    public void testIntersectZeroWidthRangeWithin() {
        assertTrue("The range should intersect with a zero-width range within its bounds",
                exampleRange.intersects(0.5, 0.5));
    }

    @Test
    public void testIntersectZeroWidthRangeOutside() {
        assertFalse("The range should not intersect with a zero-width range outside its bounds",
                exampleRange.intersects(2.0, 2.0));
    }

    @Test
    public void testIntersectWithRangesJustInsideBoundaries() {
        assertTrue("The range should intersect with a range that is just inside its boundaries",
                exampleRange.intersects(-0.999, 0.999));
    }

    //getCentalValue()
    // Kill a++, a-- in lower and upper bound
    @Test
    public void testGetCentralValue_LowerBoundIncrement() {
        Range range = new Range(1.0, 5.0);
        double result = range.getCentralValue();
        assertEquals(range.getLowerBound(), 1.0, 0);
    }

    @Test
    public void testGetCentralValue_UpperBoundDecrement() {
        Range range = new Range(1.0, 5.0);
        double result = range.getCentralValue();
        assertEquals(range.getUpperBound(), 5.0, 0);
    }
    
    //expand()
    @Test
    public void testExpand_OverOneMargin() {
        Range range = new Range(0, 10);
        double lowerMargin = 1.1;
        double upperMargin = 1.2;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be -11", -11, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 22", 22, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpand_NegativeMargins() {
        Range range = new Range(0, 10);
        double lowerMargin = -0.1;
        double upperMargin = -0.2;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 1", 1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 8", 8, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpand_NegativeAndPositiveMargins() {
        Range range = new Range(0, 10);
        double lowerMargin = -0.1;
        double upperMargin = 0.2;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be 1", 1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 12", 12, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpand_DecimalMargins() {
        Range range = new Range(0, 10);
        double lowerMargin = 0.11;
        double upperMargin = 0.22;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be -1.1", -1.1, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be 11.1", 12.2, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void testExpand_NegativeRange() {
        Range range = new Range(-10, -5);
        double lowerMargin = 0.1;
        double upperMargin = 0.2;
        Range expandedRange = Range.expand(range, lowerMargin, upperMargin);
        assertNotNull(expandedRange);
        assertEquals("The return value should be -10.5", -10.5, expandedRange.getLowerBound(), 0);
        assertEquals("The return value should be -4", -4, expandedRange.getUpperBound(), 0);
    }

    @Test
    public void expandLowerGreaterThanUpper() {
        exampleRange1 = new Range(1, 2);
        exampleRange = Range.expand(exampleRange1, -0.9, -0.9);
        assertEquals("The lower margin range will be 1.5", 1.5, exampleRange.getLowerBound(), .000000001d);
    }

    // hashCode() 
    @Test
    public void testHashCode_DifferentRanges() {
        // Create a set to track the hashCodes
        Set<Integer> hashCodes = new HashSet<>();
        double epsilon = 0.0000001;

        // Create a series of different range objects and check their hash codes
        for (double i = 0; i < 1000; i++) {
            Range range = new Range(i, i + epsilon);
            hashCodes.add(range.hashCode());
        }
        assertTrue("Hash codes should be well-distributed", hashCodes.size() > 990);
    }

    @Test
    public void testHashCode_PositiveInfinity() {
        Range range = new Range(1, Double.POSITIVE_INFINITY);
        assertNotNull(range.hashCode());
    }

    @Test
    public void testHashCode_NeagtiveInfinity() {
        Range range = new Range(Double.NEGATIVE_INFINITY, 1);
        assertNotNull(range.hashCode());
    }

    @Test
    public void testHashCode_NaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertNotNull(range.hashCode());
    }

    @Test
    public void testHashCode_MultiplicationReplacedWithAddition() {
        double lowerValue = 1 << 10; // 1024
        double upperValue = 1 << 15; // 32768

        Range range = new Range(lowerValue, upperValue);

        long lowerBits = Double.doubleToLongBits(lowerValue);
        long upperBits = Double.doubleToLongBits(upperValue);
        int expectedHashCode = (int) (lowerBits ^ (lowerBits >>> 32));
        expectedHashCode = 29 * expectedHashCode + (int) (upperBits ^ (upperBits >>> 32));

        assertEquals(expectedHashCode, range.hashCode());
    }

    @Test
    public void testHashCode_WithIncrement() {
        Range range = new Range(1, 5);
        int hashCode = range.hashCode();
        Assert.assertEquals("Lower bound must be one", 1, range.getLowerBound(), 0);
        Assert.assertEquals("Upper bound must be five", 5, range.getUpperBound(), 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
