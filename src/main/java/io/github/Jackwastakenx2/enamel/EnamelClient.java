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

public class EnamelClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudElementRegistry.attachElementAfter(VanillaHudElements.MISC_OVERLAYS, Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "pincumber_overlay"), EnamelClient::ExtractPincumber);
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
