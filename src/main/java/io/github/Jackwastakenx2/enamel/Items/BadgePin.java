package io.github.Jackwastakenx2.enamel.Items;

import eu.pb4.trinkets.api.TrinketSlotAccess;
import io.github.Jackwastakenx2.enamel.EnamelItems;
import io.github.Jackwastakenx2.enamel.PinItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class BadgePin extends PinItem {
	private int armor;
	private int toughness;
	public BadgePin(int armor,int toughness, Properties properties) {
		super(properties);
		this.toughness = toughness;
		this.armor = armor;
	}
	public static Function<Properties,BadgePin> setArmor(int armor) {
		return (Properties properties) -> new BadgePin(armor,0,properties);
	}
	public static Function<Properties,BadgePin> setArmor(int armor,int toughness) {
		return (Properties properties) -> new BadgePin(armor,toughness,properties);
	}

	@Override
	public void forEachTrinketModifier(ItemStack stack, TrinketSlotAccess slot, LivingEntity entity, Identifier slotIdentifier, BiConsumer<Holder<Attribute>, AttributeModifier> consumer) {
		Identifier id = slotIdentifier.withSuffix("/enamel/badge");
		consumer.accept(Attributes.ARMOR,new AttributeModifier(id,this.armor, AttributeModifier.Operation.ADD_VALUE));
		if (toughness!=0) {
			consumer.accept(Attributes.ARMOR_TOUGHNESS,new AttributeModifier(id,this.toughness, AttributeModifier.Operation.ADD_VALUE));

		}
		super.forEachTrinketModifier(stack, slot, entity, slotIdentifier, consumer);
	}
}
