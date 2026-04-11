package io.github.Jackwastakenx2.enamel;

import io.github.Jackwastakenx2.enamel.component.PinComponent;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class EnamelComponents {
	public static final DataComponentType<PinComponent> PIN_COMPONENT = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "pin_data"),
		DataComponentType.<PinComponent>builder().persistent(PinComponent.CODEC).build()
	);
	protected static void initialize() {
		Enamel.LOGGER.info("Registering {} components", Enamel.MOD_ID);

	}
}
