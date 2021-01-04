package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VeStructurePieceTypes;

/**
 * Pieces are where the structure is actually added to the world, a structure
 * can have one or more of them TemplateStructurePieces is just one type of
 * piece, that makes using structure blocks easier Pieces are very customizable,
 * see vanilla classes for different ways they can be used This particular class
 * layout is similar to what vanilla uses for more recent structures
 */
public class VeOverworldCabinPieces
{
    public static void init(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> list,
            VeCabinConfig cabinConfig)
    {
        list.add(new VeOverworldCabinPieces.VePiece(templateManager, cabinConfig.getTemplateLocation(), pos, rotation));
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
            super(VeStructurePieceTypes.overworldCabinPiece, 0);
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
            super(VeStructurePieceTypes.overworldCabinPiece, nbt);
            this.templateResource = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            setupTemplate(templateManager);
        }

        /**
         * Setup and prepare the template for placement
         *
         * Note: field_244108_h is mossify 20%
         */
        private void setupTemplate(TemplateManager templateManager)
        {
            Template template = templateManager.getTemplateDefaulted(this.templateResource);
            PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                    .setCenterOffset(BlockPos.ZERO).setRotation(this.rotation)
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                    .addProcessor(ProcessorLists.field_244108_h.func_242919_a().get(0));

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

        // ShipwreckPieces
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
            ResourceLocation taigaCabinPath = new ResourceLocation(VanillaExpansions.MOD_ID, "chests/taiga_cabin");
            ResourceLocation forestCabinPath = new ResourceLocation(VanillaExpansions.MOD_ID, "chests/forest_cabin");

            switch (function)
            {
                case "TaigaChest":
                    LockableLootTileEntity.setLootTable(world, rand, pos.down(), taigaCabinPath);
                    break;
                case "ForestChest":
                    LockableLootTileEntity.setLootTable(world, rand, pos.down(), forestCabinPath);
                    break;
                case "FlowerPot":
                    world.setBlockState(pos.down(), BlockTags.FLOWER_POTS.getRandomElement(rand).getDefaultState(), 3);
                    break;
                case "Bed":
                    randomizeBedColor(world, pos, rand);
                    break;
                case "TaigaVillager":
                    spawnVillager(world, pos, VillagerType.TAIGA);
                    break;
                case "ForestVillager":
                    spawnVillager(world, pos, VillagerType.PLAINS);
                    break;
                default:
                    break;
            }
        }

        /**
         * Spawns a villager located at a data structure block within the cabin.
         *
         * @param world
         * @param pos
         * @param type
         */
        private void spawnVillager(IServerWorld world, BlockPos pos, VillagerType type)
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
         * Randomizes the bed's color using the bed's direction within the cabin's
         * template. Places the bed from a data structure block above the bed's foot.
         *
         * @param world
         * @param pos
         * @param random
         */
        private void randomizeBedColor(IServerWorld world, BlockPos pos, Random random)
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
         * Here is the magic place where blocks are added to the world. Actually most of
         * that is handled by the super method, for template structure pieces. But
         * something that can be done here is setting the y-level of the structure.
         */
        @Override
        public boolean func_230383_a_(ISeedReader seedReader, StructureManager structureManager,
                ChunkGenerator chunkGenerator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos,
                BlockPos blockPos)
        {
            int x = this.templatePosition.getX();
            int z = this.templatePosition.getZ();
            int sizeX = this.template.transformedSize(this.rotation).getX();
            int sizeZ = this.template.transformedSize(this.rotation).getZ();
            BlockPos structureSize = this.templatePosition.add(sizeX - 1, 0, sizeZ - 1);
            int y = seedReader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, x, z);

            this.templatePosition = new BlockPos(x, y, z);

            for (BlockPos fillPos : BlockPos.getAllInBoxMutable(this.templatePosition, structureSize))
            {
                switch (rotation)
                {
                    case CLOCKWISE_180:
                        placeFillerBlocks(seedReader, fillPos.add(-sizeX + 1, -1, -sizeZ + 1), y);
                        break;
                    case CLOCKWISE_90:
                        placeFillerBlocks(seedReader, fillPos.add(-sizeX + 1, -1, 0), y);
                        break;
                    case COUNTERCLOCKWISE_90:
                        placeFillerBlocks(seedReader, fillPos.add(0, -1, -sizeZ + 1), y);
                        break;
                    default:
                        placeFillerBlocks(seedReader, fillPos.add(0, -1, 0), y);
                        break;
                }
            }
            return super.func_230383_a_(seedReader, structureManager, chunkGenerator, random, boundingBox, chunkPos,
                    blockPos); // Create the structure.
        }

        /**
         * A helper method that adds blocks at or above the ground level to prevent the
         * cabin from floating.
         *
         * @param seedReader
         * @param pos             The position to place each block at.
         * @param templateGroundY The template position's y level.
         * @return true when a block is placed, otherwise false.
         */
        private boolean placeFillerBlocks(ISeedReader seedReader, BlockPos pos, int templateGroundY)
        {
            int groundY = seedReader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, pos.getX(), pos.getZ());

            if (pos.getY() >= groundY)
            {
                if ((pos.getY() + 1) == templateGroundY)
                {
                    seedReader.setBlockState(pos, Blocks.GRASS_BLOCK.getDefaultState(), 4);
                    return true;
                }
                else
                {
                    seedReader.setBlockState(pos, Blocks.DIRT.getDefaultState(), 4);
                    return true;
                }
            }
            return false;
        }
    }
}
