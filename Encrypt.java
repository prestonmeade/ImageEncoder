import java.math.BigInteger;

public class Encrypt {

	static int shift = 150; // key used to shift ascii values
	static BigInteger key = new BigInteger("3628800"); // prime number used for multipying

	public static String officallyEncrypt(String in){
	 BigInteger bigIntEncrypt = bigIntEncrypt(in);

	 String finalEncrypt = encrypt(bigIntEncrypt.toString());
		return finalEncrypt;
	}

	public static String officallyDecrypt(String in){
		String mess ="";

		String round2 = decrypt(in);

		String decodedMessage = bigIntDecode(round2);

		return decodedMessage;
	}

	//method called to encrypt the text when button is clicked
	private static void encodeString(String in){

		String officalEncodedTxt = officallyEncrypt(in);
		System.out.println("Encoded message: " + officalEncodedTxt);
	}

	//method to decrypt a string enterd in the text field
	private static void decodeString(String in){
		//reverse anything done in encoded string
		String demess = decrypt(in);
		String decodeMessage = bigIntDecode(demess);

		System.out.println("Decoded message: " + decodeMessage + "***make function to actually decode!");
	}


	//method used to reverse a string
	private static String reverse(String in){
		String reversed = "";
		for(int i = in.length() - 1; i >= 0; i--){
			reversed += in.charAt(i);
		}
		return reversed;
	}

	//Takes a big int in string format and extracts values
    // reverse math operations to get original bigInt
	  private static String bigIntDecode(String in){

	    BigInteger largeNum = new BigInteger(in); // declare and initialize the largenum
		//
	    BigInteger smallNum = largeNum.divide(key); // reverse math done in encryption
	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	System.out.println(                  "Char Value *Must subtract shift now*: " + smallNum.toString());
		System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
	      in = smallNum.toString(); //updates in to the small original big int


	      String r = ""; // empty result string

	    for(int i = 0; i < in.length();i += 3){ // for loop to extract each 3 nums and get ascii value

	        char add; // declars the char to be added

	          if(in.length() < 2){ //if to determine and handle short encryption
	                 add = (char)(Integer.parseInt(in.substring(i, i + 3)) - shift);

	              }else{ // else, countine as normal, substring each 2 numbers and convert to ascii letter
	                   add = (char)(Integer.parseInt(in.substring(i, i + 3)) - shift );

	              }

	      r+= add; // updates result string
	        }
						System.out.println("--------------------------------------------------------------------------------------------------------------------------");
					System.out.println("    Decrypted Message Found: " + r);
						System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");
	    return r; // returns result string
	  }

	// method that takes a string and gets each ascii value and shifts it
	// then squashs all the values into a string
	// string is then converted to bigInt
	// math done to ecrypt further, mutiplys by key
	// returns the final huge bigint
	public static BigInteger bigIntEncrypt(String txt){

	    String res = ""; //empty result string

	    for(int i = 0; i < txt.length(); i++){ // for loop to get value of each char and adds to result

	      res += (int)txt.charAt(i) + shift; // adds the num value of ascii to res string

	    }
			System.out.println("------------------------------------------------");
	    System.out.println("       String of char value + shift: " + res); // shows the txt in big string format
			System.out.println("------------------------------------------------\n\n");

	    BigInteger bg = new BigInteger(res); // makes bigint out of res


	    BigInteger newBg = bg.multiply(key); // math done to bigint
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	    System.out.println("    String of char values * speical prime: " + newBg);
			System.out.println("--------------------------------------------------------------------------------------------------------------------------\n\n");

	    return newBg; // returns bigint, a very very big int
	  }


	//method where encrypting message goes
	private static String encrypt(String in){

	    String alphabet =  "1234567890";

	    String scramble1 = "<;\'_$,.?:|)";
	    String scramble2 = "XYZVKJUTHM";
	    String scramble3 = "tuvwxyz&*}";
	    String scramble4 = "~!-+=<>%@#";
	    String scramble5 = "PUDHCKSXWZ";

	    char messageIn[] = in.toCharArray();
	    String r = "";

	    for(int i = 0; i < in.length(); i++){

	      int letterIndex = alphabet.indexOf(in.charAt(i));

	      if(i % 3 == 0){
	          r += scramble1.charAt(letterIndex);
	      }else if (i % 3 == 1){
	        	r += scramble2.charAt(letterIndex);
	      }else if(i % 3 == 2){
	        	r += scramble3.charAt(letterIndex);
	      }
	    }
				System.out.println("--------------------------------------------------------------------------------------------");
				System.out.println("       Encoded Message: " + r);
				System.out.println("--------------------------------------------------------------------------------------------\n\n");
		return r;
	}

	//method to decrypt messages
	private static String decrypt(String in){

			String alphabet =  "1234567890";

			String scramble1 = "<;\'_$,.?:|)";
		    String scramble2 = "XYZVKJUTHM";
		    String scramble3 = "tuvwxyz&*}";
		    String scramble4 = "~!-+=<>%@#";
		    String scramble5 = "PUDHCKSXWZ";

		    char messageIn[] = in.toCharArray();
		    String r = "";

		    for(int i = 0; i < in.length(); i++){
		      if(i % 3 == 0){
		          int letterIndex = scramble1.indexOf(in.charAt(i));
		          r += alphabet.charAt(letterIndex);
		      }else if (i % 3 == 1){
		          int letterIndex = scramble2.indexOf(in.charAt(i));
		            r += alphabet.charAt(letterIndex);
		      }else if (i % 3 == 2){
		          int letterIndex = scramble3.indexOf(in.charAt(i));
		          r += alphabet.charAt(letterIndex);
		        }
		    }
		return r;
	}


}
