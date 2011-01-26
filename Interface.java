import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;



import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;

public class Interface {

	/****************************** CLASS'VARIABLES ****************************************************************************/
	// Main Frame
	private JFrame jFrame = null;
	private JPanel jContentPane = null;
	

	// Image Panel component of the Main Frame in the jContentPane
	private ImagePanel jImagePane = null;
	private JScrollPane jscrollpane = null;
	private JPanel jStatusPanel = null;

	// Tools Panel component of the Main Frame in the jContentPane
	private JButton jbTool = null;
	private JPanel jToolPane = null;
	private PrintP jLabelPrintPix = null;

	// Tools'Panel Components
	
	private JButton jButtonCrop = null;
	private JButton jButtonPrintPix = null;
	private JButton jButtonHisto = null;
	private JButton jButtonGray = null;
	private JButton jButtonBlur = null;
	private JButton jButtonFusion = null;
	private JButton jButtonResize = null;
	private JButton jbGradient = null;


	// Menu
	private JMenuBar jJMenuBar = null;
	private JMenu fileMenu = null;
	private JMenu helpMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuItem jMenuItemAbout = null;
	

	// Image variables
	private BufferedImage img = null;
	private JFileChooser jFileChooser = new JFileChooser();
	private String filename; // image's file name

	/* Histograms Var */
	private HistoFrame histoFrame = null;

	

	/******************************** CLASS' CONSTRUCTOR ***************************************************************************/
	public Interface() {
		this.getJFrame();
	}

	/******************************* COMPONENTS' GETTERS *****************************************************************************/
	/** JFRAME **/
	public JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(600, 500));
			jFrame.setTitle("Image Software");
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setLocationRelativeTo(null);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setContentPane(getJContentPane());
			jFrame.setVisible(true);
		}
		return jFrame;
	}

	/** CONTENT PANEL **/
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setBackground(Color.LIGHT_GRAY);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
			jContentPane.add(getJStatusPanel(), BorderLayout.SOUTH);
			jContentPane.add(getJToolPanel(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	/** SCROLL PANEL **/
	private JScrollPane getJScrollPane() {
		if (jscrollpane == null) {
			jscrollpane = new JScrollPane(getJImagePane());
			
			jscrollpane.setWheelScrollingEnabled(true);

			jscrollpane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jscrollpane.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
			jscrollpane.setBackground(Color.lightGray);
			jscrollpane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return jscrollpane;
	}

	/** IMAGE PANEL **/
	public ImagePanel getJImagePane() {
		if (jImagePane == null) {
			jImagePane = new ImagePanel();
			jImagePane.setBackground(Color.LIGHT_GRAY);
			}
		return jImagePane;
	}

	private JPanel getJStatusPanel(){
		if(jStatusPanel == null){
			BorderLayout borderLayout = new BorderLayout(5, 5);
			borderLayout.setHgap(0);
			borderLayout.setVgap(0);
			jStatusPanel = new JPanel();
			jStatusPanel.setLayout(borderLayout);
			jStatusPanel.setBackground(Color.orange);
			jStatusPanel.setSize(800, 50);
			jStatusPanel.setPreferredSize(new Dimension(570, 65));
			jStatusPanel.add(getJbTool(), BorderLayout.EAST);
			jStatusPanel.add(getLbPrintPix(), BorderLayout.CENTER);
			
		}
		return jStatusPanel;
	}
	private JButton getJbTool(){
		if(jbTool == null){
			jbTool = new JButton();
			jbTool.setPreferredSize(new Dimension(60,60));
			jbTool.setIcon((new ImageIcon("icon/tools.png")));
			jbTool.setToolTipText("Tools Box");
			jbTool.setBackground(new Color(51, 51, 51));
			jbTool.setForeground(Color.magenta);
			jbTool.setMnemonic(KeyEvent.VK_SPACE);
			jbTool.setBorderPainted(true);
			jbTool.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jToolPane.setVisible(!getJToolPanel().isVisible());
					if((jImagePane.getCurrentImage() != null)&& (jToolPane.isVisible()))
						jContentPane.setSize(jImagePane.getCurrentImage().getWidth()+ jToolPane.getWidth(), jImagePane.getCurrentImage().getHeight());
					else if((!jToolPane.isVisible()) && (jImagePane.getCurrentImage() != null))
						jContentPane.setSize(jImagePane.getCurrentImage().getWidth(), jImagePane.getCurrentImage().getHeight());
					else jContentPane.setSize(jContentPane.getWidth() + jToolPane.getWidth(), jContentPane.getHeight()+jToolPane.getHeight());
				}
			});
		}
		return jbTool;
	}
	
	private Dimension getJBDim(){
		return new Dimension(50, 50);
	}
	
	
	/** TOOL PANEL **/
	private JPanel getJToolPanel() {
		if (jToolPane == null) {
			jToolPane = new JPanel();
			jToolPane.setVisible(false);
			jToolPane.setBackground(Color.LIGHT_GRAY);
			jToolPane.setPreferredSize(new Dimension(60, 500));
			jToolPane.setLayout(new GridLayout(6, 1, 5, 5));
			jToolPane.add(getJButtonCrop());
			jToolPane.add(getJButtonPrintPix());
			//jToolPane.add(getLbPrintPix());
			jToolPane.add(getJButtonHisto());
			jToolPane.add(getJButtonGray());
			jToolPane.add(getJButtonBlur());
			jToolPane.add(getJButtonFusion());
		}
		return jToolPane;
	}

	/** TOOL'S BUTTONS **/
	// CROP BUTTON
	private JButton getJButtonCrop() {
        if (jButtonCrop == null) {
                jButtonCrop = new JButton(new ImageIcon("icon/crop.png"));
                jButtonCrop.setSize(getJBDim());
                jButtonCrop.setToolTipText("Crop");
                jButtonCrop.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                        img = jImagePane.getColorImage();
                                jContentPane.repaint();
                        }
                });
        }
        return jButtonCrop;
}
	//GRADIENT BUTTON
	private JButton getJBGradient(){
		if(jbGradient == null){
			
		}
		
		return jbGradient;
	}

	// PRINT PIXEL COLOR BUTTON
	private JButton getJButtonPrintPix() {
		if (jButtonPrintPix == null) {
			jButtonPrintPix = new JButton(new ImageIcon("icon/pip.png"));
			jButtonPrintPix.setSize(getJBDim());
			jButtonPrintPix.setToolTipText("Get pixel's color");
			jButtonPrintPix.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					boolean isprinting = jLabelPrintPix.getIsPrinting();
					if (isprinting) {
						jLabelPrintPix = new PrintP(jImagePane);
						jLabelPrintPix.setIsPrinting(false);
					}
					else {//jLabelPrintPix.init();
						jLabelPrintPix.setIsPrinting(true);}
					
					

				}
			});
		}
		return jButtonPrintPix;
	}

	// DISPLAY HISTOGRAMS BUTTON
	private JButton getJButtonHisto() {
		if (jButtonHisto == null) {
			jButtonHisto = new JButton(new ImageIcon("icon/histo.png"));
			jButtonHisto.setSize(getJBDim());
			jButtonHisto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					histoFrame = new HistoFrame(jImagePane);
					histoFrame.setSize(900, 550);
					histoFrame.setVisible(true);
				}
			});
			
		}
		return jButtonHisto;
	}

	// COLOR IN GRAY BUTTON
	 private JButton getJButtonGray() {
         if (jButtonGray == null) {
                 jButtonGray = new JButton(new ImageIcon("icon/gray.png"));
                 jButtonGray.setSize(getJBDim());
                 jButtonGray.addActionListener(new ActionListener(){
                         public void actionPerformed(ActionEvent e){
                                 jImagePane.setIsGray(true);
                                 img = jImagePane.getColorImage();
                                 jContentPane.repaint();
                         }
                 });
         }
         return jButtonGray;
 }

	//
	private JButton getJButtonBlur() {
		if (jButtonBlur == null) {
			jButtonBlur = new JButton(new ImageIcon("icon/blur.png"));
			jButtonBlur.setSize(getJBDim());
		}
		return jButtonBlur;
	}

	private JButton getJButtonFusion() {
		if (jButtonFusion == null) {
			jButtonFusion = new JButton(new ImageIcon("icon/fusion.png"));
			jButtonFusion.setSize(getJBDim());
		}
		return jButtonFusion;
	}

	private PrintP getLbPrintPix() {
		if (jLabelPrintPix == null) {
			jLabelPrintPix = new PrintP(jImagePane);
			jLabelPrintPix.setIsPrinting(false);
			jLabelPrintPix.setFont(new Font("Ubuntu", Font.PLAIN, 12));
		}
		return jLabelPrintPix;
	}


	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setPreferredSize(new Dimension(0, 20));
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getJMenuItem());
			fileMenu.add(getJMenuItem1());
		}
		return fileMenu;
	}

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			helpMenu.add(getMenuItemAbout());
			
		}
		return helpMenu;
	}
private JMenuItem getMenuItemAbout(){
	if (jMenuItemAbout == null) {
		jMenuItemAbout = new JMenuItem("About");
		jMenuItemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon("icon/ghost.png");
				JOptionPane.showMessageDialog(jMenuItemAbout,
					    "Picture Playground \n\n Version: 1.0 \n  Mosig Students UJF 2011.  \n \n Contributors : \n \t Ky Nguyen \n \t Zeina AbuAisha, \n \t Wafa Benkaouar",
					    "About Picture Playground",
					    JOptionPane.INFORMATION_MESSAGE,
					    icon);

					return;
				}}); 
	}
	return jMenuItemAbout;
}

	
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem("Open...");
			jMenuItem.addActionListener(new OpenAction());
		}
		return jMenuItem;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem("Save...");
			jMenuItem1.addActionListener(new SaveAction());
		}
		return jMenuItem1;
	}

	/*
	 * ==========================================================================
	 * =======================
	 */
	class OpenAction implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			int retval = jFileChooser.showOpenDialog(null);
			if (retval == jFileChooser.APPROVE_OPTION) {
				jFileChooser.setFileFilter(new ImageFilter());
				//jFileChooser.addChoosableFileFilter(new ImageFilter());
				File file = jFileChooser.getSelectedFile();
				try {
					img = ImageIO.read(file);
					jImagePane.setCurrentImage(img);
					jContentPane.repaint();
				} catch (IOException e) {
				}
			jFrame.setSize(getJImagePane().getCurrentImage().getWidth(),getJImagePane().getCurrentImage().getHeight());
			jFrame.setLocationRelativeTo(null);
			}
			
		}
	}

	/*
	 * ==========================================================================
	 * ============================================
	 */
	// ///////////////////////////////////////////////////////////////////////////////////////////
	// saving files
	public void save(File file) {
		this.filename = file.getName();
		if (jFrame != null) {
			jFrame.setTitle(filename);
		}
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);
		suffix = suffix.toLowerCase();
		if (suffix.equals("jpg") || suffix.equals("png")
				|| suffix.equals("gif") || suffix.equals("pnm")) {
			try {
				ImageIO.write(img, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Error: filename must end in .jpg or .png");
		}
		
	}

	// /////////////////////////////////////////////////////////////////////////////////////////
	public void save(String name) {
		save(new File(name));
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////
	// Action Listener for save:
	class SaveAction implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			FileDialog chooser = new FileDialog(jFrame,
					"Use a .png,jpg,gif or .jpg extension", FileDialog.SAVE);
			chooser.setVisible(true);
			if (chooser.getFile() != null) {
				save(chooser.getDirectory() + File.separator
						+ chooser.getFile());
			}
			
		}
	}



	public static void main(String[] args) {
		Interface in1 = new Interface();

	}
}
