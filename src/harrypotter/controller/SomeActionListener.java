package harrypotter.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.HarryPotterView;


public class SomeActionListener implements ActionListener{
	private Tournament Tournament;
	//private HarryPotterView HarryPotterView;
	HarryPotterView HarryPotterView;
	


	public void actionPerformed(ActionEvent e) {
		//HarryPotterView = new HarryPotterView();
		//HarryPotterViewSpells = new HarryPotterViewSpells(Tournament,HarryPotterView);
		((Wizard) Tournament.getChampions().get(0)).getSpells().add((Spell) ((JReferencingButton) e.getSource()).getValue());
		HarryPotterView.getPnlSpells1().remove((JReferencingButton)e.getSource());
		//HarryPotterView.remove(HarryPotterView.getPnlSpells1().);
		if(((Wizard) Tournament.getChampions().get(0)).getSpells().size()==3){
			HarryPotterView.remove(HarryPotterView.getSpells1Container());
			  HarryPotterView.setContentPane(HarryPotterView.getSpells2Container());
			  //counter++;
			
		}
			HarryPotterView.validate();
			HarryPotterView.revalidate();
			HarryPotterView.repaint();
		}



		

	
	
	public SomeActionListener(Tournament Tournament, HarryPotterView HarryPotterView){
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		
	}


	
	
	
	

}
