package rcarmstrong20.vanilla_expansions.item;

import java.util.List;
import java.util.Map;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.util.VeTimeUtil;

public class VeTotemItem extends Item
{
    private Map<Item, Integer> map = null;
    private int startTick = 0;
    private int maxTick = 0;
    private int endTick = 0;
    private int cooldown = 0;

    public VeTotemItem(Properties properties, Map<Item, Integer> map)
    {
        super(properties);
        this.map = map;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        TranslationTextComponent textComponent;

        if (cooldown <= 0)
        {
            textComponent = new TranslationTextComponent("totem.ready");
        }
        else
        {
            textComponent = new TranslationTextComponent(StringUtils.formatTickDuration(cooldown));
        }

        text.add(textComponent.withStyle(TextFormatting.DARK_BLUE));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int tick, boolean bool)
    {
        if (entity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) entity;
            Item item = stack.getItem();

            if (player.getCooldowns().isOnCooldown(item))
            {
                endTick = player.tickCount;
                cooldown = maxTick - (endTick - startTick);
            }
            else
            {
                startTick = player.tickCount;
                Integer val = map.get(item);

                if (val != null)
                {
                    maxTick = VeTimeUtil.convertSecsToTicks(val);
                }
            }
        }
    }
}
