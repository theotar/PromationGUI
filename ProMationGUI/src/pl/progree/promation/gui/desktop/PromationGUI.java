/**
 * 
 */
package pl.progree.promation.gui.desktop;

import pl.progree.promation.Promation;
import pl.progree.promation.gui.desktop.windows.MainWindow;

/**
 * @author Progree
 *
 */
public class PromationGUI {
	
	private MainWindow mainWindow;
	private Promation promation;
	
	protected void createMainWindow(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {;
            	PromationGUI.this.setMainWindow(new MainWindow(PromationGUI.this));

            }
        });
	}
	public PromationGUI(){
		this.createMainWindow();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PromationGUI();

	}

	/**
	 * @return the mainWindow
	 */
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	protected void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * @return the promation
	 */
	public Promation getPromation() {
		return promation;
	}

	/**
	 * @param promation the promation to set
	 */
	public void setPromation(Promation promation) {
		this.promation = promation;
	}
	

	

}
