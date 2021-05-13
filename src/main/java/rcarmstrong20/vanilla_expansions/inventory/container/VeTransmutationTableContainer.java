package rcarmstrong20.vanilla_expansions.inventory.container;

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
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeContainerTypes;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.core.VeRecipeTypes;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;
import rcarmstrong20.vanilla_expansions.item.crafting.VeTransmutationRecipe;

public class VeTransmutationTableContainer extends Container
{
    private IWorldPosCallable access;
    private IntReferenceHolder selectedRecipeIndex = IntReferenceHolder.standalone();
    private World level;
    private List<VeTransmutationRecipe> recipes = Lists.newArrayList();
    private long lastSoundTime;
    final Slot essenceInput;
    final Slot tierInput;
    final Slot rubyInput;
    final Slot resultSlot;
    private Runnable slotUpdateListener = () ->
    {};

    public final IInventory inputContainer = new Inventory(3)
    {
        @Override
        public void setChanged()
        {
            super.setChanged();
            VeTransmutationTableContainer.this.slotsChanged(this);
            VeTransmutationTableContainer.this.slotUpdateListener.run();
        }
    };

    private final CraftResultInventory resultContainer = new CraftResultInventory();

    public VeTransmutationTableContainer(int windowIdIn, PlayerInventory playerInventoryIn)
    {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.NULL);
    }

    public VeTransmutationTableContainer(int windowIdIn, PlayerInventory inventoryIn, final IWorldPosCallable accessIn)
    {
        super(VeContainerTypes.transmutationTable, windowIdIn);
        this.access = accessIn;
        this.level = inventoryIn.player.level;

        this.essenceInput = this.addSlot(new Slot(this.inputContainer, 0, 35, 40));
        this.tierInput = this.addSlot(new Slot(this.inputContainer, 1, 62, 24));
        this.rubyInput = this.addSlot(new Slot(this.inputContainer, 2, 62, 56)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return stack.getItem().equals(VeItems.ruby);
            }
        });

        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 3, 116, 40)
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
                VeTransmutationTableContainer.this.resultContainer.awardUsedRecipes(player);
                VeTransmutationTableContainer.this.essenceInput.remove(1);
                VeTransmutationTableContainer.this.tierInput.remove(1);
                VeTransmutationTableContainer.this.rubyInput.remove(1);
                Random random = player.getRandom();

                accessIn.execute((level, pos) ->
                {
                    long l = level.getGameTime();
                    if (VeTransmutationTableContainer.this.lastSoundTime != l)
                    {
                        level.playSound((PlayerEntity) null, pos, VeSoundEvents.uiTransmutationTableTakeResult,
                                SoundCategory.BLOCKS, 1.0F + random.nextFloat(), 1.0F + random.nextFloat());
                        VeTransmutationTableContainer.this.lastSoundTime = l;
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
        return stillValid(this.access, playerIn, VeBlocks.transmutationTable);
    }

    @Override
    public ContainerType<?> getType()
    {
        return VeContainerTypes.transmutationTable;
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
            VeTransmutationRecipe transmutationRecipe = this.recipes.get(this.selectedRecipeIndex.get());
            this.resultContainer.setRecipeUsed(transmutationRecipe);
            this.resultSlot.set(transmutationRecipe.assemble(inputContainer));
        }
        else
        {
            this.resultSlot.set(ItemStack.EMPTY);
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
        this.resultSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty())
        {
            this.recipes = this.level.getRecipeManager().getRecipesFor(VeRecipeTypes.transmutation, inventoryIn,
                    this.level);
        }
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slotIn)
    {
        return slotIn.container != this.resultContainer && super.canTakeItemForPickAll(stack, slotIn);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (index == 1)
            {
                item.onCraftedBy(itemstack1, playerIn.level, playerIn);
                if (!this.moveItemStackTo(itemstack1, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            }
            else if (index == 0)
            {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (this.level.getRecipeManager()
                    .getRecipeFor(VeRecipeTypes.woodcutting, new Inventory(itemstack1), this.level).isPresent())
            {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 2 && index < 29)
            {
                if (!this.moveItemStackTo(itemstack1, 29, 38, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (index >= 29 && index < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
            this.broadcastChanges();
        }
        return itemstack;
    }

    @Override
    public void removed(PlayerEntity playerIn)
    {
        super.removed(playerIn);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((level, pos) ->
        {
            this.clearContainer(playerIn, playerIn.level, this.inputContainer);
        });
    }

    @OnlyIn(Dist.CLIENT)
    public List<VeTransmutationRecipe> getRecipes()
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
