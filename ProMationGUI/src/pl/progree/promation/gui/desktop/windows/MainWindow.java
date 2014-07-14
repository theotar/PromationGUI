/**
 * 
 */
package pl.progree.promation.gui.desktop.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.MatteBorder;

import pl.progree.promation.Promation;
import pl.progree.promation.gui.desktop.PromationGUI;

/**
 * @author Wojciech Pierzchalski, Progree
 *
 */
public class MainWindow extends JFrame {
	
	private PromationGUI gui;
	
	private JPanel northPanel;
	private JPanel leftPanel;
	private JTree tree;
	private JScrollPane treeScrollPane;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param gUI
	 * @throws HeadlessException
	 */
	public MainWindow(PromationGUI gUI) throws HeadlessException {
		gui = gUI;
		
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
		this.initMenu();
		this.initNorthPanel();
		
	}
	private void initMenu(){
		JMenuBar menubar=new JMenuBar();
		JMenu menuPlik=new JMenu("Plik");
		JMenuItem miNowy=new JMenuItem("Nowy...");
		menuPlik.add(miNowy);
		menubar.add(menuPlik);

		menubar.setBorder(new MatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		this.setJMenuBar(menubar);	
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
	public PromationGUI getGUI() {
		return gui;
	}
	public Promation getPromation() {
		return gui.getPromation();
	}
	public void setGUI(PromationGUI gUI) {
		gui = gUI;
	}
	
	
	
	

}
