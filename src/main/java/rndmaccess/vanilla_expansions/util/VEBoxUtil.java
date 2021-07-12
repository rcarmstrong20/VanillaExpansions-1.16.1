package rndmaccess.vanilla_expansions.util;

import java.util.List;

import com.mojang.datafixers.util.Pair;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

/**
 * A utility class that rotates bounding boxes.
 *
 * @author Ryan
 */
public class VEBoxUtil
{
    private static final double CENTER = 0.5;
    private static final double NINETY_DEGREES = Math.toRadians(90);
    private static final double ONE_HUNDRED_EIGHTY_DEGREES = Math.toRadians(180);
    private static final double TWO_HUNDRED_SEVENTY_DEGREES = Math.toRadians(270);

    /**
     * Use the second argument's shapes to cut smaller shapes into the first
     * argument's shape and return the new shape.
     */
    public static VoxelShape cutBox(VoxelShape shape, VoxelShape... cutShapes)
    {
        for (int i = 0; i < cutShapes.length; i++)
        {
            shape = VoxelShapes.join(shape, cutShapes[i], IBooleanFunction.ONLY_FIRST);
        }
        return shape;
    }

    /**
     * @param shape The shape to rotate.
     * @return The shape passed rotated 90 radians on the Y axis.
     */
    public static VoxelShape rotate90(VoxelShape shape)
    {
        return rotateY(NINETY_DEGREES, shape);
    }

    /**
     * @param shape The shape to rotate.
     * @return The shape passed rotated 180 radians on the Y axis.
     */
    public static VoxelShape rotate180(VoxelShape shape)
    {
        return rotateY(ONE_HUNDRED_EIGHTY_DEGREES, shape);
    }

    /**
     * @param shape The shape to rotate.
     * @return The shape passed rotated 270 radians on the Y axis.
     */
    public static VoxelShape rotate270(VoxelShape shape)
    {
        return rotateY(TWO_HUNDRED_SEVENTY_DEGREES, shape);
    }

    /**
     * Rotates each box the number of radians on the Y axis.
     *
     * @param radians The degree to rotate.
     * @param shape   The shape to rotate.
     * @return A VoxelShape that contains all the boxes rotated.
     */
    public static VoxelShape rotateY(double radians, VoxelShape shape)
    {
        VoxelShape rotatedShapes = VoxelShapes.empty();
        List<AxisAlignedBB> boxList = shape.toAabbs();
        VoxelShape rotated;

        for (AxisAlignedBB box : boxList)
        {
            Pair<Double, Double> min = rotatePoint(box.minX, box.minZ, radians);
            Pair<Double, Double> max = rotatePoint(box.maxX, box.maxZ, radians);

            rotated = VoxelShapes.box(min.getFirst(), box.minY, min.getSecond(), max.getFirst(), box.maxY,
                    max.getSecond());

            rotatedShapes = VoxelShapes.or(rotatedShapes, rotated);
        }
        return rotatedShapes;
    }

    private static Pair<Double, Double> rotatePoint(double p1, double p2, double rotation)
    {
        return rotatePoint(p1, p2, rotation, CENTER);
    }

    private static Pair<Double, Double> rotatePoint(double p1, double p2, double rotation, double center)
    {
        return Pair.of(((p1 - center) * Math.cos(rotation) - ((p2 - center) * Math.sin(rotation))) + center,
                ((p1 - center) * Math.sin(rotation)) + ((p2 - center) * Math.cos(rotation)) + center);
    }
}
