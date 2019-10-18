package functions;

import static java.lang.Math.pow;

public class Function2 implements IFunction {
    @Override
    public double compute(double x) {
        return pow(x,3)-pow(x,2);
    }
}
