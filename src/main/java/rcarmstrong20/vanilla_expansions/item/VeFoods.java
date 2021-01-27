package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.item.Food;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class VeFoods
{
    public static Food garlic = new Food.Builder().hunger(4).saturation(0.2F).build();
    public static Food greenOnion = new Food.Builder().hunger(2).saturation(0.2F).build();
    public static Food bokChoy = new Food.Builder().hunger(5).saturation(0.3F).build();
    public static Food cookedNoodles = new Food.Builder().hunger(4).saturation(0.5F).build();
    public static Food noodleSoup = buildStew(10);
    public static Food pudding = buildStew(6);
    public static Food berryPudding = buildStew(8);
    public static Food caramelApple = new Food.Builder().hunger(5).saturation(0.5F).build();
    public static Food witchsCradleBranch = new Food.Builder().hunger(4).saturation(0.6F).build();
    public static Food witchsCradleSoup = buildMagicStew(7, Effects.NIGHT_VISION);
    public static Food bloodVile = new Food.Builder().hunger(3).saturation(0.6F).build();
    public static Food cranberries = new Food.Builder().hunger(2).saturation(0.3F).build();
    public static Food blueberries = new Food.Builder().hunger(3).saturation(0.2F).build();
    public static Food cranberrySauce = new Food.Builder().hunger(5).saturation(0.6F).build();
    public static Food spruceCone = new Food.Builder().hunger(2).saturation(0.3F).build();
    public static Food forestsBounty = buildStew(5);

    private static Food buildStew(int hunger)
    {
        return new Food.Builder().hunger(hunger).saturation(0.6F).build();
    }

    private static Food buildMagicStew(int hunger, Effect effect)
    {
        return new Food.Builder().hunger(hunger).saturation(0.6F)
                .effect(() -> new EffectInstance(effect, 1000, 0, true, true), 1.0F).build();
    }
}
