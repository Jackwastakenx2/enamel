package io.github.Jackwastakenx2.enamel.datagen;

import eu.pb4.trinkets.impl.TrinketsMain;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, @Nullable BlockTagsProvider blockTagsProvider) {super(output, registryLookupFuture, blockTagsProvider);}
	public static final TagKey<Item> PIN_ITEMS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TrinketsMain.MOD_ID, "offhand/pin"));
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		LOGGER.info(PIN_ITEMS.toString());
	}
}
