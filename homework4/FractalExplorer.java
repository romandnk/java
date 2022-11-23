package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {

    // размер экрана в пикселях
    private final int displaySize;

    // ссфлка JImageDisplay для обновления отображения в разных методах в процессе
    // вычисления фрактала
    private final JImageDisplay display;

    // ссылка на базовый класс для отображения других видов фракталов
    private final FractalGenerator fractal;

    // диапазон комплексной плоскости, которая выводится на экран
    private final Rectangle2D.Double range;

    // конструктор класса
    // инициализирует объекты диапазона и фрактального генератора
    public FractalExplorer(int size) {

        displaySize = size;

        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);

    }

    // инициализирует граф. интерфейс Swing
    public void createAndShowGUI() {

        // содежримое окна
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Fractal Explorer");


        myframe.add(display, BorderLayout.CENTER);


        JButton resetButton = new JButton("Reset Display");


        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);


        myframe.add(resetButton, BorderLayout.SOUTH);


        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);


        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // правильно разместят содержимое окна, сделают его видимым и запретят изменение размеров окна
        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }

    // метод вывода фрактала на экран
    private void drawFractal() {
        // проходим через каждый пиксель фрактала
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){

                // получить координату x, соотвутствующую координате пислея X
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);

                int iteration = fractal.numIterations(xCoord, yCoord);

                // если точка не выходит за границы, устанавливаем пиксель в черный цвет
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }

            }
        }

        //изображение необходимо обновлять в соответствии с цветом для каждого пикселя
        display.repaint();
    }

    // обработка кнопки сброса. обработчик должен сброить диапазон к начальному, а затем перерисовать фрактал
    private class ResetHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }

    // класс для обработки событий с дисплея
    // при щелчке, класс должен отобразить пиксельные координаты целчка в оласть фрактала, а
    // затем вызывать метод генератора с координатами, по которым щелкнули и масштабом 0.5
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);

            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }


    public static void main(String[] args) {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}
