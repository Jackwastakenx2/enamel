package io.github.Jackwastakenx2.enamel.Items;

import eu.pb4.trinkets.api.TrinketSlotAccess;
import io.github.Jackwastakenx2.enamel.PinItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class OneAttributePin extends PinItem {
	private final Holder<Attribute> attribute;
	private final double value;
	private final AttributeModifier.Operation operation;
	public OneAttributePin(Holder<Attribute> attribute, double value, AttributeModifier.Operation operation, Properties properties) {
		super(properties);
		this.attribute = attribute;
		this.value = value;
		this.operation = operation;
	}

	public static Function<Properties, OneAttributePin> SetAttribute(Holder<Attribute> attribute, double value, AttributeModifier.Operation operation) {
		return (properties -> new OneAttributePin(attribute,value,operation,properties));
	}

	@Override
	public void forEachTrinketModifier(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity, Identifier slotIdentifier, BiConsumer<Holder<Attribute>, AttributeModifier> consumer) {
		consumer.accept(attribute,new AttributeModifier(slotIdentifier.withSuffix("/enamel/generic"),this.value, operation));

		super.forEachTrinketModifier(stack, slot, entity, slotIdentifier, consumer);
	}
}
