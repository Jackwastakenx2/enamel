package io.github.Jackwastakenx2.enamel;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class EnamelAttachments {
	public static final AttachmentType<Integer> PP_ATTACHMENT = AttachmentRegistry.create(
		Identifier.fromNamespaceAndPath(Enamel.MOD_ID,"pin_point_attachment"),
		integerBuilder -> integerBuilder
			.initializer(() -> 0)
			.syncWith(
				ByteBufCodecs.INT,
				AttachmentSyncPredicate.targetOnly()
			)
	);
	public static void initialize(){}
}
