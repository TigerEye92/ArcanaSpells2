package tigereye.entity;

import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;

public class EntitySummonedCaveSpider extends EntitySummonedSpider
{
    public EntitySummonedCaveSpider(final World par1World) {
        super(par1World);
        this.setSize(0.7f, 0.5f);
    }
    
    @Override
    public boolean attackEntityAsMob(final Entity par1Entity) {
        if (super.attackEntityAsMob(par1Entity)) {
            ((EntityLivingBase)par1Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 300, 0));
            return true;
        }
        return false;
    }
}
