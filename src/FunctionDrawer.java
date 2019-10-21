import functions.IFunction;

import java.awt.*;

public class FunctionDrawer {
    public void drawFunction(DDALineDrawer lineDrawer, ScreenConverter sc, IFunction f, Color c) {
        RealPoint last = new RealPoint(sc.getXr(), f.compute(sc.getXr()));
        RealPoint r = last;
        //double d = sc.getWr() * 3 / sc.getWs();

        for (int i = 0; i < sc.getWs(); i++) {
            last = r;
            r = new RealPoint(sc.getXr() + i, f.compute(sc.getXr() + i));//r = new RealPoint(sc.getXr() + d * i, f.compute(sc.getXr() + d * i));
            System.out.println(last.toString());
            System.out.println(r.toString());
            lineDrawer.drawLine(sc.realToScreen(last), sc.realToScreen(r), c);
            //lineDrawer.drawLine(last, r, c);
            System.out.println(sc.realToScreen(last).toString());
            System.out.println(sc.realToScreen(r).toString());
        }
    }
}
