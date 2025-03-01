package tigereye.spell.shield;

import net.minecraft.potion.*;
import tigereye.potion.*;

public class SpellStormShield extends SpellShield
{
    public SpellStormShield(final int par1) {
        super(par1, "storm");
    }
    
    @Override
    public Potion getShieldEffect() {
        return ModPotions.stormShield;
    }
}
