/**
 * 
 */
package pl.progree.promation.gui.swing.trees;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import pl.progree.promation.Promation;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainTreeModel extends DefaultTreeModel {
	private Promation promation;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public MainTreeModel(Promation promation) {
		super(new DefaultMutableTreeNode("Promation",true), true);
		this.promation = promation;
	}
	
	
	

}
