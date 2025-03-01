package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.ai.*;
import net.minecraftforge.common.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;

public class EntityRisenZombie extends EntitySummoned
{
    protected static final IAttribute field_110186_bp;
    
    public EntityRisenZombie(final World par1World) {
        super(par1World);
        this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, false));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 2.0f));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.setSize(0.6f, 1.8f);
        this.setCanPickUpLoot(true);
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(EntityRisenZombie.field_110186_bp).setBaseValue(this.rand.nextDouble() * ForgeModContainer.zombieSummonBaseChance);
    }
    
    public boolean attackEntityAsMob(final Entity par1Entity) {
        final float attackDamage = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), attackDamage);
    }
    
    protected String getLivingSound() {
        return "mob.zombie.say";
    }
    
    protected String getHurtSound() {
        return "mob.zombie.hurt";
    }
    
    protected String getDeathSound() {
        return "mob.zombie.death";
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
    
    static {
        field_110186_bp = (IAttribute)new RangedAttribute("zombie.spawnReinforcements", 0.0, 0.0, 1.0).setDescription("Spawn Reinforcements Chance");
    }
}
