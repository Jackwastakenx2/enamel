package io.github.Jackwastakenx2.enamel;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnamelAttachments {
	public static AttachmentType<List<ItemStack>> PIN_ATTACHMENT = AttachmentRegistry.create(
		Identifier.fromNamespaceAndPath(Enamel.MOD_ID,"pins"),
		listBuilder -> listBuilder
			.initializer(ArrayList<ItemStack>::new)
			.persistent(ItemStack.CODEC.listOf())
			.syncWith(
				ByteBufCodecs.collection(
					ArrayList::new,
					ItemStack.STREAM_CODEC,
					16
				),
				AttachmentSyncPredicate.targetOnly()
			)
		);
	public static void initialize() {
		Enamel.LOGGER.info("initializing {}'s Attachments",Enamel.MOD_ID);
	}
}
