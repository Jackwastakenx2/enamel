package io.github.Jackwastakenx2.enamel;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class EnamelComponents {
	public static final DataComponentType<Integer> PIN_COST_COMPONENT = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "cost"),
		DataComponentType.<Integer>builder().persistent(PinCostComponent.CODEC).build()

	);
	public static void initialize() {
		Enamel.LOGGER.info("Registering {} components", Enamel.MOD_ID);
		//ItemComponentTooltipProviderRegistry.addBefore(TrinketDataComponents.EQUIPMENT,PIN_COST_COMPONENT);
	}

}
