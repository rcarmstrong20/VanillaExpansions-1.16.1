package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class VeSoupItem extends Item
{
    private Item returnItem;

    public VeSoupItem(Properties properties)
    {
        super(properties);
        this.returnItem = Items.BOWL;
    }

    public VeSoupItem(Properties properties, Item returnItem)
    {
        super(properties);
        this.returnItem = returnItem;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stackIn, World worldIn, LivingEntity livingEntityIn)
    {
        super.onItemUseFinish(stackIn, worldIn, livingEntityIn);
        boolean flag = livingEntityIn instanceof PlayerEntity && ((PlayerEntity) livingEntityIn).isCreative();

        return flag ? stackIn : new ItemStack(this.returnItem);
    }
}
