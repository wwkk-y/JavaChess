package code.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UiUtils {
    /**
     * 指定路径读取图像
     * @param path 路径
     * @return 对应图像
     */
    public static BufferedImage getImg(String path){
        BufferedImage image = null;
        try {
            File inputFile = new File(path);
            image = ImageIO.read(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage cutImg(BufferedImage img, int width, int height){
        BufferedImage rst = new BufferedImage(width, height, img.getType());
        rst.getGraphics().drawImage(img,
                0, 0, width, height,
                0, 0, img.getWidth(), img.getHeight(),
                null);
        return rst;
    }

    public static BufferedImage getImg(String path,int width, int height){
        return cutImg(getImg(path),width,height);
    }
}
