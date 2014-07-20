/**
 * 
 */
package pl.progree.promation.gui.swing.trees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import pl.progree.promation.Promation;
import pl.progree.promation.gui.desktop.PromationGUI;
import pl.progree.promation.gui.desktop.windows.MainWindow;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainTree extends JTree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PromationGUI promationGUI;
	private JPopupMenu promationMenu;
	
	public MainTree(PromationGUI promation) {
		super(new MainTreeModel(promation));
		this.promationGUI=promation;
		this.initMenu();
		this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node=(DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				if(node.isRoot()) MainTree.this.setComponentPopupMenu(MainTree.this.promationMenu);
				else  MainTree.this.setComponentPopupMenu(null);
				
			}
		});
	}
	private void initMenu(){
		this.promationMenu=new JPopupMenu("Promation");
		JMenuItem miNowy=new JMenuItem("Nowy Projekt");
		miNowy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainTree.this.promationGUI.getMainWindow().nowyProjekt();
			}
		});
		this.promationMenu.add(miNowy);
	}

	public JPopupMenu getPromationMenu() {
		return promationMenu;
	}

	public void setPromationMenu(JPopupMenu promationMenu) {
		this.promationMenu = promationMenu;
	}
	

}
