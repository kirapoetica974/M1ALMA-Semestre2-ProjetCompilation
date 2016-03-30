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
	
	public static HashMap<Integer, String> codeCaractere;
	
	// initialisation du hashmap
	static {
		codeCaractere = new HashMap<Integer, String>();
		codeCaractere.put(0, "S");
		codeCaractere.put(1, "N");
		codeCaractere.put(2, "E");
		codeCaractere.put(3, "T");
		codeCaractere.put(4, "F");
		codeCaractere.put(6, ";");
		codeCaractere.put(7,",");
		codeCaractere.put(8,"-->");
		codeCaractere.put(9,"+");
		codeCaractere.put(10,".");
		codeCaractere.put(11,"(");
		codeCaractere.put(12,")");
		codeCaractere.put(13,"[");
		codeCaractere.put(14,"]");
		codeCaractere.put(15,"(/");
		codeCaractere.put(16,"/)");
		codeCaractere.put(17,"IDNTER");
		codeCaractere.put(18,"ELTER");
}
	
	public static void main(String[] args) throws IOException {
		//imprimArbre(E);
		scan();
		
		
		
		// test de recherche
		/*dicont.add("S");
		chaine = "S";
		System.out.println(recherche(dicont));
		System.out.println(dicont);
		
		// test de g0Action
		action = 2;
		type = AtomType.Terminal;
		g0Action(2);
		System.out.println(pile.get(0).getOp().getNom());*/
		
		Boolean b = analyse(S);
		System.out.println(b);
		
		//System.out.println(codeCaractere.get(E.getOp().getCode()));
		
		//Operation n = S.getOp();
		//System.out.println( A.get(A.indexOf( n.getCode() ) +1 ));
	}
	
	static Node S = genConc(
			genStar(
					genConc(
							genConc(
									genConc(
											genAtom(1 , 0, AtomType.NonTerminal), 
											genAtom(8 , 0, AtomType.Terminal)
									), 
									genAtom(2 , 0, AtomType.NonTerminal)
							), 
							genAtom(7 , 1, AtomType.Terminal)
					)
			), 
			genAtom(6 , 0, AtomType.Terminal)
		);
	
	static Node N = genAtom(17 , 2, AtomType.Terminal);
	
	static Node E = genConc(
			genAtom(3 , 3, AtomType.NonTerminal), 
			genStar(
					genConc(
							genAtom(9 , 0, AtomType.Terminal), 
							genAtom(3 , 0, AtomType.NonTerminal)
					)
			)
		);
	
	static Node T = genConc(
			genAtom(4 , 4, AtomType.NonTerminal),
			genStar(
					genConc(
							genAtom(10 , 0, AtomType.Terminal), 
							genAtom(4 , 1, AtomType.NonTerminal)
					)
			)
		);
			
	static Node F = genUnion(
			genUnion(
					genUnion(
							genUnion(
									genAtom(17 , 5, AtomType.Terminal), 
									genAtom(18 , 5, AtomType.Terminal)
							), 
							genConc(
									genConc(
											genAtom(11, 0, AtomType.Terminal), 
											genAtom(2, 0, AtomType.Terminal)
									),
									genAtom(12, 0, AtomType.Terminal)
							)
					), 
					genConc(
							genConc(
									genAtom(13, 0, AtomType.Terminal), 
									genAtom(2, 0, AtomType.NonTerminal)
							), 
							genAtom(14, 6, AtomType.Terminal)
					)
			), 
			genConc(
					genConc(
							genAtom(15, 0, AtomType.Terminal),
							genAtom(2, 7, AtomType.Terminal)
					),
					genAtom(16, 0 , AtomType.Terminal)
			)
		);
	
	

	
	static HashMap<String, Node> A = new HashMap<String, Node>();
	static{
		A.put("S", S);
		A.put("N", N);
		A.put("E", E);
		A.put("T", T);
		A.put("F", F);
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
	public static boolean analyse(Node p) throws IOException {
		  Operation n = p.getOp();
		  
		  System.out.println("================== " + n.getNom() + "================");
		  
		  System.out.println("chaine = " + chaine);
		  
		  
		  if(n.getNom().equals("conc")){
			  System.out.println(" ----------> noeud gauche =  "+n.getLeft().getOp().getNom());
		    if (analyse(n.getLeft())) {
		    	System.out.println("----------> noeud droit =  "+n.getRight().getOp().getNom());
		      return analyse(n.getRight());
		    }
		  }
		  
		  if(n.getNom().equals("union")){
		    if (analyse(n.getLeft())) {
		      return true;
		    } else {
		      return analyse(n.getRight());
		    }
		  }

		  if(n.getNom().equals("star")){
		    while (analyse(n.getNoeud()));
			return true;
		  }

		  if(n.getNom().equals("un")){
				analyse(n.getLeft());
			return true;
		  }

		  if(n.getNom().equals("atom")){
			 
		    if(n.getAtomType().equals(AtomType.Terminal)) {
				System.out.println("2lément dans l'arbre = "+codeCaractere.get(n.getCode()));
				
				System.out.println("code element scanné = "+codeCaractere.get(code));
		      if(n.getCode() == code){
				scan();
		        return true;
		      }
		    }else{
		    	System.out.println(n.getCode());
		    	System.out.println(codeCaractere.get(n.getCode()) + "------------------------ ON SAUTE !!!");
		      if (analyse(A.get(codeCaractere.get(n.getCode()))))
		        return true;
		      }
		    }
		  
		  return false;
		}
		    
	
	
	
	
	public static int i=0;
	
	public static int code;
	public static int action;
	public static AtomType type;
	public static String chaine;
	
	
	public static void scanLigne() throws IOException{
		if(i<line.length()){
			//while(i<line.length()){
				lireBlanc();
				String a = line.charAt(i)+"";
				switch (a) {
				case "-":
					a = a+line.charAt(i+1)+line.charAt(i+2);
					code = 8;
					type = AtomType.Terminal;
					chaine = a;
					i=i+3;
					break;
					
				case "(" :
					if ((line.charAt(i+1))=='/') {
						a = a+line.charAt(i+1);
						code = 15;
						type = AtomType.Terminal;
						chaine = a;
						i=i+2;
						//System.out.println("case ( if i="+i);
						//affiche();
					}
					else{
						code = 11;
						type = AtomType.Terminal;
						chaine = a;
						i=i+1;
						//System.out.println("case ( else i="+i);
						//affiche();
					}
					break;
					
				case "/":
					if ((line.charAt(i+1))==')') {
						a = a+line.charAt(i+1);
						code = 16;
						type = AtomType.Terminal;
						chaine = a;
						i=i+2;
						//System.out.println("case / if i="+i);
						//affiche();
					}
					else{
						//code = codeCaractere.get(a);
						type = AtomType.Terminal;
						chaine = a;
						i=i+1;
						//System.out.println("case / else i="+i);
						//affiche();
					}
					break;
					
				case "'":
					a="";
					while (!((line.charAt(i+1)+"").equals("'"))) {
						
						// S'il arrive sur une guillemet, il passe au caractère suivant puis il enlève les blancs (s'il y en a)
						i++;
						lireBlanc();
						
						// Comme on lit les blancs, on peut se retrouver malencontreusement sur une guillemet
						// Dans ce cas il faut sortir du while
						if((line.charAt(i)+"").equals("'")){
							break;
						}
						// Dans le cas ou ce n'est pas une guillemet, alors on ajoute la caractère courant à "a"
						else{
							a=a+line.charAt(i);
						}
					
						
					}
					
					code = 18;
					type = AtomType.Terminal;
					chaine = a;
					//System.out.println("chaine scan = " + chaine + "________________*********************");
					lireBlanc();
					i=i+2;
					//affiche();
					break;
					
				case ".":
					code = 10;
					type = AtomType.Terminal;
					chaine = a;
					i++;
					break;
					
				case ",":
					code = 7;
					type = AtomType.Terminal;
					chaine = a;
					i++;
					break;
					
				case ";":
					code = 6;
					type = AtomType.Terminal;
					chaine = a;
					i++;
					break;
					
				case "+":
					code = 9;
					type = AtomType.Terminal;
					chaine = a;
					i++;
					break;
					
					
				case ")":
					code = 12;
					type = AtomType.Terminal;
					chaine = a;
					i++;
					break;
					
				case "[":
					code = 13;
					type = AtomType.Terminal;
					chaine = a;
					i++;
					break;
					
				case "]":
					code = 14;
					type = AtomType.Terminal;
					//System.out.println("dans le scan, chaine = " + chaine);
					chaine = a;
					i++;
					break;

				default:
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
						//System.out.println("dans le scan, chaine = " + chaine + "------------------------------------------");
						code = 17;
						type = AtomType.NonTerminal;
					break;
				}
			}
	}
	
	public static void scan() throws IOException{
		
		if(i>line.length()){
			if(br.readLine() != null){
				i=0;
				line = br.readLine();
				scanLigne();
			}
		}
		else{
			scanLigne();
		}
		
		
		
		
	}
	
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
	public static Vector<String> dicont = new Vector<String>();
	public static Vector<String> dicot = new Vector<String>();
	
	public static void g0Action(int act){
		Node t1 = null;
		Node t2 = null;
		
		if(pile.size() == 0){
			//t1 =  pile.get(pile.size()-1);
		}
		else if (pile.size() == 1){
			t1 =  pile.get(pile.size()-1);
		}
		else{
			t1 =  pile.get(pile.size()-1);
			t2 =  pile.get(pile.size()-2);
		}
		
		
		
		Node tempT1 = t1;
		Node tempT2 = t2;
		
		switch (action) {
		case 1:
			pile.remove(t1);
			pile.remove(t2);
			A.put(""+tempT1,tempT1);
			break;

		case 2:
			pile.add(genAtom(recherche(dicont), action, type));
			break;
			
		case 3:
			pile.remove(t1);
			pile.remove(t2);
			pile.add(genUnion(tempT1, tempT2));
			break;
			
		case 4:
			pile.remove(t1);
			pile.remove(t2);
			pile.add(genConc(tempT1, tempT2));
			break;
			
		case 5:
			if(type == AtomType.Terminal){
				pile.add(genAtom(recherche(dicot), act, AtomType.Terminal));
			}
			else{
				pile.add(genAtom(recherche(dicont), action, AtomType.NonTerminal));
			}
			break;
			
		case 6:
			pile.remove(t1);
			pile.add(genStar(tempT1));
			break;
			
		case 7:
			pile.remove(t1);
			pile.add(genUn(tempT1));
			break;
			
		default:
			break;
		}	
	}
	
	
	
	public static int recherche(Vector<String> dic) {
		boolean trouve = false;
		int res = 0;
		
		for (int i = 0; i < dic.size(); i++) {
			if(chaine.equals(dic.get(i))){
				trouve = true;
				res = i;
				break;
			}
		}
		
		if(!trouve){
			dic.add(chaine);
			res = dic.size()-1 ;
		}
		
		return res;

	}


	public static void affiche(){
		//System.out.println("code = "+ code);
		//System.out.println("type = "+ type);
		//System.out.print(chaine);
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
