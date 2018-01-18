//Preston Meade and Chris Sacco
// Cyber Secuirty Project, Feburary 4th, 2016
// Use: Encodes and decodes messages and images

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
   public static void main(String[] args) {
       launch(args);
   }

   @Override
   public void start(Stage primaryStage) {
	   ImageCode.start();
   }
}

//TODO
// DONE!

//HOW IT WORKS
//Example:
// Computer
// call OfficalEncrypt("computer") and it returns a long string such as &*DBHS<><TVUIjK..[{
// Then seperates out each char and adds a special shift key, the adds or subtracts color shift based on value
// Groups into 9 digits, creates a red, green, and blue
// Draws to canvas

// Really hard to explain what this actually does
// Bascially this happens
// - takes a string
// - encodes the string
//   - Runs through officalEncrypt() in Encrypt.java and returns complex symbol coded message
//   - gets char value + shift, concats into long string of nums
// - takes complexed encoded string then extracts individual char and adds shift key
// gets 9 digits at a time for rgb to make a pixel
// adds or subtracts by speical key based on color value
// draws pixel to a canvas
// reverse to decode
