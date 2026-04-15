package io.github.Jackwastakenx2.enamel;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
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
		ItemTooltipCallback.EVENT.register(((itemStack, _, _, list) -> {
			if (itemStack.has(PIN_COST_COMPONENT)) {
				int cost = itemStack.get(PIN_COST_COMPONENT);
				String key = "item.enamel.cost.info";
				if (cost!=1) {
					key+=".plural";
				}
				var compo = Component.translatable(key,cost).withStyle(ChatFormatting.LIGHT_PURPLE);
				list.add(1,compo);
			}
		}));
	}

}
