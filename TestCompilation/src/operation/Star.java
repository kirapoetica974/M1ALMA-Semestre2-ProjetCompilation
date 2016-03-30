package operation;

import type.AtomType;
import type.Node;


public class Star extends AbstractOperation{
	Node stare;
	
	public Star(Node stare2) {
		this.stare = stare2;
		this.nom = "star";
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
		return null;
	}

	@Override
	public Node getNoeud() {
		return this.stare;
	}

	@Override
	public AtomType getAtomType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCode() {
		return -1;
	}

	@Override
	public int getAct() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int genAct() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
