package tigereye.spell.shield;

import net.minecraft.potion.*;
import tigereye.potion.*;

public class SpellEarthShield extends SpellShield
{
    public SpellEarthShield(final int par1) {
        super(par1, "earth");
    }
    
    @Override
    public Potion getShieldEffect() {
        return ModPotions.earthShield;
    }
}
