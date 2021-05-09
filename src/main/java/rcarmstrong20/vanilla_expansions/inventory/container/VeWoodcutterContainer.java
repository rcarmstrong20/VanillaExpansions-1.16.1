package rcarmstrong20.vanilla_expansions.inventory.container;

import java.util.List;

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
import rcarmstrong20.vanilla_expansions.core.VeRecipeTypes;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

public class VeWoodcutterContainer extends Container
{
    private IWorldPosCallable access;
    private IntReferenceHolder selectedRecipeIndex = IntReferenceHolder.standalone();
    private World level;
    private List<VeWoodcuttingRecipe> recipes = Lists.newArrayList();
    private ItemStack input = ItemStack.EMPTY;
    private long lastSoundTime;
    final Slot inputSlot;
    final Slot resultSlot;
    private Runnable slotUpdateListener = () ->
    {};

    public final IInventory inputInventory = new Inventory(1)
    {
        @Override
        public void setChanged()
        {
            super.setChanged();
            VeWoodcutterContainer.this.slotsChanged(this);
            VeWoodcutterContainer.this.slotUpdateListener.run();
        }
    };

    private final CraftResultInventory resultContainer = new CraftResultInventory();

    public VeWoodcutterContainer(int windowIdIn, PlayerInventory playerInventoryIn)
    {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.NULL);
    }

    public VeWoodcutterContainer(int windowIdIn, PlayerInventory inventoryIn, final IWorldPosCallable accessIn)
    {
        super(VeContainerTypes.woodcutter, windowIdIn);
        this.access = accessIn;
        this.level = inventoryIn.player.level;
        this.inputSlot = this.addSlot(new Slot(this.inputInventory, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }

            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack)
            {
                stack.onCraftedBy(thePlayer.level, thePlayer, stack.getCount());
                VeWoodcutterContainer.this.resultContainer.awardUsedRecipes(thePlayer);
                ItemStack itemstack = VeWoodcutterContainer.this.inputSlot.remove(1);
                if (!itemstack.isEmpty())
                {
                    VeWoodcutterContainer.this.setupResultSlot();
                }

                accessIn.execute((level, pos) ->
                {
                    long l = level.getGameTime();
                    if (VeWoodcutterContainer.this.lastSoundTime != l)
                    {
                        level.playSound((PlayerEntity) null, pos, VeSoundEvents.uiWoodcutterTakeResult,
                                SoundCategory.BLOCKS, 1.0F, 1.0F);
                        VeWoodcutterContainer.this.lastSoundTime = l;
                    }
                });
                return super.onTake(thePlayer, stack);
            }
        });

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlot(new Slot(inventoryIn, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn)
    {
        return stillValid(this.access, playerIn, VeBlocks.woodcutter);
    }

    @Override
    public boolean clickMenuButton(PlayerEntity playerIn, int idIn)
    {
        if (this.isValidRecipeIndex(idIn))
        {
            this.selectedRecipeIndex.set(idIn);
            this.setupResultSlot();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int idIn)
    {
        return idIn >= 0 && idIn < this.recipes.size();
    }

    @Override
    public void slotsChanged(IInventory inventoryIn)
    {
        ItemStack itemstack = this.inputSlot.getItem();
        if (itemstack.getItem() != this.input.getItem())
        {
            this.input = itemstack.copy();
            this.setupRecipeList(inventoryIn, itemstack);
        }
    }

    private void setupRecipeList(IInventory inventoryIn, ItemStack stack)
    {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty())
        {
            this.recipes = this.level.getRecipeManager().getRecipesFor(VeRecipeTypes.woodcutting, inventoryIn,
                    this.level);
        }
    }

    private void setupResultSlot()
    {
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get()))
        {
            VeWoodcuttingRecipe woodcuttingRecipe = this.recipes.get(this.selectedRecipeIndex.get());
            this.resultContainer.setRecipeUsed(woodcuttingRecipe);
            this.resultSlot.set(woodcuttingRecipe.assemble(this.inputInventory));
        }
        else
        {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    @Override
    public ContainerType<?> getType()
    {
        return VeContainerTypes.woodcutter;
    }

    @OnlyIn(Dist.CLIENT)
    public void registerUpdateListener(Runnable listener)
    {
        this.slotUpdateListener = listener;
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
            this.clearContainer(playerIn, playerIn.level, this.inputInventory);
        });
    }

    @OnlyIn(Dist.CLIENT)
    public int getSelectedRecipeIndex()
    {
        return this.selectedRecipeIndex.get();
    }

    @OnlyIn(Dist.CLIENT)
    public List<VeWoodcuttingRecipe> getRecipes()
    {
        return this.recipes;
    }

    @OnlyIn(Dist.CLIENT)
    public int getNumRecipes()
    {
        return this.recipes.size();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasInputItem()
    {
        return this.inputSlot.hasItem() && !this.recipes.isEmpty();
    }
}
