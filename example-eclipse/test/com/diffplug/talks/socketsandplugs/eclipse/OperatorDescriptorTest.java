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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.equinox.nonosgi.internal.registry.RegistryProviderNonOSGI;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class OperatorDescriptorTest {
	@BeforeClass
	public static void setup() throws CoreException {
		RegistryFactory.setDefaultRegistryProvider(new RegistryProviderNonOSGI());
	}

	@Test
	public void testPi() throws Exception {
		testCase("pi", 3.14159);
	}

	@Test
	public void testPower() throws Exception {
		testCase("power", 2, 2, 4);
		testCase("power", 2, 1, 2);
		testCase("power", 1, 2, 1);
		testCase("power", 3, 1.5, 5.19615);
	}

	@Test
	public void testProduct() throws Exception {
		testCase("product", 1, 1);
		testCase("product", 2, 3, 6);
		testCase("product", 10, 10, 10, 1000);
	}

	private static void testCase(String name, double... argsAndExpected) throws Exception {
		OperatorDescriptor descriptor = OperatorDescriptor.forName(name);
		Operator func = descriptor.instantiate();
		double[] args = new double[argsAndExpected.length - 1];
		System.arraycopy(argsAndExpected, 0, args, 0, args.length);
		double result = func.calculate(args);
		Assert.assertEquals(argsAndExpected[args.length], result, .001);
	}
}
