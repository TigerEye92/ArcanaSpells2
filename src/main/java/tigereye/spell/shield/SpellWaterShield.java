package tigereye.spell.shield;

import net.minecraft.potion.*;
import tigereye.potion.*;

public class SpellWaterShield extends SpellShield
{
    public SpellWaterShield(final int par1) {
        super(par1, "water");
    }
    
    @Override
    public Potion getShieldEffect() {
        return ModPotions.waterShield;
    }
}
