package tigereye.spell;

import java.util.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;

public abstract class Spell
{
    protected static final Random random;
    public final int effectId;
    private String name;
    
    protected Spell(final int par1) {
        Spells.registerSpell(this, this.effectId = par1);
    }
    
    public short getMinLevel() {
        return 1;
    }
    
    public short getMaxLevel() {
        return 1;
    }
    
    public int getManaCost() {
        return 1;
    }
    
    public short getCooldown() {
        return 20;
    }
    
    public String getSoundName() {
        return "mob.ghast.fireball";
    }
    
    public String getParticleName() {
        return "happyVillager";
    }
    
    public double getParticleAmount() {
        return 1.0;
    }
    
    protected Spell setName(final String par1Str) {
        this.name = par1Str;
        return this;
    }
    
    private String getName() {
        return "spell." + this.name;
    }
    
    public String getTranslatedName(final int par1) {
        final String s = StatCollector.translateToLocal(this.getName());
        return s + " " + StatCollector.translateToLocal("spell.level." + par1);
    }
    
    protected int getNormalizedLevel(final short par1Level) {
        return Math.max(this.getMinLevel(), Math.min(par1Level, this.getMaxLevel()));
    }
    
    public abstract void castSpell(final short p0, final World p1, final EntityPlayer p2);
    
    static {
        random = new Random();
    }
}
