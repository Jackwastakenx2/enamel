package io.github.Jackwastakenx2.enamel.mixin.client;


import io.github.Jackwastakenx2.enamel.PinPips;
import net.fabricmc.fabric.api.client.creativetab.v1.FabricCreativeModeInventoryScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeScreenMixin extends AbstractContainerScreen<CreativeModeInventoryScreen.ItemPickerMenu> implements FabricCreativeModeInventoryScreen {
	@Shadow
	private static CreativeModeTab selectedTab;

	public CreativeScreenMixin(CreativeModeInventoryScreen.ItemPickerMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}
	@Inject(method = "extractRenderState",at=@At(value="TAIL"))
	public void renderPinPips(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
		if (this.selectedTab.getType() == CreativeModeTab.Type.INVENTORY) {
			PinPips.render(graphics, this.leftPos + this.imageWidth + 16, this.topPos, this.minecraft.player);
		}
	}
}
