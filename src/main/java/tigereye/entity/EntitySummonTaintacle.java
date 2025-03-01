package tigereye.entity;

import thaumcraft.api.entities.*;
import net.minecraft.world.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;
import thaumcraft.common.*;
import net.minecraft.enchantment.*;
import thaumcraft.api.damagesource.*;
import net.minecraft.util.*;

public class EntitySummonTaintacle extends EntitySummoned implements ITaintedMob
{
    public float flailIntensity;
    
    public EntitySummonTaintacle(final World par1World) {
        super(par1World);
        this.flailIntensity = 1.0f;
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.2, true));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(0.66f, 3.0f);
    }
    
    public boolean attackEntityAsMob1(final Entity par1Entity) {
        ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 200));
        final float attackDamage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), attackDamage);
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
    
    public float getShadowSize() {
        return 0.25f;
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
    }
    
    public boolean canBeCollidedWith() {
        return true;
    }
    
    public boolean canBePushed() {
        return true;
    }
    
    public void moveEntity(double par1, double par3, double par5) {
        par1 = 0.0;
        par5 = 0.0;
        if (par3 > 0.0) {
            par3 = 0.0;
        }
        super.moveEntity(par1, par3, par5);
    }
    
    protected void updateEntityActionState() {
        if (this.entityToAttack != null) {
            this.faceEntity(this.entityToAttack, 5.0f);
        }
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.worldObj.isRemote) {
            if (this.ticksExisted > this.height * 10.0f && (this.hurtTime > 0 || this.attackTime > 0 || (this.entityToAttack != null && this.entityToAttack.getDistanceToEntity((Entity)this) < this.height))) {
                if (this.flailIntensity < 3.0f) {
                    this.flailIntensity += 0.2f;
                }
            }
            else if (this.flailIntensity > 1.0f) {
                this.flailIntensity -= 0.2f;
            }
            if (this.ticksExisted < this.height * 10.0f && this.onGround) {
                Thaumcraft.proxy.tentacleAriseFX((Entity)this);
            }
        }
        if (this.entityToAttack == null) {
            this.entityToAttack = this.findPlayerToAttack();
        }
        else if (this.entityToAttack.isEntityAlive() && this.getAgitationState()) {
            final float f1 = this.entityToAttack.getDistanceToEntity((Entity)this);
            if (!this.worldObj.isRemote && this.canEntityBeSeen(this.entityToAttack)) {
                this.attackEntity(this.entityToAttack, f1);
            }
        }
        else {
            this.entityToAttack = null;
        }
    }
    
    protected void attackEntity(final Entity entity, final float par2) {
        if (this.attackTime <= 0 && par2 <= this.height && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
            this.attackEntityAsMob1(entity);
            this.playSound("thaumcraft:tentacle", this.getSoundVolume(), this.getSoundPitch());
        }
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        float i = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        if (this.isPotionActive(Potion.damageBoost)) {
            i += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if (this.isPotionActive(Potion.weakness)) {
            i -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        int j = 0;
        if (par1Entity instanceof EntityLivingBase) {
            i += EnchantmentHelper.getEnchantmentModifierLiving((EntityLivingBase)this, (EntityLivingBase)par1Entity);
            j += EnchantmentHelper.getKnockbackModifier((EntityLivingBase)this, (EntityLivingBase)par1Entity);
        }
        final boolean flag = par1Entity.attackEntityFrom(DamageSourceThaumcraft.causeTentacleDamage((EntityLivingBase)this), i);
        if (flag) {
            if (j > 0) {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927f / 180.0f) * j * 0.5f), 0.1, (double)(MathHelper.cos(this.rotationYaw * 3.1415927f / 180.0f) * j * 0.5f));
                this.motionX *= 0.6;
                this.motionZ *= 0.6;
            }
            final int k = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this);
            if (k > 0) {
                par1Entity.setFire(k * 4);
            }
            if (par1Entity instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase)par1Entity, (Entity)this);
            }
            EnchantmentHelper.func_151385_b((EntityLivingBase)this, par1Entity);
        }
        return flag;
    }
    
    public boolean getAgitationState() {
        return this.entityToAttack != null && this.entityToAttack.getDistanceSqToEntity((Entity)this) < this.height * 7.0f * this.height * 7.0f;
    }
    
    public void faceEntity(final Entity par1Entity, final float par2) {
        final double d0 = par1Entity.posX - this.posX;
        final double d2 = par1Entity.posZ - this.posZ;
        final float f2 = (float)(Math.atan2(d2, d0) * 180.0 / 3.141592653589793) - 90.0f;
        this.rotationYaw = this.updateRotation(this.rotationYaw, f2, par2);
    }
    
    protected float updateRotation(final float par1, final float par2, final float par3) {
        float f3 = MathHelper.wrapAngleTo180_float(par2 - par1);
        if (f3 > par3) {
            f3 = par3;
        }
        if (f3 < -par3) {
            f3 = -par3;
        }
        return par1 + f3;
    }
    
    public int getTalkInterval() {
        return 200;
    }
    
    protected String getLivingSound() {
        return "thaumcraft:roots";
    }
    
    protected float getSoundPitch() {
        return 1.3f - this.height / 10.0f;
    }
    
    protected float getSoundVolume() {
        return this.height / 8.0f;
    }
    
    protected String getHurtSound() {
        return "thaumcraft:tentacle";
    }
    
    protected String getDeathSound() {
        return "thaumcraft:tentacle";
    }
}
