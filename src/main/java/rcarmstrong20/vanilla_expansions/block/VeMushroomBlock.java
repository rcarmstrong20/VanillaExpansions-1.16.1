package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.MushroomBlock;

public class VeMushroomBlock extends MushroomBlock
{
    public VeMushroomBlock(Properties properties)
    {
        super(properties);
    }

    /**
     * Lets the small purple mushroom grow big when using bone meal.
     */
    /*
     * @Override public boolean func_226940_a_(ServerWorld serverWorld, BlockPos
     * blockPos, BlockState blockState, Random random) {
     * serverWorld.removeBlock(blockPos, false);
     * ConfiguredFeature<BigMushroomFeatureConfig, ?> configuredfeature; if (this ==
     * VeBlocks.purple_mushroom) { configuredfeature =
     * Feature.HUGE_RED_MUSHROOM.withConfiguration(VeBiomes.
     * BIG_PURPLE_MUSHROOM_CONFIG); } else { return
     * super.func_226940_a_(serverWorld, blockPos, blockState, random); } if
     * (configuredfeature.func_242765_a(serverWorld,
     * serverWorld.getChunkProvider().getChunkGenerator(), random, blockPos)) {
     * return true; } return super.func_226940_a_(serverWorld, blockPos, blockState,
     * random); }
     */
}
