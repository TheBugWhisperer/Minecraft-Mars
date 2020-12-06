package com.elmakers.mine.bukkit.plugins;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MarsWorldGenerator extends ChunkGenerator {
    private MarsPlugin mars;

    public MarsWorldGenerator(MarsPlugin mars) {
        this.mars = mars;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new OrePopulator());
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        random = new Random(world.getSeed());
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(random, 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.010D);

        Material blockType;
        int currentHeight;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                currentHeight = (int) (generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D) * 15D + 50D);

                for (int y = currentHeight; y > 0; y--) {
                    if (y > currentHeight - 3) {
                        blockType = Material.RED_SAND;
                    } else if (y > currentHeight - 8) {
                        blockType = Material.RED_SANDSTONE;
                    } else {
                        switch (random.nextInt(4)) {
                            case 0: blockType = Material.RED_TERRACOTTA; break;
                            case 1: blockType = Material.ORANGE_TERRACOTTA; break;
                            case 2: blockType = Material.TERRACOTTA; break;
                            case 3: blockType = Material.YELLOW_TERRACOTTA; break;
                            default: blockType = Material.BROWN_TERRACOTTA; break;
                        }
                    }

                    chunk.setBlock(x, y, z, blockType);
                }
                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        }
        return chunk;
    }
}
