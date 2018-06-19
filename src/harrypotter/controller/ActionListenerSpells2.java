package harrypotter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.HarryPotterView;

public class ActionListenerSpells2 implements ActionListener {
	private Tournament Tournament;
	private HarryPotterView HarryPotterView;

	@Override
	public void actionPerformed(ActionEvent e) {
		((Wizard) Tournament.getChampions().get(1)).getSpells().add((Spell) ((JReferencingButton) e.getSource()).getValue());
		HarryPotterView.getPnlSpells2().remove((JReferencingButton)e.getSource());
		if(((Wizard) Tournament.getChampions().get(1)).getSpells().size()==3){
			HarryPotterView.remove(HarryPotterView.getSpells2Container());
			  HarryPotterView.setContentPane(HarryPotterView.getSpells3Container());
		
	}
		HarryPotterView.validate();
		HarryPotterView.revalidate();
		HarryPotterView.repaint();
	}
	
	public ActionListenerSpells2(Tournament Tournament, HarryPotterView HarryPotterView){
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		
	}

	
	

}
