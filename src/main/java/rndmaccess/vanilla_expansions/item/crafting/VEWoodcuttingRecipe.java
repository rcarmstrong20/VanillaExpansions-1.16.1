package rndmaccess.vanilla_expansions.item.crafting;

import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.core.VERecipeSerializers;
import rndmaccess.vanilla_expansions.core.VERecipeTypes;

public class VEWoodcuttingRecipe implements IRecipe<IInventory>
{
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final ResourceLocation id;
    private final IRecipeType<?> type;
    private final IRecipeSerializer<?> serializer;
    protected final String group;

    public VEWoodcuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result)
    {
        this.type = VERecipeTypes.woodcutting;
        this.serializer = VERecipeSerializers.woodcutting;
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
        return new ItemStack(VEBlocks.woodcutter);
    }

    @Override
    public ItemStack assemble(IInventory inv)
    {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int height, int width)
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
    public NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.withSize(1, this.ingredient);
    }

    @Override
    public IRecipeType<?> getType()
    {
        return this.type;
    }

    public static class Serializer<R extends VEWoodcuttingRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<R>
    {
        final IRecipeFactory<R> factory;

        public Serializer(IRecipeFactory<R> factory)
        {
            this.factory = factory;
        }

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
            ItemStack itemstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(s1)), i);
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

        public interface IRecipeFactory<R extends VEWoodcuttingRecipe>
        {
            R create(ResourceLocation p_create_1_, String p_create_2_, Ingredient p_create_3_, ItemStack p_create_4_);
        }
    }
}
