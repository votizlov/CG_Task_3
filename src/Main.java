import functions.*;
import sample.ImageBufferPixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Main {

    static class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {
        private DDALineDrawer ld;
        private ScreenConverter sc;
        private ScreenPoint last = new ScreenPoint(400, 300);
        private boolean isMouseClicked = false;
        private IFunction currentFunction = new DefaultFunction();
        private FunctionDrawer functionDrawer = new FunctionDrawer();

        public DrawPanel() {
            super();
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);
            sc = new ScreenConverter(-2, 2, 4, 4, getWidth(), getHeight());
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
            ld.drawLine(last.getI(), 0, last.getI(), getHeight(), Color.CYAN);
            ld.drawLine(0, last.getJ(), getWidth(), last.getJ(), Color.CYAN);
            RealPoint previousPoint = new RealPoint(0, currentFunction.compute(0));
            RealPoint nextPoint = previousPoint;

            for (double x = 0; x < getWidth(); x++) {
                previousPoint = nextPoint;
                nextPoint = new RealPoint(x, currentFunction.compute(x));
                //ld.drawLine(sc.realToScreen(previousPoint), sc.realToScreen(nextPoint), Color.ORANGE);
                ld.drawLine((int) (previousPoint.getX()+last.getI()),(int) ((previousPoint.getY()*10+last.getJ())),(int) (x+last.getI()),(int) ((currentFunction.compute(x)*10+last.getJ())),Color.CYAN);
                //previousPoint = nextPoint;
            }

            //functionDrawer.drawFunction(ld,sc,currentFunction,Color.cyan,getWidth());
            g.setColor(Color.CYAN);
            g.drawImage(bi, 0, 0, null);
            for (int i = 0; i < getWidth(); i += 100) {
                g.drawString(String.valueOf(i), last.getI() + i, /*getHeight() / 2 - */last.getJ());
            }
            for (int i = 0; i < getHeight(); i += 100) {
                g.drawString(String.valueOf(i), /*getWidth() / 2 -*/ last.getI(), last.getJ() + i);
            }
            for (int i = 0; i > -getWidth(); i -= 100) {
                g.drawString(String.valueOf(i), last.getI() + i, /*getHeight() / 2 - */last.getJ());
            }
            for (int i = 0; i > -getHeight(); i -= 100) {
                g.drawString(String.valueOf(i), /*getWidth() / 2 -*/ last.getI(), last.getJ() + i);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (last != null) {
                ScreenPoint cur = new ScreenPoint(e.getX(), e.getY());
                int dj = cur.getJ() - last.getJ();
                int di = cur.getI() - last.getI();
                RealPoint d = sc.screenToReal(new ScreenPoint(di, dj));
                sc.setXr(d.getX());
                sc.setYr(d.getY());
                last = cur;
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //ScreenPoint m = new ScreenPoint(e.getX(), e.getY());
            //repaint();
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }

        int ZOOM = 0;
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            int notches = e.getWheelRotation();

            if (ZOOM + notches < 15 && ZOOM + notches > -20) {
                ZOOM += notches;
                double amount = Math.pow(1.1, e.getScrollAmount());
                if (notches > 0) {
                    RealPoint r = sc.screenToReal(new ScreenPoint(e.getX(), e.getY()));
                    double newX = r.getX() - (r.getX() - sc.getXr()) * amount;
                    double newY = r.getY() - (r.getY() - sc.getYr()) * amount;

                    sc.setXr(newX);
                    sc.setYr(newY);

                    sc.setHr(sc.getHr() * amount);
                    sc.setWr(sc.getWr() * amount);


                    repaint();
                } else if (notches < 0) {
                    RealPoint r = sc.screenToReal(new ScreenPoint(e.getX(), e.getY()));
                    double newX = r.getX() - (r.getX() - sc.getXr()) / amount;
                    double newY = r.getY() - (r.getY() - sc.getYr()) / amount;

                    sc.setXr(newX);
                    sc.setYr(newY);

                    sc.setHr(sc.getHr() / amount);
                    sc.setWr(sc.getWr() / amount);
                    repaint();
                }
            }
        }
    }

    public static void main(String... args) {
        DrawPanel myPanel = new DrawPanel();
        JFrame drawFrame = new JFrame();
        JPanel funcPanel = new JPanel();
        JFrame frame = new JFrame();
        HashMap<JButton, IFunction> buttonIFunctionHashMap = new HashMap<>();
        IFunction[] functions = new IFunction[]{new Function1(),new Function2(),new Function3(),new Function4(),new Function5(),new Function6(), new Function7()};
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
        int j =0;
        for (int i = 0; i < 16; i++) {
            if (flag) {
                btn = new JButton();
                buttonIFunctionHashMap.put(btn, functions[j]);
                if(j<6)
                    j++;
                btn.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myPanel.setCurrentFunction(buttonIFunctionHashMap.get(e.getSource()));
                    }
                });
                funcPanel.add(btn);
            } else {
                JTextField t = new JTextField(functions[j].getString());
                t.setEditable(false);
                funcPanel.add(t);
            }
            flag = !flag;
        }
        myPanel.setSize(800, 600);
        drawFrame.setSize(800, 600);
        drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawFrame.setContentPane(myPanel);
        drawFrame.setVisible(true);
        drawFrame.requestFocus();
        myPanel.setVisible(true);
        myPanel.grabFocus();
    }
}
