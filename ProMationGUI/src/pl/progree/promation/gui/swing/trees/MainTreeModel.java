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
import pl.progree.promation.system.SzafaSystemowa;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainTreeModel extends DefaultTreeModel implements PromationListener{
	private static class INDEKSY_PROJEKTU{
		private static int SZAFY_SYSTEMOWE=0;
	}
	private Promation promation;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode createProjektNode(Projekt projekt){
		DefaultMutableTreeNode projektNode=new DefaultMutableTreeNode(projekt, true);
		this.createSzafySystemoweNode(projektNode);
		return projektNode;
	}
	private void createSzafySystemoweNode(DefaultMutableTreeNode projektNode){
		DefaultMutableTreeNode szafyNode=new DefaultMutableTreeNode("Szafy Systemowe", true);
		projektNode.add(szafyNode);
	}
	private DefaultMutableTreeNode createSzafaSystemowaNode(SzafaSystemowa szafa){
		DefaultMutableTreeNode szafaNode=new DefaultMutableTreeNode(szafa, true);
		return szafaNode;
	}
	private DefaultMutableTreeNode getProjektNode(Projekt projekt){
		DefaultMutableTreeNode root=this.getRoot();
		for(int i=0;i<root.getChildCount();i++)
			if(((DefaultMutableTreeNode)root.getChildAt(i)).getUserObject().equals(projekt)) return (DefaultMutableTreeNode) root.getChildAt(i);
		return null;
	}

	
	public MainTreeModel(Promation promation) {
		super(new DefaultMutableTreeNode("Promation",true), true);
		this.promation = promation;
		this.promation.addListener(this);
	}
	/**
	 * 
	 * @return projekt dla korego podany zosta³ node
	 */
	public Projekt getProjekt(DefaultMutableTreeNode node){
		if(node.getUserObject() instanceof Projekt) return (Projekt)node.getUserObject();
		if(node.getParent()==null) return null;
		return this.getProjekt((DefaultMutableTreeNode)node.getParent());
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
	@Override
	public void szafaSystemowaAdded(PromationeEvent e) {
		DefaultMutableTreeNode szafyNode=(DefaultMutableTreeNode) this.getProjektNode(e.getProjekt()).getChildAt(MainTreeModel.INDEKSY_PROJEKTU.SZAFY_SYSTEMOWE);
		this.insertNodeInto(this.createSzafaSystemowaNode(e.getSzafaSystemowa()), szafyNode, szafyNode.getChildCount());
	}
	
	
	

}
