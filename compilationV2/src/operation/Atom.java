package operation;

import type.AtomType;
import type.Node;


public class Atom extends AbstractOperation{

	int cod;
	int act;
	AtomType atype;
	
	public Atom(int cod, int act, AtomType atype) {
		this.cod = cod;
		this.act = act;
		this.atype = atype;
		this.nom = "atom";
	}

	@Override
	public String getNom() {
		return this.nom;
		
	}

	@Override
	public Node getLeft() {
		return null;
	}

	@Override
	public Node getRight() {
		return null;
	}

	@Override
	public String getAtom() {
		String noeud = cod+", "+ act + ", " + atype;
		return noeud;
	}

	@Override
	public Node getNoeud() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomType getAtomType() {
		return atype;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return this.cod;
	}

	@Override
	public int getAct() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int genAct() {
		// TODO Auto-generated method stub
		return this.act;
	}
}
