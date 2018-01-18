import java.awt.image.BufferedImage;
import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import javax.imageio.ImageIO;
import java.io.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class ImageOutput{

static GraphicsContext gc;
static final int squareSize = 1;
static ArrayList<Color> colorsList;
static final int width = 1000;
static final int height = 1000;
static

 String fileUrl;

  public static  void showImage(ArrayList<Color> colors, String fileName){

    Stage window = new Stage();
      window.setTitle("Image Output");
      window.initModality(Modality.APPLICATION_MODAL);
      window.setX(ImageCode.window.getX() + 100);

    Group root = new Group();
    Scene scene = new Scene(root,width,height);
    Canvas canvas = new Canvas(width,height);

     gc = canvas.getGraphicsContext2D();
     colorsList = colors;

    root.getChildren().add(canvas);

    int row = -1;
    int col = 1;
    int sendRow = 0;
    int sendCol = 0;
    gc.setFill(Color.RED);
    gc.fillRect(0,0,width,height);

    fileUrl = fileName;

    int indexOfArray = -2;
	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
for(int r = 0; r * squareSize < width; r++){
        if(indexOfArray < colors.size() - 1){
        indexOfArray++;
        row += squareSize;
        sendRow = r;
      }

  for(int c = 0; c * squareSize < height; c++){

    if(indexOfArray < colors.size() - 1){
        indexOfArray++;
        col += squareSize;
        gc.setFill(colors.get(indexOfArray));
        gc.fillRect(r * squareSize,c * squareSize,squareSize, squareSize);
        System.out.println(" # Pixel painted: " + colors.get(indexOfArray).toString() + "(" + r + "," + c +")");
        sendCol = c;


      }
    }
}
	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");


  fillWithRandomSimilar(sendRow,sendCol);

WritableImage wim = canvas.snapshot(new SnapshotParameters(), null);
  canvas.snapshot(null, wim);

 File file = new File("EncodedImages/" + fileName + ".png");
 try {
           ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
       } catch (Exception s) {
         System.out.println("An error occured saving the image");
       }

    window.setScene(scene);
    window.show();


  }

  private static void fillWithRandomSimilar(int xStart, int yStart){
    //yStart -= 2;
    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    Color arret = colorsList.get(colorsList.size() - 1);

    int stopR = (int)(arret.getRed() * 255);
    int stopG = (int)(arret.getGreen() *255);
    int stopB = (int)(arret.getBlue() * 255);

    arret = Color.rgb(stopR + 1, stopG + 1, stopB + 1);
    //Color stop = Color.web("0x0a0a0bff");
    Color stop = arret;
    gc.setFill(stop);
    gc.fillRect((xStart) * squareSize,(yStart + 1) * squareSize,squareSize,squareSize);


    System.out.println("   ***Stop Block Filled: " + stop.toString() +  " at pixel (" + (xStart) + "," + (yStart+ 1) + ")");
    System.out.println(" \n# Filling picture with random similar pixels now... please wait...\n");

      int red = (int)(Math.random() * 100 + 100);
      int green = (int)(Math.random() * 100 + 100);
      int blue = (int)(Math.random() * 100 + 100);

      Color color = Color.rgb(red,green,blue);

    for(int r = xStart; r * squareSize < width; r++){


      for(int c = 0; c * squareSize < height; c++){

         red = (int)(Math.random() * 100 + 100);
         green = (int)(Math.random() * 100 + 100);
         blue = (int)(Math.random() * 100 + 100);

         Color ranFromPixels = colorsList.get((int)(Math.random() * colorsList.size() - 1));

         color = (ranFromPixels);

                gc.setFill(color);
                if(r == xStart){
                  gc.fillRect(r * squareSize,(c+ yStart + 2) * squareSize,squareSize,squareSize);
                }else{
                gc.fillRect(r * squareSize,c * squareSize,squareSize,squareSize);
                }
            }
          }
          System.out.println("        Complete: Image is saved in 'EncodedImages' folder as \"" + fileUrl + ".png\"" );
          	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
    }


}
