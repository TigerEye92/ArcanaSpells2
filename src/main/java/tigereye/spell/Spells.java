package tigereye.spell;

import tigereye.spell.targeted.*;
import tigereye.spell.shield.*;
import tigereye.spell.aoe.*;
import tigereye.spell.self.SpellHealSelf;
import tigereye.spell.self.SpellInvisibility;
import tigereye.spell.self.SpellRestoreHunger;
import tigereye.spell.summon.*;
import tigereye.entity.*;

public class Spells
{
    public static final Spell[] spellList;
    public static final Spell blazefire;
    public static final Spell ghastfire;
    public static final Spell witherblast;
    public static final Spell summon_wolf;
    public static final Spell raise_skeleton;
    public static final Spell raise_wither_skeleton;
    public static final Spell raise_zombie;
    public static final Spell raise_zombie_pigman;
    public static final Spell summon_witch;
    public static final Spell summon_spider;
    public static final Spell summon_cave_spider;
    public static final Spell teleport;
    public static final Spell respawn;
    public static final Spell fireShield;
    public static final Spell earthShield;
    public static final Spell waterShield;
    public static final Spell stormShield;
    public static final Spell frostShield;
    public static final Spell blazestorm;
    public static final Spell lightningstorm;
    public static final Spell summon_wolf2;
    public static final Spell risen_ice_giant;
    public static final Spell summon_eldrith_golem;
    public static final Spell summon_taintacle;
    public static final Spell healSelf;
    public static final Spell restoreHunger;
    public static final Spell invisibility;

    
    
    public static void registerSpell(final Spell par1Spell, final int par2effectId) {
        if (Spells.spellList[par2effectId] != null) {
            throw new IllegalArgumentException("Duplicate spell id!");
        }
        Spells.spellList[par2effectId] = par1Spell;
    }
    
    static {
        spellList = new Spell[256];
        blazefire = new SpellBlazeFire(1);
        ghastfire = new SpellGhastFire(2);
        witherblast = new SpellWitherBlast(3);
        summon_wolf = new SpellSummon(4, "wolf", EntitySummonedWolf.class);
        raise_skeleton = new SpellSummon(5, "skeleton", EntityRisenSkeleton.class);
        raise_wither_skeleton = new SpellSummon(6, "wither_skeleton", EntityRisenWitherSkeleton.class);
        raise_zombie = new SpellSummon(7, "zombie", EntityRisenZombie.class);
        raise_zombie_pigman = new SpellSummon(8, "zombie_pigman", EntityRisenZombiePigman.class);
        summon_witch = new SpellSummon(9, "witch", EntitySummonedWitch.class);
        summon_spider = new SpellSummon(10, "spider", EntitySummonedSpider.class);
        summon_cave_spider = new SpellSummon(11, "cave_spider", EntitySummonedCaveSpider.class);
        teleport = new SpellTeleport(12);
        respawn = new SpellRespawn(13);
        fireShield = new SpellFireShield(14);
        earthShield = new SpellEarthShield(15);
        waterShield = new SpellWaterShield(16);
        stormShield = new SpellStormShield(17);
        frostShield = new SpellFrostShield(18);
        blazestorm = new SpellBlazeStorm(19);
        lightningstorm = new SpellLightningStorm(20);
        summon_wolf2 = new SpellSummon2(21, "wolf", EntitySummonedWolf.class);
        risen_ice_giant = new SpellSummon2(22, "risen_ice_giant", EntityRisenIceGiant.class);
        summon_eldrith_golem = new SpellSummon2(23, "summon_eldrith_golem", EntitySummonEldritchGolem.class);
        summon_taintacle = new SpellSummon2(24, "summon_taintacle", EntitySummonTaintacle.class);
        healSelf = new SpellHealSelf(25);
        restoreHunger = new SpellRestoreHunger(26);
        invisibility = new SpellInvisibility(27);

        
    }
}
