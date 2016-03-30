package operation;

import type.AtomType;
import type.Node;


public class Un extends AbstractOperation{
	Node une;
	
	public Un(Node une2) {
		this.une = une2;
		this.nom = "un";
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
		return this.une;
	}

	@Override
	public AtomType getAtomType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
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
