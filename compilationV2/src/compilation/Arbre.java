package compilation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
	
	/*public static String line;
	
	static { try {
		line = br.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}*/
	
	public static HashMap<String, Integer> codeCaractere;
	
	// initialisation du hashmap
	static {
		codeCaractere = new HashMap<String, Integer>();
		/*codeCaractere.put("S", 1);
		codeCaractere.put("N", 2);
		codeCaractere.put("E", 3);
		codeCaractere.put("T", 4);
		codeCaractere.put("F", 5);*/
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
		scan();
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
	
	static Node[] A = {S, N, E, T, F};
	
	
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
	public boolean analyse(Node p) throws IOException{
		
		Grammaire g0 = new Grammaire();
		
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
						g0.action(n.getAct());
					}
					scan();
				}
				else res = false;
			}
			
			// Case :m Non Terminal
			else if(n.getAtomType().equals(AtomType.NonTerminal)){
				if(analyse(A[n.getCode()])){
					if(n.getAct() != 0){
						g0.action(n.getAct());
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
		String line = lireBlanc();
		while(i<line.length()){
			
			String a = line.charAt(i)+"";
			switch (a) {
			case "-":
				a = a+line.charAt(i+1)+line.charAt(i+2);
				code = codeCaractere.get(a);
				type = AtomType.Terminal;
				chaine = a;
				i=i+3;
				System.out.println("case - i="+i+a.toUpperCase());
				
				affiche();
				//scan();
				break;
				
			case "(" :
				if ((line.charAt(i+1))=='/') {
					a = a+line.charAt(i+1);
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+2;
					System.out.println("case ( if i="+i);
					affiche();
					//scan();
				}
				else{
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+1;
					System.out.println("case ( else i="+i);
					affiche();
					//scan();
				}
				break;
				
			case "/":
				if ((line.charAt(i+1))==')') {
					a = a+line.charAt(i+1);
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+2;
					System.out.println("case / if i="+i);
					affiche();
					//scan();
				}
				else{
					code = codeCaractere.get(a);
					type = AtomType.Terminal;
					chaine = a;
					i=i+1;
					System.out.println("case / else i="+i);
					affiche();
					//scan();
				}
				break;
				
			case "'":
				a="";
				while (!((line.charAt(i+1)+"").equals("'"))) {
					a=a+line.charAt(i+1);
					i++;
					System.out.println("case ' while i="+i);
					//affiche();
					//scan();
				}
				code = codeCaractere.get("ELTER");
				type = AtomType.Terminal;
				chaine = a;
				i=i+2;
				System.out.println("case ' i="+i);
				affiche();
				//scan();
				break;

			default:
				if(line.charAt(i)=='.' || line.charAt(i)==';' || line.charAt(i)==',' || line.charAt(i)=='+' || line.charAt(i)==')' || line.charAt(i)=='[' || line.charAt(i)==']'){
					code = codeCaractere.get("ELTER");
					type = AtomType.Terminal;
					chaine = a;
					i++;
					System.out.println("case default if i="+i);
					affiche();
					//scan();
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
					//i++;
					System.out.println("case default else i="+i);
					affiche();
					//scan();
				}
				break;
			}
		}
		
		
	}
	
	public static String lireBlanc() throws IOException{
		String sansBlanc="";
		String line;
		while ((line=br.readLine()) != null) {
			
		}
		return sansBlanc;
	}
	
	public static void affiche(){
		//System.out.println("code = "+ code);
		//System.out.println("type = "+ type);
		System.out.println("chaine = "+ chaine);
		System.out.println("_____________________");
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
