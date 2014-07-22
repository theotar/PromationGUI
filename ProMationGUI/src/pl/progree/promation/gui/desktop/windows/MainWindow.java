/**
 * 
 */
package pl.progree.promation.gui.desktop.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import pl.progree.promation.Promation;
import pl.progree.promation.gui.desktop.PromationGUI;
import pl.progree.promation.gui.swing.trees.MainTree;
import pl.progree.promation.projekt.Projekt;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainWindow extends JFrame {
	private class MenuItemList extends HashSet<JMenuItem> implements TreeSelectionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) e.getPath().getLastPathComponent();
			if(node.getUserObject() instanceof Projekt) MainWindow.this.menuItemsEnabledOnProjectSelection.enableAll();
			else  MainWindow.this.menuItemsEnabledOnProjectSelection.disableAll();
			
		}
		public void disableAll(){
			for (JMenuItem jMenuItem : menuItemsEnabledOnProjectSelection) {
				jMenuItem.setEnabled(false);
			}
		}
		public void enableAll(){
			for (JMenuItem jMenuItem : menuItemsEnabledOnProjectSelection) {
				jMenuItem.setEnabled(true);
			}
		}
		
		
	}
	
	private PromationGUI promation;
	private MenuItemList menuItemsEnabledOnProjectSelection;
	
	private JPanel northPanel;
	
	private MainTree tree;
	private JScrollPane treeScrollPane;
	private JSplitPane horizontalSplitPane;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param promation
	 * @throws HeadlessException
	 */
	public MainWindow(PromationGUI promation) throws HeadlessException {
		this.promation = promation;
		this.menuItemsEnabledOnProjectSelection=new MenuItemList();
		
		this.setTitle("ProMation");
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(PromationGUI.class.getResource("/gui/res/EdGraf.png")));
	
		Dimension size=new Dimension(1000, 300);
		this.setMinimumSize(size);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//		this.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				EdGraf.this.zakoncz();
//			}
//		});
		this.initComponents();
		this.validate();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.toFront();
	}
	private void initComponents(){
		this.getContentPane().setLayout(new BorderLayout());
		this.initNorthPanel();
		this.initHorizontalSplitPane();
		
		this.initMenu();//ostatni init ze wzgledu na jpopupy
	}
	private void initMenu(){
		JMenuBar menubar=new JMenuBar();
		JMenu menuPlik=new JMenu("Plik");
		JMenuItem miNowy=new JMenuItem("Nowy Projekt");
		miNowy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.nowyProjekt();
			}
		});
		menuPlik.add(miNowy);
		JMenuItem miOtworz=new JMenuItem("Otwórz Projekt");
		miOtworz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.otworzProjekt();
			}
		});
		menuPlik.add(miOtworz);
		
		JMenuItem miZapisz=new JMenuItem("Zapisz");
		miZapisz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object o=MainWindow.this.tree.getSelectionPath().getLastPathComponent();
				if(o==null) return;
				DefaultMutableTreeNode node=(DefaultMutableTreeNode) o;
				if(node.getUserObject() instanceof Projekt) 
					MainWindow.this.zapiszProjekt((Projekt) node.getUserObject());
			}
		});
		this.menuItemsEnabledOnProjectSelection.add(miZapisz);
		menuPlik.add(miZapisz);
		menubar.add(menuPlik);

		menubar.setBorder(new MatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		this.setJMenuBar(menubar);
		this.menuItemsEnabledOnProjectSelection.disableAll();
	}
	private void initHorizontalSplitPane(){
		this.tree=new MainTree(this.promation);
		this.tree.addTreeSelectionListener(this.menuItemsEnabledOnProjectSelection);
		this.treeScrollPane=new JScrollPane(tree);
		JPanel testPanel=new JPanel();
		this.horizontalSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.treeScrollPane, testPanel);
		//this.verticalSplitPane.setOneTouchExpandable(true);
		this.horizontalSplitPane.setDividerLocation(300);
		Dimension minimumSize = new Dimension(100, 50);
		this.tree.setMinimumSize(minimumSize);
		testPanel.setMinimumSize(minimumSize);
		this.horizontalSplitPane.setPreferredSize(new Dimension(800, 200));
        
		this.getContentPane().add(this.horizontalSplitPane, BorderLayout.CENTER);
	}
	/**
	 * Metoda inicjuj¹ca northPanel
	 * @see #northPanel
	 */
	private void initNorthPanel(){
		this.northPanel=new JPanel(new BorderLayout());
		Dimension size=new Dimension(1000, 50);
		this.northPanel.setPreferredSize(size);
		this.northPanel.setMinimumSize(size);
		this.northPanel.add(new JLabel("Tu bêdzie toolbar"));
		this.getContentPane().add(this.northPanel,BorderLayout.NORTH);
	}
	public Promation getPromation() {
		return this.promation;
	}
	public void nowyProjekt(){
		String nazwaProjektu=JOptionPane.showInputDialog(this, "Podaj nazwê");
		if(nazwaProjektu==null) return;
		nazwaProjektu=nazwaProjektu.trim();
		if(!nazwaProjektu.isEmpty()){
			Projekt p=new Projekt(nazwaProjektu);
			this.getPromation().addProjekt(p);
		}
	}
	public void zapiszProjekt(Projekt projekt){
		if(projekt.getPlik()==null) return;
		else this.getPromation().saveProjektToFile(projekt, projekt.getPlik());
	}
	public void otworzProjekt(){
		File plik=new File("E:\\TEMP\\dupa.pro");
		this.promation.loadProjektFromFile(plik);
	}
	
	
	

}
