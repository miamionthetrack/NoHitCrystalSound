package dev.miami.nohitcrystalsound.mixin;

import dev.miami.nohitcrystalsound.HitCrystalTracker;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public class SoundManagerMixin {
	@Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"), cancellable = true)
	private void onPlay(SoundInstance sound, CallbackInfo ci) {
		if (HitCrystalTracker.suppressCrystalAttackSound) {
			if (sound.getId().toString().equals("minecraft:entity.player.attack.weak")||
					sound.getId().toString().equals("minecraft:entity.player.attack.knockback")||
					sound.getId().toString().equals("minecraft:entity.player.attack.strong")) {
				ci.cancel();
			}
		}
	}
}