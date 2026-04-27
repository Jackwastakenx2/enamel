package io.github.Jackwastakenx2.enamel;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public class PinPips {
	private static final int rh = 8;
	private static final int h = 8;
	private static final int rw = 8;
	private static final int w = 8;
	private static final Identifier PIP_IDENTIFIER = Identifier.fromNamespaceAndPath(Enamel.MOD_ID,"textures/gui/sprites/container/pinpoint_icons.png");

	private enum PipType {
		EMPTY(0),
		FULL(1),
		OVER(2),
		GLITCH(3);

		final float w; // Field to store the custom value

		// Constructor - must be private or package-private
		PipType(int w) {
			this.w = rw*w;
		}
	}

	private static void blitPip(GuiGraphicsExtractor graphics,int x,int y,int i,PipType type) {
		graphics.blit(RenderPipelines.GUI_TEXTURED,PIP_IDENTIFIER,x+(w*i),y,type.w,0,w,h,rw,rh,rw*4,rh);
	}
	public static void render(GuiGraphicsExtractor graphics, int startX, int startY, Player player) {
		int used = player != null ? player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT) : 0;
		int max = 2;
		if (player.getAttribute(EnamelAttributes.PIN_POINTS) != null) {
			max = (int) player.getAttributeValue(EnamelAttributes.PIN_POINTS);
		}

		int n = 0;
		if (used<0) {
			n=-used;
			for (int i=0;i<-used;i++) {
				blitPip(graphics,startX,startY,i,PipType.GLITCH);
			}
		}
		for (int i=0;i<Math.max(used,max);i++) {
			PipType t;
			if (i>=used) {
				t = PipType.EMPTY;
			} else if (i<max) {
				t = PipType.FULL;
			} else {
				t = PipType.OVER;
			}
			blitPip(graphics,startX,startY,i+n,t);
		}
	}
}
