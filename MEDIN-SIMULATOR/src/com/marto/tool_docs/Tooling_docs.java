package com.marto.tool_docs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class Tooling_docs extends JFrame implements TreeSelectionListener {
	Tools_documents docs = new Tools_documents();

	
	
	public Tooling_docs(){
		
		paning();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	static Tooling_docs doing;
	// INSTANTIATING THE COMPONENTS TO BE USED WITHIN THIS FRAME....
	private JTextPane pane;
	private JSplitPane split;
	private JScrollPane bars;
	JPanel pans_1,pans_2;
	private JTree tree;
	private DefaultTreeModel model;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode subroot_1;
	private DefaultMutableTreeNode subroot_2;
	private DefaultMutableTreeNode esa;
	private DefaultMutableTreeNode fluke;
	
	
	private void paning(){
		// MORE INFO ON THE TEXT PANE
		pane = new JTextPane();
		pane.setContentType("text/html");
		pane.setEditable(false);
		

		// WORKING ON THE TREE AS SHOWN BELOW
		
		root = new DefaultMutableTreeNode("CONTENTS");
		subroot_1 = new DefaultMutableTreeNode("ELECTRICAL SAFETY TESTER");
		subroot_2 = new DefaultMutableTreeNode("MULTIMETER");
		esa = new DefaultMutableTreeNode("ESA620");
		fluke = new DefaultMutableTreeNode("Multimeter");
		
	
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.addTreeSelectionListener(this);
		
		// adding the contents of the exact tree
		model.insertNodeInto(subroot_2, root, 0);
		model.insertNodeInto(subroot_1, root, 0);
		
	// adding information to the subroot 1
		subroot_1.add(esa);
		
	// addingig informationto subroot 2
		subroot_2.add(fluke);
		
		// 	WORKING ON THE SCROLL BAR AS SHOWN BELOW
		bars = new JScrollPane(pane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// ADDING TO THE PANELS
		pans_2 = new JPanel();
		pans_2.setLayout(new BorderLayout());
		pans_2.add(bars);
		
		pans_1 = new JPanel();
		pans_1.setBackground(Color.white);
		pans_1.add(tree);
		
		
		// MORE ON THE SPLIT PANEL
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pans_1, pans_2);
	split.setBackground(Color.white);
	split.setResizeWeight(0.2f);
	split.setEnabled(false);
	this.add(split);
		
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		tree = (JTree) e.getSource();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		
		if(node == esa){
			pane.setText(docs.esaa);
		}
		
		else if(node == fluke){
			pane.setText(docs.fluking);
			
		}
		
		
		
	}
	
	

}
