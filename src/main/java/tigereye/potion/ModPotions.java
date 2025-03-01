package tigereye.potion;

import net.minecraft.potion.*;
import java.lang.reflect.*;

public class ModPotions
{
    public static final Potion polymorphed;
    public static final Potion fireShield;
    public static final Potion earthShield;
    public static final Potion waterShield;
    public static final Potion stormShield;
    public static final Potion frostShield;
    public static final Potion feared;
    public static final Potion projectileImmunity;
    public static final Potion knockbackImmunity;
    public static final Potion mana;
    public static final Potion manaRegen;
    private static int nextPotionId;
    
    private static int getUniquePotionId() {
        while (ModPotions.nextPotionId < Potion.potionTypes.length && Potion.potionTypes[ModPotions.nextPotionId] != null) {
            ++ModPotions.nextPotionId;
        }
        return ModPotions.nextPotionId;
    }
    
    private static void openUpPotionTypes() {
        Potion[] potionTypes = null;
        for (final Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("potionTypes")) {
                    final Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                    potionTypes = (Potion[])f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    static {
        ModPotions.nextPotionId = 60;
        openUpPotionTypes();
        polymorphed = new PotionBase(getUniquePotionId(), false, 0).setIconIndex(0, 0).setPotionName("potion.polymorphed");
        fireShield = new PotionBase(getUniquePotionId(), false, 14981690).setIconIndex(1, 0).setPotionName("potion.shield.fire");
        earthShield = new PotionBase(getUniquePotionId(), false, 10044730).setIconIndex(2, 0).setPotionName("potion.shield.earth");
        waterShield = new PotionBase(getUniquePotionId(), false, 3035801).setIconIndex(3, 0).setPotionName("potion.shield.water");
        stormShield = new PotionBase(getUniquePotionId(), false, 8171462).setIconIndex(4, 0).setPotionName("potion.shield.storm");
        frostShield = new PotionBase(getUniquePotionId(), false, 15463164).setIconIndex(5, 0).setPotionName("potion.shield.frost");
        feared = new PotionBase(getUniquePotionId(), true, 4393481).setIconIndex(6, 0).setPotionName("potion.feared");
        projectileImmunity = new PotionBase(getUniquePotionId(), false, 0).setIconIndex(7, 0).setPotionName("potion.immunity.projectile");
        knockbackImmunity = new PotionBase(getUniquePotionId(), false, 0).setIconIndex(0, 1).setPotionName("potion.immunity.knockback");
        mana = new PotionBase(getUniquePotionId(), false, 5926017).setIconIndex(1, 1).setPotionName("potion.mana.instant");
        manaRegen = new PotionBase(getUniquePotionId(), false, 5926017).setIconIndex(1, 1).setPotionName("potion.mana.regen");
    }
}
