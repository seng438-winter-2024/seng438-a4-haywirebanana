package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.ExpectationError;
import java.security.InvalidParameterException;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD calculateColumnTotal()

	@Test
	public void testNegativeValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-10));
				one(values).getValue(1, 0);
				will(returnValue(5));

			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(-5, result, 0.0001);
	}

	@Test
	public void testNullValue() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(null));
			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(0, result, 0.0001);
	}

	@Test
	public void testColumnOtherThanOne() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(4));
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(2));
				one(values).getValue(0, 2);
				will(returnValue(3));
				one(values).getValue(0, 3);
				will(returnValue(4));
				one(values).getValue(1, 0);
				will(returnValue(5));
				one(values).getValue(1, 1);
				will(returnValue(5));
				one(values).getValue(1, 2);
				will(returnValue(5));
				one(values).getValue(1, 3);
				will(returnValue(5));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 2);
		// verify
		assertEquals(result, 8.0, .000000001d);
		// tear-down: NONE in this test method
	}

	@Test
	public void testTenRowsForColumnTotal() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(10));
				one(values).getValue(0, 0);
				will(returnValue(12));
				one(values).getValue(1, 0);
				will(returnValue(13));
				one(values).getValue(2, 0);
				will(returnValue(55));
				one(values).getValue(3, 0);
				will(returnValue(123));
				one(values).getValue(4, 0);
				will(returnValue(23));
				one(values).getValue(5, 0);
				will(returnValue(21313));
				one(values).getValue(6, 0);
				will(returnValue(123));
				one(values).getValue(7, 0);
				will(returnValue(11));
				one(values).getValue(8, 0);
				will(returnValue(1));
				one(values).getValue(9, 0);
				will(returnValue(2.1));

			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateColumnTotal(values, 0);
		assertEquals(21676.1, result, 0.0001);
	}

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD calculateRowTotal()

	@Test
	public void testIntAsValueInData() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{

				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getRowCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(5));
			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(5, result, 0.0001);
	}

	@Test
	public void testDoubleAsValueInData() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{

				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(0.5));
				one(values).getValue(0, 1);
				will(returnValue(0.2));

			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals(0.7, result, 0.0001);
	}

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD createNumberArray()

	@Test
	public void testCreateNumberArrayWithValidInput() {
		double[] inputData = new double[] { 2.0, 3.4, 5.6 };

		Number[] result = DataUtilities.createNumberArray(inputData);

		// Print the result
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}

		assertEquals(2.0, result[0].doubleValue(), .000000001d);
		assertEquals(3.4, result[1].doubleValue(), .000000001d);
		assertEquals(5.6, result[2].doubleValue(), .000000001d);
	}

	@Test
	public void testCreateNumberArrayWithOneValue() {
		double[] inputData = new double[] { 2.0 };

		Number[] result = DataUtilities.createNumberArray(inputData);

		assertEquals(2.0, result[0].doubleValue(), .000000001d);
	}

	@Test
	public void testCreateNumberArrayWithEmptyInput() {
		double[] inputData = new double[] {};

		Number[] result = DataUtilities.createNumberArray(inputData);

		assertEquals(0, result.length);
	}

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD createNumberArray2D()

	@Test(expected = IllegalArgumentException.class)
	public void testNullDataValueCreateNumberArray2D() {
		Number[][] result = DataUtilities.createNumberArray2D(null);
	}

	@Test
	public void testCreateNumberArray2DWithValidInput() {
		double[][] inputData = new double[][] { { 2.0, 3.4, 5.6 }, { 1.0, 2.0, 3.0 } };

		Number[][] result = DataUtilities.createNumberArray2D(inputData);

		// Print the result
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.println(result[i][j]);
			}
		}

		assertEquals(2.0, result[0][0].doubleValue(), .000000001d);
		assertEquals(3.4, result[0][1].doubleValue(), .000000001d);
		assertEquals(5.6, result[0][2].doubleValue(), .000000001d);
		assertEquals(1.0, result[1][0].doubleValue(), .000000001d);
		assertEquals(2.0, result[1][1].doubleValue(), .000000001d);
		assertEquals(3.0, result[1][2].doubleValue(), .000000001d);
	}

	@Test
	public void testCreateNumberArray2DWithOneValue() {
		double[][] inputData = new double[][] { { 2.0 }, { 1.0 } };

		Number[][] result = DataUtilities.createNumberArray2D(inputData);

		assertEquals(2.0, result[0][0].doubleValue(), .000000001d);
		assertEquals(1.0, result[1][0].doubleValue(), .000000001d);
	}

	@Test
	public void testCreateNumberArray2DWithEmptyInput() {
		double[][] inputData = new double[][] {};

		Number[][] result = DataUtilities.createNumberArray2D(inputData);

		assertEquals(0, result.length);
	}

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD getCumulativePercentages()

	@Test
	public void calculateCumulativePercentageForThreeValues() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues vals = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(vals).getKey(0);
				will(returnValue(0));
				allowing(vals).getKey(1);
				will(returnValue(1));
				allowing(vals).getKey(2);
				will(returnValue(2));
				allowing(vals).getValue(0);
				will(returnValue(5));
				allowing(vals).getValue(1);
				will(returnValue(9));
				allowing(vals).getValue(2);
				will(returnValue(2));
				allowing(vals).getItemCount();
				will(returnValue(3));
			}
		});
		KeyedValues result = DataUtilities.getCumulativePercentages(vals);

		// verify
		assertEquals(0.875, result.getValue(1)); // Key 1 should have value 0.875
		assertEquals(1.0, result.getValue(2)); // Key 2 should have value 1.0
	}

	@Test
	public void calculateCumulativePercentageForOneValue() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues vals = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(vals).getKey(0);
				will(returnValue(0));
				allowing(vals).getValue(0);
				will(returnValue(5));
				allowing(vals).getItemCount();
				will(returnValue(1));
			}
		});
		KeyedValues result = DataUtilities.getCumulativePercentages(vals);

		// verify
		assertEquals(1.0, result.getValue(0));
	}

	@Test
	public void calculateCumulativePercentageForEmpty() {
		Mockery mockingContext = new Mockery();
		final KeyedValues vals = mockingContext.mock(KeyedValues.class);

		mockingContext.checking(new Expectations() {
			{
				allowing(vals).getItemCount();
				will(returnValue(0));
			}
		});

		KeyedValues result = DataUtilities.getCumulativePercentages(vals);
		assertTrue(result.getItemCount() == 0);
	}

	@Test
	public void calculateCumulativePercentageWithFloatingPointValues() {
		Mockery mockingContext = new Mockery();
		final KeyedValues vals = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(vals).getKey(0);
				will(returnValue(0));
				allowing(vals).getKey(1);
				will(returnValue(1));
				allowing(vals).getKey(2);
				will(returnValue(2));
				allowing(vals).getValue(0);
				will(returnValue(0.5));
				allowing(vals).getValue(1);
				will(returnValue(0.25));
				allowing(vals).getValue(2);
				will(returnValue(0.25));
				allowing(vals).getItemCount();
				will(returnValue(3));
			}
		});
		KeyedValues result = DataUtilities.getCumulativePercentages(vals);

		// verify
		assertEquals(0.5, result.getValue(0)); // Key 0 should have value 0.3125
		assertEquals(0.75, result.getValue(1)); // Key 1 should have value 0.875
		assertEquals(1.0, result.getValue(2)); // Key 2 should have value 1.0
	}

	// NEW
	@Test
	public void calculateCumulativePercentageWithSomeNullValues() {
		Mockery mockingContext = new Mockery();
		final KeyedValues vals = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(vals).getKey(0);
				will(returnValue(0));
				allowing(vals).getKey(1);
				will(returnValue(1));
				allowing(vals).getValue(0);
				will(returnValue(7));
				allowing(vals).getValue(1);
				will(returnValue(null)); // This should trigger the null branch
				allowing(vals).getItemCount();
				will(returnValue(2));
			}
		});
		KeyedValues result = DataUtilities.getCumulativePercentages(vals);

		assertEquals(1.0, result.getValue(0));
	}

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD equal(double[][] a, double[][] b)

	// Testing with two valid arrays
	@Test
	public void testEqualWithTwoEqualArrays() {
		double[][] a = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		double[][] b = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };

		assertTrue(DataUtilities.equal(a, b));
	}

	// testing with a having a null value
	@Test
	public void testEqualWithTwoNullArrays() {
		double[][] a = null;
		double[][] b = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };

		assertFalse(DataUtilities.equal(a, b));
	}

	// testing with b having a null value
	@Test
	public void testEqualWithOneNullArray() {
		double[][] a = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		double[][] b = null;

		assertFalse(DataUtilities.equal(a, b));
	}

	// testing with both arrays having a null value
	@Test
	public void testEqualWithBothNullArrays() {
		double[][] a = null;
		double[][] b = null;

		assertTrue(DataUtilities.equal(a, b));
	}

	// testing with a.length != b.length
	@Test
	public void testEqualWithFirstDimensionDifferentLengths() {
		double[][] a = new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 } };
		double[][] b = new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 6.0 } };

		assertFalse(DataUtilities.equal(a, b));
	}

	// testing with different values
	@Test
	public void testEqualWithDifferentLengthArrays() {
		double[][] a = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		double[][] b = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0, 7.0 } };
		assertFalse(DataUtilities.equal(a, b));
	}

	// --------------------------------------------------------
	// THESE TESTS ARE FOR THE METHOD clone(double[][] source)

	// Testing with a valid array
	@Test
	public void testCloneWithValidArray() {
		double[][] source = new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		double[][] result = DataUtilities.clone(source);

		assertArrayEquals(source, result);
	}

	// Testing with a null input
	@Test
	public void testCloneWithNullArray() {
		double[][] source = new double[][] { null, { 4.0, 5.0, 6.0 } };
		double[][] result = DataUtilities.clone(source);

		assertArrayEquals(source, result);
	}

	// New Tests For White Box Testing
	// Added Tests for calculateColumnTotal with valid rows params ( Did not have
	// these tests in BlackBox Testing )

	@Test
	public void getCumulativePercentagesTest() {

		DefaultKeyedValues testValue = new DefaultKeyedValues();
		testValue.addValue("Key1", 5);
		testValue.addValue("Key2", 9);
		testValue.addValue("Key3", 2);
		KeyedValues result = DataUtilities.getCumulativePercentages(testValue);
		assertEquals(0.3125, result.getValue(0).doubleValue(), .000000001d);
	}

	@Test
	public void testColumnTotalNonValidColumnsMock() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-10));
				one(values).getValue(1, 0);
				will(returnValue(5));

			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateColumnTotal(values, 0, new int[] { 0, 1 });
		assertEquals(-5, result, 0.0001);
	}

	@Test
	public void testColumnTotalNonValidRowsMock() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{

				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(0.5));
				one(values).getValue(0, 1);
				will(returnValue(0.2));

			}
		});

		// This line will throw an InvalidParameterException when a null value is
		// encountered
		double result = DataUtilities.calculateRowTotal(values, 0, new int[] { 0, 1 });
		assertEquals(0.7, result, 0.0001);
	}

	// New Assignment 4 Mutation Tests
	// --------------------------------------------------------
	@Test
	public void testCalculateRowTotal_SkipNullValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(3));
				one(values).getValue(0, 0);
				will(returnValue(7));
				one(values).getValue(0, 1);
				will(returnValue(null));
				one(values).getValue(0, 2);
				will(returnValue(3));
			}
		});

		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Row total should skip null values", 10.0, result, 0.0000001d);
	}

	@Test
	public void testCalculateRowTotal_IncludeNegativeValues() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(-2));
				one(values).getValue(0, 1);
				will(returnValue(1));
			}
		});

		double result = DataUtilities.calculateRowTotal(values, 0);
		assertEquals("Row total should include negative values", -1.0, result, 0.0000001d);
	}

	@Test
	public void testCalculateRowTotal_ValidColumnIndexOutOfBounds() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(5));
			}
		});

		double result = DataUtilities.calculateRowTotal(values, 0, new int[] { 0, 1 });
		assertEquals("Row total should handle valid column index out of bounds", 5.0, result, 0.0000001d);
	}
}
