/**
 * 
 */
package pl.progree.promation.gui.swing.trees;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import pl.progree.promation.Promation;
import pl.progree.promation.event.PromationListener;
import pl.progree.promation.event.PromationeEvent;
import pl.progree.promation.projekt.Projekt;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainTreeModel extends DefaultTreeModel implements PromationListener{
	private static class INDEKSY_PROJEKTU{
		private static int SYSTEM=0;
	}
	private Promation promation;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode createProjektNode(Projekt projekt){
		DefaultMutableTreeNode projektNode=new DefaultMutableTreeNode(projekt, true);
		this.createSystemNode(projektNode);
		return projektNode;
	}
	private void createSystemNode(DefaultMutableTreeNode projektNode){
		DefaultMutableTreeNode systemNode=new DefaultMutableTreeNode("System", true);
		projektNode.add(systemNode);
	}

	
	public MainTreeModel(Promation promation) {
		super(new DefaultMutableTreeNode("Promation",true), true);
		this.promation = promation;
		this.promation.addListener(this);
	}
	@Override
	public DefaultMutableTreeNode getRoot() {
		// TODO Auto-generated method stub
		return (DefaultMutableTreeNode) super.getRoot();
	}

	@Override
	public void projectAdded(PromationeEvent e) {
		Projekt projekt=e.getProjekt();
		this.insertNodeInto(this.createProjektNode(projekt), this.getRoot(), this.getChildCount(this.getRoot()));
	}
	
	
	

}
