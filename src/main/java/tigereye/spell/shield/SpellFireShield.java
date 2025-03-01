package tigereye.spell.shield;

import net.minecraft.potion.*;
import tigereye.potion.*;

public class SpellFireShield extends SpellShield
{
    public SpellFireShield(final int par1) {
        super(par1, "fire");
    }
    
    @Override
    public Potion getShieldEffect() {
        return ModPotions.fireShield;
    }
}
