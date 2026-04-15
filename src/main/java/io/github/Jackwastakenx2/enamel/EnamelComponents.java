package io.github.Jackwastakenx2.enamel;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class EnamelComponents {
	public static final DataComponentType<Integer> PIN_COST_COMPONENT = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "cost"),
		DataComponentType.<Integer>builder().persistent(Codec.INT).build()

	);
	public static void initialize() {
		Enamel.LOGGER.info("Registering {} components", Enamel.MOD_ID);
		ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipFlag, list) -> {
			if (itemStack.has(PIN_COST_COMPONENT)) {
				var compo = Component.translatable("item.enamel.cost.info",itemStack.get(PIN_COST_COMPONENT)).withStyle(ChatFormatting.LIGHT_PURPLE);
				list.add(1,compo);
			}
		}));
	}

}
