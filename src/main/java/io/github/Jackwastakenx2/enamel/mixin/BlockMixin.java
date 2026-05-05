package io.github.Jackwastakenx2.enamel.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import eu.pb4.trinkets.api.TrinketsApi;
import io.github.Jackwastakenx2.enamel.EnamelItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

//what... no, i DIDNT copy this from anywhere...
//(credits to @UltrusBot on modrinth, for the mod i DIDNT steal from)
//(https://modrinth.com/mod/smeltingtouch)
@Mixin(Block.class)
public class BlockMixin {

	@ModifyReturnValue(
		method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemInstance;)Ljava/util/List;",
		at = @At(value = "TAIL"))
	private static List<ItemStack> smeltDroppedStacks(List<ItemStack> originalDrops, BlockState state, ServerLevel level, BlockPos pos, BlockEntity blockEntity, @Nullable Entity breaker, ItemInstance tool) {
		List<ItemStack> drops = new ArrayList<>();

		if ((!(breaker instanceof LivingEntity)) || !TrinketsApi.getAttachment((LivingEntity) breaker).isEquipped(EnamelItems.FURNACE_PIN)) {
			return originalDrops;
		}

		for (ItemStack drop : originalDrops) {
			var input = new SingleRecipeInput(drop);
			var recipe = level.recipeAccess().getRecipeFor(RecipeType.SMELTING, input, level);

			if (recipe.isPresent()) {
				ItemStack smelted = recipe.get().value().assemble(input);
				smelted.setCount(drop.getCount());
				drops.add(smelted);
			} else {
				drops.add(drop);
			}
		}

		return drops;
	}
}
