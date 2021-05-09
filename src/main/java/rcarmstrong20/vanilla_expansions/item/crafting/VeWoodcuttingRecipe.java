package rcarmstrong20.vanilla_expansions.item.crafting;

import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeRecipeSerializers;
import rcarmstrong20.vanilla_expansions.core.VeRecipeTypes;

public class VeWoodcuttingRecipe implements IRecipe<IInventory>
{
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final ResourceLocation id;
    private final IRecipeType<?> type;
    private final IRecipeSerializer<?> serializer;
    protected final String group;

    public VeWoodcuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result)
    {
        this.type = VeRecipeTypes.woodcutting;
        this.serializer = VeRecipeSerializers.woodcutting;
        this.id = id;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn)
    {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return new ItemStack(VeBlocks.woodcutter);
    }

    @Override
    public ItemStack assemble(IInventory p_77572_1_)
    {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem()
    {
        return this.result;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return this.serializer;
    }

    @Override
    public IRecipeType<?> getType()
    {
        return this.type;
    }

    public static class Serializer<R extends VeWoodcuttingRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<R>
    {
        final IRecipeFactory<R> factory;

        public Serializer(IRecipeFactory<R> factory)
        {
            this.factory = factory;
        }

        @SuppressWarnings("deprecation")
        @Override
        public R fromJson(ResourceLocation recipeId, JsonObject json)
        {
            String s = JSONUtils.getAsString(json, "group", "");
            Ingredient ingredient;
            if (JSONUtils.isArrayNode(json, "ingredient"))
            {
                ingredient = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "ingredient"));
            }
            else
            {
                ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "ingredient"));
            }

            String s1 = JSONUtils.getAsString(json, "result");
            int i = JSONUtils.getAsInt(json, "count");
            ItemStack itemstack = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), i);
            return this.factory.create(recipeId, s, ingredient, itemstack);
        }

        @Override
        public R fromNetwork(ResourceLocation recipeId, PacketBuffer buffer)
        {
            String s = buffer.readUtf(32767);
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            return this.factory.create(recipeId, s, ingredient, itemstack);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, R recipe)
        {
            buffer.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }

        public interface IRecipeFactory<R extends VeWoodcuttingRecipe>
        {
            R create(ResourceLocation p_create_1_, String p_create_2_, Ingredient p_create_3_, ItemStack p_create_4_);
        }
    }
}
