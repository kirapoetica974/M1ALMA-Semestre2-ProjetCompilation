package compilation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import operation.Atom;
import operation.Conc;
import operation.Operation;
import operation.Star;
import operation.Un;
import operation.Union;
import type.AtomType;
import type.Node;

public class Arbre {

	public static BufferedReader br;
	
	static { try {
		br = new BufferedReader(new FileReader("grammaire.txt"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	
	public static String line;
	
	static { try {
		line = br.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	
	public static HashMap<String, Integer> codeCaractere;
	
	// initialisation du hashmap
	static {
		codeCaractere = new HashMap<String, Integer>();
		codeCaractere.put(";", 6);
		codeCaractere.put(",", 7);
		codeCaractere.put("-->", 8);
		codeCaractere.put("+", 9);
		codeCaractere.put(".", 10);
		codeCaractere.put("(", 11);
		codeCaractere.put(")", 12);
		codeCaractere.put("[", 13);
		codeCaractere.put("]", 14);
		codeCaractere.put("(/", 15);
		codeCaractere.put("/)", 16);
		codeCaractere.put("IDNTER", 17);
		codeCaractere.put("ELTER", 18);
	}
	
	public static void main(String[] args) throws IOException {
		//imprimArbre(F);
		//scan();
		Boolean b = analyse(S);
		System.out.println(b);
		
	}
	
	static Node S = genConc(
			genStar(
					genConc(
							genConc(
									genConc(
											genAtom(codeCaractere.get("IDNTER"), 0, AtomType.NonTerminal), 
											genAtom(codeCaractere.get("-->"), 0, AtomType.Terminal)
									), 
									genAtom(codeCaractere.get("IDNTER"), 0, AtomType.NonTerminal)
							), 
							genAtom(codeCaractere.get(","), 1, AtomType.Terminal)
					)
			), 
			genAtom(codeCaractere.get(";"), 0, AtomType.Terminal)
		);
	
	static Node N = genAtom(codeCaractere.get("IDNTER"), 2, AtomType.Terminal);
	
	static Node E = genConc(
			genAtom(codeCaractere.get("IDNTER"), 3, AtomType.NonTerminal), 
			genStar(
					genConc(
							genAtom(codeCaractere.get("+"), 0, AtomType.Terminal), 
							genAtom(codeCaractere.get("IDNTER"), 0, AtomType.NonTerminal)
					)
			)
		);
	
	static Node T = genConc(
			genAtom(codeCaractere.get("IDNTER"), 4, AtomType.NonTerminal),
			genStar(
					genConc(
							genAtom(codeCaractere.get("."), 0, AtomType.Terminal), 
							genAtom(codeCaractere.get("IDNTER"), 1, AtomType.NonTerminal)
					)
			)
		);
			
	static Node F = genUnion(
			genUnion(
					genUnion(
							genUnion(
									genAtom(codeCaractere.get("IDNTER"), 5, AtomType.Terminal), 
									genAtom(codeCaractere.get("ELTER"), 5, AtomType.Terminal)
							), 
							genConc(
									genAtom(codeCaractere.get("("), 0, AtomType.Terminal), 
									genAtom(codeCaractere.get(")"), 0, AtomType.Terminal)
							)
					), 
					genConc(
							genConc(
									genAtom(codeCaractere.get("["), 0, AtomType.Terminal), 
									genAtom(codeCaractere.get("IDNTER"), 0, AtomType.NonTerminal)
							), 
							genAtom(codeCaractere.get("]"), 6, AtomType.Terminal)
					)
			), 
			genConc(
					genAtom(codeCaractere.get("(/"), 0, AtomType.Terminal),
					genAtom(codeCaractere.get("/)"), 7, AtomType.Terminal)
			)
		);
	
	
	static Vector<Node> A = new Vector<Node>();
	static{
		A.add(S);
		A.add(N);
		A.add(E);
		A.add(T);
		A.add(F);
	}
	
	
	// Fonction qui dessine les arbres 
	static int prof = 1;
	public static void imprimArbre(Node n){

		for (int k = 1; k <= prof; k++) {
			System.out.print("---");
		}
		
		switch (n.getOp().getNom()) {
		case "conc":
			prof++;
			System.out.println(">Conc");
			imprimArbre(n.getOp().getLeft());
			imprimArbre(n.getOp().getRight());
			prof--;
			break;
			
		case "union":
			System.out.println(">Union");
			prof++;
			imprimArbre(n.getOp().getLeft());
			imprimArbre(n.getOp().getRight());
			prof--;
			break;
		
		case "star":
			prof++;
			System.out.println(">star");
			imprimArbre(n.getOp().getNoeud());
			prof--;
			break;
			
		case "un":
			prof++;
			System.out.println(">Un");
			imprimArbre(n.getOp().getNoeud());
			prof--;
			break;
			
		case "atom":
			System.out.println(">"+n.getOp().getAtom());
			break;
			
		default:
			System.out.println("Error");
			break;
		}
		
	}
	
	
	//Fonction qui analyse
	public static boolean analyse(Node p) throws IOException{
		
		//Grammaire g0 = new Grammaire();
		
		boolean res = false;
		
		Operation n = p.getOp();
		
		switch (n.getNom()) {
		case "conc":
			if(analyse(p.getOp().getLeft())){
				res = analyse(n.getRight());
			}
			else{
				res = false;
			}
			break;
			
		case "union" :
			if(analyse(n.getLeft())){
				res = true;
			}
			else{
				res = analyse(n.getRight());
			}
			break;
			
		case "star":
			res = true;
			while (analyse(n.getNoeud())) {}
			break;
		case "un":
			res = true;
			if(analyse(n.getNoeud())){}
			break;
		case "atom":
			// Case : Terminal
			if(n.getAtomType().equals(AtomType.Terminal)){
				if(n.getCode() == code){
					res = true;
					if(n.getAct() != 0){
						//g0.action(n.getAct());
					}
					scan();
				}
				else res = false;
			}
			
			// Case :m Non Terminal
			else if(n.getAtomType().equals(AtomType.NonTerminal)){
				if(analyse( A.get(A.indexOf(n.getCode()))) ){
					if(n.getAct() != 0){
						//g0.action(n.getAct());
					}
					res = true;
				}
				else res = false;
			}
			break;

		default:
			break;
		}
		
		return res;
	}
	
	
	
	public static int i=0;
	
	public static int code;
	public static int action;
	public static AtomType type;
	public static String chaine;
	
	public static void scan() throws IOException{
		
		
		//while(i<line.length()){
			lireBlanc();
			String a = line.charAt(i)+"";
			switch (a) {
			case "-":
				a = a+line.charAt(i+1)+line.charAt(i+2);
				code = codeCaractere.get(a);
				type = AtomType.Terminal;
				chaine = a;
				i=i+3;
				//System.out.println("case - i="+i+a.toUpperCase());
				affiche();
				break;
				
			case "(" :
				if ((line.charAt(i+1))=='/') {
					a = a+line.charAt(i+1);
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+2;
					//System.out.println("case ( if i="+i);
					affiche();
				}
				else{
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+1;
					//System.out.println("case ( else i="+i);
					affiche();
				}
				break;
				
			case "/":
				if ((line.charAt(i+1))==')') {
					a = a+line.charAt(i+1);
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+2;
					//System.out.println("case / if i="+i);
					affiche();
				}
				else{
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+1;
					//System.out.println("case / else i="+i);
					affiche();
				}
				break;
				
			case "'":
				a="";
				while (!((line.charAt(i+1)+"").equals("'"))) {
					
					// S'il arrive sur une guillemet, il passe au caractère suivant puis il enlève les blancs (s'il y en a)
					i++;
					lireBlanc();
					
					// Comme on lit les blancs, on peut se retrouver malencontreusement sur une guillmet
					// Dans ce cas il faut sortir du while
					if((line.charAt(i)+"").equals("'")){
						break;
					}
					// Dans le cas ou ce n'est pas une guillemet, alors on ajoute la caractère courant à "a"
					else{
						a=a+line.charAt(i);
					}
				
					
				}
				
				code = codeCaractere.get("ELTER");
				type = AtomType.Terminal;
				chaine = a;
				lireBlanc();
				i=i+1;
				affiche();
				break;

			default:
				if(line.charAt(i)=='.' || line.charAt(i)==';' || line.charAt(i)==',' || line.charAt(i)=='+' || line.charAt(i)==')' || line.charAt(i)=='[' || line.charAt(i)==']'){
					code = codeCaractere.get("ELTER");
					type = AtomType.Terminal;
					chaine = a;
					i++;
					//System.out.println("case default if i="+i);
					affiche();
				}
				else{
					Pattern p = Pattern .compile("[a-zA-Z1-9]");
					String test = line.charAt(i)+"";
					Matcher m = p.matcher(test);
					while (m.find()) {
						if(p.matcher(line.charAt(i+1)+"").find()){
							a=a+line.charAt(i+1);
						}
						i++;
						test = line.charAt(i)+"";
						m = p.matcher(test);
					}
					chaine = a;
					code = codeCaractere.get("IDNTER");
					type = AtomType.NonTerminal;
					//System.out.println("case default else i="+i);
					affiche();
				}
				break;
			}
		}
		
		
	//}
	
	// fonction qui permet de lire l'action qui est suivie du #
	public static void lireAction(){
		String caractereLu = line.charAt(i)+"";
		
		if(caractereLu.equals("#")){
			i++;
			Pattern p = Pattern .compile("[a-zA-Z1-9]");
			String test = line.charAt(i)+"";
			Matcher m = p.matcher(test);
			while (m.find()) {
				if(p.matcher(line.charAt(i+1)+"").find()){
					caractereLu = caractereLu + line.charAt(i+1);
				}
				i++;
				test = line.charAt(i)+"";
				m = p.matcher(test);
			}
			action = Integer.parseInt(caractereLu);
		}
		else{
			action = 0;
		}
	}
	
	
	public static void lireBlanc() throws IOException{
		while (line.charAt(i)==' ' && i<line.length()) {
			i++;
		}
	}
	
	
	public static Vector<Node> pile = new Vector<Node>();
	public static HashMap<String, String> dicont = new HashMap<String, String>();
	public static HashMap<String, String> dicot = new HashMap<String, String>();
	
	public static void goAction(int action){
		Node t1 =  new Node();
		Node t2 =  new Node();
		
		switch (action) {
		case 1:
			pile.remove(t1);
			pile.remove(t2);
			A.add(t1);
			break;

		case 2:
			pile.add(genAtom(recherche(dicont), action, caType));
			break;
			
		case 3:
			pile.remove(t1);
			pile.remove(t2);
			pile.add(genUnion(t1, t2));
			break;
			
		case 4:
			pile.remove(t1);
			pile.remove(t2);
			pile.add(genConc(t1, t2));
			break;
			
		case 5:
			if(caType == AtomType.Terminal){
				pile.add(genAtom(recherche(dicot), action, AtomType.Terminal));
			}
			else{
				pile.add(genAtom(recherche(dicont), action, AtomType.NonTerminal));
			}
			break;
			
		case 6:
			pile.remove(t1);
			pile.add(genStar(t1));
			break;
			
		case 7:
			pile.remove(t1);
			pile.add(genUn(t1));
			break;
			
		default:
			break;
		}	
	}
	
	
	
	private static int recherche(HashMap<String, String> dicot2) {
		
		return 0;
	}


	public static void affiche(){
		//System.out.println("code = "+ code);
		//System.out.println("type = "+ type);
		System.out.print(chaine);
	}
	
	public static Node genConc(Node p1, Node p2){
		Operation op = new Conc(p1,p2);	
		Node suivant = p1;
		Node p = new Node(suivant, op);
		return p;
	}
	
	public static Node genStar(Node stare){
		Operation op = new Star(stare);	
		Node suivant = stare;
		Node p = new Node(suivant, op);
		return p;
	}
	
	public static Node genUn(Node une){
		Operation op = new Un(une);	
		Node suivant = une;
		Node p = new Node(suivant, op);
		return p;
	}
	
	public static Node genAtom(int cod, int act, AtomType atype){
		Operation op = new Atom(cod, act, atype);	
		Node suivant = null;
		Node p = new Node(suivant, op);
		return p;
	}
	
	public static Node genUnion(Node p1, Node p2){
		Operation op = new Union(p1,p2);	
		Node suivant = p1;
		Node p = new Node(suivant, op);
		return p;
	}
}
