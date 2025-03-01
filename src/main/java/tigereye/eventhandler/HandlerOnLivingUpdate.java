package tigereye.eventhandler;

import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.player.*;
import tigereye.itemblock.*;
import net.minecraft.item.*;
import tigereye.spell.*;
import cpw.mods.fml.common.eventhandler.*;

public class HandlerOnLivingUpdate
{
    @SubscribeEvent
    public void OnLivingUpdate(final LivingEvent.LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)event.entity;
            final ItemStack heldItem = player.getHeldItem();
            if (heldItem != null && heldItem.getItem() == ModItemsBlocks.spell_book && heldItem.stackTagCompound != null) {
                final double particleChance = player.worldObj.rand.nextDouble();
                final short id = heldItem.stackTagCompound.getShort("id");
                final Spell spell = Spells.spellList[id];
                if (particleChance < spell.getParticleAmount()) {
                    final double d0 = player.worldObj.rand.nextGaussian() * 0.02;
                    final double d2 = player.worldObj.rand.nextGaussian() * 0.02;
                    final double d3 = player.worldObj.rand.nextGaussian() * 0.02;
                    player.worldObj.spawnParticle(spell.getParticleName(), player.posX + player.worldObj.rand.nextFloat() * player.width * 2.0f - player.width, player.posY - 1.5 + player.worldObj.rand.nextFloat() * player.height, player.posZ + player.worldObj.rand.nextFloat() * player.width * 2.0f - player.width, d0, d2, d3);
                }
            }
        }
    }
}
