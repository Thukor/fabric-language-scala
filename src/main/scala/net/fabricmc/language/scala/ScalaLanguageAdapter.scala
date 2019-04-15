/*
 * Copyright 2016, 2018, 2019 FabricMC
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

package net.fabricmc.language.scala
import net.fabricmc.loader.api.{LanguageAdapter, LanguageAdapterException, ModContainer}
import net.fabricmc.loader.launch.common.FabricLauncherBase

class ScalaLanguageAdapter extends LanguageAdapter {
	def create[T](modContainer: ModContainer, value: String, clazz: Class[T]): T = {
		
		println(value)
		val components = value.split("::")

		if(components.size >= 3) throw new LanguageAdapterException("Invalid handle format: " + value)

		val c = Class.forName(components(0) + "$", true, FabricLauncherBase.getLauncher().getTargetClassLoader())

		c.getDeclaredConstructor().newInstance().asInstanceOf[T]
	}
}

