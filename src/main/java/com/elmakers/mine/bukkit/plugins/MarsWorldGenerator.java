package com.elmakers.mine.bukkit.plugins;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class MarsWorldGenerator extends ChunkGenerator {
    private MarsPlugin mars;

    public MarsWorldGenerator(MarsPlugin mars) {
        this.mars = mars;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int currentHeight = (int) (generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D) * 15D + 50D);
                chunk.setBlock(x, currentHeight, z, Material.RED_SAND);
                chunk.setBlock(x, currentHeight - 1, z, Material.STRIPPED_ACACIA_LOG);
                for (int y = currentHeight - 2; y > 0; y--) {
                    chunk.setBlock(x, y, z, Material.RED_SANDSTONE);
                }
                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        }
        return chunk;
    }
}
