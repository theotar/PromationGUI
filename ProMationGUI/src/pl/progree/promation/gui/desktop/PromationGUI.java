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
public class PromationGUI extends Promation{
	
	private MainWindow mainWindow;
	
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

}
