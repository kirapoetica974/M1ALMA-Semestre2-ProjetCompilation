import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;


public class ProgPrincipal {
	
	public static Vector<Integer> pile_x;
	
	public static Vector<E> p_code;
	
	public static int slx; // pointeur de pile_x
	
	public static int co; // pointeur de p_code
	
	public static HashMap<PCode, Integer> tablePcode;
	
	static{
		tablePcode = new HashMap<PCode, Integer>();
		tablePcode.put(PCode.ADD, 1);
		tablePcode.put(PCode.AFF, 2);
		tablePcode.put(PCode.AND, 3);
		tablePcode.put(PCode.DEC, 4);
		tablePcode.put(PCode.DIFF, 5);
		tablePcode.put(PCode.DIV, 6);
		tablePcode.put(PCode.EG, 7);
		tablePcode.put(PCode.INC, 8);
		tablePcode.put(PCode.INDA, 9);
		tablePcode.put(PCode.INDV, 10);
		tablePcode.put(PCode.INF, 11);
		tablePcode.put(PCode.INFE, 12);
		tablePcode.put(PCode.JIF, 13);
		tablePcode.put(PCode.JMP, 14);
		tablePcode.put(PCode.JSR, 15);
		tablePcode.put(PCode.LDA, 16);
		tablePcode.put(PCode.LDC, 17);
		tablePcode.put(PCode.LDV, 18);
		tablePcode.put(PCode.MOINS, 19);
		tablePcode.put(PCode.MULT, 20);
		tablePcode.put(PCode.NEQ, 21);
		tablePcode.put(PCode.NOT, 22);
		tablePcode.put(PCode.OR, 23);
		tablePcode.put(PCode.RD, 24);
		tablePcode.put(PCode.RDLN, 25);
		tablePcode.put(PCode.RSR, 26);
		tablePcode.put(PCode.STUP, 27);
		tablePcode.put(PCode.SUP, 28);
		tablePcode.put(PCode.SUPE, 29);
		tablePcode.put(PCode.WRT, 30);
		tablePcode.put(PCode.WRTLN, 31);
	}
	
	
	

	public static void main(String[] args) {
		
	}
	
	
	
	public static BufferedReader br;
	
	static { try {
		br = new BufferedReader(new FileReader("programme.txt"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}}
	
	public static String line;
	
	static { try {
		line = br.readLine();
	} catch (IOException e) {
		e.printStackTrace();
	}}
	
	static int i = 0;
	
	public static void scanGPL() throws IOException{
		if(line == null){
			line = br.readLine();
		}
		
		String a = line.charAt(i)+"";
		switch (a) {
		case ",":
			
			break;

		default:
			break;
		}
	}
	
	
	public static void interpreter(int x){
		
		switch (x) {
		case 1: // ADD
			
			break;
			
		case 2: // AFF
			
			break;
			
		case 3: // AND
			
			break;
			
		case 4: // DEC
			
			break;
		
		case 5: // DIFF
	
			break;
			
		case 6: // DIV
			
			break;

		default:
			break;
		}
	}
	
}
