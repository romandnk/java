package homework5;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        int iteration = 0;
        double zreal = 0;
        double zimaginary = 0;
        while (iteration < MAX_ITERATIONS && zreal * zreal + zimaginary * zimaginary < 4) {
            double zrealNew = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryNew = 2 * Math.abs(zreal) * Math.abs(zimaginary) + y;

            zreal = zrealNew;
            zimaginary = zimaginaryNew;

            iteration += 1;
            if (iteration == MAX_ITERATIONS) {
                return -1;
            }
        }
        return iteration;
    }

    @Override
    public String toString() {
        return "BurningShip";
    }
}
