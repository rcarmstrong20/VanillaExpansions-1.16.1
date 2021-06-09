package rndmaccess.vanilla_expansions.block.util;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

/**
 * A utility class that rotates bounding boxes.
 *
 * @author Ryan
 */
public class VEBoxBlockUtil
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
     * @param axis   The axis to rotate the shapes on.
     * @param shapes The shapes to rotate.
     * @return The shapes passed rotated 90 degrees.
     */
    public static VoxelShape rotate90(@Nullable Axis axis, VoxelShape... shapes)
    {
        return rotate(axis, NINETY_DEGREES, shapes);
    }

    /**
     * @param axis   The axis to rotate the shapes on.
     * @param shapes The shapes to rotate.
     * @return The shapes passed rotated 180 degrees.
     */
    public static VoxelShape rotate180(@Nullable Axis axis, VoxelShape... shapes)
    {
        return rotate(axis, ONE_HUNDRED_EIGHTY_DEGREES, shapes);
    }

    /**
     * @param axis   The axis to rotate the shapes on.
     * @param shapes The shapes to rotate.
     * @return The shapes passed rotated 270 degrees.
     */
    public static VoxelShape rotate270(@Nullable Axis axis, VoxelShape... shapes)
    {
        return rotate(axis, TWO_HUNDRED_SEVENTY_DEGREES, shapes);
    }

    public static VoxelShape rotate(@Nullable Axis axis, double radians, VoxelShape... shapes)
    {
        return rotate(axis, radians, Lists.newArrayList(shapes));
    }

    public static VoxelShape rotate(@Nullable Axis axis, double radians, Collection<VoxelShape> shapes)
    {
        VoxelShape collision = VoxelShapes.empty();
        for (VoxelShape shape : shapes)
        {
            collision = VoxelShapes.or(collision, rotate(axis, radians, shape));
        }
        return collision;
    }

    public static VoxelShape rotate(@Nullable Axis axis, double radians, VoxelShape shape)
    {
        VoxelShape rotatedShapes = VoxelShapes.empty();
        List<AxisAlignedBB> list = shape.toAabbs();
        Pair<Double, Double> min;
        Pair<Double, Double> max;
        VoxelShape rotatedX;
        VoxelShape rotatedZ;
        VoxelShape rotatedY;

        switch (axis)
        {
            case X:
                for (AxisAlignedBB box : list)
                {
                    min = rotatePoint(box.minY, box.minZ, radians);
                    max = rotatePoint(box.maxY, box.maxZ, radians);

                    rotatedX = VoxelShapes.box(box.minX, min.getFirst(), min.getSecond(), box.maxX, max.getFirst(),
                            max.getSecond());

                    rotatedShapes = VoxelShapes.or(rotatedShapes, rotatedX);
                }
                return rotatedShapes;
            case Y:
                for (AxisAlignedBB box : list)
                {
                    min = rotatePoint(box.minX, box.minZ, radians);
                    max = rotatePoint(box.maxX, box.maxZ, radians);

                    rotatedY = VoxelShapes.box(min.getFirst(), box.minY, min.getSecond(), max.getFirst(), box.maxY,
                            max.getSecond());

                    rotatedShapes = VoxelShapes.or(rotatedShapes, rotatedY);
                }
                return rotatedShapes;
            case Z:
                for (AxisAlignedBB box : list)
                {
                    min = rotatePoint(box.minX, box.minY, radians);
                    max = rotatePoint(box.maxX, box.maxY, radians);

                    rotatedZ = VoxelShapes.box(min.getFirst(), min.getSecond(), box.minZ, max.getFirst(),
                            max.getSecond(), box.maxZ);

                    rotatedShapes = VoxelShapes.or(rotatedShapes, rotatedZ);
                }
                return rotatedShapes;
            default:
                return rotatedShapes;
        }
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
