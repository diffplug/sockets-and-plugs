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
package org.eclipse.equinox.nonosgi.internal.registry;

/**
 * Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 * 
 * Helper to debug None OSGi-env registry.
 */
final class DebugHelper {

	private static final String PLUGIN_NAME = "org.eclipse.equinox.nonosgi.registry"; //$NON-NLS-1$

	static final boolean DEBUG = false;

	/**
	 * Log message.
	 * 
	 * @param message
	 */
	public static void log(String message) {
		log(message, 0);
	}

	/**
	 * Log message with indent.
	 * 
	 * @param message
	 * @param indent
	 */
	public static void log(String message, int indent) {
		System.out.println(createMessage(message, indent));
	}

	/**
	 * Log error.
	 * 
	 * @param error
	 */
	public static void logError(String message) {
		logError(message, 0);
	}

	/**
	 * Log error with indent.
	 * 
	 * @param message
	 * @param indent
	 */
	public static void logError(String message, int indent) {
		System.err.println(createMessage(message, indent));
	}

	/**
	 * Log error exception.
	 * 
	 * @param e
	 */
	public static void logError(Throwable e) {
		e.printStackTrace(System.err);
		System.err.println();
	}

	/**
	 * Create message.
	 * 
	 * @param message
	 * @param indent
	 * @return
	 */
	private static String createMessage(String message, int indent) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			s.append("\t");
		}
		s.append("[");
		s.append(PLUGIN_NAME);
		s.append("] ");
		if (message != null) {
			s.append(message);
		}
		return s.toString();
	}
}
