import java.awt.*;

public interface ScreenConverter {//% coefficient of x,y. wReal, hReal, wScreen, hScreen, xReal, yReal квадрат по клику мыши, в идеале масштаб
    public Point realToScreen();
    public Point screenToReal();
}
