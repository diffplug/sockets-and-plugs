package com.diffplug.talks.socketsandplugs.eclipse;

public class Functions {
	public static class Pi implements CalculatorFunc {
		@Override
		public double calculate(double... args) {
			requireArgs(args, 0);
			return Math.PI;
		}
	}

	public static class Power implements CalculatorFunc {
		@Override
		public double calculate(double... args) {
			requireArgs(args, 2);
			return Math.pow(args[0], args[1]);
		}
	}

	public static class Product implements CalculatorFunc {
		@Override
		public double calculate(double... args) {
			requireArgs(args, 1, Integer.MAX_VALUE);
			double product = args[0];
			for (int i = 1; i < args.length; ++i) {
				product *= args[i];
			}
			return product;
		}
	}

	private static final void requireArgs(double[] args, int count) {
		requireArgs(args, count, count);
	}

	private static final void requireArgs(double[] args, int min, int max) {
		if (args.length < min || args.length > max) {
			throw new IllegalArgumentException("args with length " + args.length + " is outside of [" + min + ", " + max + "]");
		}
	}
}
