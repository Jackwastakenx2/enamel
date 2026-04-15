package io.github.Jackwastakenx2.enamel;

import eu.pb4.trinkets.api.component.TrinketDataComponents;
import eu.pb4.trinkets.api.component.TrinketsAttributeModifiersComponent;
import eu.pb4.trinkets.impl.TrinketSlot;
import io.github.Jackwastakenx2.enamel.Items.BadgePin;
import io.github.Jackwastakenx2.enamel.Items.GlitchPin;
import io.github.Jackwastakenx2.enamel.Items.PlushPin;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
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
//	public static PinItem TEST_PIN = register("test_pin", PinItem::new, new Item.Properties()
//		.component(EnamelComponents.PIN_COST_COMPONENT,1));
	public static final PinItem PLUSH_PIN = register("doll_pin", PlushPin::new,new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem COPPER_PIN = register("copper_pin", BadgePin.setArmor(1),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,1)
	);
	public static final PinItem IRON_PIN = register("iron_pin", BadgePin.setArmor(2),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,2)
	);
	public static final PinItem DIAMOND_PIN = register("diamond_pin", BadgePin.setArmor(3,2),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,3)
	);
	public static final PinItem NETHERITE_PIN = register("netherite_pin", BadgePin.setArmor(3,3),new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,4)
	);
	public static final PinItem ERROR_PIN = register("glitch_pin", GlitchPin::new,new Item.Properties()
		.component(EnamelComponents.PIN_COST_COMPONENT,-4));
	public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricCreativeModeTab.builder()
		.icon(() -> new ItemStack(PLUSH_PIN))
		.title(Component.translatable("creativeTab.enamel"))
		.displayItems((params, output) -> {
			output.accept(PLUSH_PIN);
			output.accept(COPPER_PIN);
			output.accept(IRON_PIN);
			output.accept(DIAMOND_PIN);
			output.accept(NETHERITE_PIN);
			output.accept(ERROR_PIN);
		})
		.build();
	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);
	}
}
