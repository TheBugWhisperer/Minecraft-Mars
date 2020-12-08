package com.elmakers.mine.bukkit.plugins;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class OrePopulator extends BlockPopulator {
    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int x, y, z;
        boolean isReplaceable;
        for (int i = 1; i < 15; i++) {  // Number of tries
            if (random.nextInt(100) < 60) {  // The chance of spawning
                x = random.nextInt(15);
                z = random.nextInt(15);
                y = world.getHighestBlockYAt(x, z);
                Material blockType = chunk.getBlock(x, y, z).getType();
                if (blockType == Material.RED_SAND) {
                    isReplaceable = true;
                    while (isReplaceable) {
                        chunk.getBlock(x, y, z).setType(Material.IRON_BLOCK);
                        if (random.nextInt(100) < 40)  {   // The chance of continuing the vein
                            switch (random.nextInt(5)) {  // The direction chooser
                                case 0: x++; break;
                                case 1: y++; break;
                                case 2: z++; break;
                                case 3: x--; break;
                                case 4: y--; break;
                                case 5: z--; break;
                            }
                            if (y < 0) break;
                            if (x < 0) break;
                            if (z < 0) break;
                            if (x > 15) break;
                            if (z > 15) break;
                            blockType = chunk.getBlock(x, y, z).getType();
                            isReplaceable = (blockType == Material.RED_SAND) && (blockType != Material.IRON_BLOCK);
                        } else isReplaceable = false;
                    }
                }
            }
        }
    }
}
