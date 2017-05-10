package com.marto.simulator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import com.marto.simulator.ECG;
import com.marto.simulator.Menu;





public class ECG implements ActionListener{
	
	Display disp = new Display();
	static Graphics g;
	 // THREAD TO TEST WHETHER ITS ON OR OFF
    static boolean ecg_on_state = false;//// AM NOT EVEN USING THIS.. HAVE TO USE IT....
	static boolean ecg_record_state = false;
	public Menu men;
	public Patientss pat;
	private JTextArea area = new Display();
	
	public JLabel ecg,
	black = new JLabel(new ImageIcon(getClass().getResource("/black lead.png"))),
	red = new JLabel(new ImageIcon(getClass().getResource("/red lead.png"))),
	yellow = new JLabel(new ImageIcon(getClass().getResource("/yellow lead.png"))),
	green = new JLabel(new ImageIcon(getClass().getResource("/green lead.png")));
	private boolean drag = false;
	private int x_displacement,y_displacement;
	
	
	
	public JPopupMenu menu;
	JMenu lead;
	JMenuItem red_lead, yellow_lead, black_lead,green_lead;
	
	
	// PUBLIC BUTTONS FOR THE ECG MACHINE....
	JToggleButton on,off,record;
	
	JDialog dialog;

	////////line of code below for the drag
	Point prev_point = new Point();
	
	
	ECG(Menu men){
		this.men = men;
		
		pat = new Patientss();
		
		// 	ECG EQUIPMENT
		ecg = new JLabel(new ImageIcon(getClass().getResource("/ecg.png")));
		ecg.setBounds(200,100,100,100);
		ecg.addMouseListener(new Mose());
		ecg.addMouseMotionListener(new Mosing());
		ecg.setOpaque(true);
		
		// INSTIATING THE LEAD LABELS
		
		black.setBounds(300,100,20,20);
		black.addMouseMotionListener(new Mosing());
		black.addMouseListener(new Mose());
		
		
		green.setBounds(300,50,20,20);
		green.addMouseMotionListener(new Mosing());
		green.addMouseListener(new Mose());
		
		
	
		red.setBounds(10,10,20,20);
		red.addMouseMotionListener(new Mosing());
		red.addMouseListener(new Mose());
		
		
		
		yellow.setBounds(100,200,20,20);
		yellow.addMouseMotionListener(new Mosing());
		yellow.addMouseListener(new Mose());
		
		//  INSTANTIATING THE LEADS
		lead = new JMenu("Leads");
		red_lead = new JMenuItem("Red lead");
		yellow_lead = new JMenuItem("Yellow lead");
		black_lead = new JMenuItem("Black lead");
		green_lead = new JMenuItem("Green lead");
		
		// ADDING THE ACTIONLISTENER
		red_lead.addActionListener(this);
		yellow_lead.addActionListener(this);
		black_lead.addActionListener(this);
		green_lead.addActionListener(this);
		
		 // INSTANTIATE BUTTONS FOR THE ECG DIALOG INTERFACE.....
		 on = new JToggleButton("ON");
		 off = new JToggleButton("OFF");
		 record = new JToggleButton("RECORD ECG");
		 record.setEnabled(false);
		
	}
	
	
	


	private class Mose extends MouseAdapter{
		 
		 
		
		 public void mouseClicked(MouseEvent e){
			 Object sc = e.getComponent();
			 
			 // IF ECG EQUIPMENT IS SELECTED
			 if(sc == ecg){
				 // ADDING A DIALOG AND ITS CONTENTS 
				 if(e.getClickCount() == 2){
					 
					 area.setBounds(10,10,200,100);
					 // INSTANTIATE THE DIALOG FOR THE ECG INTERFACE			
					 dialog = new JDialog();
					 dialog.setSize(500,400);
					 dialog.setTitle("ECG");
					 dialog.setLayout(new GridBagLayout());
					 dialog.setLocationRelativeTo(ecg);
					 dialog.setAlwaysOnTop(true);
					 
					 
					
					 
					 
					 // getting a button group for on and off
					 ButtonGroup of = new ButtonGroup();
					 of.add(on);
					 of.add(off);
					 of.add(record);
			
					 off.setSelected(true);
					 
					 
					 
					 //ADDING THE DISPLAY TO THE DIALOG AS SHOWN BELOW
					 GridBagConstraints con = new GridBagConstraints();
					 con.anchor = GridBagConstraints.NORTH;
					 con.gridx = 0;
					 con.gridy = 0;
					 con.weightx = 0.3;
					 con.weighty = 0.2;
					 con.ipadx = 440;
					 con.ipady = 120;
					 dialog.add(area,con);
					 
					 // ADDING THE BUTTONS TO THE ECG DIALOG
					 // adding the on button
					 GridBagConstraints con_1 = new GridBagConstraints();
					 con_1.anchor = GridBagConstraints.CENTER;
					 con_1.gridy = 1;
					 con_1.gridx = 0;
					 con_1.ipadx = 7;
					 con_1.ipady = 10;
					 con_1.insets = new Insets(0,0,190,0);
					 dialog.add(on, con_1);
					 
					 //adding the off button
					 con_1.gridx = 0;
					 con_1.ipadx = 5;
					 con_1.ipady = 10;
					 con_1.insets = new Insets(0,120,190,0);
					 dialog.add(off, con_1);
					 
					 //adding the record button
					 con_1.gridx = 0;
					 con_1.ipadx = 10;
					 con_1.ipady = 10;
					 con_1.insets = new Insets(0,-170,190,0);
					 dialog.add(record, con_1);
					 
					 
					 
					
					 
					 dialog.revalidate();
					 dialog.repaint();
					dialog.setVisible(true);
				 }
			 }
			
		 
	 }
		 
		 public void mousePressed(MouseEvent e){
			 Object sc = e.getComponent();
			drag = true;
			 
			 // CODE FOR THE ECG AS SHOWN BELOW
			 
			 if(sc == ecg){
				 
				 // POP UP MENU ON RIGHT CLICK FOR THE ECG
				 if(SwingUtilities.isRightMouseButton(e)){
					 menu = new JPopupMenu();
					
					 
					 JMenuItem properties = new JMenuItem("Properties");
					 
					 lead.add(red_lead);
					 lead.add(yellow_lead);
					 lead.add(black_lead);
					 lead.add(green_lead);
					 menu.add(lead);
					 menu.add(properties);
					 
				
					 
					 
				 }
				 
				
				 // WHEN DRAG THE ECG CODE IS BELOW
				prev_point = ecg.getLocationOnScreen();
				 ecg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				 ecg.revalidate();
			 }
			 
			 else if(sc == red){
				
				// WHEN DRAG THE ECG CODE IS BELOW
			prev_point = red.getLocationOnScreen();
				 red.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				 red.revalidate(); 
			 }
			 
			 else if(sc == yellow){
				//  CODE IS BELOW
				 
				 prev_point = yellow.getLocationOnScreen();
				 yellow.revalidate(); 
			 }
			 
			 if(sc == black){
				 drag = true;
				// WHEN DRAG THE ECG CODE IS BELOW
				prev_point = black.getLocationOnScreen();
				 black.revalidate();
			 }
			 if(sc == green){
				 drag = true;
					// WHEN DRAG THE ECG CODE IS BELOW
					prev_point = green.getLocationOnScreen();
					 green.revalidate();
				 }
			 
		 }
		 
		 public void mouseReleased(MouseEvent e){
			 Object sc = e.getComponent();
			 drag = false;
			 
			 if(sc == ecg){
				 
				 //POP UP MENU FOR ECG AS SHOWN BELOW ON RIGHT CLICK
				 if(SwingUtilities.isRightMouseButton(e)){
					 
					menu.show(ecg, e.getX(), e.getY());
					 
					 
				 }
				 
				 // WHEN DRAG IS RELEASED CODE BELOW
				 ecg.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			 }
		 }

	 }
private class Mosing extends MouseMotionAdapter{
	
	public void mouseDragged(MouseEvent e){
		
		Object sc = e.getComponent();
		Point p = e.getLocationOnScreen();
		int mov_x = p.x - prev_point.x;
		int mov_y = p.y - prev_point.y;
		
		if(drag){
			
			// FOR ECG EQUIPMENT CODE BELOW
			if(sc == ecg)
			{
				int oldx = ecg.getX();
				int oldy = ecg.getY();
				int newx = oldx + mov_x;
				int newy = oldy + mov_y;
				ecg.setLocation(newx,newy);
				prev_point = e.getLocationOnScreen();
				
				ecg.invalidate();
				ecg.repaint();
			}
			
			else if(sc == red){
				int oldx = red.getX();
				int oldy = red.getY();
				int newx = oldx + mov_x;
				int newy = oldy +mov_y;
				red.setLocation(newx,newy);
				prev_point = e.getLocationOnScreen();
				
				red.invalidate();
				red.repaint();
			}
			else if(sc == yellow){
				int oldx = yellow.getX();
				int oldy = yellow.getY();
				int newx = oldx + mov_x;
				int newy = oldy +mov_y;
				yellow.setLocation(newx,newy);
				prev_point = e.getLocationOnScreen();
				
				yellow.invalidate();
				yellow.repaint();
			}
			
			else if(sc == black){
				int oldx = black.getX();
				int oldy =black.getY();
				int newx = oldx + mov_x;
				int newy = oldy +mov_y;
				black.setLocation(newx,newy);
				prev_point = e.getLocationOnScreen();
				
				black.invalidate();
				black.repaint();
			}
			else if(sc == green){
				int oldx = green.getX();
				int oldy = green.getY();
				int newx = oldx + mov_x;
				int newy = oldy +mov_y;
				green.setLocation(newx,newy);
				prev_point = e.getLocationOnScreen();
				
				green.invalidate();
				green.repaint();
			}
		}
		
	}
	
}

// CODE THAT HANDLES THE DISPLAY OF THE ECG IS SHOWN RIGHT BELOW, HERE.....
  class Display extends JTextArea{
	   Thread ecg_on;
	
	 
	 public void start_ecg_on(){
		
			ecg_on = new Thread();
			ecg_on.start();
			
		}
	
	 
	 
	
	 
	 
	Display(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setBackground(Color.LIGHT_GRAY);
		setSize(440,120);
		setEditable(false);
		
		start_ecg_on();
		
	}
	
	//// THE GRID FOR THE ECG IS BELOW... THE CODE.. OFCOURSE
	public void grid(Graphics g, int space){
		Insets insets = new Insets(0,0,0,0);
		int firstx = insets.left;
	int	firsty = insets.top;
	int lastx = getWidth() - insets.right;
	int lasty = getHeight() - insets.bottom;
	
	/// drawin the vertical lines below
	int x = 0;
	while( x < lastx){
		g.setColor(Color.red);
		g.drawLine(x, firsty, x, lasty);
		x+= space;
	}
	
	/// drawing the horizontal lines below
	int y =0;
	while(y < lasty){
		g.setColor(Color.red);
		g.drawLine(firstx, y, lastx, y);
		y += space;
	}
	
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		RenderingHints hints =
		new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHints(hints);
		
		
		
	
	if(off.isSelected()){
		// NOTHINT
		record.setEnabled(false);
		on.setEnabled(true);
	}
	
	else if(on.isSelected()){
		// set record boolean to true
		record.setEnabled(true);
		ecg_on_state = true;
	
		leads_no_contact(g); ///   FLAT LINE
		grid(g,38);
	}
	
	else if(record.isSelected()){
		// ecg record state to true
		ecg_record_state = true;
		grid(g,38);
		// disable on button
		on.setEnabled(false);
		
		 if(green.getBounds().intersects(pat.ll.getBounds()) | green.getBounds().intersects
				 (pat.lan.getBounds()) && red.getBounds().intersects(pat.ra.getBounds())
				 | red.getBounds().intersects(pat.rw.getBounds()) && yellow.getBounds().intersects
				 (pat.la.getBounds()) | yellow.getBounds().intersects(pat.lw.getBounds())){

		normal_ecg_adult(g);
			
	
			}
		 
		 else if(green.getBounds().intersects(pat.ll.getBounds()) | green.getBounds().intersects
				 (pat.lan.getBounds()) && yellow.getBounds().intersects(pat.la.getBounds())
			 | yellow.getBounds().intersects(pat.lw.getBounds())){
			 reverse_lead_three(g);
		 }
		 
		 else if(green.getBounds().intersects(pat.ll.getBounds()) | green.getBounds().intersects
				 (pat.lan.getBounds()) && red.getBounds().intersects(pat.ra.getBounds()) |
				 red.getBounds().intersects(pat.rw.getBounds())){
			 reverse_lead_two(g);
			 
		 }
		 
	 else if( green.getBounds().intersects(men.panel.getBounds())){
			 interference_ecg_lead(g);
		 }
		 
	 else if(yellow.getBounds().intersects(men.panel.getBounds()) | red.getBounds().intersects
			 (men.panel.getBounds())){
		 interference_ecg_lead(g);
	 }
		 
	
		 
	 
		
	}
	
	
	repaint();
	}
	
	/// reverse lead II
	private void reverse_lead_two(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		g2.setColor(Color.black);
		
		p.moveTo(0.0f, 110.0f);
		p.lineTo(20.0f, 110.0f);
		p.lineTo(25.0f,130.0f);
		p.lineTo(27.0f, 115.0f);
		p.lineTo(33.0f, 115.0f);
		p.lineTo(36.0f, 120.f);
		p.lineTo(37.0f, 124.0f);
		p.lineTo(39.0f, 126.0f);
		p.lineTo(43.0f,126.0f);
		p.lineTo(45.0f, 112.0f);
		p.lineTo(85.0f, 112.0f);
		
		p.lineTo(90.0f, 130.0f);
		p.lineTo(92.0f,115.0f);
		p.lineTo(97.0f, 115.0f);
		p.lineTo(100.0f, 120.0f);
		p.lineTo(101.0f, 124.0f);
		p.lineTo(103.0f, 126.0f);
		p.lineTo(107.0f, 126.0f);
		p.lineTo(109.0f, 112.0f);
		p.lineTo(166.0f, 112.0f);
		
		p.lineTo(171.0f, 130.0f);
		p.lineTo(173.0f, 115.0f);
		p.lineTo(178.0f, 115.0f);
		p.lineTo(181.0f, 120.0f);
		p.lineTo(182.0f, 124.0f);
		p.lineTo(184.0f,126.0f);
		p.lineTo(189.0f, 126.0f);
		p.lineTo(191.0f, 112.0f);
		p.lineTo(254.0f, 112.0f);
		
		p.lineTo(259.0f, 130.0f);
		p.lineTo(262.0f,115.0f);
		p.lineTo(267.0f, 115.0f);
		p.lineTo(270.0f, 120.0f);
		p.lineTo(272.0f, 124.0f);
		p.lineTo(274.0f, 126.0f);
		p.lineTo(278.0f, 126.0f);
		p.lineTo(280.0f, 112.0f);
		
		p.lineTo(340.0f, 112.0f);
		p.lineTo(345.0f, 130.0f);
		p.lineTo(348.0f,115.0f);
		p.lineTo(352.0f, 115.0f);
		p.lineTo(355.0f, 120.0f);
		p.lineTo(357.0f, 124.0f);
		p.lineTo(359.0f, 126.0f);
		p.lineTo(363.0f, 126.0f);
		p.lineTo(365.0f, 112.0f);
		
		p.lineTo(425.0f, 112.0f);
		p.lineTo(430.0f, 130.0f);
		p.lineTo(433.0f, 115.0f);
		p.lineTo(437.0f, 115.0f);
		p.lineTo(440.0f, 120.0f);
	
		
		g2.draw(p);
	}
	
	//// REVERSE LEAD III
	private void reverse_lead_three(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		g2.setColor(Color.black);
		
		p.moveTo(0.0, 110.0f);
		p.lineTo(10.0f, 110.0f);
		p.lineTo(13.0f, 117.0f);
		p.lineTo(16.0f, 110.0f);
		p.lineTo(60.0f, 110.0f);
		p.lineTo(63.0f, 117.0f);
		p.lineTo(69.0f, 110.0f);
		p.lineTo(120.0f,110.0f);
		p.lineTo(123.0f, 117.0f);
		p.lineTo(126.0f, 110.0f);
		p.lineTo(220.0f, 110.0f);
		p.lineTo(223.0f, 117.0f);
		p.lineTo(226.0f, 110.0f);
		p.lineTo(340.0f, 110.0f);
		p.lineTo(343.0f	, 117.0f);
		p.lineTo(346.0f, 110.0f);
		p.lineTo(440.0f, 110.0f);
		g2.draw(p);
		
	
	
	}
	
	private void interference_ecg_lead(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		g2.setColor(Color.black);
		
		p.moveTo(0.0f, 110.0f);
		p.lineTo(10.0f, 105.0f);
		p.lineTo(20.0f, 110.0f);
		p.lineTo(30.0f, 105.0f);
		p.lineTo(40.0f, 110.0f);
		p.lineTo(45.0f, 105.0f);
		p.lineTo(50.0f, 110.0f);
		p.lineTo(55.0f, 105.0f);
		p.lineTo(60.0f, 110.0f);
		p.lineTo(65.0f, 105.0f);
		p.lineTo(70.0f, 110.0f);
		p.lineTo(75.0f,105.0f);
		p.lineTo(80.0f, 110.0f);
		p.lineTo(85.0f, 105.0f);
		p.lineTo(90.0f, 110.0f);
		p.lineTo(95.0f, 105.0f);
		p.lineTo(100.0f, 110.0f);
		p.lineTo(105.0f, 105.0f);
		p.lineTo(110.0f, 110.0f);
		p.lineTo(115.0f, 105.0f);
		p.lineTo(120.0f, 110.0f);
		p.lineTo(125.0f, 105.0f);
		p.lineTo(130.0f, 110.0f);
		p.lineTo(140.0f, 105.0f);
		p.lineTo(145.0f, 110.0f);
		p.lineTo(150.0f, 105.0f);
		p.lineTo(155.0f,110.0f);
		p.lineTo(165.0f, 108.0f);
		p.lineTo(170.0f, 104.0f);
		p.lineTo(175.0f, 110.0f);
		p.lineTo(180.0f, 105.0f);
		p.lineTo(185.0f, 110.0f);
		p.lineTo(190.0f, 105.0f);
		p.lineTo(195.0f, 110.0f);
		p.lineTo(205.0f, 107.0f);
		p.lineTo(210.0f, 105.0f);
		p.lineTo(220.0f, 108.0f);
		p.lineTo(225.0f, 105.0f);
		p.lineTo(230.0f, 110.0f);
		p.lineTo(235.0f,105.0f);
		p.lineTo(245.0f, 108.0f);
		p.lineTo(256.0f, 105.0f);
		p.lineTo(270.0f, 110.0f);
		p.lineTo(280.0f, 107.0f);
		p.lineTo(300.0f, 105.0f);
		p.lineTo(310.0f, 108.0f);
		p.lineTo(315.0f, 105.0f);
		p.lineTo(320.0f, 100.0f);
		p.lineTo(325.0f, 105.0f);
		p.lineTo(330.0f, 110.0f);
		p.lineTo(335.0f, 105.0f);
		p.lineTo(340.0f, 110.0f);
		p.lineTo(345.0f, 105.0f);
		p.lineTo(350.0f, 110.0f);
		p.lineTo(355.0f, 105.0f);
		p.lineTo(360.0f,110.0f);
		p.lineTo(365.0f, 105.0f);
		p.lineTo(370.0f, 110.0f);
		p.lineTo(375.0f, 105.0f);
		p.lineTo(380.0f, 110.0f);
		p.lineTo(400.0f, 105.0f);
		p.lineTo(405.0f, 110.0f);
		p.lineTo(410.0f, 105.0f);
		p.lineTo(415.0f, 110.0f);
		p.lineTo(420.0f,105.0f);
		p.lineTo(425.0f, 110.0f);
		p.lineTo(430.0f, 105.0f);
		p.lineTo(435.0f, 100.0f);
		p.lineTo(440.0f, 105.0f);
		
		g2.draw(p);
	}
	

	public void normal_ecg_adult(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
g2.setColor(Color.black);
				p.moveTo(0.0f, 110.0f);
				p.lineTo(10.0f, 110.0f); // 1
				p.lineTo(15.0f, 100.f); // 2
				p.lineTo(23.0f, 113.0f); //3 
				p.lineTo(27.0f,50.0f);		//4
				p.lineTo(32.0f, 122.0f);//5
				p.lineTo(37.0f, 116.0f);//6
				p.lineTo(49.0f, 110.0f);//7
				p.lineTo(55.0f, 100.0f);//8
				p.lineTo(61.0f, 110.0f);//9
				p.lineTo(70.0f, 111.f);//10
				p.lineTo(73.0f, 107.0f);//11
				
				p.lineTo(76.0f, 103.0f);
				p.lineTo(78.0f, 105.0f);
				p.lineTo(83.0f, 110.0f);
				p.lineTo(88.0f, 115.0f);
				p.lineTo(93.0f, 50.0f);
				p.lineTo(98.0f, 122.0f);
				p.lineTo(104.0f, 116.0f);
				p.lineTo(124.0f, 110.f);
				p.lineTo(130.0f, 100.0f);
				p.lineTo(136.0f, 110.0f);
				p.lineTo(147.0f, 113.0f);
				p.lineTo(148.0f, 111.0f);
				p.lineTo(153.0f, 103.0f);
				
				p.lineTo(164.0f, 117.0f);
				p.lineTo(170.0f, 50.0f);
				p.lineTo(175.0f, 124.0f);
				p.lineTo(181.0f, 116.0f);
				p.lineTo(201.0f, 110.0f);
				p.lineTo(207.0f, 100.0f);
				p.lineTo(213.0f, 110.0f);
				p.lineTo(219.0f, 113.0f);
				p.lineTo(220.0f, 111.0f);
				p.lineTo(229.0f, 103.0f);
				
				
				p.lineTo(240.0f, 117.0f);
				p.lineTo(254.0f, 50.0f);
				p.lineTo(254.0f,122.0f);
				p.lineTo(260.0f, 116.0f);
				p.lineTo(281.0f, 110.0f);
				p.lineTo(290.0f, 100.0f);
				p.lineTo(297.0f, 110.0f);
				p.lineTo(304.0f, 112.0f);
				p.lineTo(306.0f, 111.0f);
				p.lineTo(315.0f, 103.0f);
				
				p.lineTo(327.0f, 117.0f);
				p.lineTo(336.0f, 50.0f);
				p.lineTo(339.0f, 122.0f);
				p.lineTo(343.0f,116.0f);
				p.lineTo(368.0f,110.0f);
				p.lineTo(374.0f, 100.0f);
				p.lineTo(383.0f, 110.0f);
				p.lineTo(390.0f,112.0f);
				p.lineTo(392.0f, 111.0f);
				p.lineTo(403.0f, 103.0f);
				p.lineTo(406.0f, 118.0f);
				p.lineTo(423.0f,50.0f);
				p.lineTo(430.0f, 122.0f);
				p.lineTo(441.0f, 116.0f);
			
				
					g2.draw(p);		
							
	}
	
	// baseline graph shown below
	private void leads_no_contact(Graphics g){
		g.setColor(Color.black);
		g.drawLine(0, 100, 450, 100);
	}
	
	
	//// the artifacts graph as shown below
	private void artifacts_lead_ecg(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		
		GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		
		p.moveTo(0.0f, 60.0f);
		p.lineTo(10.0f, 100.0f); // 1
		p.lineTo(20.0f, 130.0f);
		p.lineTo(30.0f, 90.0f);
		p.lineTo(40.0f, 130.0f);
		p.lineTo(50.0f, 120.0f);
		p.lineTo(60.0f, 90.0f);
		p.lineTo(70.0f, 100.0f);
		p.lineTo(90.0f, 70.0f);
		p.lineTo(100.0f,90.0f);
		p.lineTo(120.0f, 120.0f);
		p.lineTo(150.0f, 30.0f);
		p.lineTo(170.0f,70.0f);
		p.lineTo(200.0f, 100.0f);
		p.lineTo(230.0f, 120.0f);
		p.lineTo(250.0f, 130.0f);
		p.lineTo(270.f, 100.0f);
		p.lineTo(300.0f, 89.0f);
		p.lineTo(320.0f, 100.0f);
		p.lineTo(340.0f, 80.0f);
		p.lineTo(350.0f	, 70.0f);
		p.lineTo(370.0f, 90.0f);
		p.lineTo(380.0f, 100.0f);
		p.lineTo(400.0f, 120.0f);
		p.lineTo(410.0f, 90.0f);
		p.lineTo(420.0f, 105.0f);
		p.lineTo(430.0f, 97.0f);
		p.lineTo(440.0f, 80.0f);
		
		
		g2.draw(p);
		
	}
	
	// weak signal artifacts graph shown below
	private void weak_signal(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);

		GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);

						p.moveTo(0.0f, 110.0f);
						p.lineTo(10.0f, 110.0f); 
						p.lineTo(15.0f, 100.f); 
						p.lineTo(23.0f, 113.0f);
						p.lineTo(27.0f,90.0f);		
						p.lineTo(32.0f, 120.0f);
						p.lineTo(37.0f, 116.0f);
						p.lineTo(49.0f, 110.0f);
						p.lineTo(55.0f, 100.0f);
						p.lineTo(61.0f, 110.0f);
						p.lineTo(70.0f, 111.f);
						p.lineTo(73.0f, 107.0f);
						p.lineTo(120.0f, 107.0f);
						p.lineTo(140.0f, 100.0f);
						p.lineTo(200, 110.0f);
						p.lineTo(300.0f, 110.0f);
						p.lineTo(350.0f, 100.0f);
						p.lineTo(390.0f,108.0f);
						p.lineTo(420.0f, 107.0f);
						p.lineTo(440.0f, 100.4f);
						
						g2.draw(p);
		
	}
}

@Override
public void actionPerformed(ActionEvent e) {
	Object sc = e.getSource();
	

	if(sc == red_lead){
		men.panel.add(red, new Integer(400),(0));
		men.panel.repaint();
	}
	
	else if(sc == yellow_lead){
		men.panel.add(yellow, new Integer(400),(0));
		men.panel.repaint();
	}
	
	else if(sc == black_lead){
		men.panel.add(black, new Integer(400),(0));
		men.panel.repaint();
	}
	
	else if(sc == green_lead){
		men.panel.add(green, new Integer(400),(0));
		men.panel.repaint();
	}
		
}




///// INNER CLASS TO HANDLE ADULT USER ERRORS/////
 class Adult_user_errors{
	Display disp = new Display();
	
 
}




class Patientss {
	JPopupMenu menu;
	JMenu placements;
public JPanel child,adult, elderly;
public JLabel lab_child,lab_adult,lab_elderly;
public JLabel RW,RL,RA,LW,LL,LA,LAN,RAN;
public JLabel rw,rl,ra,lw,ll,la,lan,ran;

JMenuItem red;
JMenuItem green;
JMenuItem yellow;
	
	private boolean drag = false;
	private int x_displacement, y_displacement;
	
	public Patientss(){
		labels_on_screen();
				 
				childn();
				adultn();
				elderlyn();
			}
			
			public void labels_on_screen(){
				rw = new JLabel(" RW");
				rw.setOpaque(true);
				rw.setBounds(620, 100, 20, 20);
				
				rl = new JLabel(" RL");
				rl.setOpaque( true);
				rl.setBounds(620, 130, 20, 20);
				
				ra = new JLabel(" RA");
				ra.setOpaque(true);
				ra.setBounds(650,100,20,20);
				
				lw = new JLabel(" LW");
				lw.setOpaque(true);
				lw.setBounds(650, 160, 20, 20);
				
				ll = new JLabel(" LL");
				ll.setOpaque(true);
				ll.setBounds(620,190,20,20);
				
				la = new JLabel(" LA");
				la.setOpaque(true);
				la.setBounds(620,160,20,20);
				
				lan = new JLabel(" LAN");
				lan.setOpaque(true);
				lan.setBounds(650,190,25,20);

				ran  = new JLabel(" RAN");
				ran.setOpaque(true);
				ran.setBounds(650,130,25,20);
			}


			// IMAGE FOR CHILD
			public void childn(){
				/// INSTANTIATING THE PANEL FOR THE CHILD
				child = new JPanel(null);
				child.addMouseMotionListener(new Mosing ());
				child.addMouseListener(new Mose());

				
				// INSTANTIATING THE LABEL FOR THE CHILD
				lab_child = new JLabel();
				lab_child.setIcon(new ImageIcon(getClass().getResource("/child.png")));
				lab_child.setBounds(300,200,200,299);
				
			}
			
			// IMAGE FOR ADULT
			public void adultn(){
				
				/// INSTANTIATING THE ADULT LABEL...
				adult = new JPanel(null);
				adult.addMouseMotionListener(new Mosing());
				adult.addMouseListener(new Mose());
				adult.setBounds(399, 28, 200, 402);
				adult.setLayout(null);
				adult.setBackground(Color.white);
				
				
				/// FIRST ATTACHING THE SENSITIVE AREAS ONTO THE PATIENTS...
				
				
			RL = new JLabel(" RL"); // RL
		RL.setBounds(55, 168, 20, 19);
				RL.addMouseListener(new Mose());
				RL.setBackground(Color.gray);
				RL.setOpaque(true);
				
				
				RA = new JLabel(" RA"); //RA
				RA.setBounds(47, 79, 25, 19);
				RA.setOpaque(true);
				RA.setBackground(Color.gray);
				RA.addMouseListener(new Mose());
				RA.addMouseMotionListener(new Mosing());
						
				LL = new JLabel(" LL"); // LL
				LL.setBounds(105, 168, 20, 19);
				LL.setOpaque(true);
				LL.setBackground(Color.gray);
				LL.addMouseListener(new Mose());
				
				LA = new JLabel(" LA"); //LA
				LA.setBounds(105, 79, 25, 19);
				LA.setOpaque(true);
				LA.setBackground(Color.gray);
				LA.addMouseListener(new Mose());
						
			RW = new JLabel(" RW");// RW
				RW.setBounds(20, 168, 20, 33);
				RW.setOpaque(true);
				RW.setBackground(Color.gray);
				RW.addMouseListener(new Mose());
						
				LW = new JLabel(" LW"); // LW
				LW.setBounds(144, 179, 20, 19);
				LW.setOpaque(true);
				LW.setBackground(Color.gray);
				LW.addMouseListener(new Mose());
						
				RAN = new JLabel(" RAN"); // RAN
				RAN.setOpaque(true);
				RAN.setBackground(Color.gray);
				RAN.setBounds(65, 345, 25, 20);
				RAN.addMouseListener(new Mose());
						
				LAN = new JLabel(" LAN"); // LAN
				LAN.setOpaque(true);
				LAN.setBackground(Color.gray);
				LAN.setBounds(96, 345, 25, 19);
				LAN.addMouseListener(new Mose());
				
				
				
				
				
			
				
				
				lab_adult = new JLabel( new ImageIcon(getClass().getResource("/adult pic.png")));
				lab_adult.setBounds(1,1,182,400);
				lab_adult.addMouseMotionListener(new Mosing());
				lab_adult.addMouseListener(new Mose());
				lab_adult.setOpaque(true);
				lab_adult.setVisible(true);
				
				
				
				adult.add(RAN);
				adult.add(LAN);
				adult.add(LL );
				adult.add(LW);
				adult.add(RA);
				adult.add(RL);
				adult.add(RW);
				adult.add(LA);
				adult.add(lab_adult);

			
				
			}
			
			// IMAGE FOR ELDERLY
			public void elderlyn(){
				elderly = new JPanel();
				elderly.addMouseMotionListener(new Mosing());
				elderly.addMouseListener(new Mose());
				
				lab_elderly = new JLabel();
				lab_elderly.setIcon(new ImageIcon(getClass().getResource("/elderly.png")));
				lab_elderly.setBounds(450,30,200,400);
				
			}

			
			// CLASS TO HANDLE MOUSE EVENTS
			private class Mose extends MouseAdapter{
				@Override
				public void mousePressed(MouseEvent e){
					Object sc = e.getComponent();
					drag = true;
					if(sc == adult){
						// HANDLING THE DRAG EVENT
						
						
						x_displacement = e.getPoint().x - adult.getLocation().x;
						y_displacement = e.getPoint().y - adult.getLocation().y;
						adult.revalidate();
					}
					
					else if(sc == RA){
						x_displacement = e.getPoint().x - RA.getLocation().x;
						y_displacement = e.getPoint().y - RA.getLocation().y;
						RA.revalidate();
					}
					
					else if(sc == lab_adult){
						x_displacement = e.getPoint().x - lab_adult.getLocation().x;
						y_displacement = e.getPoint().y - lab_adult.getLocation().y;
						lab_adult.revalidate();
					}
					
					
				}
				
				@Override
				public void mouseReleased(MouseEvent e){
					Object sc = e.getComponent();
					
					if(sc == adult){
						drag = false;
					}
					
					
				}
			}
			
			// CLASS TO HANDLE MOUSE MOTION EVENTS
			private class Mosing extends MouseMotionAdapter{
				public void mouseDragged(MouseEvent e){
					Object sc = e.getComponent();
					Point p = e.getPoint();
					
					if(sc == adult){
						adult.setLocation(p.x - x_displacement, p.y - y_displacement);
						adult.invalidate();
						adult.repaint();
						
					}
					
					else if(sc == RA){
						RA.setLocation(p.x - x_displacement, p.y - y_displacement);
						RA.invalidate();
						RA.repaint();
					}
					
					else if(sc == lab_adult){
						lab_adult.setLocation(p.x - x_displacement, p.y - y_displacement);
						lab_adult.invalidate();
						lab_adult.repaint();
					}
				}
			}

			
	
	
}


		
}	


