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
package com.diffplug.talks.socketsandplugs.intellij;

import java.util.Arrays;
import java.util.List;

import com.intellij.openapi.extensions.AbstractExtensionPointBean;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.util.xmlb.annotations.Attribute;

public class OperatorDescriptor extends AbstractExtensionPointBean {
	@Attribute("name")
	public String name;

	@Attribute("implementationClass")
	public String implementation;

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
		ExtensionPointName<OperatorDescriptor> point = new ExtensionPointName<>("com.diffplug.talks.socketsandplugs.intellij.operatorDescriptor");
		return Arrays.asList(point.getExtensions());
	}

	/** Returns all the OperatorDescriptors in the project. */
	public static OperatorDescriptor forName(String name) {
		return all().stream().filter(desc -> desc.name.equals(name)).findFirst().get();
	}
}
