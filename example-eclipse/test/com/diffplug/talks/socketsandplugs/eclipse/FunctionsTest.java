package com.diffplug.talks.socketsandplugs.eclipse;

import org.junit.Assert;
import org.junit.Test;

import com.diffplug.talks.socketsandplugs.eclipse.CalculatorFunc;
import com.diffplug.talks.socketsandplugs.eclipse.Functions.Pi;

public class FunctionsTest {
	@Test
	public void testProduct() throws Exception {
		testCase(Pi.class, 3.14159);
	}

	private void testCase(Class<? extends CalculatorFunc> clazz, double... argsAndExpected) throws Exception {
		CalculatorFunc func = clazz.getConstructor().newInstance();
		double[] args = new double[argsAndExpected.length - 1];
		System.arraycopy(argsAndExpected, 0, args, 0, args.length);
		double result = func.calculate(args);
		Assert.assertEquals(argsAndExpected[args.length], result, .001);
	}
}
