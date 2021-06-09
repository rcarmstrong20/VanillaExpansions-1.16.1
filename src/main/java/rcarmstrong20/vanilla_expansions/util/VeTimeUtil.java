package rcarmstrong20.vanilla_expansions.util;

public class VETimeUtil
{
    /**
     * Converts seconds to Minecraft ticks.
     *
     * @param sec The number of seconds.
     * @return The equivalent number of ticks.
     */
    public static int convertSecsToTicks(int sec)
    {
        return sec * 20;
    }
}
