import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Main {

    static class DrawPanel extends JPanel implements MouseMotionListener, MouseListener {
        private Object ld;
        private ScreenConverterInterface sc;
        private Line l;
        private boolean isMouseClicked = false;

        public DrawPanel(){
            super();
            //sc = new ScreenConverter(-2,2,4,4,500,500);
            l = new Line(new RealPoint(0,0),new RealPoint(1,1));
        }

        @Override
        public void paint(Graphics g){
            BufferedImage bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
            if(ld!=null){
                //ld.drawLine();//todo make constr for points
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
            //ScreenPoint screenPoint = new ScreenPoint();
            //l.setP2(sc.screenToReal(screenPoint));
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
    public static void main(String... args){
        DrawPanel myPanel = new DrawPanel();
         JFrame drawFrame = new JFrame();
        JPanel funcPanel = new JPanel();
        JFrame frame = new JFrame();

        funcPanel.setSize(200, 500);
        frame.setSize(200, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(funcPanel);
        frame.setVisible(true);
        frame.setTitle("Function chooser");
        funcPanel.setVisible(true);
        funcPanel.setLayout(new GridLayout(8, 2));
        boolean flag = true;
        for(int i =0;i<16;i++){
            if(flag)
                funcPanel.add(new JRadioButton());
            else
                funcPanel.add(new JTextField(i));
            flag=!flag;
        }

        myPanel.setSize(800, 600);
        drawFrame.setSize(800, 600);
        drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawFrame.setContentPane(myPanel);
        drawFrame.setVisible(true);
        myPanel.setVisible(true);
    }
}
