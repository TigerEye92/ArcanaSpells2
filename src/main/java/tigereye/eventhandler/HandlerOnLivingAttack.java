package tigereye.eventhandler;

import net.minecraftforge.event.entity.living.*;
import tigereye.potion.*;
import net.minecraft.util.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;
import cpw.mods.fml.common.eventhandler.*;

public class HandlerOnLivingAttack
{
    @SubscribeEvent
    public void onLivingAttack(final LivingAttackEvent event) {
        if (event.entityLiving.worldObj.isRemote) {
            return;
        }
        final Entity attacker = event.source.getEntity();
        if (attacker != null) {
            if (event.entityLiving.isPotionActive(ModPotions.projectileImmunity) && event.source.isProjectile()) {
                event.setCanceled(true);
            }
            if (event.entityLiving.isPotionActive(ModPotions.knockbackImmunity)) {
                event.entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
            }
            else {
                event.entityLiving.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0);
            }
            if (event.entityLiving.isPotionActive(ModPotions.fireShield) && attacker instanceof EntityLivingBase) {
                final int amplifier = event.entityLiving.getActivePotionEffect(ModPotions.fireShield).getAmplifier() + 1;
                attacker.setFire(4 * amplifier);
                attacker.attackEntityFrom(DamageSource.onFire, (float)amplifier);
            }
            else if (event.entityLiving.isPotionActive(ModPotions.earthShield)) {
                final int amplifier = event.entityLiving.getActivePotionEffect(ModPotions.earthShield).getAmplifier();
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 80, amplifier));
                event.entityLiving.addPotionEffect(new PotionEffect(ModPotions.knockbackImmunity.getId(), 80));
            }
            else if (event.entityLiving.isPotionActive(ModPotions.waterShield)) {
                final int amplifier = event.entityLiving.getActivePotionEffect(ModPotions.waterShield).getAmplifier();
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 80, amplifier));
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 160));
            }
            else if (event.entityLiving.isPotionActive(ModPotions.stormShield)) {
                final int amplifier = event.entityLiving.getActivePotionEffect(ModPotions.stormShield).getAmplifier();
                event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 80, amplifier));
                event.entityLiving.addPotionEffect(new PotionEffect(ModPotions.projectileImmunity.getId(), 80));
            }
            else if (event.entityLiving.isPotionActive(ModPotions.frostShield) && attacker instanceof EntityLivingBase) {
                final int amplifier = event.entityLiving.getActivePotionEffect(ModPotions.frostShield).getAmplifier();
                ((EntityLivingBase)attacker).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 80, amplifier));
                ((EntityLivingBase)attacker).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 80, amplifier * 2));
            }
        }
    }
}
