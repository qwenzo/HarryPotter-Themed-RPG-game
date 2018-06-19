package harrypotter.model.tournament;

import harrypotter.controller.HarryPotterGUI;
import harrypotter.controller.InvListener;
import harrypotter.controller.JReferencingButton;
import harrypotter.controller.MovesAction;
import harrypotter.controller.MovesActionTask2;
import harrypotter.controller.useSpellsListener2;
import harrypotter.controller.useTraitListener;
import harrypotter.view.*;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.WallCell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Tournament implements TaskListener {

	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private FirstTask firstTask;
	private SecondTask SecondTask;
	private ThirdTask thirdTask;
	private TournamentListener listener;
	private HarryPotterView HarryPotterView;

	public Tournament(HarryPotterView HarryPotterView) throws IOException {
		this.HarryPotterView=HarryPotterView;

		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");

	}
	
	public Tournament() throws IOException {

		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");

	}

	public HarryPotterView getHarryPotterView() {
		return HarryPotterView;
	}

	public void setHarryPotterView(HarryPotterView harryPotterView) {
		HarryPotterView = harryPotterView;
	}

	public ArrayList<Champion> getChampions() {
		return champions;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public SecondTask getSecondTask() {
		return SecondTask;
	}

	public ThirdTask getThirdTask() {
		return thirdTask;
	}

	public void addChampion(Champion c) {
		champions.add(c);
	}

	private void loadSpells(String filePath) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {

			String[] content = line.split(",");
			switch (content[0]) {

			case "DMG":
				spells.add(new DamagingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "HEL":
				spells.add(new HealingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "REL":
				spells.add(new RelocatingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			}

			line = br.readLine();

		}

		br.close();

	}

	public void beginTournament() throws IOException {

		firstTask = new FirstTask(champions);
		firstTask.setListener(this);

	}

	public void onFinishingFirstTask(ArrayList<Champion> winners)
			throws IOException {

		if (!winners.isEmpty()) {
			SecondTask = new SecondTask(winners);
			SecondTask.setListener(this);
			//generatemap();
			HarryPotterView.validate();
			HarryPotterView.revalidate();
			HarryPotterView.repaint();
		}

	}
	
/*public void generatemap(){
	HarryPotterView.removeKeyListener(HarryPotterView.getKeyListener());
	HarryPotterView.getTest().setVisible(false);
	HarryPotterView.remove(HarryPotterView.getTest());
	//HarryPotterView.removeAll();
	//HarryPotterView.getButtons().removeAll();
	 //HarryPotterView.getFirstTaskView().removeAll();
	// HarryPotterView.getStats().removeAll();
	// HarryPotterView.getSpellstest().removeAll();
	HarryPotterView.getSecondTaskWholeView().setVisible(true);
	HarryPotterView.validate();
	HarryPotterView.revalidate();
	HarryPotterView.repaint();
			
			JButton Trait = new JButton();
			if(SecondTask.isTraitActivated() && SecondTask.getCurrentChamp() instanceof GryffindorWizard){
				ImageIcon image = new ImageIcon("emptycell - Copy.png");
				((JButton) Trait).setIcon(image);
			}
			if(SecondTask.isTraitActivated() && SecondTask.getCurrentChamp() instanceof RavenclawWizard){
				ImageIcon image = new ImageIcon("emptycell - Copy.png");
				((JButton) Trait).setIcon(image);
			}
			if(SecondTask.isTraitActivated() && SecondTask.getCurrentChamp() instanceof SlytherinWizard){
				ImageIcon image = new ImageIcon("emptycell - Copy.png");
				((JButton) Trait).setIcon(image);
			}
			if(SecondTask.isTraitActivated() && SecondTask.getCurrentChamp() instanceof HufflepuffWizard){
				ImageIcon image = new ImageIcon("emptycell - Copy.png");
				((JButton) Trait).setIcon(image);
			}
			JLabel turn = new JLabel("its "+((Wizard) SecondTask.getCurrentChamp()).getName()+ " Turn " +"with allowed moves "+SecondTask.getAllowedMoves() ,SwingConstants.RIGHT );
			HarryPotterView.getButtons().add(turn,BorderLayout.WEST);
			JProgressBar health = new JProgressBar(((Wizard) SecondTask.getCurrentChamp()).getHp(),((Wizard) SecondTask.getCurrentChamp()).getDefaultHp());
			JProgressBar ip = new JProgressBar(((Wizard) SecondTask.getCurrentChamp()).getIp(),((Wizard) SecondTask.getCurrentChamp()).getDefaultIp());
			ip.setBackground(Color.BLUE);
			ip.setStringPainted(true);
			HarryPotterView.getButtons().add(ip);
			UIManager.put("ProgressBar.foreground", Color.BLUE);
			health.setStringPainted(true);
			HarryPotterView.getButtons().add(health);
			health.setString(""+((Wizard) SecondTask.getCurrentChamp()).getHp()+"/"+((Wizard) SecondTask.getCurrentChamp()).getDefaultHp()+"");
			ip.setString(""+((Wizard) SecondTask.getCurrentChamp()).getIp()+"/"+((Wizard) SecondTask.getCurrentChamp()).getDefaultIp()+"");
			health.setStringPainted(true);
			//health.setForeground(Color.green);
			health.setBackground(Color.green);
			JTextArea inv1 = new JTextArea("INVENTORY");
			inv1.setEditable(false);
			HarryPotterView.getStats().add(inv1);
			if(((Wizard) SecondTask.getCurrentChamp()).getInventory().size()==0){
				JTextArea inv2=new JTextArea("EMPTY");
				HarryPotterView.getStats().add(inv2);
				inv2.setEditable(false);
			}
			//HarryPotterView.getButtons().add(Inv);
			for(Collectible x:((Wizard) SecondTask.getCurrentChamp()).getInventory()){
				JReferencingButton btn2 = new JReferencingButton();
				//btn2.setText(x.getName());
				String st = "<html><html>";
				st+="<br>Name: "+x.getName()+"<br>";
				st+="<br>intelligence healing : "+((Potion) x).getAmount()+"<br>";
				btn2.setToolTipText(st);
				btn2.setPreferredSize(new Dimension(100, 100));
				btn2.setValue(x);
				//btn2.addActionListener(new InvListener(Tournament, HarryPotterView, FirstTask));
			HarryPotterView.getStats().add(btn2);
			}
			HarryPotterView.getButtons().add(turn,BorderLayout.WEST);
			 JReferencingButton<Spell> Spell1 = new JReferencingButton<Spell>();
			 JReferencingButton<Spell> Spell2 = new JReferencingButton<Spell>();
			 JReferencingButton<Spell> Spell3 = new JReferencingButton<Spell>();
			 String x1 ="<html>1<html>";
			 x1+="<html>Name:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getName()+"<br><html>";
			  if(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0) instanceof DamagingSpell){
				  ImageIcon image = new ImageIcon("fire.jpg");
					Spell1.setIcon(image);
				 x1+="Damage:"+((DamagingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0)).getDamageAmount()+"<br>";
			 }
			 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0) instanceof RelocatingSpell){
				 x1+="Range:"+((RelocatingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0)).getRange()+"<br>";
			 }
			 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0) instanceof HealingSpell){
				 ImageIcon image = new ImageIcon("spell-icons-2-crop.jpg");
				 Spell1.setIcon(image);
				 x1+="Healing:"+((HealingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0)).getHealingAmount()+"<br>";
			 }
			  x1+="Cost:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getCost()+"<br>";
			  x1+="CoolDown:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getCoolDown()+"/"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getDefaultCooldown()+"<br>";
			 Spell1.setToolTipText(x1);
			 String x2 ="<html>1<html>";
			 x2+="<html>Name:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getName()+"<br><html>";
			  if(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1) instanceof DamagingSpell){
				  ImageIcon image = new ImageIcon("fire.jpg");
					Spell2.setIcon(image);
				  x2+="Damage:"+((DamagingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1)).getDamageAmount()+"<br>";
			 }
			 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1) instanceof RelocatingSpell){
				 x2+="Range:"+((RelocatingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1)).getRange()+"<br>";
			 }
			 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1) instanceof HealingSpell){
				 ImageIcon image = new ImageIcon("spell-icons-2-crop.jpg");
				 Spell2.setIcon(image);
				 x2+="Healing:"+((HealingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1)).getHealingAmount()+"<br>";
			 }
			  x2+="Cost:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getCost()+"<br>";
			  x2+="CoolDown:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getCoolDown()+"/"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
			 Spell2.setToolTipText(x2);
			 String x3 ="<html>1<html>";
			 x3+="<html>Name:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2).getName()+"<br><html>";
			  if(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2) instanceof DamagingSpell){
				  ImageIcon image = new ImageIcon("fire.jpg");
					Spell3.setIcon(image);
				  x3+="Damage:"+((DamagingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2)).getDamageAmount()+"<br>";
			 }
			 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2) instanceof RelocatingSpell){
				 x3+="Range:"+((RelocatingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2)).getRange()+"<br>";
			 }
			 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2) instanceof HealingSpell){
				 ImageIcon image = new ImageIcon("spell-icons-2-crop.jpg");
				 Spell3.setIcon(image);
				 x3+="Healing:"+((HealingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2)).getHealingAmount()+"<br>";
			 }
			  x3+="Cost:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getCost()+"<br>";
			  x3+="CoolDown:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getCoolDown()+"/"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
			 Spell3.setToolTipText(x3);
				Spell2.setText(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getName());
				Spell3.setText(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2).getName());
				Spell1.setValue(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0));
				Spell2.setValue(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1));
				Spell3.setValue(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2));
				Spell1.setPreferredSize(new Dimension(100, 100));
				Spell2.setPreferredSize(new Dimension(100, 100));
				Spell3.setPreferredSize(new Dimension(100, 100));
				//Spell1.addActionListener(new useSpellsListener2(FirstTask,Tournament,HarryPotterView));
				//Spell2.addActionListener(new useSpellsListener2(FirstTask,Tournament,HarryPotterView));
				//Spell3.addActionListener(new useSpellsListener2(FirstTask,Tournament,HarryPotterView));
				 JLabel winners = new JLabel("wiiners so far for this Task: "+SecondTask.getWinners());
				 HarryPotterView.getButtons().add(winners,BorderLayout.WEST);
			HarryPotterView.getSpellstest().add(Spell1);
			HarryPotterView.getSpellstest().add(Spell2);
			HarryPotterView.getSpellstest().add(Spell3);
			HarryPotterView.getSpellstest().add(Trait);
			String text="<html><html>";
			if(SecondTask.getCurrentChamp() instanceof GryffindorWizard){
				text+= "<br>Special Trait: can see Dragen's marked cells before it's attack <br>";
				text+= "Cooldown:"+((GryffindorWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
			}
			if(SecondTask.getCurrentChamp() instanceof RavenclawWizard){
				text+="<br>Special Trait: can see Dragen's marked cells before it's attack <br>";
				text+= "Cooldown:"+((RavenclawWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
			}
			if(SecondTask.getCurrentChamp() instanceof SlytherinWizard){
				text+="<br>Special Trait: can teleport himself 2 cells <br>";
				text+= "Cooldown:"+((SlytherinWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
			}
			if(SecondTask.getCurrentChamp() instanceof HufflepuffWizard){
				text+="<br>Special Trait: Dragon doesn't attack this Turn <br>";
				text+= "Cooldown:"+((HufflepuffWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
			}
			Trait.setToolTipText(text);
			Trait.setPreferredSize(new Dimension(100, 100));
			
			//useTraitListener  useTraitListener= new  useTraitListener(FirstTask , Tournament , HarryPotterView);
			//Trait.addActionListener(useTraitListener);
			
		 
			for (int i = 0; i<SecondTask.getMap().length; i++) {
				for (int j = 0; j<SecondTask.getMap().length ; j++) {
					//JOptionPane.showMessageDialog(null, FirstTask.getMarkedCells());
					//JButton btnmap = new JButton();
					JLabel btnmap = new JLabel();
					if(SecondTask.getMap()[i][j] instanceof ChampionCell ){
					if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp() instanceof GryffindorWizard){
						if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp()==SecondTask.getCurrentChamp()){
						ImageIcon image = new ImageIcon("currentgryff.png");
						btnmap.setIcon(image);
						String st ="<html><html>";
						st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
						st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
						st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
						st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
						btnmap.setToolTipText(st);
						}
						else{
							ImageIcon image = new ImageIcon("normalgryff.png");
							btnmap.setIcon(image);
							String st ="<html><html>";
							st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
							st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
							st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
							st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
							btnmap.setToolTipText(st);
																}
					}
						if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp() instanceof HufflepuffWizard){
							if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp()==SecondTask.getCurrentChamp()){
								ImageIcon image = new ImageIcon("currenthuffle.png");
								btnmap.setIcon(image);
								String st ="<html><html>";
								st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
								st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
								st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
								st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
								btnmap.setToolTipText(st);
								}
								else{
									ImageIcon image = new ImageIcon("normalhuffle.png");
									btnmap.setIcon(image);
									String st ="<html><html>";
									st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
									st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
									st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
									st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
									btnmap.setToolTipText(st);
								}
						}
							if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp() instanceof SlytherinWizard){
								if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp()==SecondTask.getCurrentChamp()){
									ImageIcon image = new ImageIcon("currentslyth.png");
									btnmap.setIcon(image);
									String st ="<html><html>";
									st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
									st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
									st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
									st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
									btnmap.setToolTipText(st);
									}
									else{
										ImageIcon image = new ImageIcon("normalslyth.png");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
									}
							}
								if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp() instanceof RavenclawWizard){
									if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp()==SecondTask.getCurrentChamp()){
										ImageIcon image = new ImageIcon("currentraven.png");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
										}
										else{
											ImageIcon image = new ImageIcon("emptycell - Copy.png");
											btnmap.setIcon(image);
											String st ="<html><html>";
											st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
											st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
											st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
											st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
											btnmap.setToolTipText(st);
										}
								}
					
						//btnmap.setPreferredSize(new Dimension(200, 200));
					
					}
					
					 if ( SecondTask.getMap()[i][j] instanceof EmptyCell){
						ImageIcon image = new ImageIcon("emptycell.png");
						btnmap.setIcon(image);
					}
					 if (SecondTask.getMap()[i][j] instanceof CollectibleCell ){
						 ImageIcon image = new ImageIcon("emptycell.png");
							btnmap.setIcon(image);
					 }
					 if(SecondTask.getMap()[i][j] instanceof WallCell ){
						 ImageIcon image = new ImageIcon("wall.png");
							btnmap.setIcon(image);
					 }
					 
					 if(SecondTask.getMap()[i][j] instanceof ObstacleCell && ((ObstacleCell) SecondTask.getMap()[i][j]).getObstacle() instanceof PhysicalObstacle  ){
						 ImageIcon image = new ImageIcon("po (2).png");
							btnmap.setIcon(image);
							String st ="";
							st+="Hp: "+( ((ObstacleCell) SecondTask.getMap()[i][j]).getObstacle()).getHp()+"";
							btnmap.setToolTipText(st);
					 }
			 btnmap.setPreferredSize(new Dimension(130,90));
			HarryPotterView.getSecondTaskView().add(btnmap);
			HarryPotterView.validate();
			HarryPotterView.revalidate();
			HarryPotterView.repaint();
			//HarryPotterView.getTest().add(HarryPotterView.getSpellstest());
			//HarryPotterView.getTest().add(HarryPotterView.getStats());
			//HarryPotterView.getTest().add(HarryPotterView.getButtons());
			//HarryPotterView.getTest().add(HarryPotterView.getFirstTaskView());
		}
	}
			//HarryPotterView.getSecondTaskWholeView().setVisible(true);
			 MovesActionTask2 MovesAction = new MovesActionTask2(SecondTask , this , HarryPotterView);
				HarryPotterView.addKeyListener(MovesAction);
				HarryPotterView.setFocusable(true);
				HarryPotterView.setFocusTraversalKeysEnabled(false);
			HarryPotterView.setContentPane(HarryPotterView.getSecondTaskView());
			HarryPotterView.validate();
			HarryPotterView.revalidate();
			HarryPotterView.repaint();
		}
	*/

	public void onFinishingSecondTask(ArrayList<Champion> winners)
			throws IOException {
		

		if (!winners.isEmpty()) {
			thirdTask = new ThirdTask(winners);
			thirdTask.setListener(this);
		}

	}

	public void onFinishingThirdTask(Champion winner) {

		JOptionPane.showMessageDialog(null, "THE WINNER IS"+((Wizard) winner).getName());

	}

	public void setListener(HarryPotterGUI listener) {
		if(listener !=null)
		this.listener=listener;
		
	}

}
