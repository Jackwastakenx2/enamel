package io.github.Jackwastakenx2.enamel.mixin;

import io.github.Jackwastakenx2.enamel.Enamel;
import io.github.Jackwastakenx2.enamel.EnamelAttachments;
import io.github.Jackwastakenx2.enamel.EnamelAttributes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends Avatar implements ContainerUser {
	protected PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
		super(type, level);
	}

	@ModifyVariable(method = "hurtServer", at = @At(value = "HEAD"), argsOnly = true, name = "damage")
	public float PincumberDamage(float damage) {
		var player = (Player)(Object) this;
		int used = player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
		AttributeInstance attr =  player.getAttribute(EnamelAttributes.PIN_POINTS);
		if (attr != null && used > attr.getValue()) {
			return damage*1.5F;
		}
		return damage;
	}
//	as far as i could tell, this failed to work... sadge...
//	@ModifyVariable(method = "causeFoodExhaustion", at = @At(value = "HEAD"), argsOnly = true, name = "amount")
//	public float PincumberHunger(float amount) {
//		var player = (Player)(Object) this;
//		int used = player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
//		AttributeInstance attr =  player.getAttribute(EnamelAttributes.PIN_POINTS);
//		if (attr != null && used > attr.getValue()) {
//			return amount*1.5F;
//		}
//		return amount;
//	}
	@Unique
	private static final Identifier SPEED_MODIFIER_PINCUMBER = Identifier.fromNamespaceAndPath(Enamel.MOD_ID,"pincumber_speed");
	@Inject(method = "aiStep",at=@At(value = "HEAD"))
	public void PincumberTick(CallbackInfo ci) {
		var player = (Player)(Object) this;
		int used = player.getAttachedOrCreate(EnamelAttachments.PP_ATTACHMENT);
		AttributeInstance attr =  player.getAttribute(EnamelAttributes.PIN_POINTS);
		AttributeInstance speed = this.getAttribute(Attributes.MOVEMENT_SPEED);
		if (speed != null) {
			if (attr != null && used > attr.getValue()) {
				if (!speed.hasModifier(SPEED_MODIFIER_PINCUMBER)) {
					speed.addTransientModifier(new AttributeModifier(SPEED_MODIFIER_PINCUMBER, (double) -1 /3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
				}
			} else if (speed.hasModifier(SPEED_MODIFIER_PINCUMBER)) {
				speed.removeModifier(SPEED_MODIFIER_PINCUMBER);
			}
		}

	}
}
