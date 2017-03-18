/* Lata Sundaram
   800988539
*/
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Decoder {
	
	public static void main(String[] args) throws IOException {
		
		HashMap<Integer,String> table=new HashMap<Integer,String>();
		int maxTableSize;
		String newString="";
		String input=args[0];
		try{
		int bitlength=Integer.parseInt(args[1]);
		String fileName=input.substring(0, input.lastIndexOf("."));
		maxTableSize=(int) Math.pow(2, bitlength);
		
		for(int i=0;i<256;i++)
			table.put(i,Character.toString((char)i));
		
		BufferedWriter writer=new BufferedWriter(new FileWriter(fileName+"_decoded.txt"));
		
		FileInputStream inputStream = new FileInputStream(input);
		Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
		
		int code=reader.read();
		String str=table.get(code);
		writer.write(str);
		
		while((code=reader.read())!=-1)
		{
			if(!table.containsKey(code))
				newString=str+str.charAt(0);
			else
				newString=table.get(code);
			
			writer.write(newString);
			if(table.size()<maxTableSize)
				table.put(table.size(),str+newString.charAt(0));
			
			str=newString;					
		}
		
		writer.close();
		reader.close();
	    }		
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			System.out.println("Invalid input for bit length");
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			ex.printStackTrace();
			System.out.println("Invalid input");
		}		
		
}

}
