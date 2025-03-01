package tigereye.spell.targeted;

import tigereye.spell.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;

public class SpellTeleport extends Spell implements ISpellTargeted
{
    private final int[] distances;
    
    public SpellTeleport(final int par1) {
        super(par1);
        this.distances = new int[] { 3, 5 };
        this.setName("teleport");
    }
    
    @Override
    public int getManaCost() {
        return 3;
    }
    
    @Override
    public short getCooldown() {
        return 200;
    }
    
    @Override
    public short getMaxLevel() {
        return (short)this.distances.length;
    }
    
    @Override
    public String getParticleName() {
        return "portal";
    }
    
    @Override
    public double getParticleAmount() {
        return 1.0;
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        final int distance = this.distances[this.getNormalizedLevel(par1Level) - 1];
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.renderViewEntity.rayTrace((double)distance, 1.5f) != null) {
            par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
            final int blockHitX = mc.renderViewEntity.rayTrace((double)distance, 1.4f).blockX;
            final int blockHitY = mc.renderViewEntity.rayTrace((double)distance, 1.4f).blockY;
            final int blockHitZ = mc.renderViewEntity.rayTrace((double)distance, 1.4f).blockZ;
            par3EntityPlayer.setPositionAndUpdate(blockHitX - 0.9, (double)(blockHitY + 3), blockHitZ - 0.9);
            par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        }
    }
}
