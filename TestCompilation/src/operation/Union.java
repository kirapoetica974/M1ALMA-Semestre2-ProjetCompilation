package operation;

import type.AtomType;
import type.Node;


public class Union extends AbstractOperation{
	Node left;
	Node right;
	
	public Union(Node left, Node right) {
		this.left = left;
		this.right = right;
		this.nom = "union";
	}

	@Override
	public Node getLeft() {
		return left;
	}

	@Override
	public Node getRight() {
		return right;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public String getAtom() {
		return null;
	}

	@Override
	public Node getNoeud() {
		// TODO Auto-generated method stub
		return null;
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
