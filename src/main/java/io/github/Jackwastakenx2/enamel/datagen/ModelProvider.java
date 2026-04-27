package io.github.Jackwastakenx2.enamel.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

import static io.github.Jackwastakenx2.enamel.EnamelItems.*;

public class ModelProvider extends FabricModelProvider {
	public ModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerators) {
		itemModelGenerators.generateFlatItem(PLUSH_PIN, ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(COPPER_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(IRON_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(GOLD_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(DIAMOND_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(NETHERITE_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(ERROR_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(ROCK_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(HEALTH_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(SPEED_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(LONGNAIL,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(FURNACE_PIN,ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(FEST_PIN,ModelTemplates.FLAT_ITEM);



	}
}
