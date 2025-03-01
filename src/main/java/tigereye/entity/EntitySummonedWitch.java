package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.init.*;
import net.minecraft.block.material.*;
import net.minecraft.potion.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.entity.ai.attributes.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.util.*;

public class EntitySummonedWitch extends EntitySummoned implements IRangedAttackMob
{
    private static final UUID field_110184_bp;
    private static final AttributeModifier field_110185_bq;
    private int witchAttackTimer;
    
    public EntitySummonedWitch(final World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 60, 10.0f));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(0.6f, 1.8f);
    }
    
    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObject(21, (byte)0); // Исправлено: передаем byte вместо int
    }
    
    protected String getLivingSound() {
        return "mob.witch.idle";
    }
    
    protected String getHurtSound() {
        return "mob.witch.hurt";
    }
    
    protected String getDeathSound() {
        return "mob.witch.death";
    }
    
    protected String getSwimSound() {
        return "game.hostile.swim";
    }
    
    protected String getSplashSound() {
        return "game.hostile.swim.splash";
    }
    
    public void setAggressive(final boolean par1) {
        this.getDataWatcher().updateObject(21, (byte)(par1 ? 1 : 0)); // Исправлено: передаем byte
    }
    
    public boolean getAggressive() {
        return this.getDataWatcher().getWatchableObjectByte(21) == 1;
    }
    
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.getAggressive()) {
                if (this.witchAttackTimer-- <= 0) {
                    this.setAggressive(false);
                    final ItemStack itemstack = this.getHeldItem();
                    this.setCurrentItemOrArmor(0, null);
                    if (itemstack != null && itemstack.getItem() == Items.potionitem) {
                        final List<PotionEffect> list = Items.potionitem.getEffects(itemstack);
                        if (list != null) {
                            for (final PotionEffect potioneffect : list) {
                                this.addPotionEffect(new PotionEffect(potioneffect));
                            }
                        }
                    }
                    this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(EntitySummonedWitch.field_110185_bq);
                }
            }
            else {
                short short1 = -1;
                if (this.rand.nextFloat() < 0.15f && this.isInsideOfMaterial(Material.water) && !this.isPotionActive(Potion.waterBreathing)) {
                    short1 = 8237;
                }
                else if (this.rand.nextFloat() < 0.15f && this.isBurning() && !this.isPotionActive(Potion.fireResistance)) {
                    short1 = 16307;
                }
                else if (this.rand.nextFloat() < 0.05f && this.getHealth() < this.getMaxHealth()) {
                    short1 = 16341;
                }
                else if (this.rand.nextFloat() < 0.25f && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0) {
                    short1 = 16274;
                }
                else if (this.rand.nextFloat() < 0.25f && this.getAttackTarget() != null && !this.isPotionActive(Potion.moveSpeed) && this.getAttackTarget().getDistanceSqToEntity(this) > 121.0) {
                    short1 = 16274;
                }
                if (short1 > -1) {
                    this.setCurrentItemOrArmor(0, new ItemStack(Items.potionitem, 1, short1));
                    ItemStack heldItem = this.getHeldItem();
                    if (heldItem != null) {
                        this.witchAttackTimer = heldItem.getMaxItemUseDuration();
                    }
                    this.setAggressive(true);
                    final IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
                    iattributeinstance.removeModifier(EntitySummonedWitch.field_110185_bq);
                    iattributeinstance.applyModifier(EntitySummonedWitch.field_110185_bq);
                }
            }
            if (this.rand.nextFloat() < 7.5E-4f) {
                this.worldObj.setEntityState(this, (byte)15);
            }
        }
        super.onLivingUpdate();
    }
    
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(final byte par1) {
        if (par1 == 15) {
            for (int i = 0; i < this.rand.nextInt(35) + 10; ++i) {
                this.worldObj.spawnParticle("witchMagic", this.posX + this.rand.nextGaussian() * 0.12999999523162842, this.boundingBox.maxY + 0.5 + this.rand.nextGaussian() * 0.12999999523162842, this.posZ + this.rand.nextGaussian() * 0.12999999523162842, 0.0, 0.0, 0.0);
            }
        }
        else {
            super.handleHealthUpdate(par1);
        }
    }
    
    protected float applyPotionDamageCalculations(final DamageSource par1DamageSource, float par2) {
        par2 = super.applyPotionDamageCalculations(par1DamageSource, par2);
        if (par1DamageSource.getEntity() == this) {
            par2 = 0.0f;
        }
        if (par1DamageSource.isMagicDamage()) {
            par2 *= (float)0.15;
        }
        return par2;
    }
    
    public void attackEntityWithRangedAttack(final EntityLivingBase par1EntityLivingBase, final float par2) {
        if (!this.getAggressive()) {
            final EntityPotion entityPotion;
            final EntityPotion entitypotion = entityPotion = new EntityPotion(this.worldObj, (EntityLivingBase)this, 32732);
            entityPotion.rotationPitch += 20.0f;
            final double d0 = par1EntityLivingBase.posX + par1EntityLivingBase.motionX - this.posX;
            final double d2 = par1EntityLivingBase.posY + par1EntityLivingBase.getEyeHeight() - 1.100000023841858 - this.posY;
            final double d3 = par1EntityLivingBase.posZ + par1EntityLivingBase.motionZ - this.posZ;
            final float f1 = MathHelper.sqrt_double(d0 * d0 + d3 * d3);
            if (f1 >= 8.0f && !par1EntityLivingBase.isPotionActive(Potion.moveSlowdown)) {
                entitypotion.setPotionDamage(32698);
            }
            else if (par1EntityLivingBase.getHealth() >= 8.0f && !par1EntityLivingBase.isPotionActive(Potion.poison)) {
                entitypotion.setPotionDamage(32660);
            }
            else if (f1 <= 3.0f && !par1EntityLivingBase.isPotionActive(Potion.weakness) && this.rand.nextFloat() < 0.25f) {
                entitypotion.setPotionDamage(32696);
            }
            entitypotion.setThrowableHeading(d0, d2 + f1 * 0.2f, d3, 0.75f, 8.0f);
            this.worldObj.spawnEntityInWorld((Entity)entitypotion);
        }
    }
    
    static {
        field_110184_bp = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
        field_110185_bq = new AttributeModifier(EntitySummonedWitch.field_110184_bp, "Drinking speed penalty", -0.25, 0).setSaved(false);
    }
}