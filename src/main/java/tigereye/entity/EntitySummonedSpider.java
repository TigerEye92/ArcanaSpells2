package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;

public class EntitySummonedSpider extends EntitySummoned
{
    public EntitySummonedSpider(final World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.2, true));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(1.4f, 0.9f);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        final float attackDamage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), attackDamage);
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)new Byte((byte)0));
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote) {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }
    
    protected String getLivingSound() {
        return "mob.spider.say";
    }
    
    protected String getHurtSound() {
        return "mob.spider.say";
    }
    
    protected String getDeathSound() {
        return "mob.spider.death";
    }
    
    protected String getSwimSound() {
        return "game.hostile.swim";
    }
    
    protected String getSplashSound() {
        return "game.hostile.swim.splash";
    }
    
    protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
        this.playSound("mob.spider.step", 0.15f, 1.0f);
    }
    
    public boolean isOnLadder() {
        return this.isBesideClimbableBlock();
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }
    
    public boolean isPotionApplicable(final PotionEffect par1PotionEffect) {
        return par1PotionEffect.getPotionID() != Potion.poison.id && super.isPotionApplicable(par1PotionEffect);
    }
    
    public boolean isBesideClimbableBlock() {
        return (this.dataWatcher.getWatchableObjectByte(18) & 0x1) != 0x0;
    }
    
    public void setBesideClimbableBlock(final boolean par1) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(18);
        if (par1) {
            b0 |= 0x1;
        }
        else {
            b0 &= 0xFFFFFFFE;
        }
        this.dataWatcher.updateObject(18, (Object)b0);
    }
}
