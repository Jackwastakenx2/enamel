package io.github.Jackwastakenx2.enamel;

import io.github.Jackwastakenx2.enamel.Items.BadgePin;
import io.github.Jackwastakenx2.enamel.Items.GlitchPin;
import io.github.Jackwastakenx2.enamel.Items.OneAttributePin;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class EnamelItems {
	public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
		// Create the item key.
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Enamel.MOD_ID, name));

		// Create the item instance.
		T item = itemFactory.apply(settings.setId(itemKey));

		// Register the item.
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);
		return item;
	}
	public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
		BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "creative_tab")
	);
	public static final PinItem PLUSH_PIN = register("doll_pin", OneAttributePin.SetAttribute(Attributes.SCALE,-0.7, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem HEALTH_PIN = register("health_pin", OneAttributePin.SetAttribute(Attributes.MAX_HEALTH,4, AttributeModifier.Operation.ADD_VALUE),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,2)
	);
	public static final PinItem SPEED_PIN = register("speed_pin", OneAttributePin.SetAttribute(Attributes.MOVEMENT_SPEED,0.3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,2)
	);
	public static final PinItem ROCK_PIN = register("rock_pin", OneAttributePin.SetAttribute(Attributes.KNOCKBACK_RESISTANCE,0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem LONGNAIL = register("longsword_pin", OneAttributePin.SetAttribute(Attributes.ENTITY_INTERACTION_RANGE,0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem FEST_PIN = register("modfest_pin",PinItem::new,new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem COPPER_PIN = register("copper_pin", BadgePin.setArmor(1),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem IRON_PIN = register("iron_pin", BadgePin.setArmor(2),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,2)
	);
	public static final PinItem GOLD_PIN = register("gold_pin", BadgePin.setArmor(1),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem DIAMOND_PIN = register("diamond_pin", BadgePin.setArmor(3,2),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,3)
	);
	public static final PinItem NETHERITE_PIN = register("netherite_pin", BadgePin.setArmor(3,3),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,4)
	);
	public static final PinItem ERROR_PIN = register("glitch_pin", GlitchPin::new,new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,-4)
	);
	public static final PinItem FURNACE_PIN = register("furnace_pin",PinItem::new,new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,2)
	);
	public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(PLUSH_PIN))
		.title(Component.translatable("creativeTab.enamel"))
		.displayItems((_, output) -> {
			output.accept(COPPER_PIN);
			output.accept(IRON_PIN);
			output.accept(GOLD_PIN);
			output.accept(DIAMOND_PIN);
			output.accept(NETHERITE_PIN);
			output.accept(HEALTH_PIN);
			output.accept(SPEED_PIN);
			output.accept(FURNACE_PIN);
			output.accept(ROCK_PIN);
			output.accept(LONGNAIL);
			output.accept(PLUSH_PIN);
			output.accept(ERROR_PIN);
			output.accept(FEST_PIN);
		})
		.build();
	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
	}
}
