package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.util.*;

public class EntityRisenSkeleton extends EntitySummoned implements IRangedAttackMob
{
    public EntityRisenSkeleton(final World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 20, 60, 15.0f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setCurrentItemOrArmor(0, new ItemStack((Item)Items.bow));
        this.setCanPickUpLoot(true);
    }
    
    protected String getLivingSound() {
        return "mob.skeleton.say";
    }
    
    protected String getHurtSound() {
        return "mob.skeleton.hurt";
    }
    
    protected String getDeathSound() {
        return "mob.skeleton.death";
    }
    
    protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
        this.playSound("mob.skeleton.step", 0.15f, 1.0f);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        return false;
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.worldObj.isRemote) {
            this.setSize(0.6f, 1.8f);
        }
    }
    
    public void updateRidden() {
        super.updateRidden();
        if (this.ridingEntity instanceof EntityCreature) {
            final EntityCreature entitycreature = (EntityCreature)this.ridingEntity;
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }
    
    public void attackEntityWithRangedAttack(final EntityLivingBase par1EntityLivingBase, final float par2) {
        final EntityArrow entityarrow = new EntityArrow(this.worldObj, (EntityLivingBase)this, par1EntityLivingBase, 1.6f, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        entityarrow.setDamage(par2 * 2.0f + this.rand.nextGaussian() * 0.25 + this.worldObj.difficultySetting.getDifficultyId() * 0.11f);
        this.playSound("random.bow", 1.0f, 1.0f / (this.getRNG().nextFloat() * 0.4f + 0.8f));
        this.worldObj.spawnEntityInWorld((Entity)entityarrow);
    }
    
    public double getYOffset() {
        return super.getYOffset() - 0.5;
    }
    
    protected String getSwimSound() {
        return "game.hostile.swim";
    }
    
    protected String getSplashSound() {
        return "game.hostile.swim.splash";
    }
    
    public boolean attackEntityFrom(final DamageSource par1DamageSource, final float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        if (!super.attackEntityFrom(par1DamageSource, par2)) {
            return false;
        }
        final Entity entity = par1DamageSource.getEntity();
        if (this.riddenByEntity != entity && this.ridingEntity != entity) {
            if (entity != this) {
                this.entityToAttack = entity;
            }
            return true;
        }
        return true;
    }
    
    protected String func_146067_o(final int p_146067_1_) {
        return (p_146067_1_ > 4) ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }
}
