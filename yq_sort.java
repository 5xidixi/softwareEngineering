
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.Comparator;

public class yq_sort {
	
	public static void dataSort(String[][] data, int n) {
		Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		for (int i = 0; i < n - 1; i ++) {
			for (int j = i + 1; j < n ; j ++) {
				if(Integer.parseInt(data[i][2]) < Integer.parseInt(data[j][2]) ) {
					String[] temp = data[j];
					data[j] = data[i];
					data[i] = temp;
				}
				if (Integer.parseInt(data[i][2]) == Integer.parseInt(data[j][2])) {
					if(cmp.compare(data[i][1], data[j][1]) > 0) {
						String[] temp = data[j];
						data[j] = data[i];
						data[i] = temp; 
						
					}
				}
			}
		}
	}
	
	public static void proSort(String[][] proSum, int a) {
		Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		for (int i = 0; i < a - 1; i ++) {
			for (int j = i + 1; j < a; j ++) {
				if(Integer.parseInt(proSum[i][1]) < Integer.parseInt(proSum[j][1]) ) {
					String[] temp = proSum[j];
					proSum[j] = proSum[i];
					proSum[i] = temp;
				}
				if (Integer.parseInt(proSum[i][1]) == Integer.parseInt(proSum[j][1])) {
					if(cmp.compare(proSum[i][1], proSum[j][1]) > 0) {
						String[] temp = proSum[j];
						proSum[j] = proSum[i];
						proSum[i] = temp;
					}
				}
			}
		}
	}
	
	
	public static void main(String args[]) throws IOException {
		Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		
		String filename = "C:\\Users\\Administrator\\Desktop\\" + args[0]+ ".txt";
		FileReader reader = new FileReader(filename);
		BufferedReader br = new BufferedReader(reader);

		File writename = new File("C:\\Users\\Administrator\\Desktop\\" + args[1] + ".txt");
		writename.createNewFile();
		
		FileWriter write = new FileWriter(writename);
		BufferedWriter out = new BufferedWriter(write);
		
		String contrast = "�㽭ʡ";
		String str = "";
		
		String[] array = new String[3];
		String[][] yqdata = new String[255][3];
		String[][] proSum = new String[31][2];
		
		int n = 0 , count = 0 , sum = 0 , a = 0;
		
		while((str = br.readLine()) != null) {
			array = str.split("\t");
			
			yqdata[n][0] = array[0];
			yqdata[n][1] = array[1];
			yqdata[n][2] = array[2];
			
			if (yqdata[n][0].equals(contrast)) {
				count = Integer.parseInt(yqdata[n][2]);
				sum = count + sum;
				proSum[a][0] = yqdata[n][0];
				proSum[a][1] = String.valueOf(sum);
			} else {
				contrast = yqdata[n][0];
				//sum = 0;
				sum = Integer.parseInt(yqdata[n][2]);
				a ++;
			}
			n++;
		}

		dataSort(yqdata, n);
		proSort(proSum, a);

		int len = args.length;
		
		if(len == 2) {
			for(int k = 0; k <= a; k++) {
				out.write(proSum[k][0] +"\t" + proSum[k][1]);
				out.newLine();
				for(int i = 0; i < n ; i++) {
					if (yqdata[i][0].equals(proSum[k][0])) {
						out.write(yqdata[i][1] + "\t" + yqdata[i][2]);
						out.newLine();
					}
				}
				out.newLine();
			}
		}
		
		if(len == 3) {
			contrast = args[2];
			for(int k = 0; k <= a; k++) {
				if(proSum[k][0].equals(contrast)) {
					out.write(proSum[k][0] +"\t" + proSum[k][1]);
					out.newLine();
				}
			}
			for(int i = 0; i < n; i++) {
				if (yqdata[i][0].equals(contrast)) {
					out.write(yqdata[i][1] + "\t" + yqdata[i][2]);
					out.newLine();
				}
			}
		}
		
//		for(int x = 0; x < n; x++) {
//			for (int y = 0; y <= 2; y++) {
//				System.out.print(yqdata[x][y] + "\t");
//			}
//			System.out.print("\n");
//		}
		
		out.newLine();
		out.flush();
		out.close();
		br.close();			
	}
}
