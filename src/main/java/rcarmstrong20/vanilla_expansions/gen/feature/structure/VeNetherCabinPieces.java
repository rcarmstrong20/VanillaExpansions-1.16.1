package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.List;
import java.util.Random;

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
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VeStructurePieceTypes;

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
            VillageConfig config)
    {
        list.add(new VeNetherCabinPieces.VePiece(templateManager, config.func_242810_c().get().getName(), pos,
                rotation));
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
            super(VeStructurePieceTypes.cabinPiece, 0);
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
            super(VeStructurePieceTypes.cabinPiece, nbt);
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
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
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
            if ("taiga_cabin_chest".equals(function))
            {
                LockableLootTileEntity.setLootTable(world, rand, pos.down(),
                        new ResourceLocation(VanillaExpansions.MOD_ID, "chests/taiga_cabin"));
            }
            else if ("forest_cabin_chest".equals(function))
            {
                LockableLootTileEntity.setLootTable(world, rand, pos.down(),
                        new ResourceLocation(VanillaExpansions.MOD_ID, "chests/forest_cabin"));
            }
            else if ("cabin_flowers".equals(function))
            {
                world.setBlockState(pos.down(), BlockTags.FLOWER_POTS.getRandomElement(rand).getDefaultState(), 3);
            }
        }

        /**
         * Here is the magic place where blocks are added to the world Actually most of
         * that is handled by the super method, for template structure pieces But
         * something that can be done here is setting the y-level of the structure.
         */
        @Override
        public boolean func_230383_a_(ISeedReader seedReader, StructureManager structureManager,
                ChunkGenerator chunkGenerator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos,
                BlockPos blockPos)
        {
            BlockPos pos = this.findHeight(seedReader, blockPos);

            this.templatePosition = pos;

            return super.func_230383_a_(seedReader, structureManager, chunkGenerator, random, boundingBox, chunkPos,
                    blockPos); // New method for create
        }

        private BlockPos findHeight(ISeedReader seedReader, BlockPos pos)
        {

            // BlockPos structureSize =
            // this.templatePosition.add(this.template.getSize().getX() - 1, 0,
            // this.template.getSize().getZ() - 1);

            /*
             * for (BlockPos pos : BlockPos.getAllInBoxMutable(this.templatePosition,
             * structureSize)) { if (seedReader.getBlockState(pos).getBlock() != Blocks.AIR)
             * { if (pos.getY() >= 128) // 128 is the height the ceiling in the nether. {
             * return pos; } pos = pos.up(); } System.out.println("Found at " + pos.getY());
             * return pos; }
             */

            return templatePosition;

            /*
             * while (seedReader.getBlockState(pos).getBlock() != Blocks.AIR) { if
             * (pos.getY() >= 128) // 128 is the height the ceiling in the nether. { return
             * pos; } pos = pos.up(); }
             */

            /*
             * System.out.println( "Found height at " + pos.getY() + " " +
             * seedReader.getBlockState(pos).getBlock().getRegistryName()); return pos;
             */
        }
    }
}
