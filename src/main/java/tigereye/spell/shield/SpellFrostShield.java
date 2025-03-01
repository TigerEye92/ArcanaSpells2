package tigereye.spell.shield;

import net.minecraft.potion.*;
import tigereye.potion.*;

public class SpellFrostShield extends SpellShield
{
    public SpellFrostShield(final int par1) {
        super(par1, "frost");
    }
    
    @Override
    public Potion getShieldEffect() {
        return ModPotions.frostShield;
    }
}
