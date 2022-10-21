package homework2;

import org.apache.commons.math3.util.Precision;

import java.io.ObjectInputStream;

public class Point3d extends Point2d {

    private double zCoord;
    public Point3d(double x, double y, double z) {
        super(x, y);
        zCoord = z;
    }

    public Point3d() {
        this(0,0,0);
    }

    public double getZ() {
        return zCoord;
    }


    public void setZ(double val) {
        zCoord = val;
    }

    public double distanceTo(Point3d objectPoint3d) {
        double distance = Math.sqrt(Math.pow(this.getX()-objectPoint3d.getX(),2) + Math.pow(this.getY()-objectPoint3d.getY(),2)+Math.pow(this.getZ()-objectPoint3d.getZ(),2));
        return Precision.round(distance,2);
    }

    public static double computeArea(Point3d first, Point3d second, Point3d third) {
        double firstSide = first.distanceTo(second);
        double secondSide = second.distanceTo(third);
        double thirdSide = third.distanceTo(first);

        if (firstSide >= (secondSide+thirdSide) || secondSide >= (firstSide+thirdSide) || thirdSide >= (firstSide+secondSide)) {
            System.out.println("The sum of any two sides must be greater than the third");
            return -1;
        }
        double p = (firstSide + secondSide + thirdSide) / 2;
        return Precision.round(Math.sqrt(p*(p-firstSide)*(p-secondSide)*(p-thirdSide)),2);
    }
}
