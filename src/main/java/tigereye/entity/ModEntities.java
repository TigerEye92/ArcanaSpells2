package tigereye.entity;

import cpw.mods.fml.common.registry.*;

public class ModEntities
{
    public static void registerModEntities() {
        EntityRegistry.registerGlobalEntityID((Class)EntitySummonedWolf.class, "summoned_wolf", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntityRisenSkeleton.class, "risen_skeleton", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntityRisenWitherSkeleton.class, "risen_wither_skeleton", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntityRisenZombie.class, "risen_zombie", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntityRisenZombiePigman.class, "risen_zombie_pigman", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntitySummonedWitch.class, "summoned_witch", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntitySummonedSpider.class, "summoned_spider", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntitySummonedCaveSpider.class, "summoned_cave_spider", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntityRisenIceGiant.class, "risen_ice_giant", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntitySummonEldritchGolem.class, "summon_eldrith_golem", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerGlobalEntityID((Class)EntitySummonTaintacle.class, "summon_taintacle", EntityRegistry.findGlobalUniqueEntityId());
    }
}
