package io.github.Jackwastakenx2.enamel.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.Jackwastakenx2.enamel.attributes.PseudoAttributeModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.List;
import java.util.function.Consumer;

public record PinComponent(int cost, List<Entry> modifiers) implements TooltipProvider {
	public static final Codec<PinComponent> CODEC = RecordCodecBuilder.create(
		builder -> builder.group(
			Codec.INT.optionalFieldOf("cost",0).forGetter(PinComponent::cost),
			Entry.CODEC.listOf().fieldOf("modifiers").forGetter(PinComponent::modifiers)
		).apply(builder,PinComponent::new)
	);
//temp.
	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
		if (this.cost==1) {
			consumer.accept(Component.translatable("item.enamel.modifiers.pin.single",this.cost).withStyle(ChatFormatting.GRAY));
		} else {
			consumer.accept(Component.translatable("item.enamel.modifiers.pin",this.cost).withStyle(ChatFormatting.GRAY));
		}
		for (Entry entry : this.modifiers) {
			Holder<Attribute> attribute = entry.attribute();
			PseudoAttributeModifier modifier = entry.modifier();
			double amount = modifier.amount();

			double displayAmount;
			if (modifier.operation() == AttributeModifier.Operation.ADD_MULTIPLIED_BASE || modifier.operation() == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
				displayAmount = amount * 100.0;
			} else if (attribute.is(Attributes.KNOCKBACK_RESISTANCE)) {
				displayAmount = amount * 10.0;
			} else {
				displayAmount = amount;
			}

			if (amount > 0.0) {
				consumer.accept(
					Component.translatable(
							"attribute.modifier.plus." + modifier.operation().id(),
							ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount),
							Component.translatable(attribute.value().getDescriptionId())
						)
						.withStyle(attribute.value().getStyle(true))
				);
			} else if (amount < 0.0) {
				consumer.accept(
					Component.translatable(
							"attribute.modifier.take." + modifier.operation().id(),
							ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(-displayAmount),
							Component.translatable(attribute.value().getDescriptionId())
						)
						.withStyle(attribute.value().getStyle(false))
				);
			}
		}
	}
	public record Entry(Holder<Attribute> attribute, PseudoAttributeModifier modifier) {
		public static final Codec<Entry> CODEC = RecordCodecBuilder.create(
			i -> i.group(
					Attribute.CODEC.fieldOf("type").forGetter(Entry::attribute),
					PseudoAttributeModifier.MAP_CODEC.forGetter(Entry::modifier)
				)
				.apply(i, Entry::new)
		);
		public static final StreamCodec<RegistryFriendlyByteBuf, Entry> STREAM_CODEC = StreamCodec.composite(
			Attribute.STREAM_CODEC,
			Entry::attribute,
			PseudoAttributeModifier.STREAM_CODEC,
			Entry::modifier,
			Entry::new
		);
	}
}
