package io.github.Jackwastakenx2.enamel.datagen;

import io.github.Jackwastakenx2.enamel.EnamelItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {
	public ModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerators) {
		itemModelGenerators.generateFlatItem(EnamelItems.PLUSH_PIN, ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.COPPER_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.IRON_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.GOLD_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.DIAMOND_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.NETHERITE_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.ERROR_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.ROCK_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.HEALTH_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.SPEED_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.LONGNAIL,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(EnamelItems.FURNACE_PIN,ModelTemplates.FLAT_ITEM);




	}
}
