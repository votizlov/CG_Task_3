package functions;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class Function4 implements  IFunction {
    @Override
    public double compute(double x) {
        return log(pow(x,2)+1)/(pow(x,2)+2);
    }

    @Override
    public String getString() {
        return "log(x^2+1)/(x^2+2)";
    }
}
