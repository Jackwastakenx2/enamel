package io.github.Jackwastakenx2.enamel.mixin;

import io.github.Jackwastakenx2.enamel.Enamel;
import io.github.Jackwastakenx2.enamel.EnamelAttachments;
import io.github.Jackwastakenx2.enamel.EnamelAttributes;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractRecipeBookScreen<InventoryMenu> {
	public InventoryScreenMixin(InventoryMenu menu, RecipeBookComponent<?> recipeBookComponent, Inventory inventory, Component title) {
		super(menu, recipeBookComponent, inventory, title);
	}

	@Inject(method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",at=@At(value = "TAIL"))
	public void renderPinPips(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
		Player player = this.minecraft.player;
		int used = player != null ? player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT) : 0;
		int max = 2;
		if (player.getAttribute(EnamelAttributes.PIN_POINTS) != null) {
			max = (int) player.getAttributeValue(EnamelAttributes.PIN_POINTS);
		}
		int ox = this.leftPos;
		int oy = this.topPos+this.imageHeight;
		Enamel.LOGGER.info("{} {}",oy,ox);
		int rh = 8;
		int h = 8;
		int rw = 8;
		int w = 8;
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
			Enamel.LOGGER.info("{} {} {}",i,ox+(w*i),u);
			graphics.blit(RenderPipelines.GUI_TEXTURED,id,ox+(w*i),oy,u,0,w,h,rw,rh,24,8);
		}
	}
}
