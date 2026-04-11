package io.github.Jackwastakenx2.enamel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enamel implements ModInitializer {
	public static final String MOD_ID = "enamel";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Preparing Powerful Pins...");
		EnamelComponents.initialize();
		EnamelAttributes.initialize();
		EnamelAttachments.initialize();
		EnamelCommands.initialize();
		ItemComponentTooltipProviderRegistry.addAfter(DataComponents.ATTRIBUTE_MODIFIERS,EnamelComponents.PIN_COMPONENT);
		FabricDefaultAttributeRegistry.register(EntityType.PLAYER,
			Player.createAttributes()
				.add(EnamelAttributes.PIN_POINTS)
				.build());
	}
}
