package tigereye.entity;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class EntityAIBeg2 extends EntityAIBase
{
    private EntitySummonedWolf theWolf;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;
    
    public EntityAIBeg2(final EntitySummonedWolf par1EntityWolf, final float par2) {
        this.theWolf = par1EntityWolf;
        this.worldObject = par1EntityWolf.worldObj;
        this.minPlayerDistance = par2;
        this.setMutexBits(2);
    }
    
    public boolean shouldExecute() {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity((Entity)this.theWolf, (double)this.minPlayerDistance);
        return this.thePlayer != null && this.hasPlayerGotBreedingItemInHand(this.thePlayer);
    }
    
    public boolean continueExecuting() {
        return this.thePlayer.isEntityAlive() && this.theWolf.getDistanceSqToEntity((Entity)this.thePlayer) <= this.minPlayerDistance * this.minPlayerDistance && this.field_75384_e > 0 && this.hasPlayerGotBreedingItemInHand(this.thePlayer);
    }
    
    public void startExecuting() {
        this.theWolf.func_70918_i(true);
        this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
    }
    
    public void resetTask() {
        this.theWolf.func_70918_i(false);
        this.thePlayer = null;
    }
    
    public void updateTask() {
        this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0f, (float)this.theWolf.getVerticalFaceSpeed());
        --this.field_75384_e;
    }
    
    private boolean hasPlayerGotBreedingItemInHand(final EntityPlayer par1EntityPlayer) {
        final ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        return itemstack != null && this.theWolf.isBreedingItem(itemstack);
    }
}
