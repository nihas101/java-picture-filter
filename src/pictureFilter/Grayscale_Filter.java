package pictureFilter;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Applies a black and white filter to an image using following formula: 0.21 R + 0.72 G + 0.07 B
 */
public class Grayscale_Filter implements Filter {
    @Override
    public void apply(Image image, WritableImage writableImage) {
        if(writableImage == null || image == null) return;

        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        double imageHeight = writableImage.getHeight();
        double imageWidth = writableImage.getWidth();

        /* Iterate through all pixels */
        for(int y=0 ; y < imageHeight ; y++) {
            for(int x=0 ; x < imageWidth ; x++) {
                Color color = pixelReader.getColor(x,y);

                /* Apply formula for grayscale source: http://www.tannerhelland.com/3643/grayscale-image-algorithm-vb6/ */
                double grey = color.getRed()*.3 + color.getBlue()*.59 + color.getGreen()*.11;

                Color grayColor = new Color(grey, grey, grey, color.getOpacity());

                /* Set new color */
                pixelWriter.setColor(x,y,grayColor);
            }
        }
    }

    @Override
    public String getFilterName() {
        return "bw";
    }
}