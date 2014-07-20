/**
 * 
 */
package pl.progree.promation.gui.swing.trees;

import javax.swing.JTree;

import pl.progree.promation.Promation;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainTree extends JTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainTree(Promation promation) {
		super(new MainTreeModel(promation));
	}

}
