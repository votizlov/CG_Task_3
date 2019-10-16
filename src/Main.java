import sample.lineDrawers.DDALineDrawer;
import sample.lineDrawers.LineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String... args){
        class DrawPanel extends JPanel implements MouseMotionListener, MouseListener {
            private LineDrawer ld;
            private ScreenConverterInterface sc;
            private Line l;
            private boolean isMouseClicked = false;

            public DrawPanel(){
                super();
                sc = new ScreenConverter(-2,2,4,4,500,500);
                l = new Line(new RealPoint(0,0),new RealPoint(1,1));
            }

            @Override
            public void paint(Graphics g){
                BufferedImage bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
                if(ld!=null){
                    ld.drawLine();//todo make constr for points
                }
            }
            private ScreenPoint last = null;
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                if(last!=null){

                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                ScreenPoint screenPoint = new ScreenPoint();
                l.setP2(sc.screenToReal(screenPoint));
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                isMouseClicked = !isMouseClicked;
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                isMouseClicked = !isMouseClicked;
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        }
    }
}
