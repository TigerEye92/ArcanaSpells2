package tigereye.misc;

import cpw.mods.fml.common.*;
import java.util.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import tigereye.itemblock.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.world.gen.feature.*;

public class ManaCrystalGen implements IWorldGenerator
{
    public void generate(final Random random, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1: {
                this.generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 0: {
                this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 1: {
                this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
        }
    }
    
    private void generateNether(final World world, final Random random, final int x, final int z) {
        this.generateOre(ModItemsBlocks.mana_crystal_ore, world, random, x, z, 16, 16, 3 + random.nextInt(6), 3, 1, 127, Blocks.netherrack);
    }
    
    private void generateSurface(final World world, final Random random, final int x, final int z) {
    }
    
    private void generateEnd(final World world, final Random random, final int x, final int z) {
    }
    
    public void generateOre(final Block block, final World world, final Random random, final int blockXPos, final int blockZPos, final int maxX, final int maxZ, final int maxVeinSize, final int chancesToSpawn, final int minY, final int maxY, final Block target) {
        for (int x = 0; x < chancesToSpawn; ++x) {
            final int posX = blockXPos + random.nextInt(maxX);
            final int posY = minY + random.nextInt(maxY - minY);
            final int posZ = blockZPos + random.nextInt(maxZ);
            new WorldGenMinable(block, maxVeinSize, target).generate(world, random, posX, posY, posZ);
        }
    }
}
