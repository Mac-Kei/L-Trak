/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;




public class ImageResizer {
    
    public static void resizeImage(File inputFile, File outputFile, int width, int height) throws IOException {
        // Read the input image
        BufferedImage inputImage = ImageIO.read(inputFile);
        
        // Create a new buffered image with the desired dimensions
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // Get graphics object from the new image
        Graphics2D g2d = outputImage.createGraphics();
        
        // Set rendering hints for better quality
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw the input image scaled to the new size
        g2d.drawImage(inputImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();
        
        // Write the output image to file
        ImageIO.write(outputImage, "jpg", outputFile); // You can also use "png" or other formats
    }
    
    public static void main(String[] args) {
        try {
            File inputFile = new File("Images/BackGround/reading_book.jpg");
            File outputFile = new File("Images/BackGround/reading_book.jpg");
            int width = 800; // Desired width
            int height = 600; // Desired height
            
            resizeImage(inputFile, outputFile, width, height);
            System.out.println("Image resized successfully!");
        } catch (IOException e) {
            System.err.println("Error resizing image: " + e.getMessage());
        }
    }
}