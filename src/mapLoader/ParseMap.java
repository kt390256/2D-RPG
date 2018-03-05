package mapLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseMap {
	
	public static String loadFileAsString(String path) {
		
		
		//File object can be directly manipulated on, delete, check existence, do tests
		//but FileReader can be used solely to READ a file
		
		
		StringBuilder newString = new StringBuilder();//StringBuilder class has much better performance than the String class
													//This is the StringBuilder class which allows the ".append()" method
													//The String class doesn't allow append, and all string is immutable
													//I mean, you can do the same thing with String class, but you have to do something like, str += i;
													//which would create 500 new string objects
													//If it is used locally, use String builder, if it is accessed by multiple threads, use StringBuffer
													//String d = new StringBuilder(a).append(b).append(c).toString();
		try {
			
			BufferedReader file = new BufferedReader(new FileReader(path));//general format to read a file
			
			//Important, there are two ways to read a file
			//The first way is to use a Scanner, the second way is to uew a BufferedReader
			//The difference is that, if you want to parse the file, use the Scanner class
			//If you just want to read the file(line by line), use the BufferedReader class, and synchronized
			//which means read operations on a BufferedREader can safely be done from multiple threads
			
			//Scanner can do all a BuffedReader can do and at the same level of efficiency as well, but the Scanner
			//class can parse as well
			
			//using which depends on the code you are writing, if you are writingg a simple log reader then Buffferedreader is enough
			// but if you are writing an XML parser Scanner is the more natural choice
			
			
			//BufferedReader has significantly larger buffer memory than Scanner. Use BufferedReader if you want to get long strings from a stream
			//and use Scanner if you want to parse specific type of token from a stream
			
			String line;
			
			while((line = file.readLine())!= null)//store a kine of text into the variable line
												// .readline() this reads a line of text
			{
				newString.append(line + "\n");//add the line + a space to the new StringBuilder
			}
			
			file.close();//all texts have been added to the builder, now we can close the file
		
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
		return newString.toString();
	}
	
	
	public static int parseInt(String number) {//turing a String number like "20" to an int 20
		
		
		//another form of parseInt is parseInt(String s, int radix)
		//this has to do with decimal and stuffs
		
		
		try {
			
			return Integer.parseInt(number);
			
		}catch(NumberFormatException e) {
			
			e.printStackTrace();
			return 0;
		}
		
		
		
		
	}
	
	

}
