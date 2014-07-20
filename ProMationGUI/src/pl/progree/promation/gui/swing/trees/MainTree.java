/**
 * 
 */
package pl.progree.promation.gui.swing.trees;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

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
	
	private JPopupMenu promationMenu;
	
	public MainTree(Promation promation) {
		super(new MainTreeModel(promation));
		this.promationMenu=new JPopupMenu("Promation");
		this.promationMenu.add(new JMenuItem("Dupa"));
		this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node=(DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				if(node.isRoot()) MainTree.this.setComponentPopupMenu(MainTree.this.promationMenu);
				else  MainTree.this.setComponentPopupMenu(null);
				
			}
		});
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				this.showPopupMenu(e);
//			}
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				this.showPopupMenu(e);
//			}
//			private void showPopupMenu(MouseEvent e){
//				if(e.isPopupTrigger()) {
//					System.err.println("works!");
//					MainTree.this.getPromationMenu().show(MainTree.this, e.getX(), e.getY());
//				}
//			}
//		});
	}

	public JPopupMenu getPromationMenu() {
		return promationMenu;
	}

	public void setPromationMenu(JPopupMenu promationMenu) {
		this.promationMenu = promationMenu;
	}
	

}
