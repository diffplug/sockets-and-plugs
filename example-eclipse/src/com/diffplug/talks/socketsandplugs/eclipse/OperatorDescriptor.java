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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

public class OperatorDescriptor {
	private final String name;
	private final String implementation;

	private OperatorDescriptor(IExtension element) {
		IConfigurationElement[] configElements = element.getConfigurationElements();
		if (configElements.length != 1) {
			throw new IllegalArgumentException("Extension should have had a single configuration element.");
		}
		IConfigurationElement configElement = configElements[0];
		name = configElement.getAttribute("name");
		implementation = configElement.getAttribute("implementation");
	}

	public String getName() {
		return name;
	}

	private Operator instance;

	public Operator instantiate() {
		if (instance != null) {
			return instance;
		} else {
			try {
				Class<?> clazz = Class.forName(implementation);
				instance = (Operator) clazz.newInstance();
				return instance;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/** Returns all the OperatorDescriptors in the project. */
	public static List<OperatorDescriptor> all() {
		IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint("com.diffplug.talks.socketsandplugs.eclipse.operator");
		IExtension[] extensions = point.getExtensions();
		return Arrays.asList(extensions).stream()
				.map(OperatorDescriptor::new)
				.collect(Collectors.toList());
	}

	/** Returns all the OperatorDescriptors in the project. */
	public static OperatorDescriptor forName(String name) {
		return all().stream().filter(desc -> desc.name.equals(name)).findFirst().get();
	}
}
