package io.github.Jackwastakenx2.enamel;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class PinPips {
	public static void render(GuiGraphicsExtractor graphics, int startX, int startY, Player player) {
		int used = player != null ? player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT) : 0;
		int max = 2;
		if (player.getAttribute(EnamelAttributes.PIN_POINTS) != null) {
			max = (int) player.getAttributeValue(EnamelAttributes.PIN_POINTS);
		}
		int rh = 16;
		int h = 16;
		int rw = 16;
		int w = 16;
		float u_empty = 0;
		float u_full = rw;
		float u_over = 2*rw;
		Identifier id = Identifier.fromNamespaceAndPath(Enamel.MOD_ID,"textures/gui/sprites/container/pinpoint_icons.png");
		for (int i=0;i<Math.max(used,max);i++) {
			float u;
			if (i>=used) {
				u = u_empty;
			} else if (i<max) {
				u = u_full;
			} else {
				u = u_over;
			}
			graphics.blit(RenderPipelines.GUI_TEXTURED,id,(startX-w),startY+(h*i),u,0,w,h,rw,rh,rw*3,rh);
		}
	}
}
