import java.awt.*;

public interface ScreenConverterInterface {//% coefficient of x,y. wReal, hReal, wScreen, hScreen, xReal, yReal квадрат по клику мыши, в идеале масштаб
    public ScreenPoint realToScreen(RealPoint point);
    public RealPoint screenToReal(ScreenPoint point);
}
