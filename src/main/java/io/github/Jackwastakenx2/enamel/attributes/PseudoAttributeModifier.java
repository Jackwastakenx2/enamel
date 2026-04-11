package io.github.Jackwastakenx2.enamel.attributes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

//long story short: pins have dynamic attribute ids to support wearing duplicates
//SOOOO I had to kill the id held here LMAO
public record PseudoAttributeModifier(double amount, AttributeModifier.Operation operation) {
	public static final MapCodec<PseudoAttributeModifier> MAP_CODEC = RecordCodecBuilder.mapCodec(
		i -> i.group(
				Codec.DOUBLE.fieldOf("amount").forGetter(PseudoAttributeModifier::amount),
				AttributeModifier.Operation.CODEC.fieldOf("operation").forGetter(PseudoAttributeModifier::operation)
			)
			.apply(i, PseudoAttributeModifier::new)
	);
	public static final Codec<PseudoAttributeModifier> CODEC = MAP_CODEC.codec();
	public static final StreamCodec<ByteBuf, PseudoAttributeModifier> STREAM_CODEC = StreamCodec.composite(
		ByteBufCodecs.DOUBLE,
		PseudoAttributeModifier::amount,
		AttributeModifier.Operation.STREAM_CODEC,
		PseudoAttributeModifier::operation,
		PseudoAttributeModifier::new
	);
}
