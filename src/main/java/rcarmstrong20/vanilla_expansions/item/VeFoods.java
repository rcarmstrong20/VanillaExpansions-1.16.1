package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.item.Food;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class VEFoods
{
    public static Food garlic = new Food.Builder().nutrition(4).saturationMod(0.2F).build();
    public static Food greenOnion = new Food.Builder().nutrition(2).saturationMod(0.2F).build();
    public static Food bokChoy = new Food.Builder().nutrition(5).saturationMod(0.3F).build();
    public static Food cookedNoodles = new Food.Builder().nutrition(4).saturationMod(0.5F).build();
    public static Food noodleSoup = stew(10);
    public static Food pudding = stew(6);
    public static Food berryPudding = stew(8);
    public static Food caramelApple = new Food.Builder().nutrition(5).saturationMod(0.5F).build();
    public static Food witchsCradleBranch = new Food.Builder().nutrition(4).saturationMod(0.6F).build();
    public static Food witchsCradleSoup = magicStew(7, Effects.NIGHT_VISION);
    public static Food bloodVile = new Food.Builder().nutrition(3).saturationMod(0.6F).build();
    public static Food cranberries = new Food.Builder().nutrition(2).saturationMod(0.3F).build();
    public static Food blueberries = new Food.Builder().nutrition(3).saturationMod(0.2F).build();
    public static Food cranberrySauce = new Food.Builder().nutrition(5).saturationMod(0.6F).build();
    public static Food spruceCone = new Food.Builder().nutrition(2).saturationMod(0.3F).build();
    public static Food forestsBounty = stew(5);
    public static Food greenCattail = new Food.Builder().nutrition(4).saturationMod(0.5F).build();

    private static Food stew(int nutrition)
    {
        return new Food.Builder().nutrition(nutrition).saturationMod(0.6F).build();
    }

    private static Food magicStew(int nutrition, Effect effect)
    {
        return new Food.Builder().nutrition(nutrition).saturationMod(0.6F)
                .effect(() -> new EffectInstance(effect, 1000, 0, true, true), 1.0F).build();
    }
}
