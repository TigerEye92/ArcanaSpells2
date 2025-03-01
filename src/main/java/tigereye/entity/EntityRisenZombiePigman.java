package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;

public class EntityRisenZombiePigman extends EntitySummoned
{
    public EntityRisenZombiePigman(final World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, false));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(0.6f, 1.8f);
        this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
        this.setCanPickUpLoot(this.isImmuneToFire = true);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        final float attackDamage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), attackDamage);
    }
    
    protected String getLivingSound() {
        return "mob.zombiepig.zpig";
    }
    
    protected String getHurtSound() {
        return "mob.zombiepig.zpighurt";
    }
    
    protected String getDeathSound() {
        return "mob.zombiepig.zpigdeath";
    }
    
    protected String getSwimSound() {
        return "game.hostile.swim";
    }
    
    protected String getSplashSound() {
        return "game.hostile.swim.splash";
    }
    
    protected void func_145780_a(final int p_145780_1_, final int p_145780_2_, final int p_145780_3_, final Block p_145780_4_) {
        this.playSound("mob.zombie.step", 0.15f, 1.0f);
    }
    
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }
}
