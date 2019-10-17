import functions.Function1;
import functions.IFunction;
import sample.ImageBufferPixelDrawer;
import sample.lineDrawers.DDALineDrawer;
import sample.lineDrawers.WuLineDrawerDDA;
//import sample.lineDrawers.LineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {
        private DDALineDrawer ld;
        private ScreenConverterInterface sc;
        private Line l;
        private boolean isMouseClicked = false;
        private IFunction currentFunction;

        public DrawPanel() {
            super();
            //sc = new ScreenConverter(-2,2,4,4,500,500);
            l = new Line(new RealPoint(0, 0), new RealPoint(1, 1));
        }

        public void setCurrentFunction(IFunction function) {
            this.currentFunction = function;
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            ImageBufferPixelDrawer pd = new ImageBufferPixelDrawer(bi);
            ld = new DDALineDrawer(pd);
            //ld.drawLine((int) getWidth() / 2, 0, (int) getWidth() / 2, (int) getHeight(), Color.CYAN);//todo make constr for points
            ld.drawLine(0, 0, 100, 100, Color.CYAN);
            //ScreenPoint tPoint = new ScreenPoint(0,currentFunction.compute(0));
            for (int x = 0; x < getWidth(); x++) {
//                currentFunction.compute(x);
                //ld.drawLine();
            }
            g.drawImage(bi, 0, 0, null);
        }

        private ScreenPoint last = null;

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            if (last != null) {

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

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            e.getScrollAmount();
        }
    }

    public static void main(String... args) {
        DrawPanel myPanel = new DrawPanel();
        JFrame drawFrame = new JFrame();
        JPanel funcPanel = new JPanel();
        JFrame frame = new JFrame();
        HashMap<JButton, IFunction> buttonIFunctionHashMap = new HashMap<>();


        funcPanel.setSize(300, 500);
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(funcPanel);
        frame.setVisible(true);
        frame.setTitle("Function chooser");
        funcPanel.setVisible(true);
        funcPanel.setLayout(new GridLayout(8, 2));
        boolean flag = true;
        JButton btn;
        for (int i = 0; i < 16; i++) {
            if (flag) {
                btn = new JButton();
                buttonIFunctionHashMap.put(btn, new Function1());
                btn.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myPanel.setCurrentFunction(buttonIFunctionHashMap.get(e.getSource()));
                    }
                });
                funcPanel.add(btn);
            } else
                funcPanel.add(new JTextField(i));
            flag = !flag;
        }

        myPanel.setSize(800, 600);
        drawFrame.setSize(800, 600);
        drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawFrame.setContentPane(myPanel);
        drawFrame.setVisible(true);
        myPanel.setVisible(true);
    }
}
