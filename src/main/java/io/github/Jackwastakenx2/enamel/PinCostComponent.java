package io.github.Jackwastakenx2.enamel;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record PinCostComponent(int cost) implements TooltipProvider {
	public static final Codec<Integer> CODEC = Codec.INT;
	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
		consumer.accept(Component.translatable("enamel.pin_points", this.cost).withStyle(ChatFormatting.DARK_GRAY));
	}
}
