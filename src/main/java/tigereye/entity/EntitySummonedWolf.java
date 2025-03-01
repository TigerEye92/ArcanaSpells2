package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;

public class EntitySummonedWolf extends EntitySummoned
{
    private float field_70926_e;
    private float field_70924_f;
    private boolean isShaking;
    private boolean field_70928_h;
    private float timeWolfIsShaking;
    private float prevTimeWolfIsShaking;
    
    public EntitySummonedWolf(final World par1World) {
        super(par1World);
        this.setSize(0.6f, 0.8f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIBeg2(this, 8.0f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }
    
    public boolean interact(final EntityPlayer par1EntityPlayer) {
        final ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof ItemFood) {
            final ItemFood itemfood = (ItemFood)itemstack.getItem();
            if (itemfood.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0f) {
                if (!par1EntityPlayer.capabilities.isCreativeMode) {
                    final ItemStack itemStack2;
                    final ItemStack itemStack = itemStack2 = itemstack;
                    --itemStack2.stackSize;
                }
                this.heal((float)itemfood.func_150905_g(itemstack));
                if (itemstack.stackSize <= 0) {
                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
                return true;
            }
        }
        return false;
    }
    
    protected void updateAITick() {
        this.dataWatcher.updateObject(18, (Object)this.getHealth());
    }
    
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, (Object)new Float(this.getHealth()));
        this.dataWatcher.addObject(19, (Object)new Byte((byte)0));
    }
    
    protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
        this.playSound("mob.wolf.step", 0.15f, 1.0f);
    }
    
    protected String getLivingSound() {
        return (this.rand.nextInt(3) == 0) ? ((this.dataWatcher.getWatchableObjectFloat(18) < 10.0f) ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark";
    }
    
    protected String getHurtSound() {
        return "mob.wolf.hurt";
    }
    
    protected String getSwimSound() {
        return "game.hostile.swim";
    }
    
    protected String getSplashSound() {
        return "game.hostile.swim.splash";
    }
    
    protected String getDeathSound() {
        return "mob.wolf.death";
    }
    
    protected float getSoundVolume() {
        return 0.4f;
    }
    
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && this.onGround) {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
            this.worldObj.setEntityState((Entity)this, (byte)8);
        }
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.field_70924_f = this.field_70926_e;
        if (this.func_70922_bv()) {
            this.field_70926_e += (1.0f - this.field_70926_e) * 0.4f;
        }
        else {
            this.field_70926_e += (0.0f - this.field_70926_e) * 0.4f;
        }
        if (this.func_70922_bv()) {
            this.numTicksToChaseTarget = 10;
        }
        if (this.isWet()) {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
        }
        else if ((this.isShaking || this.field_70928_h) && this.field_70928_h) {
            if (this.timeWolfIsShaking == 0.0f) {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            }
            this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
            this.timeWolfIsShaking += 0.05f;
            if (this.prevTimeWolfIsShaking >= 2.0f) {
                this.isShaking = false;
                this.field_70928_h = false;
                this.prevTimeWolfIsShaking = 0.0f;
                this.timeWolfIsShaking = 0.0f;
            }
            if (this.timeWolfIsShaking > 0.4f) {
                final float f = (float)this.boundingBox.minY;
                for (int i = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4f) * 3.1415927f) * 7.0f), j = 0; j < i; ++j) {
                    final float f2 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f;
                    final float f3 = (this.rand.nextFloat() * 2.0f - 1.0f) * this.width * 0.5f;
                    this.worldObj.spawnParticle("splash", this.posX + f2, (double)(f + 0.8f), this.posZ + f3, this.motionX, this.motionY, this.motionZ);
                }
            }
        }
    }
    
    public boolean func_70922_bv() {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }
    
    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(final float par1) {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15f * 3.1415927f;
    }
    
    public void func_70918_i(final boolean par1) {
        if (par1) {
            this.dataWatcher.updateObject(19, (Object)1);
        }
        else {
            this.dataWatcher.updateObject(19, (Object)0);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean getWolfShaking() {
        return this.isShaking;
    }
    
    @SideOnly(Side.CLIENT)
    public float getShadingWhileShaking(final float par1) {
        return 0.75f + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0f * 0.25f;
    }
    
    @SideOnly(Side.CLIENT)
    public float getShakeAngle(final float par1, final float par2) {
        float f2 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return MathHelper.sin(f2 * 3.1415927f) * MathHelper.sin(f2 * 3.1415927f * 11.0f) * 0.15f * 3.1415927f;
    }
    
    public float getEyeHeight() {
        return this.height * 0.8f;
    }
    
    public boolean attackEntityFrom(final DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        }
        final Entity entity = par1DamageSource.getEntity();
        if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
            par2 = (par2 + 1.0f) / 2.0f;
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 4.0f);
    }
    
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(final byte par1) {
        if (par1 == 8) {
            this.field_70928_h = true;
            this.timeWolfIsShaking = 0.0f;
            this.prevTimeWolfIsShaking = 0.0f;
        }
        else {
            super.handleHealthUpdate(par1);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return (0.55f - (20.0f - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02f) * 3.1415927f;
    }
    
    public boolean func_142018_a(final EntityLivingBase par1EntityLivingBase, final EntityLivingBase par2EntityLivingBase) {
        if (!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast)) {
            if (par1EntityLivingBase instanceof EntitySummonedWolf) {
                final EntitySummonedWolf entitywolf = (EntitySummonedWolf)par1EntityLivingBase;
                if (entitywolf.getOwner() == par2EntityLivingBase) {
                    return false;
                }
            }
            return (!(par1EntityLivingBase instanceof EntityPlayer) || !(par2EntityLivingBase instanceof EntityPlayer) || ((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase)) && (!(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame());
        }
        return false;
    }
    
    @Override
    public boolean isBreedingItem(final ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() instanceof ItemFood && ((ItemFood)par1ItemStack.getItem()).isWolfsFavoriteMeat();
    }
}
