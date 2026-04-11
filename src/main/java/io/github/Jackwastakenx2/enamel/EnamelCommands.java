package io.github.Jackwastakenx2.enamel;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class EnamelCommands {
	private static int EquipPin(CommandContext<CommandSourceStack> context, int slot) throws CommandSyntaxException {
		var source = context.getSource();
		var player = source.getPlayerOrException();
		var pinArray = new ArrayList<>(player.getAttachedOrCreate(EnamelAttachments.PIN_ATTACHMENT));

		var pinPoints = player.getAttribute(EnamelAttributes.PIN_POINTS).getValue();
		double usedPoints = 0;
		for (ItemStack pin: pinArray) {
			if (pin.has(EnamelComponents.PIN_COMPONENT)) {
				var component = pin.get(EnamelComponents.PIN_COMPONENT);
				usedPoints  += component.cost();
			} else {
				pinArray.remove(pin);
				player.drop(pin,true,false);
			}
		}
		if (usedPoints < pinPoints) {
			var item = player.getItemInHand(InteractionHand.MAIN_HAND);
			if (item.has(EnamelComponents.PIN_COMPONENT)) {
				if (slot==-1) {
					pinArray.add(item);
				} else {
					pinArray.add(slot,item);
				}
				player.setAttached(EnamelAttachments.PIN_ATTACHMENT,pinArray);
				player.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
				return 1;
			} else {
				source.sendFailure(Component.literal("Not a pin!"));

			}
		} else {
			source.sendFailure(Component.literal("Too many pins equipped (%s/%s)".formatted(usedPoints,pinPoints)));
		}
		return 0;
	}
	private static int UnequipPin(CommandContext<CommandSourceStack> context, int slot) throws CommandSyntaxException {
		var source = context.getSource();
		var player = source.getPlayerOrException();
		var pinArray = new ArrayList<>(player.getAttachedOrCreate(EnamelAttachments.PIN_ATTACHMENT));
		ItemStack pinItem;
		try {
			if (slot==-1) {
				pinItem = pinArray.removeLast();
			} else {
				pinItem = pinArray.remove(slot);
			}
		} catch (IndexOutOfBoundsException e) {
			source.sendFailure(Component.literal("Invalid Slot!"));
			return 0;
		}
		player.getInventory().add(pinItem);
		player.setAttached(EnamelAttachments.PIN_ATTACHMENT,pinArray);
		return 1;
	}
	private static int UnequipPins(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		var source = context.getSource();
		var player = source.getPlayerOrException();
		var pinArray = new ArrayList<>(player.getAttachedOrCreate(EnamelAttachments.PIN_ATTACHMENT));
		Enamel.LOGGER.info(String.valueOf(pinArray));
		for (int i = 0; !pinArray.isEmpty(); i++) {
			ItemStack pin = pinArray.removeFirst();
			player.getInventory().add(pin);
		}
		Enamel.LOGGER.info(String.valueOf(pinArray));
		player.setAttached(EnamelAttachments.PIN_ATTACHMENT,pinArray);
		return 1;
	}
	public static void initialize() {
		Enamel.LOGGER.info("Initializing {} Commands!",Enamel.MOD_ID);
		CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, selection) -> {
			dispatcher.register(Commands.literal("equipPin")
				.executes(context -> EquipPin(context,-1))
				.then(Commands.argument("slot",IntegerArgumentType.integer(0,15))
					.executes(context -> EquipPin(context,IntegerArgumentType.getInteger(context,"slot")))));
			dispatcher.register(Commands.literal("unequipPin")
				.executes(context -> UnequipPin(context,-1))
				.then(Commands.argument("slot", IntegerArgumentType.integer(0,15))
					.executes(context -> UnequipPin(context,IntegerArgumentType.getInteger(context,"slot")))));
			dispatcher.register(Commands.literal("unequipPins")
				.executes(EnamelCommands::UnequipPins));
		}));
	}
}
