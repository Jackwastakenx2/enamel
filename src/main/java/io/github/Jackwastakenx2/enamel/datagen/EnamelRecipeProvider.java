package io.github.Jackwastakenx2.enamel.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;
import static io.github.Jackwastakenx2.enamel.EnamelItems.*;
public class EnamelRecipeProvider extends FabricRecipeProvider {
	public EnamelRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		return new RecipeProvider(registries,output) {
			private void badgeCrafting(Item output,Item input) {
				shaped(RecipeCategory.TOOLS,output)
					.pattern("xxx")
					.pattern("rbr")
					.define('x',input)
					.define('r',Items.RESIN_BRICK)
					.define('b',PIN_BASE)
					.unlockedBy(getHasName(input),has(input))
					.save(this.output);
			}
			private void sidePinCraft(Item output, ItemLike inOne, ItemLike inTwo) {
				shaped(RecipeCategory.TOOLS,output)
					.pattern("xyx")
					.pattern("rbr")
					.define('x',inOne)
					.define('y',inTwo)
					.define('r',Items.RESIN_BRICK)
					.define('b',PIN_BASE)
					.unlockedBy(getHasName(inOne),has(inOne))
					.save(this.output);
			}
			public void buildRecipes() {
				HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
				shaped(RecipeCategory.MISC, PIN_BASE)
					.pattern("I")
					.pattern("i")
					.define('I', Items.IRON_INGOT)
					.define('i', Items.IRON_NUGGET)
					.group("pin_base")
					.unlockedBy(getHasName(Items.IRON_INGOT),has(Items.IRON_INGOT))
					.save(output);
				badgeCrafting(COPPER_PIN,Items.COPPER_INGOT);
				badgeCrafting(IRON_PIN,Items.IRON_INGOT);
				badgeCrafting(GOLD_PIN,Items.GOLD_INGOT);
				badgeCrafting(DIAMOND_PIN,Items.DIAMOND);
				netheriteSmithing(DIAMOND_PIN,RecipeCategory.TOOLS,NETHERITE_PIN);
				sidePinCraft(FEST_PIN, Items.MAGENTA_WOOL,Items.PINK_PETALS);
				//soup for the soul /ref
				sidePinCraft(HEALTH_PIN,Items.GOLD_INGOT,Items.BEETROOT);
				sidePinCraft(FURNACE_PIN,Items.COMPARATOR,Items.FURNACE);
				sidePinCraft(SPEED_PIN,Items.SUGAR,Items.BREEZE_ROD);
				//build a doll workshop...
				//hopefully i give atleast one other doll a '*hm.*' moment
				//(like what wybad did to me, lolz)
				shaped(RecipeCategory.TOOLS, PLUSH_PIN)
					.pattern("xyx")
					.pattern("rbr")
					.define('x',ItemTags.WOOL)
					.define('y',Items.PAPER)
					.define('r',Items.RESIN_BRICK)
					.define('b',PIN_BASE)
					.unlockedBy(getHasName(Items.WHITE_WOOL),has(ItemTags.WOOL))
					.save(this.output);
				shaped(RecipeCategory.TOOLS,ROCK_PIN)
					.pattern("sss")
					.pattern("rbr")
					.define('s', ItemTags.STONE_TOOL_MATERIALS)
					.define('r',Items.RESIN_BRICK)
					.define('b',PIN_BASE)
					.unlockedBy(getHasName(Items.STONE),has(ItemTags.STONE_TOOL_MATERIALS))
					.save(this.output);
				shaped(RecipeCategory.TOOLS,LONGNAIL)
					.pattern("sss")
					.pattern("rbr")
					.define('s',Items.IRON_SWORD)
					.define('r',Items.RESIN_BRICK)
					.define('b',PIN_BASE)
					.unlockedBy(getHasName(Items.IRON_SWORD),has(Items.IRON_SWORD))
					.save(this.output);
			}
		};
	}

	@Override
	public String getName() {
		return "EnamelRecipeProvider";
	}
}
