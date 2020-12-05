package com.elmakers.mine.bukkit.plugins;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class MarsWorldGenerator extends ChunkGenerator {
    private MarsPlugin mars;

    public MarsWorldGenerator(MarsPlugin mars) {
        this.mars = mars;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        return chunk;
    }
}
