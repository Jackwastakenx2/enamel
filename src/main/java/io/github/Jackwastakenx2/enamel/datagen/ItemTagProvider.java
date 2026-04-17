package io.github.Jackwastakenx2.enamel.datagen;

import eu.pb4.trinkets.impl.TrinketsMain;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

import static io.github.Jackwastakenx2.enamel.EnamelItems.*;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public static final TagKey<Item> PIN_ITEMS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TrinketsMain.MOD_ID, "offhand/pin"));
	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
		LOGGER.info(PIN_ITEMS.toString());
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		valueLookupBuilder(PIN_ITEMS)
			.add(PLUSH_PIN)
			.add(COPPER_PIN)
			.add(IRON_PIN)
			.add(DIAMOND_PIN)
			.add(NETHERITE_PIN)
			.add(GOLD_PIN)
			.add(ERROR_PIN)
			.add(HEALTH_PIN)
			.add(LONGNAIL)
			.add(SPEED_PIN)
			.add(ROCK_PIN)
			.add(FURNACE_PIN)
			.setReplace(false);
	}
}
