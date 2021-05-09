package rcarmstrong20.vanilla_expansions;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import rcarmstrong20.vanilla_expansions.core.VeItems;

public class VeItemGroup extends ItemGroup
{
    public VeItemGroup(String label)
    {
        super(label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(VeItems.ruby);
    }
}
