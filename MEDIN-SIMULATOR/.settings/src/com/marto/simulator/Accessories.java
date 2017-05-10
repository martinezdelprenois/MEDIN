package com.marto.simulator;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Accessories {
	public JLabel white, black_1,black_2,black_3;
	private	boolean drag = false;
	private	int x_displacement, y_displacement;
		
		Accessories(){
			white = new JLabel();
			white.setIcon(new ImageIcon(getClass().getResource("/white plank.png")));
			white.setLayout(null);
			white.setBounds(100,100,100,100);
			white.addMouseListener(new Mose());
			white.addMouseMotionListener(new Mosing());
			white.setDoubleBuffered(true);
			
			black_1 = new JLabel();
			black_1.setIcon(new ImageIcon(getClass().getResource("/black.png")));
			black_1.setBounds(38, 20, 20, 20);
			
			black_2 = new JLabel();
			black_2.setIcon(new ImageIcon(getClass().getResource("/black.png")));
			black_2.setBounds(13,60,20,20);
			
			black_3 = new JLabel();
			black_3.setIcon(new ImageIcon(getClass().getResource("/black.png")));
			black_3.setBounds(70,60,20,20);
			
			white.add(black_1);
			white.add(black_2);
			white.add(black_3);
			
		}
		
		private class Mose extends MouseAdapter{
			
			public void mousePressed(MouseEvent e){
				drag = true;
				
				Object sc = e.getComponent();
				Point point = e.getPoint();
				
				if (sc == white){
					x_displacement = e.getPoint().x - white.getLocation().x;
					y_displacement = e.getPoint().y - white.getLocation().y;
					white.contains(point);
					white.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					white.revalidate();
				}
				
			}
			
			public void mouseReleased(MouseEvent e){
				drag = false;
				
				Object sc = e.getComponent();
				if(sc == white){
					white.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
			
		}
		
		private class Mosing extends MouseMotionAdapter{
			public void mouseDragged(MouseEvent e){
				
				
				Point point = e.getPoint();
				Object sc = e.getComponent();
				
				
				
				if(drag){
					
					if(sc == white){
					white.setLocation(point.x - x_displacement,point.y - y_displacement);
					white.invalidate();
					white.repaint();
					}
				}
			}
			
		}

}
