package com.marto.simulator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



public class Tool {
	// FOR THE POP MENUS 
		private JPopupMenu menu;
		
		
	 // FOR THE MULTIMETER
		public JLabel mult;
	public 	JLabel red_pot;
		public JLabel black_pot;
		public JLabel red_probe;
		public JLabel black_probe;
		
		// DIALOGS FOR THE TOOLS
		public JDialog multimeter;
		
		// FOR THE ELECTRICAL SAFETY TESTER
	public	JLabel tester;

	// DISPLACEMENTS FOR DRAGGING MOVEMENTS
	private int x_displacement,y_displacement;

	// BOOLEAN FOR DRAGGING
	private boolean drag = false, red_dragging = false;
	Point prev_point = new Point();
		
		public Tool(){
			
			
			
			//the red port instantiated
			red_pot = new JLabel();
			red_pot.setIcon(new ImageIcon(getClass().getResource("/red port.png")));
			red_pot.setBounds(50, 50, 10, 10);
			red_pot.addMouseListener(new Mose());
			
			// the black port instantiated
			black_pot = new JLabel();
			black_pot.setIcon(new ImageIcon(getClass().getResource("/black port.png")));
			black_pot.setBounds(50, 70, 10, 10);
			black_pot.addMouseListener(new Mose());
			
			// the multimeter label instantiated
			mult = new JLabel();
			mult.setIcon(new ImageIcon(getClass().getResource("/multimeter.png")));
		mult.setBounds(100,100,70,100);
		mult.add(red_pot);
		mult.add(black_pot);
			mult.addMouseListener(new Mose());
			mult.addMouseMotionListener(new Mosing());
			
			
			// ADDING COMPONENTS TO THE TESTER
			tester= new JLabel();
			tester.setIcon(new ImageIcon(getClass().getResource("/tester.png")));
			tester.setBounds(300,300,70,100);
			tester.addMouseListener(new Mose());
			tester.addMouseMotionListener(new Mosing());
		}
		
		class Mose extends MouseAdapter{
			
			public void mouseClicked(MouseEvent e){
	      Object sc = e.getComponent();
	      
	      if (sc == mult){
	    	 
	    	  
	    	
	    	    if (e.getClickCount() == 2){
	    	    	
	    	    	
	    	    	
	    		  
	    		  // SETTING THE FONT
	    		  Font btn_font = new Font("Serif",Font.BOLD,5);
	    		 Font fielding = new Font("Arial",Font.BOLD,25);
	    		  
	    		  // INSTANTIATING A TEXT FIELD
	    		 JTextField field = new JTextField();
	    
	    	field.setBackground(Color.LIGHT_GRAY);
	    	field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
	    		  field.setSize(330, 70);
	    		  field.setFont(fielding);
	    		  field.setHorizontalAlignment(SwingConstants.TRAILING);
	    		  
	    		  
	    		  // SETTING FONT SIZE FOR THE NUMBERS AND LETTERS FOR BUTTONS
	    		 
	    		  
	    		  JToggleButton ac = new JToggleButton();
	    		  JToggleButton dc = new JToggleButton();
	    		  JToggleButton voltage = new JToggleButton("V");
	    		  JToggleButton amps = new JToggleButton("A");
	    		JToggleButton ohms = new JToggleButton();
	    		  JToggleButton continuity = new JToggleButton();
	    		  
	    		  
	    		  // TOGGLE BUTTONS FOR THE VOLTAGE SUPPLY
	    		  JToggleButton v_1 = new JToggleButton("200m");
	    		  JToggleButton v_2 = new JToggleButton("2");
	    		  JToggleButton v_3 = new JToggleButton("20");
	    		  JToggleButton v_4 = new JToggleButton("200");
	    		  JToggleButton v_5 = new JToggleButton("1000");
	    		  
	    		  //TOGGLE BUTTONS FOR THE CURRENT SUPPLY
	    		  JToggleButton a_1 = new JToggleButton("2m");
	    		  JToggleButton a_2 = new JToggleButton("20m");
	    		  JToggleButton a_3 = new JToggleButton("200m");
	    		  JToggleButton a_4 = new JToggleButton("20");
	    		  
	    		  // TOGGLE BUTTONS FOR THE OHMS SIDE'
	    		  JToggleButton o_1 = new JToggleButton("200");
	    		  JToggleButton o_2 = new JToggleButton("2k");
	    		  JToggleButton o_3 = new JToggleButton("20k");
	    		  JToggleButton o_4 = new JToggleButton("200k");
	    		  JToggleButton o_5 = new JToggleButton("2M");
	    		  JToggleButton o_6 = new JToggleButton("20M");
	    		  
	    		  
	    		  
	    		  
	    		  // THE BUTTON GROUPS
	    		  ButtonGroup currents = new ButtonGroup();
	    		  currents.add(ac);
	    		  currents.add(dc);
	    		  
	    		  ButtonGroup states = new ButtonGroup();
	    		  states.add(voltage);
	    		  states.add(amps);
	    		  states.add(ohms);
	    		  states.add(continuity);
	    		  
	    		  ButtonGroup voltage_states = new ButtonGroup();
	    		  voltage_states.add(v_1);
	    		  voltage_states.add(v_2);
	    		  voltage_states.add(v_3);
	    		  voltage_states.add(v_4);
	    		  voltage_states.add(v_5);
	    		  
	    		  ButtonGroup current_values = new ButtonGroup();
	    		  current_values.add(a_1);
	    		  current_values.add(a_2);
	    		  current_values.add(a_3);
	    		  current_values.add(a_4);
	    		  
	    		  ButtonGroup ohms_values = new ButtonGroup();
	    		  ohms_values.add(o_1);
	    		  ohms_values.add(o_2);
	    		  ohms_values.add(o_3);
	    		  ohms_values.add(o_4);
	    		  ohms_values.add(o_5);
	    		  ohms_values.add(o_6);
	    		  
	    		// adding the dialog for the multimeter for better view
	    		  multimeter = new JDialog();
	    		   multimeter.setTitle("Multimeter");
	    			multimeter.setLocationRelativeTo(mult);
	    			multimeter.setSize(100,100);
	    			multimeter.setBackground(Color.WHITE);
	    			multimeter.setLayout(new GridBagLayout());
	    			
	    		
	    		// ADDING ALL NECESSARY COMPONENTS AS SHOWN BELOW
	    			GridBagConstraints con = new GridBagConstraints();
	    		
	    			
	    			// ADDING THE FIELD AS SHOWN BELOW
	    			con.anchor = GridBagConstraints.NORTH;
	    			con.weightx = 0.2;
	    			con.weighty = 0.2;
	    			con.gridx = 0;
	    			con.gridy = 0;
	    			con.ipady = 20;
	    			con.ipadx = 300;
	    			multimeter.add(field, con);
	    			
	    			//ADDING THE AC BUTTON
	    			con.anchor = GridBagConstraints.LINE_START;
	    			con.gridx = 0;
	    			con.gridy = 1;
	    			con.ipadx = 30;
	    			con.ipady = 10;
	    			multimeter.add(ac, con);
	    			
	    			//ADDINT THE DC BUTTON
	    			con.anchor = GridBagConstraints.LINE_END;
	    			con.gridx = 0;
	    			con.gridy = 1;
	    			con.ipadx = 30;
	    			con.ipady = 10;
	    			multimeter.add(dc, con);
	    			
	    			// ADDING THE MODES
	    			// ADDING THE VOLTAGE BUTTON
	    			con.anchor = GridBagConstraints.LINE_START;
	    			con.gridx = 0;
	    			con.gridy = 2;
	    			con.ipadx = 5;
	    			con.ipady = 10;
	    			multimeter.add(voltage, con);
	    			
	    			// ADDING THE VOLTAGE VALUES BUTTONS
	    			GridBagConstraints cons = new GridBagConstraints();
	    		
	    			cons.insets = new Insets(0,-140,0,0);
	    			cons.weightx = 0.2;
	    			cons.gridx = 0;
	    			cons.gridy = 2;
	    			cons.ipadx = 10;
	    			cons.ipady = 10;
	    			multimeter.add(v_1, cons);
	    			
	    			cons.weightx = 0.9;
	    			cons.insets = new Insets(0,-360,0,0);
	    			cons.gridx = 1;
	    			cons.gridy = 2;
	    			cons.ipadx = 30;
	    			cons.ipady = 10;
	    			multimeter.add(v_2, cons);
	    			
	    			cons.insets = new Insets(0,-200,0,0);
	    			cons.weightx = 0.2;
	    			cons.gridx = 1;
	    			cons.gridy = 2;
	    			cons.ipadx = 30;
	    			cons.ipady = 10;
	    			multimeter.add(v_3, cons);
	    			
	    			cons.insets = new Insets(0,-20,0,0);
	    			cons.weightx = 0.2;
	    			cons.gridx = 1;
	    			cons.gridy = 2;
	    			cons.ipadx = 10;
	    			cons.ipady = 10;
	    			multimeter.add(v_5, cons);
	    			
	    			// ADDING THE CURRENT BUTTON
	    			con.anchor = GridBagConstraints.LINE_START;
	    			con.gridx = 0;
	    			con.gridy = 3;
	    			con.ipadx = 5;
	    			con.ipady = 1;
	    			multimeter.add(amps, con);
	    			
	    			// ADDING THE CURRRENT VALUES TO THE MULTIMETER
	    			GridBagConstraints ampes = new GridBagConstraints();
	    			
	    			ampes.gridx = 0;
	    			ampes.gridy = 3;
	    			ampes.insets  = new Insets(0,-160,0,0);
	    			ampes.ipady = 3;
	    			ampes.weightx = 0.2;
	    			multimeter.add(a_1, ampes);
	    			
	    			ampes.gridx = 1;
	    			ampes.gridy = 3;
	    			ampes.insets  = new Insets(0,-390,0,0);
	    			ampes.ipady = 3;
	    			ampes.ipadx = 2;
	    			ampes.weightx = 0.2;
	    			multimeter.add(a_3, ampes);
	    			
	    			ampes.gridx = 1;
	    			ampes.gridy = 3;
	    			ampes.insets  = new Insets(0,-260,0,0);
	    			ampes.ipady = 3;
	    			ampes.weightx = 0.2;
	    			multimeter.add(a_4, ampes);
	    			
	    			
	    			
	    			// ADDING THE OHMS BUTTON
	    			con.anchor = GridBagConstraints.LINE_START;
	    			con.gridx = 0;
	    			con.gridy = 4;
	    			con.ipadx = 3;
	    			con.ipady = 10;
	    			multimeter.add(ohms, con);
	    			
	    			// ADDING RESISTANCE VALUES BELOW
	    			GridBagConstraints ohmage = new GridBagConstraints();
	    			
	    			ohmage.gridx = 0;
	    			ohmage.gridy = 4;
	    			ohmage.insets = new Insets(0,-155,0,0);
	    			multimeter.add(o_1, ohmage);
	    			
	    			ohmage.gridx = 1;
	    			ohmage.gridy = 4;
	    			ohmage.insets = new Insets(0,-398,0,0);
	    			multimeter.add(o_4, ohmage);
	    			
	    			ohmage.gridx = 1;
	    			ohmage.gridy = 4;
	    			ohmage.insets = new Insets(0,-255,0,0);
	    			multimeter.add(o_6, ohmage);
	    			
	    			//ADDING THE CONTINUITY BUTTON
	    			con.anchor = GridBagConstraints.LINE_START;
	    			con.gridx = 0;
	    			con.gridy = 5;
	    			con.ipadx = 30;
	    			con.ipady = 10;
	    			multimeter.add(continuity, con);
	    			
	    		
	    			
	    		
	    			multimeter.revalidate();
	    			multimeter.repaint();
	    			multimeter.setVisible(true);
	    		
	    			
	    			
	    	  }
	    	  
	    	 
	      }
	      
	      else  if(sc == red_pot){
	    	  drag = true;
	    	 red_dragging = true;
	    	  
	    	  red_probe = new JLabel();
	    	  red_probe.setIcon(new ImageIcon(getClass().getResource("/red probe.png")));
	    	  red_probe.setBounds(10,10, 10, 66 );
	    	  
	    	  red_pot.add(red_probe);
	          mult.repaint();
	    	
	      }
	      else if(sc == black_pot){
	    	  red_probe = new JLabel();
	    	  red_probe.setIcon(new ImageIcon(getClass().getResource("/black probe.png")));
	    	  
	    	  mult.repaint();
	      }
			}
			
			public void mousePressed(MouseEvent e){
				Object sc = e.getComponent();
				drag = true;
				
				if(sc == mult){
					
					  // THE POP UP MENU ON RIGHT CLICK
			    	  if(SwingUtilities.isRightMouseButton(e)){
			    		  
			    		   menu = new JPopupMenu();
			    		  
			    		  JMenuItem properties = new JMenuItem("Properties");
			    		  
			    		  menu.add(properties);
			    		  
			    		  menu.show(mult, e.getX(), e.getY());
			    		  
			    	  }
					
					prev_point = mult.getLocationOnScreen();
					mult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					mult.revalidate();
				}
				
	if(sc == tester){
					
				prev_point = tester.getLocationOnScreen();
					tester.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					tester.revalidate();
				}
			}
			
			public void mouseReleased(MouseEvent e){
				drag = false;
				
				Object sc = e.getComponent();
				
				// CODE FOR THE MULTIMETER LABEL
				if(sc == mult){
					
					// FOR RIGHT MOUSE CLICK ON THE MULTIMETER, CODE BELOW
					if(SwingUtilities.isRightMouseButton(e)){
					menu.show(mult, e.getX(), e.getY());
					}
					
					// FOR DRAG WHEN RELEASED
					mult.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
				
				// CODE FOR THE TESTER
				if(sc == tester){
					tester.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}
		
		private class Mosing extends MouseMotionAdapter{
			
			public void mouseDragged(MouseEvent e){
				Object sc = e.getComponent();
				Point p = e.getLocationOnScreen();// new point of location of drag
				int mov_x = p.x - prev_point.x; // new point minus previous point of the component
				int mov_y = p.y - prev_point.y; // difference of the movement ofcourse
				
				if(drag){
					
					if(sc == mult){
				int oldx = mult.getX();
				int oldy = mult.getY();
				int newx = oldx + mov_x;
				int newy = oldy + mov_y;
				mult.setLocation(newx,newy); // new points of the component
				prev_point = e.getLocationOnScreen();
				
				mult.invalidate();
				mult.repaint();
					}
					
					if(sc == tester){
			int oldx= tester.getX();
			int oldy = tester.getY();
			int newx = oldx + mov_x;
			int newy = oldy+ mov_y;
			tester.setLocation(newx,newy);
			prev_point = e.getLocationOnScreen();
					
						tester.invalidate();
						tester.repaint();
							}
				}
				
				
			}
			
		}
		

}
