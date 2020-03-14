
import java.io.*;

public class InputFile {
public static void main(String args[]) throws IOException {
	String line = "";
	String contrast = "�㽭ʡ";
	String sf = "";
	boolean tag = false;

		String filename = "D:\\Eclipse-Workspace\\yq_in.txt";
		FileReader reader = new FileReader(filename);
		BufferedReader br = new BufferedReader(reader);	
		
		File writename = new File( "D:\\Eclipse-Workspace\\yq_out.txt");
		writename.createNewFile();
		FileWriter write = new FileWriter(writename);
		BufferedWriter out = new BufferedWriter(write);
	
			line = br.readLine();
			while (line != null) {
			line = br.readLine();
			
			if(line != null){
			sf = line.substring(0,3);
			}
			
				if(sf.equals(contrast))
				{
					if(tag == false) {
						tag = true;
						out.write(sf);
						out.newLine();
					}

				}else {
					out.newLine();
					contrast = sf;
					out.write(sf);
					out.newLine();
				}
				
			if(line != null) {
			line = line.substring(4);
			}
			
		
			if(line != null) {
				out.write(line);
			}
			
			
			out.newLine();
			out.flush();
			}
			out.close();
			br.close();
	}
}