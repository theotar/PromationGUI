/**
 * 
 */
package pl.progree.promation.gui.swing.trees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import pl.progree.promation.gui.desktop.PromationGUI;
import pl.progree.promation.gui.desktop.windows.MainWindow;
import pl.progree.promation.projekt.Projekt;

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
	private JPopupMenu projektMenu;
	private JPopupMenu szafySystemoweMenu;
	
	public MainTree(PromationGUI promation) {
		super(new MainTreeModel(promation));
		this.promationGUI=promation;
		this.initMenu();
		this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node=(DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				if(node.isRoot()) MainTree.this.setComponentPopupMenu(MainTree.this.promationMenu);
				else if(node.getUserObject() instanceof Projekt) MainTree.this.setComponentPopupMenu(MainTree.this.projektMenu);
				else if(node.getUserObject().equals("Szafy Systemowe")) MainTree.this.setComponentPopupMenu(MainTree.this.szafySystemoweMenu);
				else MainTree.this.setComponentPopupMenu(null);
				
			}
		});
	}
	private void initMenu(){
		this.initPromationMenu();
		this.initProjektMenu();
		this.initSzafySystemoweMenu();
	}
	private void initPromationMenu(){
		this.promationMenu=new JPopupMenu("Promation");
		JMenuItem miNowy=new JMenuItem("Nowy Projekt");
		miNowy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainTree.this.promationGUI.getMainWindow().nowyProjekt();
			}
		});
		this.promationMenu.add(miNowy);
		JMenuItem miOtworz=new JMenuItem("Otwórz Projekt");
		miOtworz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainTree.this.promationGUI.getMainWindow().otworzProjekt();
			}
		});
		this.promationMenu.add(miOtworz);
	}
	private void initProjektMenu(){
		this.projektMenu=new JPopupMenu("Projekt");
		JMenuItem miZapisz=new JMenuItem("Zapisz");
		miZapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o=MainTree.this.getSelectionPath().getLastPathComponent();
				if(o==null) return;
				DefaultMutableTreeNode node=(DefaultMutableTreeNode) o;
				if(node.getUserObject() instanceof Projekt) 
					MainTree.this.promationGUI.getMainWindow().zapiszProjekt((Projekt) node.getUserObject());
			}
		});
		this.projektMenu.add(miZapisz);
	}
	private void initSzafySystemoweMenu(){
		this.szafySystemoweMenu=new JPopupMenu("Szafy Systemowe");
		JMenuItem miDodaj=new JMenuItem("Dodaj Szafê");
		miDodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o=MainTree.this.getSelectionPath().getLastPathComponent();
				if(o==null) return;
				DefaultMutableTreeNode node=(DefaultMutableTreeNode) o;
				if(node.getUserObject().equals("Szafy Systemowe")) 
					MainTree.this.promationGUI.getMainWindow().dodajSzafeSystemowa(MainTree.this.getModel().getProjekt(node));
			}
		});
		this.szafySystemoweMenu.add(miDodaj);
		
	}

	public JPopupMenu getPromationMenu() {
		return promationMenu;
	}

	public void setPromationMenu(JPopupMenu promationMenu) {
		this.promationMenu = promationMenu;
	}
	@Override
	public MainTreeModel getModel() {
		return (MainTreeModel) super.getModel();
	}

}
