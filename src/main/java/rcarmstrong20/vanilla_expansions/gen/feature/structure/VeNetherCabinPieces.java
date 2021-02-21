package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.AlwaysTrueRuleTest;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.RandomBlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleEntry;
import net.minecraft.world.gen.feature.template.RuleStructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeStructurePieceTypes;
import rcarmstrong20.vanilla_expansions.core.VeVillagerType;
import rcarmstrong20.vanilla_expansions.util.VeStructureUtil;

/**
 * Pieces are where the structure is actually added to the world, a structure
 * can have one or more of them TemplateStructurePieces is just one type of
 * piece, that makes using structure blocks easier Pieces are very customisable,
 * see vanilla classes for different ways they can be used This particular class
 * layout is similar to what vanilla uses for more recent structures
 */
public class VeNetherCabinPieces
{
    public static void init(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> list,
            VeCabinConfig config)
    {
        list.add(new VeNetherCabinPieces.VePiece(templateManager, config.getTemplateLocation(), pos, rotation));
    }

    // Features
    public static class VePiece extends TemplateStructurePiece
    {
        private final ResourceLocation templateResource;
        private final Rotation rotation;

        /**
         * This is the constructor we use ourselves, the other constructor is needed for
         * the piece type
         *
         * @return
         */
        public VePiece(TemplateManager templateManager, ResourceLocation templateResource, BlockPos pos,
                Rotation rotation)
        {
            super(VeStructurePieceTypes.netherCabinPiece, 0);
            this.templateResource = templateResource;
            this.templatePosition = pos;
            this.rotation = rotation;
            setupTemplate(templateManager);
        }

        /**
         * This constructor must always be implemented, so that the piece type can be
         * created
         */
        public VePiece(TemplateManager templateManager, CompoundNBT nbt)
        {
            super(VeStructurePieceTypes.netherCabinPiece, nbt);
            this.templateResource = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            setupTemplate(templateManager);
        }

        /**
         * Setup and prepare the template for placement
         */
        private void setupTemplate(TemplateManager templateManager)
        {
            Template template = templateManager.getTemplateDefaulted(this.templateResource);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation)
                    .setMirror(Mirror.NONE).setCenterOffset(new BlockPos(0, 0, 0))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                    .addProcessor(new RuleStructureProcessor(ImmutableList.of(new RuleEntry(
                            new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.2F), AlwaysTrueRuleTest.INSTANCE,
                            VeBlocks.crimsonChytridNetherBricks.getDefaultState()))));
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * Where to save things to nbt You might get away with simply using Write, but
         * this is what vanilla uses
         */
        @Override
        protected void readAdditional(CompoundNBT nbt)
        {
            super.readAdditional(nbt);
            nbt.putString("Template", this.templateResource.toString());
            nbt.putString("Rot", this.rotation.name());
        }

        /**
         * This method is called for every structure block set to the 'data' mode inside
         * the structure It is mainly used to add loot to chests
         *
         * @param function the string inside the data structure block
         * @param pos      the position of the structure block in the world
         */
        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld world, Random rand,
                MutableBoundingBox boundingBox)
        {
            switch (function)
            {
                case "CrimsonChest":
                    LockableLootTileEntity.setLootTable(world, rand, pos.down(),
                            new ResourceLocation(VanillaExpansions.MOD_ID, "chests/crimson_cabin"));
                    break;
                case "FlowerPot":
                    world.setBlockState(pos.down(), BlockTags.FLOWER_POTS.getRandomElement(rand).getDefaultState(), 3);
                    break;
                case "OverworldBoxCrop":
                    VeStructureUtil.setOverworldCrop(world, VeBlockTags.singleCrops.getRandomElement(rand), pos);
                    break;
                case "CrimsonVillager":
                    VeStructureUtil.spawnVillager(world, pos, VeVillagerType.crimson);
                    break;
                default:
                    break;
            }
        }

        /**
         * Note: This is a new create method for 1.16.
         *
         * Here is the magic place where blocks are added to the world. Actually most of
         * that is handled by the super method, for template structure pieces. But
         * something that can be done here is setting the y-level of the structure.
         */
        @Override
        public boolean func_230383_a_(ISeedReader seedReader, StructureManager structureManager,
                ChunkGenerator chunkGenerator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos,
                BlockPos blockPos)
        {
            // Calculate the height for the cabin and generate the structure.
            int x = this.templatePosition.getX();
            int z = this.templatePosition.getZ();
            int y = findGroundHeight(seedReader, new BlockPos(x, 0, z));

            this.templatePosition = new BlockPos(x, y, z);

            return super.func_230383_a_(seedReader, structureManager, chunkGenerator, random, boundingBox, chunkPos,
                    blockPos);
        }

        private int findGroundHeight(ISeedReader seedReader, BlockPos pos)
        {
            List<Block> groundBlocks = Arrays.asList(Blocks.NETHERRACK, Blocks.CRIMSON_NYLIUM, Blocks.WARPED_HYPHAE,
                    Blocks.LAVA);
            int y = 128;

            while (pos.getY() != 128)
            {
                Block atBlock = seedReader.getBlockState(pos).getBlock();
                Block aboveBlock = seedReader.getBlockState(pos.up()).getBlock();

                if (groundBlocks.contains(atBlock) && aboveBlock == Blocks.AIR)
                {
                    y = pos.getY();
                }
                pos = pos.up();
            }
            return y;
        }
    }
}
