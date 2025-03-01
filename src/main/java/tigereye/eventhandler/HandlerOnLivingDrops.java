package tigereye.eventhandler;

import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.*;
import cpw.mods.fml.common.eventhandler.*;
import tigereye.spell.*;
import tigereye.itemblock.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;

public class HandlerOnLivingDrops
{
    @SubscribeEvent
    public void OnLivingDrops(final LivingDropsEvent event) {
        final Class<? extends Entity> entityClass = event.entity.getClass();
        if (entityClass.equals(EntityBlaze.class)) {
            this.randomDrop(event, Spells.blazefire, (short)1, 10.0);
            this.randomDrop(event, Spells.blazestorm, (short)1, 5.0);
            this.randomDrop(event, Spells.fireShield, (short)1, 5.0);
        }
        else if (entityClass.equals(EntityGhast.class)) {
            this.randomDrop(event, Spells.ghastfire, (short)1, 20.0);
            this.randomDrop(event, Spells.fireShield, (short)1, 10.0);
        }
        else if (entityClass.equals(EntityWither.class)) {
            this.randomDrop(event, Spells.witherblast, (short)1, 100.0);
        }
        else if (entityClass.equals(EntityCreeper.class)) {
            this.randomDrop(event, Spells.lightningstorm, (short)1, 5.0);
            this.randomDrop(event, Spells.stormShield, (short)1, 5.0);
        }
        else if (entityClass.equals(EntityZombie.class)) {
            this.randomDrop(event, Spells.raise_zombie, (short)1, 5.0);
            this.randomDrop(event, Spells.earthShield, (short)1, 5.0);
        }
        else if (entityClass.equals(EntityPigZombie.class)) {
            this.randomDrop(event, Spells.raise_zombie_pigman, (short)1, 5.0);
            this.randomDrop(event, Spells.earthShield, (short)1, 5.0);
        }
        else if (entityClass.equals(EntitySkeleton.class)) {
            final int skeletonType = ((EntitySkeleton)event.entity).getSkeletonType();
            if (skeletonType == 0) {
                this.randomDrop(event, Spells.raise_skeleton, (short)1, 5.0);
            }
            else if (skeletonType == 1) {
                this.randomDrop(event, Spells.raise_wither_skeleton, (short)1, 5.0);
            }
        }
        else if (entityClass.equals(EntityWolf.class)) {
            this.randomDrop(event, Spells.summon_wolf, (short)1, 5.0);
            this.randomDrop(event, Spells.frostShield, (short)1, 5.0);
        }
        else if (entityClass.equals(EntityWitch.class)) {
            this.randomDrop(event, Spells.summon_witch, (short)1, 15.0);
        }
        else if (entityClass.equals(EntitySpider.class)) {
            this.randomDrop(event, Spells.summon_spider, (short)1, 5.0);
        }
        else if (entityClass.equals(EntityCaveSpider.class)) {
            this.randomDrop(event, Spells.summon_cave_spider, (short)1, 5.0);
        }
        else if (entityClass.equals(EntityEnderman.class)) {
            this.randomDrop(event, Spells.teleport, (short)1, 10.0);
            this.randomDrop(event, Spells.respawn, (short)1, 5.0);
        }
        else if (entityClass.equals(EntitySquid.class)) {
            this.randomDrop(event, Spells.waterShield, (short)1, 5.0);
        }
        else if (entityClass.equals(EntitySnowman.class)) {
            this.randomDrop(event, Spells.frostShield, (short)1, 5.0);
        }
    }
    
    private void randomDrop(final LivingDropsEvent event, final Spell spell, final short level, final double percentage) {
        final double randResult = event.entity.worldObj.rand.nextDouble() * 100.0;
        if (randResult < percentage) {
            final SpellData spellData = new SpellData(spell, level);
            final ItemStack spellBookDrop = ((ItemSpellScroll)ModItemsBlocks.spell_book).getSpellItemStack(spellData);
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, spellBookDrop));
        }
    }
}
