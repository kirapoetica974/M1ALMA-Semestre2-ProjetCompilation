import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


public class ProgPrincipal {
	
	public static Vector<Integer> pile_x = new Vector<Integer>();
	
	public static Vector<Integer> p_code = new Vector<Integer>();
	
	public static int spx; // pointeur de pile_x
	
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
		tablePcode.put(PCode.STOP, 27);
		tablePcode.put(PCode.SUP, 28);
		tablePcode.put(PCode.SUPE, 29);
		tablePcode.put(PCode.WRT, 30);
		tablePcode.put(PCode.WRTLN, 31);
		//tablePcode.put(PCode.STOP, 32);
	}
	
	
	

	public static void main(String[] args) {
		
		spx = 1;
		
		pile_x.add(10); // Element i
		pile_x.add(21); // Element j
		
		//-------------------------------------------
		// test des opérations  ADD, MOINS, MULT, DIV
		// ------------------------------------------
		
		/*
		 
		// on charge i
		p_code.add(18);
		p_code.add(0);
		
		// on charge j
		p_code.add(18);
		p_code.add(1);
		
		// on fait i/j  (a commenter si tu veux tester les autres opérations)
		p_code.add(6);
		
		// on fait i+j
		//p_code.add(1);
		
		// on fait i-j
		// p_code.add(19)
		
		//on fait i*j
		// p_code.add(20)
		
		// on affiche i/j
		p_code.add(30);
		
		//stop
		p_code.add(27);
		
		*/
		
		
		//-------------------------------------------
		// test des opérations  RD, RDLN
		// ------------------------------------------
		
		//on demande a l'utilisateur RD
		//p_code.add(24);
		
		// on demande RDLN
		p_code.add(25);
		
		// on affiche 
		p_code.add(30);
		
		p_code.add(27);
		
		
		
		System.out.println("p_code = " + p_code);
		while(p_code.get(co) != 27){
			System.out.println("co = " + co);
			System.out.println("p_code[co] = "+p_code.get(co));
			interpreter(p_code.get(co));
		}
		
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
		
		String a = line.charAt(i) + "";
		switch (a) {
		case ",":
			
			break;

		default:
			break;
		}
	}
	
	
	public static void interpreter(int x){
		
		int aAjouter;
		
		switch (x) {
		case 1: // ADD
			aAjouter = pile_x.get(spx - 1) +  pile_x.get(spx);
			pile_x.set(spx-1, aAjouter);
			spx--;
			co++;
			break;
			
		case 2: // AFF
			aAjouter = pile_x.get(spx);
			pile_x.set(spx-1, aAjouter);
	   		spx = spx - 2;
	   		co++;
			break;
			
		case 3: // AND
			//TODO
			break;
			
		case 4: // DEC
			aAjouter = pile_x.get(spx) - 1 ;
			pile_x.set(spx, aAjouter);
	   		co++;
			break;
		
		case 5: // DIFF
			if(pile_x.get(spx-1) != pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			
			spx--;
	   	 	co++;
			break;
			
			
		case 6: // DIV
			aAjouter = pile_x.get(spx-1) / pile_x.get(spx);
			pile_x.set(spx-1, aAjouter);
	   	 	spx--;
	   	 	co++;
			break;
			
			
		case 7: // EG
			if(pile_x.get(spx-1) == pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			spx--;
	   	 	co++;
			break;
			
		case 8: //INC
			aAjouter = pile_x.get(spx) + 1 ;
			pile_x.set(spx, aAjouter);
	   		co++;
			break;
			
		case 9: //INDA
			//TODO
			break;
			
		case 10: //INDV
			//TODO
			break;
	
		case 11: //INF
			if(pile_x.get(spx-1) < pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			spx--;
	   	 	co++;
			break;
	
		case 12: //INFE
			if(pile_x.get(spx-1) <= pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			
			spx--;
	   	 	co++;
			break;
	
		case 13: //JIF
			if(pile_x.get(spx) == 0){
				co = (int) p_code.get(co+1);
			}
			else{
				co = co + 2;
			}
			break;
	
		case 14: //JMP
			co = (int) p_code.get(co+1);
			break;
	
		case 15: //JSR
			//TODO
			break;
	
		case 16: //LDA
			spx++;
			aAjouter = p_code.get(co+1);
			pile_x.add(aAjouter);
	   		co = co + 2;
			break;
	
		case 17: //LDC
			spx++;
			aAjouter = p_code.get(co+1);
			pile_x.add(aAjouter);
	   		co = co + 2;
			break;
	
		case 18: //LDV
			spx++;
			aAjouter = pile_x.get(p_code.get(co+1));
			System.out.println("aAjouter = " + aAjouter);
			System.out.println("spx = " + spx);
			pile_x.add(aAjouter);
	   		co = co + 2;
			break;
			
		case 19: //MOINS
			aAjouter = pile_x.get(spx-1) - pile_x.get(spx);
			pile_x.set(spx-1, aAjouter);
	   	 	spx--;
	   	 	co++;
			break;
			
		case 20: //MULT
			aAjouter = pile_x.get(spx-1) * pile_x.get(spx);
			pile_x.set(spx-1, aAjouter);
	   	 	spx--;
	   	 	co++;
			break;
			
		case 21: //NEQ
			if(pile_x.get(spx-1) != pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			spx--;
	   	 	co++;
			break;
	
		case 22: //NOT
			//TODO
			break;
	
		case 23: //OR
			//TODO
			break;
	
		case 24: //RD
			spx++;
			System.out.print("Entrer un nombre");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			aAjouter = Integer.parseInt(str);
			pile_x.add(aAjouter);
			co++;
			break;
	
			
		case 25: //RDLN
			spx++;
			System.out.println();
			Scanner sc2 = new Scanner(System.in);
			String str2 = sc2.nextLine();
			aAjouter = Integer.parseInt(str2);
			pile_x.add(aAjouter);
			co++;
			break;

			
		case 26: //RSR
			// TODO
			break;
			
		case 27: //ST0P
			// jamais interprété
			break;
			
			
		case 28: //SUP
			if(pile_x.get(spx-1) > pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			spx--;
	   	 	co++;
			break;
	
			
		case 29: //SUPE
			if(pile_x.get(spx-1) >= pile_x.get(spx)){
				pile_x.set(spx-1, 1);
			}
			
			else{
				pile_x.set(spx-1, 0);
			}
			
			spx--;
	   	 	co++;
			break;
	
			
		case 30: //WRT
			System.out.print(pile_x.get(spx));
			spx++;
			co++;
			break;
	
		case 31: //WRTLN
			System.out.println(pile_x.get(spx));
			spx++;
			co++;
			break;
	
		default:
			break;
		}
	}
	
}
