/* Lata Sundaram
   800988539
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Encoder {

	public static void main(String[] args) throws IOException {
	
		HashMap<String,Integer> table=new HashMap<>();
		int maxTableSize;
		String str="";
		char ch;
		int c=0;
		String input=args[0];
		try{
		int bitlength=Integer.parseInt(args[1]);
		String fileName=input.substring(0, input.length()-4);
		maxTableSize=(int) Math.pow(2, bitlength);
		for(int i=0;i<256;i++)
			table.put(Character.toString((char)i), i);

		BufferedReader reader=new BufferedReader(new FileReader(input));
		FileOutputStream outputStream=new FileOutputStream(fileName+".lzw");
		Writer writer=new OutputStreamWriter(outputStream,Charset.forName("UTF-16BE"));
		while((c=reader.read())!=-1)
		{
			ch=(char)c;
			//System.out.println(ch);
			if(table.containsKey(str+ch))
				str=str+ch;
			else
			{
				writer.write(table.get(str));
				System.out.println(str+ch);
				if(table.size()<maxTableSize)
					table.put(str+ch, table.size());				
				str=ch+"";
			}
		}
		writer.write(table.get(str));
		writer.flush();
		reader.close();
		outputStream.close();
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			System.out.println("Invalid input for bit length");
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
			System.out.println("File not found");
		}					
	}
}
