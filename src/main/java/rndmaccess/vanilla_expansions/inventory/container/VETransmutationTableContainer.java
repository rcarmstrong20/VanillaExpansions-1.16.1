package rndmaccess.vanilla_expansions.inventory.container;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.core.VEContainerTypes;
import rndmaccess.vanilla_expansions.core.VEItems;
import rndmaccess.vanilla_expansions.core.VERecipeTypes;
import rndmaccess.vanilla_expansions.core.VESoundEvents;
import rndmaccess.vanilla_expansions.item.crafting.VETransmutationRecipe;

public class VETransmutationTableContainer extends Container
{
    private IWorldPosCallable access;
    private IntReferenceHolder selectedRecipeIndex = IntReferenceHolder.standalone();
    private World level;
    private List<VETransmutationRecipe> recipes = Lists.newArrayList();
    private long lastSoundTime;
    final Slot essenceInput;
    final Slot tierInput;
    final Slot rubyInput;
    final Slot outputSlot;
    private Runnable slotUpdateListener = () ->
    {};

    public final IInventory inputContainer = new Inventory(3)
    {
        @Override
        public void setChanged()
        {
            super.setChanged();
            VETransmutationTableContainer.this.slotsChanged(this);
            VETransmutationTableContainer.this.slotUpdateListener.run();
        }
    };

    private final CraftResultInventory outputContainer = new CraftResultInventory();

    public VETransmutationTableContainer(int windowIdIn, PlayerInventory playerInventoryIn)
    {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.NULL);
    }

    public VETransmutationTableContainer(int windowIdIn, PlayerInventory inventoryIn, final IWorldPosCallable accessIn)
    {
        super(VEContainerTypes.transmutationTable, windowIdIn);
        this.access = accessIn;
        this.level = inventoryIn.player.level;

        this.essenceInput = this.addSlot(new Slot(this.inputContainer, 0, 35, 40));
        this.tierInput = this.addSlot(new Slot(this.inputContainer, 1, 62, 24));
        this.rubyInput = this.addSlot(new Slot(this.inputContainer, 2, 62, 56)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return stack.getItem().equals(VEItems.ruby);
            }
        });

        this.outputSlot = this.addSlot(new Slot(this.outputContainer, 3, 120, 40)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }

            @Override
            public ItemStack onTake(PlayerEntity player, ItemStack stack)
            {
                stack.onCraftedBy(player.level, player, stack.getCount());
                VETransmutationTableContainer.this.outputContainer.awardUsedRecipes(player);
                VETransmutationTableContainer.this.essenceInput.remove(1);
                VETransmutationTableContainer.this.tierInput.remove(1);
                VETransmutationTableContainer.this.rubyInput.remove(1);
                Random random = player.getRandom();

                accessIn.execute((level, pos) ->
                {
                    long l = level.getGameTime();
                    if (VETransmutationTableContainer.this.lastSoundTime != l)
                    {
                        level.playSound((PlayerEntity) null, pos, VESoundEvents.uiTransmutationTableTakeResult,
                                SoundCategory.BLOCKS, 1.0F + random.nextFloat(), 1.0F + random.nextFloat());
                        VETransmutationTableContainer.this.lastSoundTime = l;
                    }
                });
                return super.onTake(player, stack);
            }
        });

        // Add inventory slots (3*9 = 27 slots added here with index starting at
        // OUTPUT+1 and ending at OUTPUT+27)
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventoryIn, j + i * 9 + 9, 8 + j * 18, 94 + i * 18));
            }
        }

        // Add hotbar slots (9 more slots added here, index starts at OUTPUT+27+1 and
        // ends at OUTPUT+27+1+9)
        for (int k = 0; k < 9; ++k)
        {
            this.addSlot(new Slot(inventoryIn, k, 8 + k * 18, 152));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn)
    {
        return stillValid(this.access, playerIn, VEBlocks.transmutationTable);
    }

    @Override
    public ContainerType<?> getType()
    {
        return VEContainerTypes.transmutationTable;
    }

    @OnlyIn(Dist.CLIENT)
    public void registerUpdateListener(Runnable listener)
    {
        this.slotUpdateListener = listener;
    }

    private void setupResultSlot()
    {
        if (!this.recipes.isEmpty())
        {
            VETransmutationRecipe transmutationRecipe = this.recipes.get(this.selectedRecipeIndex.get());
            this.outputContainer.setRecipeUsed(transmutationRecipe);
            this.outputSlot.set(transmutationRecipe.assemble(inputContainer));
        }
        else
        {
            this.outputSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    @Override
    public void slotsChanged(IInventory inventoryIn)
    {
        ItemStack essenceStack = this.essenceInput.getItem();
        ItemStack tierStack = this.tierInput.getItem();
        ItemStack rubyStack = this.rubyInput.getItem();

        if (!essenceStack.isEmpty())
        {
            setupRecipeList(inventoryIn, essenceStack);
        }
        if (!tierStack.isEmpty())
        {
            setupRecipeList(inventoryIn, tierStack);
        }
        if (!rubyStack.isEmpty())
        {
            setupRecipeList(inventoryIn, rubyStack);
        }
        this.setupResultSlot();
    }

    private void setupRecipeList(IInventory inventoryIn, ItemStack stack)
    {
        this.recipes.clear();
        this.outputSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty())
        {
            this.recipes = this.level.getRecipeManager().getRecipesFor(VERecipeTypes.transmutation, inventoryIn,
                    this.level);
        }
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn)
    {
        return slotIn.container != this.outputContainer && super.canTakeItemForPickAll(stack, slotIn);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();

            // Move the stack from the output slot.
            if (index == 3)
            {
                item.onCraftedBy(itemstack1, player.level, player);

                // The moveItemStackTo method takes four arguments
                // (itemStack, startSlot, finalContainerIndex, reverseOrder)
                if (!this.moveItemStackTo(itemstack1, 4, 40, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);
            }
            // Move the stack from the input slots.
            else if (index == 0 || index == 1 || index == 2)
            {
                if (!this.moveItemStackTo(itemstack1, 4, 40, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            // Move the stack into the input slots.
            else if (itemstack1.getItem().equals(VEItems.ruby) || this.isIngredientInRecipe(itemstack1))
            {
                if (this.moveItemStackTo(itemstack1, 2, 3, false))
                {
                    return ItemStack.EMPTY;
                }
                else if (this.moveItemStackTo(itemstack1, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
                else if (this.moveItemStackTo(itemstack1, 1, 2, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            // Move the stack around the inventory.
            else if (index >= 4 && index < 31)
            {
                if (!this.moveItemStackTo(itemstack1, 31, 40, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 31 && index < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false))
            {
                return ItemStack.EMPTY;
            }

            // The stack moved.
            if (itemstack1.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            // The stack didn't move.
            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
            this.broadcastChanges();
        }
        return itemstack;
    }

    @Override
    public void removed(PlayerEntity playerIn)
    {
        super.removed(playerIn);
        this.outputContainer.removeItemNoUpdate(1);
        this.access.execute((level, pos) ->
        {
            this.clearContainer(playerIn, playerIn.level, this.inputContainer);
        });
    }

    private boolean isIngredientInRecipe(ItemStack stack)
    {
        Ingredient ingredient = Ingredient.of(stack);

        for (VETransmutationRecipe recipe : this.getRecipes())
        {
            if (recipe.getIngredients().contains(ingredient))
            {
                return true;
            }
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public List<VETransmutationRecipe> getRecipes()
    {
        return this.recipes;
    }

    @OnlyIn(Dist.CLIENT)
    public int getNumRecipes()
    {
        return this.recipes.size();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasInputItems()
    {
        return this.essenceInput.hasItem() && this.rubyInput.hasItem() && this.tierInput.hasItem()
                && !this.recipes.isEmpty();
    }
}
