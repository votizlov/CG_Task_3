package functions;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class Function3 implements IFunction {
    @Override
    public double compute(double x) {
        return pow(x,1/3)*sin(x);
    }

    @Override
    public String getString() {
        return "x^(1/3)*sin(x)";
    }
}
