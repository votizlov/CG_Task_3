package functions;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class Function1 implements IFunction {
    @Override
    public double compute(double x) {
        return pow(Math.E,sin(x*3));
    }

    @Override
    public String getString() {
        return "e^sin(3x)";
    }
}
