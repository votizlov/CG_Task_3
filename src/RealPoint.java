public class RealPoint {
    private double x,y;

    public RealPoint(double x,double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "RealPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
