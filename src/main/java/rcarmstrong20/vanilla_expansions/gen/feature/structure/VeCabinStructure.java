package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VeBiomes;

public class VeCabinStructure extends Structure<VeCabinFeatureConfig>
{
    public VeCabinStructure(Codec<VeCabinFeatureConfig> config)
    {
        super(config);
    }

    /**
     * Get the start of the structure In vanilla the start is usually a static inner
     * class of the structure class, follow this convention if you wish
     *
     * @return method reference to Start constructor
     */
    @Override
    public Structure.IStartFactory<VeCabinFeatureConfig> getStartFactory()
    {
        return VeCabinStructure.Start::new;
    }

    /**
     * The name used for the locate command.
     *
     * @return The name for the structure
     */
    @Override
    public String getStructureName()
    {
        return VanillaExpansions.MOD_ID + ":cabin";
    }

    public static enum Type implements IStringSerializable
    {
        TAIGA_CABIN("taiga_cabin"), BIRCH_FOREST_CABIN("birch_forest_cabin"), FOREST_CABIN("forest_cabin");

        private final String name;

        private Type(String name)
        {
            this.name = name;
        }

        public static final Codec<Type> field_236998_c_ = IStringSerializable.func_233023_a_(Type::values,
                Type::getType);
        private static final Map<String, Type> BY_NAME = Arrays.stream(values())
                .collect(Collectors.toMap(Type::getName, (type) ->
                {
                    return type;
                }));

        public String getName()
        {
            return this.name;
        }

        @Nullable
        public static Type getType(String name)
        {
            return BY_NAME.get(name);
        }

        @Override
        public String func_176610_l()
        {
            return this.name;
        }
    }

    /**
     * The structure start is responsible for creating the structure in memory, but
     * not for placing the blocks themselves
     */
    public static class Start extends StructureStart<VeCabinFeatureConfig>
    {
        public Start(Structure<VeCabinFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox,
                int references, long seed)
        {
            super(structure, chunkX, chunkZ, boundingBox, references, seed);
        }

        /**
         * For most structures this is the only method you will need to care about Not a
         * lot needs to be done here, most of the work is done by structure pieces
         * Examples of things vanilla does for different structures here include: -
         * Getting configs from the chunk generator - Deciding the rotation of the
         * structure - Getting height (rarely, most times height is determined in the
         * piece)
         */
        @Override
        public void func_230364_a_(ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ,
                Biome biome, VeCabinFeatureConfig featureConfig)
        {
            String id = VanillaExpansions.MOD_ID;
            ResourceLocation templateLocation;

            if (VeBiomes.TAIGA_CABIN_BIOMES.contains(biome))// featureConfig.type == VeCabinStructure.Type.TAIGA
            {
                templateLocation = new ResourceLocation(id, VeCabinStructure.Type.TAIGA_CABIN.getName());
            }
            else if (VeBiomes.BIRCH_FOREST_CABIN_BIOMES.contains(biome))// featureConfig.type ==
                                                                        // VeCabinStructure.Type.BIRCH_FOREST
            {
                templateLocation = new ResourceLocation(id, VeCabinStructure.Type.BIRCH_FOREST_CABIN.getName());
            }
            else
            {
                templateLocation = new ResourceLocation(id, VeCabinStructure.Type.FOREST_CABIN.getName());
            }

            Rotation rotation = Rotation.randomRotation(this.rand);
            BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            VeCabinPieces.init(templateManager, templateLocation, blockPos, rotation, this.components);
            this.recalculateStructureSize();
        }
    }
}
