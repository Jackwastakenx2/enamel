package io.github.Jackwastakenx2.enamel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import eu.pb4.trinkets.api.TrinketSlotAccess;
import eu.pb4.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class PinRenderer implements TrinketRenderer {
	@Override
	public void submit(ItemStack stack, TrinketSlotAccess slotReference, EntityModel<? extends LivingEntityRenderState> contextModel, PoseStack matrices, SubmitNodeCollector submit, int light, LivingEntityRenderState state, float limbAngle, float limbDistance) {
		ItemModelResolver resolver = Minecraft.getInstance().getItemModelResolver();
		ItemStackRenderState itemState = new ItemStackRenderState();
		resolver.appendItemLayers(itemState,stack,ItemDisplayContext.NONE,null,null,0);
		TrinketRenderer.translateToChest(matrices, (HumanoidModel<?>) contextModel, (HumanoidRenderState) state);
		matrices.mulPose(Axis.ZP.rotationDegrees(180));
		matrices.translate(0.25,0.5,0);
		matrices.scale(0.125F,0.125F,0.125F);
		matrices.translate(-0.5,-1.25,0.125);
		int index = slotReference.index();
		matrices.translate(-(index%4),-Math.floorDiv(index,4),0);

		itemState.submit(matrices,submit,light,0,0);
	}
}
