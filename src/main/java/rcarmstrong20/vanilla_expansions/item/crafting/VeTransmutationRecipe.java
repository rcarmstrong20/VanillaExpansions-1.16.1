package rcarmstrong20.vanilla_expansions.item.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeRecipeSerializers;
import rcarmstrong20.vanilla_expansions.core.VeRecipeTypes;

public class VeTransmutationRecipe implements IRecipe<IInventory>
{
    protected final NonNullList<Ingredient> ingredients;
    protected final ItemStack result;
    protected final ResourceLocation id;
    private final IRecipeType<?> type;
    private final IRecipeSerializer<?> serializer;
    protected final String group;

    public VeTransmutationRecipe(ResourceLocation id, String group, ItemStack result,
            NonNullList<Ingredient> ingredients)
    {
        this.type = VeRecipeTypes.transmutation;
        this.serializer = VeRecipeSerializers.transmutation;
        this.id = id;
        this.group = group;
        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn)
    {
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < inv.getContainerSize(); ++j)
        {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty())
            {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && RecipeMatcher.findMatches(inputs, this.ingredients) != null;
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return new ItemStack(VeBlocks.transmutationTable);
    }

    @Override
    public ItemStack assemble(IInventory inv)
    {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
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
        return this.ingredients;
    }

    @Override
    public IRecipeType<?> getType()
    {
        return this.type;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<VeTransmutationRecipe>
    {
        @Override
        public VeTransmutationRecipe fromJson(ResourceLocation recipeId, JsonObject json)
        {
            String group = JSONUtils.getAsString(json, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(JSONUtils.getAsJsonArray(json, "ingredients"));
            if (nonnulllist.isEmpty())
            {
                throw new JsonParseException("No ingredients for transmutation recipe");
            }
            else if (nonnulllist.size() > 3)
            {
                throw new JsonParseException("Too many ingredients for transmutation recipe the max is " + 2);
            }
            else
            {
                ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
                return new VeTransmutationRecipe(recipeId, group, itemstack, nonnulllist);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray jsonArray)
        {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < jsonArray.size(); ++i)
            {
                Ingredient ingredient = Ingredient.fromJson(jsonArray.get(i));
                if (!ingredient.isEmpty())
                {
                    ingredients.add(ingredient);
                }
            }
            return ingredients;
        }

        @Override
        public VeTransmutationRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer)
        {
            String group = buffer.readUtf(32767);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < ingredients.size(); ++j)
            {
                ingredients.set(j, Ingredient.fromNetwork(buffer));
            }

            ItemStack itemstack = buffer.readItem();
            return new VeTransmutationRecipe(recipeId, group, itemstack, ingredients);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, VeTransmutationRecipe recipe)
        {
            buffer.writeUtf(recipe.group);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients)
            {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.result);
        }
    }
}
