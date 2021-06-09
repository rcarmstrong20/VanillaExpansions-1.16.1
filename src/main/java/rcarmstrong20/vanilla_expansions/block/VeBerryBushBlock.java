package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.PlantType;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VEItems;

public class VEBerryBushBlock extends SweetBerryBushBlock
{
    private static final VoxelShape WITCHS_CRADLE_STAGE_0_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 9.0D, 14.0D);

    public VEBerryBushBlock(Properties properties)
    {
        super(properties);
    }

    /**
     * Called when the player right-clicks a block.
     */
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn,
            BlockRayTraceResult hit)
    {
        int currentAge = state.getValue(AGE);
        boolean maxAgeFlag = currentAge == this.getMaxAge();

        if (!maxAgeFlag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL)
        {
            return ActionResultType.PASS;
        }
        else if (currentAge > 1)
        {
            dropResources(state, world, pos);
            world.playSound((PlayerEntity) null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS,
                    1.0F, 0.8F + world.random.nextFloat() * 0.4F);
            world.setBlock(pos, state.setValue(AGE, Integer.valueOf(1)), 2);
            return ActionResultType.SUCCESS;
        }
        return super.use(state, world, pos, player, handIn, hit);
    }

    /**
     * Creates the bounding box for this block.
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
    {
        if (this.getBlock() == VEBlocks.witchsCradle && state.getValue(AGE) == 0)
        {
            return WITCHS_CRADLE_STAGE_0_SHAPE;
        }
        else
        {
            return super.getShape(state, world, pos, context);
        }
    }

    /**
     * Called when pick-blocking this block.
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state)
    {
        Block block = this.getBlock();

        if (block.equals(VEBlocks.blueberryBush))
        {
            return new ItemStack(VEItems.blueberries);
        }
        else if (block.equals(VEBlocks.cranberryBush))
        {
            return new ItemStack(VEItems.cranberries);
        }
        else if (block.equals(VEBlocks.witchsCradle))
        {
            return new ItemStack(VEItems.witchsCradleBranch);
        }
        else
        {
            return super.getCloneItemStack(world, pos, state);
        }
    }

    /**
     * @return The max age of this plant.
     */
    private int getMaxAge()
    {
        return 3;
    }

    /**
     * Called when an entity collides with this block.
     */
    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity)
        {
            if (this.getBlock() == VEBlocks.witchsCradle)
            {
                if (entityIn.getType() != EntityType.SLIME)
                {
                    super.entityInside(state, world, pos, entityIn);
                }
            }
            else
            {
                if (entityIn.getType() != EntityType.FOX && entityIn.getType() != EntityType.BEE)
                {
                    entityIn.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
                }
            }
        }
    }

    /**
     * Decides what blocks this plant can be placed on.
     */
    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return PlantType.PLAINS;
    }
}
