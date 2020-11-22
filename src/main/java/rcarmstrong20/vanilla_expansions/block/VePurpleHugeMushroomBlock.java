package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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

    /**
     * Called when an Entity lands on this Block. This method *must* update motionY
     * because the entity will not do that on its own.
     */
    @Override
    public void onLanded(IBlockReader world, Entity entity)
    {}

    /**
     * Block's chance to react to a living entity falling on it.
     */
    @Override
    public void onFallenUpon(World world, BlockPos pos, Entity entityIn, float fallDistance)
    {
        float strength = 2.0F;
        float height = entityIn.fallDistance * strength;
        if (height > 0 && !entityIn.isCrouching())
        {
            if (height >= 100)
            {
                height = 100;
            }
            entityIn.setMotion(entityIn.getMotion().mul(1.0, 0.0, 1.0));
            entityIn.addVelocity(0, Math.sqrt(0.22 * (height + 0.25F)), 0);
            world.playSound(null, pos, VeSoundEvents.block_mushroom_bounce, SoundCategory.BLOCKS, 1.0F,
                    0.8F + world.rand.nextFloat() * 0.4F);
        }
        entityIn.fallDistance = 0;
    }

    /**
     * Allows a block to override the standard EntityLivingBase.updateFallState
     * particles, this is a server side method that spawns particles with
     * WorldServer.spawnParticle.
     *
     * @param worldserver       The current Server World
     * @param pos               The position of the block.
     * @param state2            The state at the specific world/pos
     * @param entity            The entity that hit landed on the block
     * @param numberOfParticles That vanilla world have spawned
     * @return True to prevent vanilla landing particles from spawning
     */
    @Override
    public boolean addLandingEffects(BlockState state1, ServerWorld worldserver, BlockPos pos, BlockState state2,
            LivingEntity entity, int numberOfParticles)
    {
        return true;
    }
}
