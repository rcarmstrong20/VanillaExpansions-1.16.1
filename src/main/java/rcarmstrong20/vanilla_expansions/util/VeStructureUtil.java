package rcarmstrong20.vanilla_expansions.util;

import java.util.Random;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;

/**
 * A utility class that holds a few helper methods for adding things to
 * structures.
 *
 * @author Ryan
 *
 */
public class VeStructureUtil
{
    /**
     * Spawns a villager located at a data structure block within the structure.
     *
     * @param world
     * @param pos   The position of the data structure block.
     * @param type  The type of villager to spawn.
     */
    public static void spawnVillager(IServerWorld world, BlockPos pos, VillagerType type)
    {
        VillagerEntity villagerEntity = EntityType.VILLAGER.create(world.getWorld());
        villagerEntity.enablePersistence();
        villagerEntity.moveToBlockPosAndAngles(pos, 0.0F, 0.0F);
        villagerEntity.setVillagerData(new VillagerData(type, villagerEntity.getVillagerData().getProfession(), 0));
        villagerEntity.onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.STRUCTURE,
                (ILivingEntityData) null, (CompoundNBT) null);
        world.func_242417_l(villagerEntity); // spawn the villager.
    }

    /**
     * Randomizes the bed's color using the bed's direction within the structure's
     * template. Places the bed from a data structure block above the bed's foot.
     *
     * @param world
     * @param pos    The position of the data structure block above the bed's foot.
     * @param random
     */
    public static void randomizeBedColor(IServerWorld world, BlockPos pos, Random random)
    {
        BlockState state = world.getBlockState(pos.down());

        if (state.getBlock() instanceof BedBlock)
        {
            Direction facing = state.get(BedBlock.HORIZONTAL_FACING);
            BlockState bed = BlockTags.BEDS.getRandomElement(random).getDefaultState();

            // Place foot
            world.setBlockState(pos.down(), bed.with(BedBlock.HORIZONTAL_FACING, facing), 1);

            // Place head
            world.setBlockState(pos.down().offset(facing),
                    bed.with(BedBlock.HORIZONTAL_FACING, facing).with(BedBlock.PART, BedPart.HEAD), 1);
        }
    }

    /**
     * Sets the inputed crop block at its max stage at the position of the data
     * structure block.
     *
     * @param world
     * @param block The crop block to place.
     * @param pos   The position of the data structure block.
     * @return true if the block was placed, otherwise false.
     */
    public static boolean setOverworldCrop(IServerWorld world, Block block, BlockPos pos)
    {
        if (block instanceof CropsBlock)
        {
            if (block instanceof BeetrootBlock)
            {
                world.setBlockState(pos, block.getDefaultState().with(BeetrootBlock.BEETROOT_AGE, 3), 3);
                return true;
            }
            else
            {
                world.setBlockState(pos, block.getDefaultState().with(CropsBlock.AGE, 7), 3);
                return true;
            }
        }
        else
        {
            world.setBlockState(pos, block.getDefaultState(), 3);
            return false;
        }
    }
}
