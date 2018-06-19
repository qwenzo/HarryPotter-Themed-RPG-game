package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.HarryPotterView;

public class ActionListenerSpells3 implements ActionListener{
	private Tournament Tournament;
	private HarryPotterView HarryPotterView;

	@Override
	public void actionPerformed(ActionEvent e) {
		((Wizard) Tournament.getChampions().get(2)).getSpells().add((Spell) ((JReferencingButton) e.getSource()).getValue());
		HarryPotterView.getPnlSpells3().remove((JReferencingButton)e.getSource());
		if(((Wizard) Tournament.getChampions().get(2)).getSpells().size()==3){
			HarryPotterView.remove(HarryPotterView.getSpells3Container());
			  HarryPotterView.setContentPane(HarryPotterView.getSpells4Container());
		
	}
		HarryPotterView.validate();
		HarryPotterView.revalidate();
		HarryPotterView.repaint();
		
	}
	
	public ActionListenerSpells3(Tournament Tournament, HarryPotterView HarryPotterView){
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		
	}

}
