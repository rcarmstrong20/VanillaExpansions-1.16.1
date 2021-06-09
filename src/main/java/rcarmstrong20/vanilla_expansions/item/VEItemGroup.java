package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import rcarmstrong20.vanilla_expansions.core.VEItems;

public class VEItemGroup extends ItemGroup
{
    public VEItemGroup(String label)
    {
        super(label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(VEItems.ruby);
    }
}
