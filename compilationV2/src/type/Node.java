package type;

import operation.Operation;

public class Node {
	Node suivant;
	//Node precedent;
	Operation op;
	
	public Node(Node suivant, Operation op){
		this.suivant = suivant;
		this.op = op;
	}

	/**
	 * @return the suivant
	 */
	public Node getSuivant() {
		return suivant;
	}

	/**
	 * @return the op
	 */
	public Operation getOp() {
		return op;
	}

	/**
	 * @param suivant the suivant to set
	 */
	public void setSuivant(Node suivant) {
		this.suivant = suivant;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(Operation op) {
		this.op = op;
	}
}
