package com.marto.documents;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Docs extends JFrame implements TreeSelectionListener {


static Docs docing;
	
	JLabel classone,classtwo;
	private Equipment_docs equip = new Equipment_docs();
	JPanel panel_1,panel_2;
	JSplitPane split;
	
	JTextArea area_1,area_2;
	JScrollPane scroll,scroll_2;
	JTree tree;
	JTextPane paning;
	DefaultTreeModel model;
	
	// TREE WORKING CODE AS SHOWN BELOW
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode subroot_1;
	DefaultMutableTreeNode subroot_2;
	DefaultMutableTreeNode subroot_3;
	DefaultMutableTreeNode ecg;
	DefaultMutableTreeNode intro;
	DefaultMutableTreeNode class_1;
	DefaultMutableTreeNode class_2;
	DefaultMutableTreeNode class_3;
	DefaultMutableTreeNode equipment_types;
	DefaultMutableTreeNode intro_2;
	DefaultMutableTreeNode earth_cont;
	DefaultMutableTreeNode Insulation_test;
	DefaultMutableTreeNode earth_leakage_test;
	DefaultMutableTreeNode touch_current;
	DefaultMutableTreeNode patient_leakage;
	DefaultMutableTreeNode patient_auxiliary;
	DefaultMutableTreeNode mains_test;
	
	public Docs(Docs docing){
		this.docing = docing;
		add_panel();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void add_panel(){
		// PANEL WITH THE TREE
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		// PANEL WITH THE TEXT AREA
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new BorderLayout());
		panel_2.setMaximumSize(new Dimension(getWidth()/2,getHeight()));
		
		// TREE WORKING CODE AS SHOWN BELOW
		 root = new DefaultMutableTreeNode("CONTENTS");
		 subroot_1 = new DefaultMutableTreeNode("CLASSES OF EQUIPMENT");
		subroot_2 = new DefaultMutableTreeNode("ELECTRICAL SAFETY TESTS");
		subroot_3  = new DefaultMutableTreeNode("EQUIPMENT INFORMATION");
		 intro = new DefaultMutableTreeNode("INTRODUCTION");
		 class_1 = new DefaultMutableTreeNode("CLASS I");
		 class_2 = new DefaultMutableTreeNode("CLASS II");
		 class_3 = new DefaultMutableTreeNode("CLASS III");
		 equipment_types = new DefaultMutableTreeNode("EQUIPMENT TYPES");
		 
		 // NODES FOR THE ELECTRICAL SAFETY TESTS
		 intro_2 = new DefaultMutableTreeNode("INTRODUCTION");
		 earth_cont = new DefaultMutableTreeNode("EARTH CONTINUITY TEST");
		  Insulation_test = new DefaultMutableTreeNode("INSULATION TEST");
			 earth_leakage_test = new DefaultMutableTreeNode("EARTH LEAKAGE CURRENT TEST");
		 touch_current =new DefaultMutableTreeNode("TOUCH CURRENT");
		 patient_leakage =new DefaultMutableTreeNode("PATIENT LEAKAGE CURRENT TEST");
		 patient_auxiliary = new DefaultMutableTreeNode("PATIENT AUXILIARY TEST");
		 mains_test = new DefaultMutableTreeNode("MAINS ON APPLIED PARTS TEST");
		 
		 /// NODES FOR THE EQUIPMENT INFORMATION
		 ecg = new DefaultMutableTreeNode("ECG");
		 
		 
		
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.addTreeSelectionListener(this);
		
		// adding nodes
		model.insertNodeInto(subroot_1, root, 0);
		model.insertNodeInto(subroot_2, root, 0);
		model.insertNodeInto(subroot_3, root, 0);
		
		// ADDING THE EQUIPMENT CLASSES NODE TO THEIR PARENT NODE
		subroot_1.add(intro);
		subroot_1.add(class_1);
		subroot_1.add(class_2);
		subroot_1.add(class_3);
		subroot_1.add(equipment_types);
		
		// ADDING THE ELECTRICAL SAFETY TESTS NODE TO ITS PARENT NODE
		root.add(subroot_2);
		subroot_2.add(intro_2);
		subroot_2.add(earth_cont);
		subroot_2.add(Insulation_test);
		subroot_2.add(earth_leakage_test);
		subroot_2.add(touch_current);
		subroot_2.add(patient_leakage);
		subroot_2.add(patient_auxiliary);
		subroot_2.add(mains_test);
		
		// ADDING THE EQUIPMENT INFORMATION
		root.add(subroot_3);
		subroot_3.add(ecg);
		
	
		//INSTANTIATING THE TEXT PANE BELOW
		Font font = new Font("Arial", Font.PLAIN, 15);
		paning = new JTextPane();
		paning.setContentType("text/html");
		paning.setEditable(false);
		paning.setFont(font);
		paning.setAlignmentX(300.0f);

		
		
		
		// TEXT AREA CODE AS SHOWN BELOW
		Font font1 = new Font("Arial", Font.PLAIN, 15);
		area_2 = new JTextArea();
		area_2.setEditable(false);
		area_2.setLineWrap(true);
		area_2.setWrapStyleWord(true);
		
		area_2.setFont(font1);
		
// ADDING THE JSCROLL PANE AS SHOWN BELOW		
scroll = new JScrollPane(paning,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scroll.setAutoscrolls(false);

		// ADDING THE SPLIT PANE AS SHOWN BELOW
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel_1,panel_2);
		split.setBackground(Color.WHITE);
		split.setResizeWeight(0.2f);
		split.setEnabled(false);
	
		

		
		
		panel_1.add(tree);
		panel_2.add(scroll);
		
		
		
	
		this.add(split);
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
tree = (JTree) e.getSource();
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		
		if(node == intro){
			paning.setText(equip.intro_1);
		}
		
		
		else if(node == class_1){
			
			paning.setText(equip.class_1);

		
		}
		
		else if(node == class_2){
			paning.setText(equip.class_2);
		}
		
		else if(node == class_3){
			paning.setText(equip.class_3);
		}
		
		else if(node == equipment_types){
			paning.setText(equip.equipment_types);
		}
		else if(node == intro_2){
			paning.setText(equip.safety_intro);
		}
		else if(node == earth_cont){
			paning.setText(equip.earth_continuity);
		}
		
		else if(node == Insulation_test){
			paning.setText(equip.insulation_test);
		}
		else if(node == earth_leakage_test){
			paning.setText(equip.earth_leakage);
		}
		else if (node == touch_current){
		paning.setText(equip.touch_current);
		}
		else if(node == patient_leakage){
			paning.setText(equip.patient_leakage);
		}
		else if( node ==patient_auxiliary){
		paning.setText(equip.patient_auxiliary);
		}
		else if(node ==  mains_test){
			paning.setText(equip.mains);
		}
		
		else if(node == ecg){
			paning.setText(equip.ecg);
		}
	}
		
	}


