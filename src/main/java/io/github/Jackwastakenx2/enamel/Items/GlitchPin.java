package io.github.Jackwastakenx2.enamel.Items;

import eu.pb4.trinkets.api.TrinketSlotAccess;
import io.github.Jackwastakenx2.enamel.PinItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public class GlitchPin extends PinItem {
	public GlitchPin(Properties properties) {
		super(properties);
	}

	@Override
	public void forEachTrinketModifier(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity, Identifier slotIdentifier, BiConsumer<Holder<Attribute>, AttributeModifier> consumer) {
		Identifier id = slotIdentifier.withSuffix("/enamel/glitch");
		consumer.accept(Attributes.MAX_HEALTH,new AttributeModifier(id,-1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
		super.forEachTrinketModifier(stack, slot, entity, slotIdentifier, consumer);
	}
}
