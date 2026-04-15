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
		int rh = 8;
		int h = 8;
		int rw = 8;
		int w = 8;
		int m = 4;
		float u_empty = 0;
		float u_full = 8;
		float u_over = 16;
		Identifier id = Identifier.fromNamespaceAndPath(Enamel.MOD_ID,"textures/gui/pp_ind.png");
		for (int i=0;i<Math.max(used,max);i++) {
			float u;
			if (i>=used) {
				u = u_empty;
			} else if (i<max) {
				u = u_full;
			} else {
				u = u_over;
			}
			graphics.blit(RenderPipelines.GUI_TEXTURED,id,(startX-w)-m,startY+(h*i)+m,u,0,w,h,rw,rh,24,8);
		}
	}
}
