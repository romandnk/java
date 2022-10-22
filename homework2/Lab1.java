package homework2;
import org.apache.commons.math3.util.Precision;
import java.util.Scanner;
public class Lab1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x1: ");
        double x1 = scanner.nextDouble();
        System.out.println("Enter y1: ");
        double y1 = scanner.nextDouble();
        System.out.println("Enter z1: ");
        double z1 = scanner.nextDouble();
        System.out.println("Enter x2: ");
        double x2 = scanner.nextDouble();
        System.out.println("Enter y2: ");
        double y2 = scanner.nextDouble();
        System.out.println("Enter z2: ");
        double z2 = scanner.nextDouble();
        System.out.println("Enter x3: ");
        double x3 = scanner.nextDouble();
        System.out.println("Enter y3: ");
        double y3 = scanner.nextDouble();
        System.out.println("Enter z3: ");
        double z3 = scanner.nextDouble();

        Point3d firstPoint = new Point3d(x1, y1, z1);
        Point3d secondPoint = new Point3d(x2, y2, z2);
        Point3d thirdPoint = new Point3d(x3, y3, z3);

        if (firstPoint.equals(secondPoint) || firstPoint.equals(thirdPoint) || secondPoint.equals(thirdPoint)) {
            System.out.println("The coordinates match");
            return;
        } else {
            double area = computeArea(firstPoint, secondPoint, thirdPoint);
            System.out.println("Area is " + area);
        }
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
