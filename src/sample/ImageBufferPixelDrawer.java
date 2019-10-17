package sample;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBufferPixelDrawer {//todo switch code to this architecture
    private BufferedImage bi;
    private static ImageBufferPixelDrawer pixelDrawer;

    public ImageBufferPixelDrawer(BufferedImage bi) {
        this.bi = bi;
    }

    public void drawPixel(int x, int y, Color c) {
        bi.setRGB(x,y,c.getRGB());
    }

    //@Override
    public ImageBufferPixelDrawer getInstance(BufferedImage bi) {
        if(pixelDrawer == null){
            pixelDrawer = new ImageBufferPixelDrawer(bi);
            return pixelDrawer;
        } else {
            return pixelDrawer;
        }
    }
}
