package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;

public class VePurpleHugeMushroomBlock extends HugeMushroomBlock
{
    public VePurpleHugeMushroomBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void fallOn(World world, BlockPos pos, Entity entityIn, float fallDistance)
    {
        float strength = 0.5F;
        float height = fallDistance * strength;
        if (entityIn.isSuppressingBounce())
        {
            Vector3d vector3d = entityIn.getDeltaMovement();

            // Added a little bounce at the end and play a higher pitched bounce sound.
            entityIn.setDeltaMovement(vector3d.x, vector3d.y + Math.sqrt(0.22 * (height + 0.25F)), vector3d.z);
            world.playSound(null, pos, VeSoundEvents.blockMushroomBounce, SoundCategory.BLOCKS, 1.0F,
                    0.8F + world.random.nextFloat() / 0.4F);
        }
        else
        {
            Vector3d vector3d = entityIn.getDeltaMovement();

            // Set a maximum height of 50.
            if (height >= 50)
            {
                height = 50;
            }

            // Update the entities motion and play the bounce sound.
            entityIn.setDeltaMovement(vector3d.x, -vector3d.y + Math.sqrt(0.22 * (height + 0.25F)), vector3d.z);
            world.playSound(null, pos, VeSoundEvents.blockMushroomBounce, SoundCategory.BLOCKS, 1.0F,
                    0.8F + world.random.nextFloat() * 0.4F);
        }
    }

    @Override
    public void updateEntityAfterFallOn(IBlockReader world, Entity entityIn)
    {}

    @Override
    public boolean addLandingEffects(BlockState state1, ServerWorld worldserver, BlockPos pos, BlockState state2,
            LivingEntity entity, int numberOfParticles)
    {
        return true; // True to prevent vanilla landing particles from spawning.
    }
}
