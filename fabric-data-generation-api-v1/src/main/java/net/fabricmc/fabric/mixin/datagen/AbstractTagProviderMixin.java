/*
 * Copyright 2016, 2017, 2018, 2019 FabricMC
 * Copyright 2022 QuiltMC
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

package net.fabricmc.fabric.mixin.datagen;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

import com.google.gson.JsonElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.data.DataWriter;
import net.minecraft.data.server.tag.AbstractTagProvider;
import net.minecraft.registry.tag.TagBuilder;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.impl.datagen.FabricTagBuilder;

@Mixin(AbstractTagProvider.class)
public class AbstractTagProviderMixin {
	@Inject(method = "method_27046", at = @At(value = "INVOKE", target = "Lnet/minecraft/data/DataOutput$PathResolver;resolveJson(Lnet/minecraft/util/Identifier;)Ljava/nio/file/Path;"), locals = LocalCapture.CAPTURE_FAILHARD)
	public void addReplaced(Predicate<?> p, DataWriter dataWriter, Map.Entry<?, ?> entry, CallbackInfoReturnable<CompletableFuture<?>> ci, Identifier id, TagBuilder builder, List list, List list2, JsonElement jsonElement) {
		if (builder instanceof FabricTagBuilder fabricTagBuilder) {
			jsonElement.getAsJsonObject().addProperty("replace", fabricTagBuilder.fabric_isReplaced());
		}
	}
}
