/*
 * Copyright 2016 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.talks.socketsandplugs.eclipse;

public class Operators {
	public static class Pi implements Operator {
		@Override
		public double calculate(double... args) {
			requireArgs(args, 0);
			return Math.PI;
		}
	}

	public static class Power implements Operator {
		@Override
		public double calculate(double... args) {
			requireArgs(args, 2);
			return Math.pow(args[0], args[1]);
		}
	}

	public static class Product implements Operator {
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
