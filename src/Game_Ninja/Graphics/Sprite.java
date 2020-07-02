package Game_Ninja.Graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Sprite { //Là lớp các đối tượng đồ họa của game được gọi từ file
    private BufferedImage image;
    public Sprite(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getBufferedImage() {
        return image;
    }
}
