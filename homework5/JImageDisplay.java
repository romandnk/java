package homework5;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends javax.swing.JComponent {

    // поле окончательное и его нельзя изменить
    private final BufferedImage displayImage;

    //инициализируют объект BufferImage(длина, ширина, тип изображения)
    public JImageDisplay(int width, int height) {
        displayImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }

    // родительский метод переопределен в наследнике.
    // выводин на экран данные изображения
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(),
                displayImage.getHeight(), null);
    }

    //устанавливает все пиксели изображения в черный цвет
    public void clearImage() {
        int[] blankArray = new int[getWidth() * getHeight()];
        displayImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }

    // устанавливает пиксель в определнный цвет
    public void drawPixel(int x, int y, int rgbColor) {
        displayImage.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage() {
        return displayImage;
    }
}
