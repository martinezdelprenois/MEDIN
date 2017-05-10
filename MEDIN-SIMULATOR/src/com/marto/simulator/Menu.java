package com.marto.simulator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;

import com.marto.documents.Docs;
import com.marto.simulator.Accessories;
import com.marto.simulator.ECG;
import com.marto.simulator.ECG.Patientss;
import com.marto.tool_docs.Tooling_docs;
import com.marto.simulator.Tool;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Menu extends JFrame implements  ActionListener,ChangeListener{

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        		| UnsupportedLookAndFeelException ex) {
        }
		SwingUtilities.invokeLater(new Runnable(){
				public void run(){
		new Menu(men);	
		}
		}
				);
		

	}
	
	static Menu men;

	///  EXTERNAL CLASS OBJECTS BELOW FROM SAME PACKAGE
	ECG eq = new ECG(this);
	Tool tool = new Tool();
	Accessories access = new Accessories();
	Patientss pat = eq.new Patientss();
	
	
	/// CLASS OBJECTS FROM EXTERNAL PACKAGES
	Docs doc;
	Tooling_docs tooling;
		
	public JPopupMenu right_menu;
		public JTabbedPane tab;
		JFrame frm;
		JComponent com;
		 JLayeredPane panel;
		 JPanel panel_tab;
		 JMenuBar menu;
			int count = 1;
			
			
			//// BUTTONS INVOLVED
			JButton btn;
			
			JMenu file;
			JMenu edit;
			JMenu view;
			JMenu Accessories;
			JMenu tools;
			JMenu sim;
			JMenu help;
			
			// making menu items for the view menu
		     JMenuItem snap;
			 JMenuItem zoomin;
			 JMenuItem zoomout;
			 JMenuItem full_screen;
			 JMenuItem two;
			 JMenuItem three;
						
			// making menu items for accessories
			JMenuItem socket;
			
			// MENU ITEMS FOR THE SIMULATION MENU
			JMenuItem user;
			JMenuItem system;
			JMenuItem electrical;
			JMenuItem randomise;
			
			// making menu items for edit menu
						JMenuItem rename;
						JMenuItem undo;
						JMenuItem redo;
						JMenuItem cut;
						JMenuItem copy;
						JMenuItem paste;
						JMenuItem delete;
						
			// making menu items for tools menu
			JMenuItem mult;
			JMenuItem testing;
			
			// ITEMS FOR THE FILE MENU
			JMenuItem panels;
			JMenuItem exit;
			
			//making menu items for the placements
			JMenu patient;
			
	
			
			/// menu items for help menu
			JMenuItem about_equip;
			JMenuItem about_dev;
			JMenuItem about_tools;
			JMenuItem short_cuts;
			JMenuItem about;
			JMenuItem helping;
			
			
			// MAKING MENU ITEMS FOR EQUIPMENT
	JMenuItem ecg;
		 
		
		
		
		public Menu(Menu men){
			this.men = men;
			
			setExtendedState(JFrame.MAXIMIZED_BOTH);
				setResizable(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				simulator_menu();
				tools_menu();
				tabbed();
				right_men();
				pack();
			 setVisible(true);
				
			
			}
		
		/// CODE BELOW FOR THE JPOP UP MENU AS IT SHOULD BE IN ALL SOFTWARE GUIS
				public void right_men(){
					right_menu = new JPopupMenu();
					
					JMenuItem rename = new JMenuItem("Rename Pane");
					JMenuItem cut = new JMenuItem("Cut");
					JMenuItem undo = new JMenuItem("Undo");
					JMenuItem delete = new JMenuItem("Delete");
					JMenuItem place = new JMenuItem("Place");
					JMenuItem properties = new JMenuItem("Properties");
					JMenuItem select = new JMenuItem("Select");
					
					right_menu.add(select);
					right_menu.addSeparator();
					right_menu.add(rename);
					right_menu.addSeparator();
					right_menu.add(place);
					right_menu.addSeparator();
					right_menu.add(cut);
					right_menu.add(undo);
					right_menu.addSeparator();
					right_menu.add(delete);
					right_menu.addSeparator();
					right_menu.add(properties);
				}
				
				public void tabbed(){
				/// WORKING ON THE JTABBEDPANE CODE IS BELOW....
			panel = new JLayeredPane();
				panel.addMouseListener(new Mose());
				panel.setLayout(null);
				panel.setOpaque(true);
				panel.setBackground(Color.getHSBColor(250, 220, 210));
				panel.setDoubleBuffered(true);
				panel.addMouseMotionListener(new Mosing());

				       
				      // CHANGING APPEARANCE OF TABBED PANE USING UI MANAGER 
				       UIManager.put("TabbedPane.selected",ColorUIResource.WHITE);
				    UIManager.put("TabbedPane.darkShadow", ColorUIResource.WHITE);
				   UIManager.put("TabbedPane.light", ColorUIResource.getHSBColor(100, 100, 100));
			 UIManager.put("TabbedPane.highlight", ColorUIResource.getHSBColor(100, 100, 100));
				   UIManager.put("TabbedPane.focus", ColorUIResource.getHSBColor(100, 100, 100));
 UIManager.put("TabbedPane.unselectedBackground", ColorUIResource.getHSBColor(100, 200, 100));
	 UIManager.put("TabbedPane.selectHighlight", ColorUIResource.getHSBColor(100, 100, 100));
		 UIManager.put("TabbedPane.tabAreaBackground", ColorUIResource.getHSBColor(100, 100, 100));
	 UIManager.put("TabbedPane.borderHightlightColor", ColorUIResource.getHSBColor(100, 100, 100));
				       UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
				      
				       // JTABBED PANE CODE ADDED RIGHT BELOW..........
				tab = new JTabbedPane();
					tab.addTab("SIM 1" , panel);
				tab.setTabPlacement(JTabbedPane.BOTTOM);
				tab.getSelectedComponent();
				tab.addChangeListener(this);
					this.add(tab);
					
				}
				
				// TOOLBAR CODE IS JUST RIGHT BELOW AS YOU SEE IT.....
				public void tools_menu(){
					// ADDING THE JTOOLBAR AS SHOWN BELOW...........
					JToolBar toolbar = new JToolBar();
					toolbar.setFloatable(false);
					toolbar.setRollover(true);
					
					// ADDINDG BUTTONS CODE AS SHOWN BELOW........
					 btn = new JButton("Equipment_Library");
					btn.addActionListener(this);
					toolbar.add(btn);
					this.add(toolbar,BorderLayout.NORTH);
					
				}
				
				public void simulator_menu(){
					
				
					 
					 
					// creating the menu bar as shown below
					menu = new JMenuBar();
					this.setJMenuBar(menu);
					
					
					// adding menus as shown below
					 file = new JMenu("File");
					 edit = new JMenu("Edit");
					 view = new JMenu("View");
					 Accessories = new JMenu("Accessories");
				 tools = new JMenu("Tools");
					 sim = new JMenu("Simulate");
					 help = new JMenu("Help");
					
					
					// adding the menus to the menu bar
					menu.add(file);
					menu.add(edit);
					menu.add(view);
					menu.add(Accessories);
					menu.add(tools);
					menu.add(sim);
					menu.add(help);
					
					// ADDING ITEMS TO ACCESSORIES
					socket = new JMenuItem("Socket outlet");
		             Accessories.add(socket);
		             socket.addActionListener(this);
		             
		             
		             // ADDING ITEMS TO THE SIMULATION MENU(ALL ABOUT SIMULATION)
		             user = new JMenuItem("User Errors");
		             system = new JMenuItem("System Errors");
		             electrical = new JMenuItem("Electrical Errors");
		             randomise = new JMenuItem("Randomise");
		             
		             sim.add(user);
		             sim.add(system);
		             sim.add(electrical);
		             sim.add(randomise);
		             
		             
					
					// making menu items for the view menu
					 snap = new JMenuItem("Take snap shot");
					 zoomin = new JMenuItem("Zoom in");
				 zoomout = new JMenuItem("Zoom out");
					 full_screen = new JMenuItem("Full Screen");
					 two = new JMenuItem("2D mode");
					three = new JMenuItem("3D mode");
					
					
					//ADDING ACTION LISTENING TO THE JMENU ITEMS FOR VIEW MENU
					zoomin.addActionListener(this);
					
					// making menu items for edit menu
					 rename = new JMenuItem("Rename Pane");
					undo = new JMenuItem("Undo");
					 redo = new JMenuItem("Redo");
					 cut = new JMenuItem("Cut");
					 copy = new JMenuItem("Copy");
					 paste = new JMenuItem("paste");
					 delete = new JMenuItem("delete");
					
					edit.add(rename);
					edit.addSeparator();
					edit.add(undo);
					edit.add(redo);
					edit.addSeparator();
					edit.add(cut);
					edit.add(copy);
					edit.add(paste);
					edit.addSeparator();
					edit.add(delete);
					
					
					 // making menu items for tools menu
					 mult = new JMenuItem("Multimeter");
					 mult.addActionListener(this);
					 
					 testing = new JMenuItem("Electrical safety tester");
					 testing.addActionListener(this);
					
		// making menu items for the file menu
				 panels = new JMenuItem("New Pane");
					panels.setActionCommand("add a new pane");
					panels.addActionListener(this);
					 exit = new JMenuItem("Exit");
					exit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ev){
							System.exit(0);
						}
						
					});
			
					
					
					// making menu items for equipment//////////////////////////////////////////////////
				 ecg = new JMenuItem("ECG");
					ecg.addActionListener(this);
						
							
							
						
						
				
					//making menu items for the placements
							 patient = new JMenu("Patient");
							
			      
							
							
				
					// adding items to the view menu
							view.add(snap);
							view.addSeparator();
							view.add(two);
							view.add(three);
							view.addSeparator();
					view.add(zoomin);
					view.add(zoomout);
					view.addSeparator();
					view.add(full_screen);
					
					
				   // adding items to the tools menu
					tools.add(mult);
					tools.add(testing);
					
				   // adding items to the file menu
					file.add(panels);
					file.addSeparator();
					file.add(exit);
					
					// working on the help menu
				 about = new JMenuItem("About Medin-Simulator");
				 about_dev  = new JMenuItem("About Developers");
				 helping = new JMenuItem("Help Information");
				 short_cuts = new JMenuItem("Keyboard Short-Cuts");
					
					 about_equip = new JMenuItem("About_Equipment");
					about_equip.addActionListener(this);
					
					 about_tools = new JMenuItem("About_Tools");
					about_tools.addActionListener(this);
					
					
					help.add(about_equip);
					help.add(about_tools);
					help.addSeparator();
					help.add(helping);
					help.add(short_cuts);
					help.add(about);
					help.add(about_dev);
				}
				
				
		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object sc = e.getSource();
		
		if (sc == mult){
			panel.add(tool.mult,new Integer(400));
			repaint();
		}
		
		else if(sc == about_equip){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					doc = new Docs(doc);
				}
				
			});
		}
		
		else if(sc == about_tools){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					tooling = new Tooling_docs();
				}
				
			});
		}
		
		
		else if(sc == panels){
		//// ADDING ACTION LISTENING WHEN ONE CLICKS ADD NEW PANE.....
			  // CHANGING APPEARANCE OF TABBED PANE USING UI MANAGER 
		       UIManager.put("TabbedPane.selected",ColorUIResource.WHITE);
		    UIManager.put("TabbedPane.darkShadow", ColorUIResource.WHITE);
		   UIManager.put("TabbedPane.light", ColorUIResource.getHSBColor(100, 100, 100));
	 UIManager.put("TabbedPane.highlight", ColorUIResource.getHSBColor(100, 100, 100));
		   UIManager.put("TabbedPane.focus", ColorUIResource.getHSBColor(100, 100, 100));
UIManager.put("TabbedPane.unselectedBackground", ColorUIResource.getHSBColor(100, 200, 100));
UIManager.put("TabbedPane.selectHighlight", ColorUIResource.getHSBColor(100, 100, 100));
UIManager.put("TabbedPane.tabAreaBackground", ColorUIResource.getHSBColor(100, 100, 100));
UIManager.put("TabbedPane.borderHightlightColor", ColorUIResource.getHSBColor(100, 100, 100));
		       UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
		       
		      int tabcount = count + tab.getTabCount();
		 tab.addTab("SIM " +tabcount,  panel_tab);
			
		}
		
		else if(sc == btn ){
			panel.add(new Internal(),new Integer(200));
			panel.repaint();
			
		}
		
		else if(sc == testing){
			panel.add(tool.tester, new Integer(400));
			panel.repaint();
		}
		
		else if(sc == socket){
			panel.add(access.white);
			panel.repaint();
			
		}
		
	
		
	}

	// CLASS TO HANDLE RIGHT CLICKS ON THE JPANEL
			class Mose extends MouseAdapter{
				public void mousePressed(MouseEvent e){
					
					Object sc = e.getSource();
					
					if(sc == panel){
						if(SwingUtilities.isRightMouseButton(e)){
							right_menu.show(panel, e.getX(), e.getY());
						}
						
					}
					
					if(sc == eq.ecg){
						
						if(SwingUtilities.isRightMouseButton(e)){
							eq.menu.show(ecg, e.getX(), e.getY());
						}
					}
				}
				
				public void mouseReleased(MouseEvent e){
					if(SwingUtilities.isRightMouseButton(e)){
						right_menu.show(panel, e.getX(), e.getY());
					}
				}
				
			}
		

			private class Mosing extends MouseMotionAdapter{
				
				public void mouseDragged(MouseEvent e){
					
					
					
				}
				
				
			}
			
			/////////// CLASS FOR THE INTERNAL FRAME FOR EQUIPMENT LIBRARY
	class Internal extends JInternalFrame implements ActionListener,ListSelectionListener{
				
				JLabel category,equipment,patients;
				JComboBox equip_cat,pats;
				JList ward_equip;
				JTextField search;
				
				String[] cats = {"WARD EQUIPMENT"};
				String[] paties = {"ADULT", "ELDERLY", "NEO-NATE"};
				String[] ward_equipment = {"ECG"};
				
				DefaultListSelectionModel m = new DefaultListSelectionModel( );
					
				Internal(){
					
					setBackground(Color.white);
					setLayout(null);
					setClosable(true);
					setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
					setTitle("EQUIPMENT LIBRARY");
			
					setMaximizable(true);
					setResizable(true);
					
					
				
					///  three lines of code below to handle the size and dimension of the shizzy
					Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
					int x = screen.width - 280;
					int y = screen.height - 130;
					
					setBounds(30,0,x,y);
					elements();
					setVisible(true);
				}
				
				public void elements(){
					///////// LABELS BEING HANDLED HERE....
					category = new JLabel("EQUIPMENT CATEGORY");
					category.setBounds(10, 10, 123, 15);
					this.add(category);
					
					patients = new JLabel("PATIENT");
					patients.setBounds(280, 10, 50, 20);
					this.add(patients);
					
					equipment = new JLabel("EQUIPMENT");
					equipment.setBounds(10,120,60,15);
					equipment.setOpaque(true);
					this.add(equipment);
					
					/////// COMBO BOXES BEING HANDLED BELOW
					equip_cat = new JComboBox(cats);
					equip_cat.setBounds(10, 26, 130, 25);
					equip_cat.setOpaque(true);
					this.add(equip_cat);
					
					pats = new JComboBox(paties);
					pats.setBounds(280,26,130,25);
					pats.setOpaque(true);
					pats.addActionListener(this);
					this.add(pats);
					
					
					//// CODE FOR JTEXT FIELD IS RIGHT BELOW
					search = new JTextField();
					search.setBounds(10, 140, 100, 20);
					search.setEditable(true);
					this.add(search);
					
					///////// CODE BELOW HANDLES THE LIST
				ward_equip = new JList(ward_equipment);
				ward_equip.addListSelectionListener(this);
				m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				m.setLeadAnchorNotificationEnabled(false);
			ward_equip.setSelectionModel(m);
			
				JScrollPane pane = new JScrollPane(ward_equip,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pane.setBounds(10, 180, 130, 26);
				
				this.add(pane);
					
				}
				@Override
				public void valueChanged(ListSelectionEvent e) {
					
					ward_equip = (JList) e.getSource();
					
					String sel = (String) ward_equip.getSelectedValue();
					
					if(sel == ward_equipment[0]){
						panel.add(eq.ecg, new Integer(400), (0));
						panel.repaint();
					}
				
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					Object sc = e.getSource();
					pats = (JComboBox) sc;
					String select = (String) pats.getSelectedItem();
			
					
					if(select.equals(paties[0])){
						panel.add(pat.adult,new Integer(400));
						panel.add(pat.rw, new Integer(400));
						panel.add(pat.rl, new Integer(400));
						panel.add(pat.ra, new Integer(400));
						panel.add(pat.lw, new Integer(400));
						panel.add(pat.ran, new Integer(400));
						panel.add(pat.la, new Integer(400));
						panel.add(pat.ll, new Integer(400));
						panel.add(pat.lan, new Integer(400));
						panel.repaint();
					}
					
				
					
				}

				

			

				
				}

	//// ANY CHANGE LISTENING EVENTS...
	@Override
	public void stateChanged(ChangeEvent e) {
		tab = (JTabbedPane)e.getSource();
		int sel = tab.getSelectedIndex();
		
	}
			}

