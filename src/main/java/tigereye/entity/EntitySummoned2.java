package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.ai.*;
import net.minecraft.pathfinding.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.*;

public abstract class EntitySummoned2 extends EntityTameable
{
    int spawnTimer;
    
    public EntitySummoned2(final World par1World) {
        super(par1World);
        this.targetTasks.addTask(1, (EntityAIBase)new EntityAIOwnerHurtByTarget((EntityTameable)this));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAIOwnerHurtTarget((EntityTameable)this));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.setTamed(true);
        this.spawnTimer = 0;
        this.setPathToEntity((PathEntity)null);
        this.setAttackTarget((EntityLivingBase)null);
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0);
    }
    
    public EntityAgeable createChild(final EntityAgeable var1) {
        return null;
    }
    
    public boolean isAIEnabled() {
        return true;
    }
    
    protected Item getDropItem() {
        return Item.getItemById(-1);
    }
    
    public boolean isBreedingItem(final ItemStack par1ItemStack) {
        return false;
    }
    
    public int getMaxSpawnedInChunk() {
        return 0;
    }
    
    public boolean canMateWith(final EntityAnimal par1EntityAnimal) {
        return false;
    }
    
    protected boolean canDespawn() {
        return false;
    }
    
    public int getSpawnTimer() {
        return this.spawnTimer;
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (this.getSpawnTimer() > 0) {
            --this.spawnTimer;
        }
        if (this.worldObj.isRemote) {
            return;
        }
        final EntityLivingBase owner = this.getOwner();
        if (owner != null) {
            if (owner.isDead) {
                this.attackEntityFrom(DamageSource.generic, this.getMaxHealth());
            }
        }
        else {
            this.attackEntityFrom(DamageSource.generic, this.getMaxHealth());
        }
    }
}
