package io.github.Jackwastakenx2.enamel;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Enamel implements ModInitializer {
	public static final String MOD_ID = "enamel";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Preparing Powerful Pins...");

		EnamelAttributes.initialize();
		EnamelAttachments.initialize();
		EnamelComponents.initialize();
		EnamelItems.initialize();
	}
}
