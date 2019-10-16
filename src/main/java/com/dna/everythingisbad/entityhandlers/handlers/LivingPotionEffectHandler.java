package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.handlers.PotionEffectHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class LivingPotionEffectHandler extends LivingHandlerBase {
    @Override
    public void livingTick(EntityLivingBase livingBase) {
        super.livingTick(livingBase);
        boolean highness_active = livingBase.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion());
        boolean common_cold_active = livingBase.isPotionActive(ModPotions.POTION_COMMON_COLD.getPotion());
        boolean adrenaline_active = livingBase.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion());

        if(highness_active){
            if (livingBase instanceof EntityPlayerMP) {
                // Casting to entityplayermp
                EntityPlayerMP mp = (EntityPlayerMP)livingBase;
                int highness_duration = mp.getEntityData().getInteger("highness_duration");
                //Main.logger.info("Highness duration in client handler: " + highness_duration);
                PotionEffectHandler.livingEntityHighnessActive(mp, highness_duration);
            }else if (livingBase instanceof EntityCreature){
                int highness_duration = livingBase.getEntityData().getInteger("highness_duration");
                PotionEffectHandler.livingEntityHighnessActive(livingBase, highness_duration);
            }
        }

        if (common_cold_active && !(livingBase instanceof EntityPlayerSP)){
            int common_cold_duration = livingBase.getEntityData().getInteger("common_cold_duration");
            PotionEffectHandler.livingEntityCommonColdActive(livingBase,common_cold_duration);
        }

        if (adrenaline_active && !(livingBase instanceof EntityPlayerSP)){
            int adrenaline_duration = livingBase.getEntityData().getInteger("adrenaline_duration");
            PotionEffectHandler.livingEntityAdrenalineActive(livingBase,adrenaline_duration);
        }
    }
}
