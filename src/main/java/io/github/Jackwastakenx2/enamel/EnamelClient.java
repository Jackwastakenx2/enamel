package io.github.Jackwastakenx2.enamel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.Item;

import static eu.pb4.trinkets.api.client.TrinketRendererRegistry.registerRenderer;
import static io.github.Jackwastakenx2.enamel.EnamelItems.*;

public class EnamelClient implements ClientModInitializer {
	private static void registerPinRenderer(Item pin) {
		registerRenderer(pin,new PinRenderer());
	}
	@Override
	public void onInitializeClient() {
		HudElementRegistry.attachElementAfter(VanillaHudElements.MISC_OVERLAYS, Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "pincumber_overlay"), EnamelClient::ExtractPincumber);
		registerPinRenderer(COPPER_PIN);
		registerPinRenderer(IRON_PIN);
		registerPinRenderer(GOLD_PIN);
		registerPinRenderer(DIAMOND_PIN);
		registerPinRenderer(NETHERITE_PIN);
		registerPinRenderer(HEALTH_PIN);
		registerPinRenderer(SPEED_PIN);
		registerPinRenderer(FURNACE_PIN);
		registerPinRenderer(ROCK_PIN);
		registerPinRenderer(LONGNAIL);
		registerPinRenderer(PLUSH_PIN);
		registerPinRenderer(ERROR_PIN);
		registerPinRenderer(FEST_PIN);
	}

	private static final Identifier PINCUMBER_OUTLINE_LOCATION = Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "textures/misc/overpincumberance_overlay.png");

	private static void ExtractPincumber(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
		LocalPlayer player =  Minecraft.getInstance().player;
		int used = player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
		AttributeInstance attr =  player.getAttribute(EnamelAttributes.PIN_POINTS);
		if (attr != null && used > attr.getValue()) {
			graphics.blit(
				RenderPipelines.GUI_TEXTURED, PINCUMBER_OUTLINE_LOCATION, 0, 0, 0.0F, 0.0F, graphics.guiWidth(), graphics.guiHeight(), graphics.guiWidth(), graphics.guiHeight()
			);
		}

	}
}
