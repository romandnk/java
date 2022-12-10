package homework6;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FractalExplorer {

    private final int displaySize;

    private final JImageDisplay display;

    private FractalGenerator fractal;

    private final Rectangle2D.Double range;

    JButton resetButton;

    JButton saveButton;

    JComboBox<FractalGenerator> fractalComboBox;

    int rowsRemaining;

    public FractalExplorer(int size) {

        displaySize = size;

        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    public void createAndShowGUI() {
        display.setLayout(new BorderLayout());
        JFrame myFrame = new JFrame("Fractal Explorer");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.add(display, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        resetButton = new JButton("Reset Display");

        JPanel myPanel = new JPanel();
        JLabel Label = new JLabel("Fractal:");
        fractalComboBox = new JComboBox<>();

        fractalComboBox.addItem(new Mandelbrot());
        fractalComboBox.addItem(new Tricorn());
        fractalComboBox.addItem(new BurningShip());

        myPanel.add(Label);
        myPanel.add(fractalComboBox);
        fractalComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<FractalGenerator> mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                assert fractal != null;
                fractal.getInitialRange(range);
                drawFractal();
            }
        });
        myFrame.add(myPanel, BorderLayout.NORTH);

        SaveHandler saveHandler = new SaveHandler();
        MouseHandler click = new MouseHandler();
        ResetHandler handler = new ResetHandler();

        resetButton.addActionListener(handler);
        saveButton.addActionListener(saveHandler);
        display.addMouseListener(click);

        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myFrame.add(myBottomPanel, BorderLayout.SOUTH);

        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }

    private class SaveHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);
            int returnVal = chooser.showSaveDialog(display);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File outputFile = chooser.getSelectedFile();
                try {
                    BufferedImage displayImage = display.getImage();
                    ImageIO.write(displayImage, "png", outputFile);
                }
                catch(IOException exception) {
                    JOptionPane.showMessageDialog(display, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class FractalWorker extends SwingWorker<Object, Object> {

        private  int y;
        private int[] values;

        public FractalWorker(int y) {
            this.y = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            values = new int[displaySize];
            for (int x=0; x<displaySize; x++){

                double xCoord = FractalGenerator.getCoord(range.x,
                        range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y,
                        range.y + range.height, displaySize, y);

                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1){
                    values[x] = 0;
                }

                else {
                    float hue = 0.7f + iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    values[x] = rgbColor;
                }
            }
            return null;
        }

        @Override
        protected void done() {
            for (int i = 0; i < displaySize; i++) {
                display.drawPixel(i, y, values[i]);
            }
            display.repaint(0,0, y, displaySize, 1);
            rowsRemaining --;
            if (rowsRemaining == 0){
                enableUI(true);
            }
            super.done();
        }

    }


    private void drawFractal() {
        enableUI(false);
        rowsRemaining = displaySize;
        for (int i = 0; i < displaySize; i++) {
            FractalWorker worker = new FractalWorker(i);
            worker.execute();
        }
    }

    private void enableUI(boolean value){
        resetButton.setEnabled(value);
        saveButton.setEnabled(value);
        fractalComboBox.setEnabled(value);
    }

    private class ResetHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }
    
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (rowsRemaining ==0) {
                int x = e.getX();
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);

                int y = e.getY();
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);

                fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

                drawFractal();
            }
        }
    }


    public static void main(String[] args) {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}
