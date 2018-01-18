import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class ImageCode{

  static ArrayList<Color> pixelColors = new ArrayList<>();
  static int shift = 103; // num must be able to be added to any ascii value and make under 255; max color value
  static int colorShift = 5; // used to shift r g b values of pixels based on value of that pixel
  static int middleOfAscii = 182; // middle of the ascii table with the shift added
  static int emptyColorValue = 101;// color used to fill empty pixel values
  static String possibleEmpty = "10010102103104105106107108109110111112113115"; //not in use
  static int maxEmptyColor = 115; // the max empty color value with shift
  static int decodeEmptyColorValue = emptyColorValue + colorShift; // finds the color value of decoded empty



  //Graphics components
  static Stage window;
  static BorderPane root;
  static Scene scene;
  static Label output;
  static TextArea input;

public static void start(){
  printTitleInConsole();
  initWindow();
  }

  private static void initWindow(){
      window = new Stage();
        window.setTitle("Cyber Security Encryption : Preston Meade, Chris Sacco");

      root = new BorderPane();
      scene = new Scene(root,600,400);

      loadComponents();
      window.setScene(scene);

      window.show();
  }

  private static void loadComponents(){
    Label dir1 = new Label("\t\t\t\tReady to encrypt and decrypt (Read Help.txt for help)");
    Label dir2 = new Label("~To encode a string, enter a string and click Encode. *May take upto 1 minute*");
    Label dir3 = new Label("~To decode an image, click Decode and selct the .png file");
dir1.setTextFill(Color.WHITE);
dir2.setTextFill(Color.WHITE);
dir3.setTextFill(Color.WHITE);
     output = new Label();

    GridPane rootTop = new GridPane();
    GridPane rootBottom = new GridPane();

    input = new TextArea();
      input.setPromptText("Enter your message here! Then press Encode");

    Button encrypt = new Button("Encode");
      encrypt.setOnAction(e -> saveAs(input.getText()));

    Button decrypt = new Button("Decode");
      decrypt.setOnAction(e -> prepareDecode());

    root.setTop(rootTop);
    root.setStyle("-fx-background-image: url('/Background/mainBG.jpg')");
    root.setBottom(rootBottom);
    root.setPadding(new Insets(10,10,10,10));

    rootTop.add(dir1,0,0);
    rootTop.add(dir2,0,1);
    rootTop.add(dir3,0,2);
    rootTop.add(input,0,3);
    rootTop.setVgap(15);
    rootTop.setPadding(new Insets(10,10,10,10));

    rootBottom.add(encrypt,0,0);
    rootBottom.add(decrypt,0,1);
    rootBottom.add(output,1,0);
    rootBottom.setVgap(15);
    rootBottom.setHgap(10);
    rootBottom.setPadding(new Insets(5,5,5,5));
  }

  private static void prepareDecode(){
    FileChooser chooser = new FileChooser();
    	chooser.setTitle("Open File");

    File file = chooser.showOpenDialog(new Stage());

    if (file != null && isImage(file)) {
      try {
              BufferedImage bufferedImage = ImageIO.read(file);
              Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             decode(image);

          } catch (IOException ex) {
            setStatus("Please select an image", Color.RED);
          }
    	}
  }

  private static boolean isImage(File in){
    boolean isImg = false;

    String url = in.getName();
    String ext = url.substring(url.length() - 3, url.length());

    if(ext.equals("png")){
      isImg = true;
      System.out.println("\t\t# Selected image is a png file, will work!");
    }else{
      isImg = false;
      System.out.println("\t\t# Must select a .png file that is encoded");
    }
    return isImg;
  }

  private static void saveAs(String txt){
	  Stage askWindow = new Stage();
	  	askWindow.setTitle("Save as");
	  	askWindow.initModality(Modality.APPLICATION_MODAL);
      askWindow.setX(window.getX() + 100);
      askWindow.setY(window.getY() + 100);

	  GridPane rootAsk = new GridPane();
      rootAsk.setVgap(10);
      rootAsk.setPadding(new Insets(10,10,10,10));
      rootAsk.setStyle("-fx-background-image: url('/Background/saveBG.jpg')");

	  Scene sceneAsk = new Scene(rootAsk,350,150);
	  Label dir = new Label("Enter a name to save file as, no extensions needed");
	  TextField fileNameField = new TextField();

	  Button saveAndEncrypt = new Button("Encrypt");
	  	saveAndEncrypt.setOnAction(e -> {
	  		askWindow.close();
	  		encode(fileNameField.getText(),txt);
	  	});

	  Button cancel = new Button("Cancel");
	  	cancel.setOnAction(e -> askWindow.close());

	  rootAsk.add(dir, 0, 0);
	  rootAsk.add(fileNameField, 0, 1);
	  rootAsk.add(saveAndEncrypt, 0,2);
	  rootAsk.add(cancel, 0, 3);
	  askWindow.setScene(sceneAsk);
	  askWindow.show();
  }

  private static void encode(String fileName, String txt){
    	System.out.println("             ------------------------------------------------------------------------------------");
      System.out.println("             |                       STATUS: ENCODING MESSAGE                                   |");
      System.out.println("             ------------------------------------------------------------------------------------\n\n");
	  pixelColors.clear();
    setStatus("Encoding... please wait",Color.WHITE);
  if(!txt.isEmpty()){
  	  setStatus("Encoding...", Color.GREEN);

	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
System.out.println("         Orignal Txt: " + txt);
	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");

  txt = Encrypt.officallyEncrypt(txt);
  	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	 System.out.println("       Translating to ArrayList of pixels: " + txt);
	 System.out.println("---------------------------------------------------------------------------------------------------------------------------\n\n");
	  String res = "";

	    for(int i = 0; i < txt.length(); i++){
	      res += (int)txt.charAt(i) + shift;
	    	}
        	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println("    Adding shift to create all digits under 225: " + res);
        	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");

	    for(int i = 0; i < res.length(); i += 9){
	      int colorValue;
	        if(res.length() - i < 9 && res.length() - i >= 6){
	          colorValue = Integer.parseInt(res.substring(i, i + 6)) * 1000;
	        }else if (res.length() - i < 6){
	            colorValue = Integer.parseInt(res.substring(i, i + 3)) * 1000000;
	        }else{
	       colorValue = Integer.parseInt(res.substring(i, i + 9));
	        }

        createColor(colorValue);

	    }
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
      System.out.println("                ***Creating Image Now.... please wait... may take up to 1 minute***");
      	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
    setStatus("Successfully encoded and saved as: " + fileName + ".png in EncryptedImages folder", Color.GREEN);
    ImageOutput.showImage(pixelColors,fileName);
  }else{
    setStatus("Please enter a message to encode", Color.RED);
    }
  }


  private static void createColor(int colorValue){

    String value = colorValue + "";

    int red = Integer.parseInt(value.substring(0,3));

    int green = Integer.parseInt(value.substring(3,6));

    int blue = Integer.parseInt(value.substring(6,9));

      if(red <= middleOfAscii && red > 5){
        red -= colorShift;
      }else if(red == 0){
        red = 0;
      }else{
        red += colorShift;
      }

      if(green <= middleOfAscii && green > 5){
        green -= colorShift;
      }else if(green == 0){
        green = 0;
      }else{
        green += colorShift;
      }

      if(blue <= middleOfAscii && blue > 5){
        blue -= colorShift;
      }else if(blue == 0){

        blue = 0;
      }else{
        blue += colorShift;
      }


   Color newColor =  Color.rgb(red,green,blue);
   	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
   System.out.println("    Color to be drawn to canvas: rgb(" + red + "," + green + "," + blue + ")" +  "[" + newColor.toString() +"]");
   	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
   pixelColors.add(newColor);
  }

  public static void decode(Image in){
    System.out.println("             ------------------------------------------------------------------------------------");
    System.out.println("             |                       STATUS: DECODING MESSAGE                                   |");
    System.out.println("             ------------------------------------------------------------------------------------\n\n");
	 input.setText("");
	 setStatus("Scanning image for text", Color.BLUE);
    ArrayList<Color> colors = new ArrayList<>();

    Image encodedImage =in;
    boolean stopBlockNotFound = true;


    PixelReader reader = encodedImage.getPixelReader();

    int width = (int)encodedImage.getWidth();
    int height = (int)encodedImage.getHeight();
    int squareSize = ImageOutput.squareSize;
    int row = 0;
    int col = 0;

    String longNumResult = "";
    boolean canDecodeNext = true;
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
      System.out.println("            Colors Found: ");

    for(int r = 0; r * squareSize < width; r++){

      for(int c = 0; c * squareSize < height; c++){



        if(canDecodeNext && r < width && c < height ){
try{
          String strColor = reader.getColor(r* squareSize,c* squareSize).toString();
          Color curPixel = reader.getColor(r* squareSize,c* squareSize);
          Color nextPixel = reader.getColor(r* squareSize,((c + 1) * squareSize));

          /*
          System.out.println((int)(curPixel.getRed() * 255) + " : " + (int)(nextPixel.getRed() * 255)+ "\n" +
          (int)(curPixel.getGreen() * 255) + " : " + (int)(nextPixel.getGreen() * 255) + "\n" +
          (int)(curPixel.getBlue() * 255) + " : " + (int)(nextPixel.getBlue() * 255));
          */
            colors.add(reader.getColor(r* squareSize,c* squareSize));
              System.out.println("  #" +strColor);
              int x = r * squareSize;
              int y = c * squareSize;
              Color curColor = reader.getColor(x,y);

              int red = (int)(curColor.getRed() * 255);
              int green = (int)(curColor.getGreen() * 255);
              int blue = (int)(curColor.getBlue() * 255);

              String redString =  "000", greenString = "000", blueString = "000";

              if(red <= middleOfAscii && red > maxEmptyColor){
                red += colorShift;
                redString = red+"";
              }else if(red == 0){
                red = 000;
                redString = "0";
              }else{
                red -= colorShift;
                redString = red +"";
              }


              if(green <= middleOfAscii && green > maxEmptyColor){
                green += colorShift;
                greenString = green +"";
              }else if(green == 0){
                green = 000;
                greenString = "0";
                System.out.println(green + " green value ");
              }else{
                green -= colorShift;
                greenString = green +"";
              }


              if(blue <= middleOfAscii  && blue > maxEmptyColor){
                blue += colorShift;
                blueString = blue +"";
              }else if(blue == 0){
                System.out.println(blue + " blue value ");
                blue = 000;
                blueString = "0";
              }else{
                blue -= colorShift;
                blueString = blue +"";
              }

              String colorValue = redString +"" + greenString +"" + blueString;


            longNumResult += colorValue;

            if(stop(curPixel,nextPixel)){
              canDecodeNext = false;
              //System.out.println("stopped");
            }
          }catch(IndexOutOfBoundsException ex){
            System.out.println("Ran out of spcae on the image...");
          }

          }else{

          }
        }
      }
      	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    System.out.println("     Found Encoded Message: " + longNumResult);
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");

    String result = "";

    for(int i = 0; i <= longNumResult.length() - 3; i += 3){
      int colorValue;
      try{

      result += (char)(Integer.parseInt(longNumResult.substring(i,i + 3)) - shift);

    }catch(NumberFormatException ex){
      System.out.println("Can not decode");
    }
    }
  	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    System.out.println("              Encoded Message Extracted From Image: " + result );
	 System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
    try{

    result = Encrypt.officallyDecrypt(result);
    output.setText("Decrypted: " + (result));

  }catch(StringIndexOutOfBoundsException ex){
    System.out.println("Error in decoding, try another image");
    setStatus("There is no message in this image, try another valid png image", Color.RED);
  }

  }

private static boolean stop(Color current, Color next){
  boolean stop = false;


  if((int)(current.getRed() * 255) == ((int)(next.getRed() * 255)) -  1
    && (int)(current.getGreen() * 255) == ((int)(next.getGreen() * 255)) -  1
    && (int)(current.getBlue() * 255) == ((int)(next.getBlue() * 255)) -  1){
      stop = true;
    }
    return stop;
}

private static int getEmptyColor(){
  int colorValue = 0;
  int possibleIndex = (possibleEmpty.length() / 3) - 1;
  int startIndex = (int)(Math.random() * possibleIndex);

  if(startIndex % 3 == 0){
    System.out.println("mod 0");
    colorValue = (int)(Integer.parseInt(possibleEmpty.substring((startIndex * 3) + 1 , (startIndex* 3) + 3)));
  }else if (startIndex % 3 == 1){
    System.out.println("mod 1");
    startIndex -= 1;
    colorValue = (int)(Integer.parseInt(possibleEmpty.substring(startIndex * 3, (startIndex* 3) + 3)));
  }else{
    System.out.println("mod 2");
    startIndex += 1;
    colorValue = (int)(Integer.parseInt(possibleEmpty.substring(startIndex * 3, (startIndex* 3) + 3)));
  }
  System.out.println(colorValue + " ran possible empty selected");
  return colorValue;
}

public static boolean isEmptyColor(int in){
  System.out.println(in);
  boolean isEmpty = false;
  if(possibleEmpty.contains(in + "")){
    isEmpty = true;
  }
  return isEmpty;
}


private static void setStatus(String in, Color c){
  output.setTextFill(Color.WHITE);
  output.setText(in);
}

private static void printTitleInConsole(){
  System.out.println("\n\n                      -----------------------------------------------------------   ");
  System.out.println("---------------------- Encode A Message Into An Image: Preston Meade, Chris Sacco ---------------------------------");
  System.out.println("                      -----------------------------------------------------------\n\n   ");
}


}
