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
public interface Operation {

	String getNom();
	
	Node getLeft();
	
	Node getRight();
	
	String getAtom();
	
	Node getNoeud();
	
	AtomType getAtomType();
	
	int getCode();
	
	int getAct();
}
