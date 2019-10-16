import java.awt.*;

public class ScreenConverter implements ScreenConverterInterface {
    private double xr,yr,wr,hr;
    private int ws,hs;
    @Override
    public ScreenPoint realToScreen(RealPoint point) {
        int i = (int)((point.getX() - xr)*ws/wr);
        int j = (int)((yr - point.getY())*hs/hr);
        return new ScreenPoint(i,j);
    }

    @Override
    public RealPoint screenToReal(ScreenPoint point) {
        double x = xr + point.getI() * wr/ws;
        double y = yr - point.getJ() * hr/hs;
        return new RealPoint(x,y);
    }

    public double getXr() {
        return xr;
    }

    public void setXr(double xr) {
        this.xr = xr;
    }

    public double getYr() {
        return yr;
    }

    public void setYr(double yr) {
        this.yr = yr;
    }

    public double getWr() {
        return wr;
    }

    public void setWr(double wr) {
        this.wr = wr;
    }

    public double getHr() {
        return hr;
    }

    public void setHr(double hr) {
        this.hr = hr;
    }

    public int getHs() {
        return hs;
    }

    public void setHs(int hs) {
        this.hs = hs;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }
}
