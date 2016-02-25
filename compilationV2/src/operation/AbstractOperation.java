/**
 * 
 */
package operation;

import type.AtomType;
import type.Node;

/**
 * @author Rachelle
 *
 */
public abstract class AbstractOperation implements Operation{

	protected String nom;
	
	
	public abstract String getNom();
	
	public abstract Node getLeft();
	
	public abstract Node getRight();
	
	public abstract String getAtom();
	
	public abstract Node getNoeud();
	
	public abstract AtomType getAtomType();
	
	public abstract int getCode();
	
	public abstract int genAct();
}
