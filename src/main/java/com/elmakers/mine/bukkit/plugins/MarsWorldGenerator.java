package com.elmakers.mine.bukkit.plugins;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MarsWorldGenerator extends ChunkGenerator {
    private static final int SEA_LEVEL = 64;
    private static final int HIGHEST_PEAK = 110;
    private static final int LOWEST_LAKE = 60;
    private final MarsPlugin mars;

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
        int maxHeight;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // This number goes from -1 to 1
                double noise = generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D);
                // This goes from 0 to 1
                noise = (noise + 1) / 2;
                // Change noise into a number from lowest lake to highest mountain peak
                maxHeight = (int) (noise * (HIGHEST_PEAK - LOWEST_LAKE) + LOWEST_LAKE);

                for (int y = SEA_LEVEL; y > maxHeight; y--) {
                    switch (random.nextInt(4)) {
                        case 0:
                            blockType = Material.PACKED_ICE;
                            break;
                        case 1:
                            blockType = Material.ICE;
                            break;
                        default:
                            blockType = Material.BLUE_ICE;
                            break;
                    }
                    biome.setBiome(x, y, z, Biome.CRIMSON_FOREST);
                    chunk.setBlock(x, y, z, blockType);
                }
                for (int y = maxHeight; y > 0; y--) {
                    if (y > maxHeight - 3) {
                        blockType = Material.RED_SAND;
                    } else if (y > maxHeight - 8) {
                        blockType = Material.RED_SANDSTONE;
                    } else {
                        switch (random.nextInt(4)) {
                            case 0:
                                blockType = Material.RED_TERRACOTTA;
                                break;
                            case 1:
                                blockType = Material.ORANGE_TERRACOTTA;
                                break;
                            case 2:
                                blockType = Material.TERRACOTTA;
                                break;
                            case 3:
                                blockType = Material.YELLOW_TERRACOTTA;
                                break;
                            default:
                                blockType = Material.BROWN_TERRACOTTA;
                                break;
                        }
                    }

                    biome.setBiome(x, y, z, Biome.CRIMSON_FOREST);
                    chunk.setBlock(x, y, z, blockType);
                }
                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        }
        return chunk;
    }
}
