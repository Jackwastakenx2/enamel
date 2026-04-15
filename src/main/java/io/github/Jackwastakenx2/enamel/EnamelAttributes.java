package io.github.Jackwastakenx2.enamel;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.player.Player;

public class EnamelAttributes {
	private static Holder<Attribute> register(
		String name,
		double defaultValue,
		double minValue,
		double maxValue,
		boolean syncedWithClient
	) {
		Identifier identifier = Identifier.fromNamespaceAndPath(Enamel.MOD_ID, name);
		Attribute entityAttribute = new RangedAttribute(
			identifier.toLanguageKey(),
			defaultValue,
			minValue,
			maxValue
		).setSyncable(syncedWithClient);

		return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, identifier, entityAttribute);
	}
	public static Holder<Attribute> PIN_POINTS = register(
		"pin_points",
		4,
		4,
		16,
		true
	);
	public static void initialize() {
		Enamel.LOGGER.info("Registering {} attributes", Enamel.MOD_ID);
		FabricDefaultAttributeRegistry.register(EntityType.PLAYER,
			Player.createAttributes()
				.add(PIN_POINTS,4)
				.build());
	}
}
