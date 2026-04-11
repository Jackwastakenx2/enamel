package io.github.Jackwastakenx2.enamel.mixin;

import io.github.Jackwastakenx2.enamel.Enamel;
import io.github.Jackwastakenx2.enamel.EnamelAttachments;
import io.github.Jackwastakenx2.enamel.EnamelComponents;
import io.github.Jackwastakenx2.enamel.attributes.PseudoAttributeModifier;
import io.github.Jackwastakenx2.enamel.component.PinComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(Player.class)
public abstract class PlayerMixin extends Avatar implements ContainerUser {
	protected PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	private static List<ItemStack> lastPins;

	@Inject(method = "tick", at = @At(value = "HEAD"))
	public void PinAttributeTickMixin(CallbackInfo ci) {
		Player player = (Player) (Object) this;
		if (!player.level().isClientSide()) {
			var pins = player.getAttachedOrCreate(EnamelAttachments.PIN_ATTACHMENT);
			if (!pins.equals(lastPins)) {
				Enamel.LOGGER.info("??? {} {}", pins, lastPins);
				List<ItemStack> newPins = new ArrayList<>(16);
				for (int i = 0; i < 16; i++) {
					var ID = Identifier.fromNamespaceAndPath(Enamel.MOD_ID, "pin_modifier_%s".formatted(i));
					ItemStack lastPin = ItemStack.EMPTY;
					if (lastPins != null && i < lastPins.size()) {
						lastPin = lastPins.get(i);
					}
					var lastComponent = lastPin.get(EnamelComponents.PIN_COMPONENT);
					if (lastComponent != null) {
						for (PinComponent.Entry entry : lastComponent.modifiers()) {
							AttributeInstance attrInst = player.getAttribute(entry.attribute());
							if (attrInst != null) {
								attrInst.removeModifier(ID);
							}
						}
					}
					ItemStack pin = ItemStack.EMPTY;
					if (i < pins.size()) {
						pin = pins.get(i);
						newPins.add(pin.copy());
					}
					var component = pin.get(EnamelComponents.PIN_COMPONENT);
					if (component != null) {
						for (PinComponent.Entry entry : component.modifiers()) {
							AttributeInstance attrInst = player.getAttribute(entry.attribute());
							PseudoAttributeModifier modifier = entry.modifier();
							AttributeModifier attrMod = new AttributeModifier(ID, modifier.amount(), modifier.operation());
							if (attrInst != null) {
								double before = attrInst.getValue();
								attrInst.addOrUpdateTransientModifier(attrMod);

								if (before!=attrInst.getValue()) {
									this.onAttributeUpdated(entry.attribute());
								}
							}
						}
					}
				}
				Enamel.LOGGER.info("{}",newPins);
				lastPins = newPins;
			}
		}
	}
}
