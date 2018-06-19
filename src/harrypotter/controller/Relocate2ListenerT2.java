package harrypotter.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
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
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.HarryPotterView;

public class Relocate2ListenerT2 implements ActionListener {
	private SecondTask SecondTask;
	private Tournament Tournament;
	private HarryPotterView HarryPotterView;
	private useSpellListenerT2 useSpellsListenerT2;
	private RelocatingListenerT2 RelocatingListenerT2;
	
	public  Relocate2ListenerT2(SecondTask SecondTask, Tournament Tournament,HarryPotterView HarryPotterView, useSpellListenerT2 useSpellListenerT2,RelocatingListenerT2 RelocatingListenerT2 ){
		this.SecondTask=SecondTask;
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		this.useSpellsListenerT2=useSpellListenerT2;
		this.RelocatingListenerT2=RelocatingListenerT2;
		
	}

	
	public void generatemapT2(){

		HarryPotterView.getTest().setVisible(false);
		HarryPotterView.getSecondTaskWholeView().removeAll();
		HarryPotterView.getButtonsT2().removeAll();
		 HarryPotterView.getSecondTaskView().removeAll();
		 HarryPotterView.getStatsT2().removeAll();
		 HarryPotterView.getSpellsT2().removeAll();
		HarryPotterView.getSecondTaskWholeView().setVisible(true);
		HarryPotterView.validate();
		HarryPotterView.revalidate();
		HarryPotterView.repaint();
		
		JButton Trait = new JButton();
				
		if(SecondTask.getCurrentChamp() instanceof GryffindorWizard){
			if(SecondTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("Kayle_W - Copy.png");
			 Trait.setIcon(image);
			}
			else{
				ImageIcon image = new ImageIcon("Kayle_W.png");
				((JButton) Trait).setIcon(image);
			}
		}
		if(SecondTask.getCurrentChamp() instanceof RavenclawWizard){
			if(SecondTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("Kalista_W.png");
			 Trait.setIcon(image);
			}else{
				ImageIcon image = new ImageIcon("Kalista_W - Copy.png");
				((JButton) Trait).setIcon(image);
				
			}
		}
		if(SecondTask.getCurrentChamp() instanceof SlytherinWizard){
			if(SecondTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("BCDaFSy - Copy.png");
			 Trait.setIcon(image);
			}
			else{
				ImageIcon image = new ImageIcon("BCDaFSy.png");
				((JButton) Trait).setIcon(image);
				
			}
		}
		if(SecondTask.getCurrentChamp() instanceof HufflepuffWizard){
			if(SecondTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("Annie_E - Copy.png");
			 Trait.setIcon(image);
			}else{
				ImageIcon image = new ImageIcon("Annie_E.png");
				((JButton) Trait).setIcon(image);
				
			}
		}
		JLabel turn = new JLabel("its "+((Wizard) SecondTask.getCurrentChamp()).getName()+ " Turn " +"with allowed moves "+SecondTask.getAllowedMoves() ,SwingConstants.RIGHT );
		HarryPotterView.getButtonsT2().add(turn,BorderLayout.WEST);
		JProgressBar health = new JProgressBar(((Wizard) SecondTask.getCurrentChamp()).getHp(),((Wizard) SecondTask.getCurrentChamp()).getDefaultHp());
		if(((Wizard) SecondTask.getCurrentChamp()).getIp()<((Wizard) SecondTask.getCurrentChamp()).getDefaultIp() || ((Wizard) SecondTask.getCurrentChamp()).getIp()==((Wizard) SecondTask.getCurrentChamp()).getDefaultIp()){
			JProgressBar ip = new JProgressBar(((Wizard) SecondTask.getCurrentChamp()).getIp(),((Wizard) SecondTask.getCurrentChamp()).getDefaultIp());
			ip.setBackground(Color.BLUE);
			ip.setStringPainted(true);
			HarryPotterView.getButtonsT2().add(ip);
			ip.setString("IP :"+((Wizard) SecondTask.getCurrentChamp()).getIp()+"/"+((Wizard) SecondTask.getCurrentChamp()).getDefaultIp()+"");
			}
			else if(((Wizard) SecondTask.getCurrentChamp()).getIp()>((Wizard) SecondTask.getCurrentChamp()).getDefaultIp()){
				((Wizard) SecondTask.getCurrentChamp()).setDefaultIp(((Wizard) SecondTask.getCurrentChamp()).getIp());
				JProgressBar ip = new JProgressBar(((Wizard) SecondTask.getCurrentChamp()).getIp(),((Wizard) SecondTask.getCurrentChamp()).getDefaultIp());
				//ip.setString("IP :"+((Wizard) FirstTask.getCurrentChamp()).getIp()+"/"+((Wizard) FirstTask.getCurrentChamp()).getDefaultIp()+"");
				ip.setBackground(Color.BLUE);
				ip.setStringPainted(true);
				HarryPotterView.getButtonsT2().add(ip);
			}
		UIManager.put("ProgressBar.foreground", Color.BLUE);
		health.setStringPainted(true);

		HarryPotterView.getButtonsT2().add(health);
		health.setString("HP :"+((Wizard) SecondTask.getCurrentChamp()).getHp()+"/"+((Wizard) SecondTask.getCurrentChamp()).getDefaultHp()+"");
		health.setStringPainted(true);
		health.setBackground(Color.green);
		JTextArea inv1 = new JTextArea("INVENTORY");
		inv1.setEditable(false);
		HarryPotterView.getStatsT2().add(inv1);
		if(((Wizard) SecondTask.getCurrentChamp()).getInventory().size()==0){
			JTextArea inv2=new JTextArea("EMPTY");
			HarryPotterView.getStatsT2().add(inv2);
			inv2.setEditable(false);
		}
		for(Collectible x:((Wizard) SecondTask.getCurrentChamp()).getInventory()){
			JReferencingButton btn2 = new JReferencingButton();
			if(((Potion) x).getAmount()==1700){
				 ImageIcon image = new ImageIcon("e9c5e1b5409cfc0ee8eb111efe22f88b.jpg");
				 btn2.setIcon(image);
			}
			if(((Potion) x).getAmount()==200){
				 ImageIcon image = new ImageIcon("RefillablePotion.png");
				 btn2.setIcon(image);
			}
			if(((Potion) x).getAmount()==1000){
				 ImageIcon image = new ImageIcon("Hunter's_Potion_item.png");
				 btn2.setIcon(image);
			}
			if(((Potion) x).getAmount()==500){
				 ImageIcon image = new ImageIcon("ManaPotion.png");
				 btn2.setIcon(image);
			}
			if(((Potion) x).getAmount()==700){
				 ImageIcon image = new ImageIcon("7385.png");
				 btn2.setIcon(image);
			}
			if(((Potion) x).getAmount()==100){
				 ImageIcon image = new ImageIcon("609539a51582270654c8a48178fd1997.png");
				 btn2.setIcon(image);
			}
			String st = "<html><html>";
			st+="<br>Name: "+x.getName()+"<br>";
			st+="<br>intelligence healing : "+((Potion) x).getAmount()+"<br>";
			btn2.setToolTipText(st);
			btn2.setText(x.getName());
			btn2.setPreferredSize(new Dimension(100, 100));
			btn2.setValue(x);
			//btn2.addActionListener(new InvListenerT2(Tournament, HarryPotterView, FirstTask));
		HarryPotterView.getStatsT2().add(btn2);
		}
		HarryPotterView.getButtonsT2().add(turn,BorderLayout.WEST);
		 JReferencingButton<Spell> Spell1 = new JReferencingButton<Spell>();
		 JReferencingButton<Spell> Spell2 = new JReferencingButton<Spell>();
		 JReferencingButton<Spell> Spell3 = new JReferencingButton<Spell>();
		 JLabel winners = new JLabel("winners so far for this Task: "+SecondTask.getWinners());
		 HarryPotterView.getButtons().add(winners,BorderLayout.WEST);
		 String x1 ="<html>1<html>";
		 x1+="<html>Name:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getName()+"<br><html>";
		  if(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0) instanceof DamagingSpell){
			  ImageIcon image = new ImageIcon("VelKoz_E.png");
				Spell1.setIcon(image);
			 x1+="Damage:"+((DamagingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0)).getDamageAmount()+"<br>";
		 }
		 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0) instanceof RelocatingSpell){
			 x1+="Range:"+((RelocatingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0)).getRange()+"<br>";
			 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
				Spell1.setIcon(image);
		 }
		 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0) instanceof HealingSpell){
			 ImageIcon image = new ImageIcon("Soraka_W.png");
			 Spell1.setIcon(image);
			 x1+="Healing:"+((HealingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0)).getHealingAmount()+"<br>";
		 }
		  x1+="Cost:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getCost()+"<br>";
		  x1+="CoolDown:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getCoolDown()+"/"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0).getDefaultCooldown()+"<br>";
		 Spell1.setToolTipText(x1);
		 String x2 ="<html>1<html>";
		 x2+="<html>Name:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getName()+"<br><html>";
		  if(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1) instanceof DamagingSpell){
			  ImageIcon image = new ImageIcon("VelKoz_E.png");
				Spell2.setIcon(image);
			  x2+="Damage:"+((DamagingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1)).getDamageAmount()+"<br>";
		 }
		 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1) instanceof RelocatingSpell){
			 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
			 x2+="Range:"+((RelocatingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1)).getRange()+"<br>";
			 Spell2.setIcon(image);
		 }
		 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1) instanceof HealingSpell){
			 ImageIcon image = new ImageIcon("Soraka_W.png");
			 Spell2.setIcon(image);
			 x2+="Healing:"+((HealingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1)).getHealingAmount()+"<br>";
		 }
		  x2+="Cost:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getCost()+"<br>";
		  x2+="CoolDown:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getCoolDown()+"/"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
		 Spell2.setToolTipText(x2);
		 String x3 ="<html>1<html>";
		 x3+="<html>Name:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2).getName()+"<br><html>";
		  if(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2) instanceof DamagingSpell){
			  ImageIcon image = new ImageIcon("VelKoz_E.png");
				Spell3.setIcon(image);
			  x3+="Damage:"+((DamagingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2)).getDamageAmount()+"<br>";
		 }
		 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2) instanceof RelocatingSpell){
			 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
			 x3+="Range:"+((RelocatingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2)).getRange()+"<br>";
			 Spell3.setIcon(image);
		 }
		 else if (((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2) instanceof HealingSpell){
			 ImageIcon image = new ImageIcon("Soraka_W.png");
			 Spell3.setIcon(image);
			 x3+="Healing:"+((HealingSpell) ((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2)).getHealingAmount()+"<br>";
		 }
		  x3+="Cost:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2).getCost()+"<br>";
		  x3+="CoolDown:"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2).getCoolDown()+"/"+((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
		 Spell3.setToolTipText(x3);
			Spell2.setText(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1).getName());
			Spell3.setText(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2).getName());
			Spell1.setValue(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(0));
			Spell2.setValue(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(1));
			Spell3.setValue(((Wizard) SecondTask.getCurrentChamp()).getSpells().get(2));
			Spell1.setPreferredSize(new Dimension(100, 100));
			Spell2.setPreferredSize(new Dimension(100, 100));
			Spell3.setPreferredSize(new Dimension(100, 100));
			Spell1.addActionListener(new useSpellListenerT2(SecondTask,Tournament,HarryPotterView));
			Spell2.addActionListener(new useSpellListenerT2(SecondTask,Tournament,HarryPotterView));
			Spell3.addActionListener(new useSpellListenerT2(SecondTask,Tournament,HarryPotterView));
		HarryPotterView.getSpellsT2().add(Spell1);
		HarryPotterView.getSpellsT2().add(Spell2);
		HarryPotterView.getSpellsT2().add(Spell3);
		HarryPotterView.getSpellsT2().add(Trait);
		String text="<html><html>";
		if(SecondTask.getCurrentChamp() instanceof GryffindorWizard){
			text+= "<br>Special Trait: can do two moves instead of one this turn<br>";
			text+= "Cooldown:"+((GryffindorWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
		}
		if(SecondTask.getCurrentChamp() instanceof RavenclawWizard){
			text+="<br>Special Trait: gets a hint for his treasure <br>";
			text+= "Cooldown:"+((RavenclawWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
		}
		if(SecondTask.getCurrentChamp() instanceof SlytherinWizard){
			text+="<br>Special Trait: can teleport himself 2 cells <br>";
			text+= "Cooldown:"+((SlytherinWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
		}
		if(SecondTask.getCurrentChamp() instanceof HufflepuffWizard){
			text+="<br>Special Trait: Merpersons don't do any damage this turn  <br>";
			text+= "Cooldown:"+((HufflepuffWizard) SecondTask.getCurrentChamp()).getTraitCooldown();
		}
		Trait.setToolTipText(text);
		Trait.setPreferredSize(new Dimension(100, 100));
		useTraitListenerT2  useTraitListener= new  useTraitListenerT2(SecondTask , Tournament , HarryPotterView);
		Trait.addActionListener(useTraitListener);
				for (int i = 0; i<SecondTask.getMap().length; i++) {
					for (int j = 0; j<SecondTask.getMap().length ; j++) {
						//JOptionPane.showMessageDialog(null, FirstTask.getMarkedCells());
						//JButton btnmap = new JButton();
						JLabel btnmap = new JLabel();
						Point z= new Point(i,j);
						if(SecondTask.getMap()[i][j] instanceof ChampionCell ){
						if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp() instanceof GryffindorWizard){
							if(((ChampionCell) SecondTask.getMap()[i][j]).getChamp()==SecondTask.getCurrentChamp()){
							ImageIcon image = new ImageIcon("currgryfft2.png");
							btnmap.setIcon(image);
							String st ="<html><html>";
							st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
							st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
							st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
							st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
							btnmap.setToolTipText(st);
							}
							else{
								ImageIcon image = new ImageIcon("gryfft2.png");
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
									ImageIcon image = new ImageIcon("currhufflet2.png");
									btnmap.setIcon(image);
									String st ="<html><html>";
									st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
									st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
									st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
									st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
									btnmap.setToolTipText(st);
									}
									else{
										ImageIcon image = new ImageIcon("hufflet2.png");
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
										ImageIcon image = new ImageIcon("currslyth2.png");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
										}
										else{
											ImageIcon image = new ImageIcon("slytht2.png");
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
											ImageIcon image = new ImageIcon("currravent2.png");
											btnmap.setIcon(image);
											String st ="<html><html>";
											st+="<br>Name: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getName()+" <br>";
											st+="<br>Hp: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
											st+="<br>Ip: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
											st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) SecondTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
											btnmap.setToolTipText(st);
											}
											else{
												ImageIcon image = new ImageIcon("ravent2.png");
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
							ImageIcon image = new ImageIcon("emptyt2.png");
							btnmap.setIcon(image);
						 }
						 if (SecondTask.getMap()[i][j] instanceof CollectibleCell ){
							 ImageIcon image = new ImageIcon("emptyt2.png");
								btnmap.setIcon(image);
						 }
						 if(SecondTask.getMap()[i][j] instanceof WallCell ){
							 ImageIcon image = new ImageIcon("wall.png");
								btnmap.setIcon(image);
						 }
						 
						 if(SecondTask.getMap()[i][j] instanceof TreasureCell ){
							 ImageIcon image = new ImageIcon("emptyt2.png");
								btnmap.setIcon(image);
						 }
						 
						 if(SecondTask.getMap()[i][j] instanceof ObstacleCell && ((ObstacleCell) SecondTask.getMap()[i][j]).getObstacle() instanceof Merperson  ){
							 ImageIcon image = new ImageIcon("merperson.png");
								btnmap.setIcon(image);
								String st ="<html><html>";
								st+="<br>Hp: "+( ((ObstacleCell) SecondTask.getMap()[i][j]).getObstacle()).getHp()+"<br>";
								st+="<br>Damage : "+( (Merperson) ((ObstacleCell) SecondTask.getMap()[i][j]).getObstacle()).getDamage()+"<br>";
								btnmap.setToolTipText(st);
						 }
				 btnmap.setPreferredSize(new Dimension(130,90));
				 MovesActionTask2 MovesAction = new MovesActionTask2(SecondTask , Tournament , HarryPotterView);
				HarryPotterView.getSecondTaskView().add(btnmap);
			}
		}
					HarryPotterView.setFocusable(true);
					HarryPotterView.setFocusTraversalKeysEnabled(false);
					HarryPotterView.getSecondTaskWholeView().add(HarryPotterView.getSpellsT2());
					HarryPotterView.getSecondTaskWholeView().add(HarryPotterView.getSecondTaskView());
					HarryPotterView.getSecondTaskWholeView().add(HarryPotterView.getStatsT2());
					HarryPotterView.getSecondTaskWholeView().add(HarryPotterView.getButtonsT2());
			HarryPotterView.setContentPane(HarryPotterView.getSecondTaskWholeView());
				HarryPotterView.validate();
				HarryPotterView.revalidate();
				HarryPotterView.repaint();
			}


	@Override
	public void actionPerformed(ActionEvent args) {
		if(RelocatingListenerT2.getList2().getSelectedValue()=="FORWARD"){
			try {
				RelocatingListenerT2.dispose();
				SecondTask.castRelocatingSpell((RelocatingSpell) useSpellsListenerT2.getJReferencingButton(), RelocatingListenerT2.getDirectionx(), Direction.FORWARD, Integer.parseInt((String) RelocatingListenerT2.getList1().getSelectedValue()));
				generatemapT2();
			} catch (InCooldownException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughIPException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfBordersException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTargetCellException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfRangeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(RelocatingListenerT2.getList2().getSelectedValue()=="BACKWARD"){
			try {
				RelocatingListenerT2.dispose();
				SecondTask.castRelocatingSpell((RelocatingSpell) useSpellsListenerT2.getJReferencingButton(), RelocatingListenerT2.getDirectionx(), Direction.BACKWARD, Integer.parseInt((String) RelocatingListenerT2.getList1().getSelectedValue()));
				generatemapT2();
			} catch (InCooldownException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughIPException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfBordersException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTargetCellException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfRangeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(RelocatingListenerT2.getList2().getSelectedValue()=="LEFT"){
			try {
				RelocatingListenerT2.dispose();
				SecondTask.castRelocatingSpell((RelocatingSpell) useSpellsListenerT2.getJReferencingButton(), RelocatingListenerT2.getDirectionx(), Direction.LEFT, Integer.parseInt((String) RelocatingListenerT2.getList1().getSelectedValue()));
				generatemapT2();
			} catch (InCooldownException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughIPException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfBordersException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTargetCellException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfRangeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(RelocatingListenerT2.getList2().getSelectedValue()=="RIGHT"){
			try {
				RelocatingListenerT2.dispose();
				SecondTask.castRelocatingSpell((RelocatingSpell) useSpellsListenerT2.getJReferencingButton(), RelocatingListenerT2.getDirectionx(), Direction.RIGHT, Integer.parseInt((String) RelocatingListenerT2.getList1().getSelectedValue()));
				generatemapT2();
			} catch (InCooldownException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughIPException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfBordersException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTargetCellException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OutOfRangeException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				generatemapT2();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}

}
