package homework2;

import org.apache.commons.math3.util.Precision;

import java.io.ObjectInputStream;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3d point3d = (Point3d) o;
        return Double.compare(point3d.zCoord, zCoord) == 0;
    }
}
