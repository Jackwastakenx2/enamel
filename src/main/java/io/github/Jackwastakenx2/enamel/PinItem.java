package io.github.Jackwastakenx2.enamel;

import eu.pb4.trinkets.api.SlotAttributes;
import eu.pb4.trinkets.api.TrinketSlotAccess;
import eu.pb4.trinkets.api.callback.TrinketCallback;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public class PinItem extends Item implements TrinketCallback {
	private final Holder<Attribute> pinSlotModifier;
	public PinItem(Properties properties) {
		properties.stacksTo(1);
		super(properties);
		this.pinSlotModifier = SlotAttributes.createAttributeForSlot("offhand/pin");

	}

	@Override
	public void onEquip(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity) {
		TrinketCallback.super.onEquip(stack, slot, entity);
		if (stack.has(EnamelComponents.PIN_COST_COMPONENT)) {
			int cost = stack.get(EnamelComponents.PIN_COST_COMPONENT);
			int used = entity.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
			entity.setAttached(EnamelAttachments.PP_ATTACHMENT,cost+used);
		}
	}

	@Override
	public void onUnequip(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity) {
		TrinketCallback.super.onUnequip(stack, slot, entity);
		if (stack.has(EnamelComponents.PIN_COST_COMPONENT)) {
			int cost = stack.get(EnamelComponents.PIN_COST_COMPONENT);
			int used = entity.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
			entity.setAttached(EnamelAttachments.PP_ATTACHMENT,Math.max(0,used-cost));
		}
	}

	@Override
	public boolean canEquip(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity) {
		int used = entity.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
		AttributeInstance attr =  entity.getAttribute(EnamelAttributes.PIN_POINTS);
		if (attr != null && used < attr.getValue()) {
			return TrinketCallback.super.canEquip(stack, slot, entity);
		}
		return false;
	}

	@Override
	public void forEachTrinketModifier(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity, Identifier slotIdentifier, BiConsumer<Holder<Attribute>, AttributeModifier> consumer) {
		TrinketCallback.super.forEachTrinketModifier(stack, slot, entity, slotIdentifier, consumer);
		consumer.accept(this.pinSlotModifier,new AttributeModifier(slotIdentifier.withSuffix("/enamel/addpin"),1,AttributeModifier.Operation.ADD_VALUE));
	}
}
