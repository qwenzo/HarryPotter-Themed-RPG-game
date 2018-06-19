package harrypotter.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
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
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.HarryPotterView;

public class MovesActionTask3 implements KeyListener {
	private ThirdTask ThirdTask;
	private Tournament Tournament;
	private  HarryPotterView HarryPotterView;
	
	
	
	
	
	
	public void generatemapT3(){
		 ThirdTask =Tournament.getThirdTask();
		
		HarryPotterView.getThirdTaskWholeView().removeAll();
		HarryPotterView.getButtonsT3().removeAll();
		 HarryPotterView.getThirdTaskView().removeAll();
		 HarryPotterView.getStatsT3().removeAll();
		 HarryPotterView.getSpellsT3().removeAll();
		
		HarryPotterView.getSecondTaskWholeView().setVisible(false);
		HarryPotterView.getThirdTaskWholeView().setVisible(true);
		HarryPotterView.validate();
		HarryPotterView.revalidate();
		HarryPotterView.repaint();
		
		JButton Trait = new JButton();
				
		if(ThirdTask.getCurrentChamp() instanceof GryffindorWizard){
			if(ThirdTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("Kayle_W - Copy.png");
			 Trait.setIcon(image);
			}
			else{
				ImageIcon image = new ImageIcon("Kayle_W.png");
				((JButton) Trait).setIcon(image);
			}
		}
		if(ThirdTask.getCurrentChamp() instanceof RavenclawWizard){
			if(ThirdTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("Kalista_W.png");
			 Trait.setIcon(image);
			}else{
				ImageIcon image = new ImageIcon("Kalista_W - Copy.png");
				((JButton) Trait).setIcon(image);
				
			}
		}
		if(ThirdTask.getCurrentChamp() instanceof SlytherinWizard){
			if(ThirdTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("BCDaFSy - Copy.png");
			 Trait.setIcon(image);
			}
			else{
				ImageIcon image = new ImageIcon("BCDaFSy.png");
				((JButton) Trait).setIcon(image);
				
			}
		}
		if(ThirdTask.getCurrentChamp() instanceof HufflepuffWizard){
			if(ThirdTask.isTraitActivated()){
			 ImageIcon image = new ImageIcon("Annie_E - Copy.png");
			 Trait.setIcon(image);
			}else{
				ImageIcon image = new ImageIcon("Annie_E.png");
				((JButton) Trait).setIcon(image);
				
			}
		}
		JLabel turn = new JLabel("its "+((Wizard) ThirdTask.getCurrentChamp()).getName()+ " Turn " +"with allowed moves "+ThirdTask.getAllowedMoves() ,SwingConstants.RIGHT );
		HarryPotterView.getButtonsT3().add(turn,BorderLayout.WEST);
		JProgressBar health = new JProgressBar(((Wizard) ThirdTask.getCurrentChamp()).getHp(),((Wizard) ThirdTask.getCurrentChamp()).getDefaultHp());
		if(((Wizard) ThirdTask.getCurrentChamp()).getIp()<((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp() || ((Wizard) ThirdTask.getCurrentChamp()).getIp()==((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp()){
			JProgressBar ip = new JProgressBar(((Wizard) ThirdTask.getCurrentChamp()).getIp(),((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp());
			ip.setBackground(Color.BLUE);
			ip.setStringPainted(true);
			HarryPotterView.getButtonsT3().add(ip);
			ip.setString("IP :"+((Wizard) ThirdTask.getCurrentChamp()).getIp()+"/"+((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp()+"");
			}
			else if(((Wizard) ThirdTask.getCurrentChamp()).getIp()>((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp()){
				((Wizard) ThirdTask.getCurrentChamp()).setDefaultIp(((Wizard) ThirdTask.getCurrentChamp()).getIp());
				JProgressBar ip = new JProgressBar(((Wizard) ThirdTask.getCurrentChamp()).getIp(),((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp());
				ip.setString("IP :"+((Wizard) ThirdTask.getCurrentChamp()).getIp()+"/"+((Wizard) ThirdTask.getCurrentChamp()).getDefaultIp()+"");
				ip.setBackground(Color.BLUE);
				ip.setStringPainted(true);
				HarryPotterView.getButtonsT3().add(ip);
			}
		UIManager.put("ProgressBar.foreground", Color.BLUE);
		health.setStringPainted(true);

		HarryPotterView.getButtonsT3().add(health);
		health.setString("HP :"+((Wizard) ThirdTask.getCurrentChamp()).getHp()+"/"+((Wizard) ThirdTask.getCurrentChamp()).getDefaultHp()+"");
		health.setStringPainted(true);
		health.setBackground(Color.green);
		JTextArea inv1 = new JTextArea("INVENTORY");
		inv1.setEditable(false);
		HarryPotterView.getStatsT3().add(inv1);
		if(((Wizard) ThirdTask.getCurrentChamp()).getInventory().size()==0){
			JTextArea inv2=new JTextArea("EMPTY");
			HarryPotterView.getStatsT3().add(inv2);
			inv2.setEditable(false);
		}
		for(Collectible x:((Wizard) ThirdTask.getCurrentChamp()).getInventory()){
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
			//btn2.addActionListener(new InvlistenerT3(Tournament, HarryPotterView, ThirdTask));
		HarryPotterView.getStatsT3().add(btn2);
		}
		HarryPotterView.getButtonsT3().add(turn,BorderLayout.WEST);
		 JReferencingButton<Spell> Spell1 = new JReferencingButton<Spell>();
		 JReferencingButton<Spell> Spell2 = new JReferencingButton<Spell>();
		 JReferencingButton<Spell> Spell3 = new JReferencingButton<Spell>();
		 String x1 ="<html>1<html>";
		 x1+="<html>Name:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0).getName()+"<br><html>";
		  if(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0) instanceof DamagingSpell){
			  ImageIcon image = new ImageIcon("VelKoz_E.png");
				Spell1.setIcon(image);
			 x1+="Damage:"+((DamagingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0)).getDamageAmount()+"<br>";
		 }
		 else if (((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0) instanceof RelocatingSpell){
			 x1+="Range:"+((RelocatingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0)).getRange()+"<br>";
			 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
				Spell1.setIcon(image);
		 }
		 else if (((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0) instanceof HealingSpell){
			 ImageIcon image = new ImageIcon("Soraka_W.png");
			 Spell1.setIcon(image);
			 x1+="Healing:"+((HealingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0)).getHealingAmount()+"<br>";
		 }
		  x1+="Cost:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0).getCost()+"<br>";
		  x1+="CoolDown:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0).getCoolDown()+"/"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0).getDefaultCooldown()+"<br>";
		 Spell1.setToolTipText(x1);
		 String x2 ="<html>1<html>";
		 x2+="<html>Name:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1).getName()+"<br><html>";
		  if(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1) instanceof DamagingSpell){
			  ImageIcon image = new ImageIcon("VelKoz_E.png");
				Spell2.setIcon(image);
			  x2+="Damage:"+((DamagingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1)).getDamageAmount()+"<br>";
		 }
		 else if (((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1) instanceof RelocatingSpell){
			 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
			 x2+="Range:"+((RelocatingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1)).getRange()+"<br>";
			 Spell2.setIcon(image);
		 }
		 else if (((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1) instanceof HealingSpell){
			 ImageIcon image = new ImageIcon("Soraka_W.png");
			 Spell2.setIcon(image);
			 x2+="Healing:"+((HealingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1)).getHealingAmount()+"<br>";
		 }
		  x2+="Cost:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1).getCost()+"<br>";
		  x2+="CoolDown:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1).getCoolDown()+"/"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
		 Spell2.setToolTipText(x2);
		 String x3 ="<html>1<html>";
		 x3+="<html>Name:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2).getName()+"<br><html>";
		  if(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2) instanceof DamagingSpell){
			  ImageIcon image = new ImageIcon("VelKoz_E.png");
				Spell3.setIcon(image);
			  x3+="Damage:"+((DamagingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2)).getDamageAmount()+"<br>";
		 }
		 else if (((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2) instanceof RelocatingSpell){
			 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
			 x3+="Range:"+((RelocatingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2)).getRange()+"<br>";
			 Spell3.setIcon(image);
		 }
		 else if (((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2) instanceof HealingSpell){
			 ImageIcon image = new ImageIcon("Soraka_W.png");
			 Spell3.setIcon(image);
			 x3+="Healing:"+((HealingSpell) ((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2)).getHealingAmount()+"<br>";
		 }
		  x3+="Cost:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2).getCost()+"<br>";
		  x3+="CoolDown:"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2).getCoolDown()+"/"+((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
		 Spell3.setToolTipText(x3);
			Spell2.setText(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1).getName());
			Spell3.setText(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2).getName());
			Spell1.setValue(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(0));
			Spell2.setValue(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(1));
			Spell3.setValue(((Wizard) ThirdTask.getCurrentChamp()).getSpells().get(2));
			Spell1.setPreferredSize(new Dimension(100, 100));
			Spell2.setPreferredSize(new Dimension(100, 100));
			Spell3.setPreferredSize(new Dimension(100, 100));
			Spell1.addActionListener(new useSpellListenerT3(ThirdTask,Tournament,HarryPotterView));
			Spell2.addActionListener(new useSpellListenerT3(ThirdTask,Tournament,HarryPotterView));
			Spell3.addActionListener(new useSpellListenerT3(ThirdTask,Tournament,HarryPotterView));
		HarryPotterView.getSpellsT3().add(Spell1);
		HarryPotterView.getSpellsT3().add(Spell2);
		HarryPotterView.getSpellsT3().add(Spell3);
		HarryPotterView.getSpellsT3().add(Trait);
		String text="<html><html>";
		if(ThirdTask.getCurrentChamp() instanceof GryffindorWizard){
			text+= "<br>Special Trait: can do two moves instead of one this turn<br>";
			text+= "Cooldown:"+((GryffindorWizard) ThirdTask.getCurrentChamp()).getTraitCooldown();
		}
		if(ThirdTask.getCurrentChamp() instanceof RavenclawWizard){
			text+="<br>Special Trait: gets a hint of where the cup is hidden <br>";
			text+= "Cooldown:"+((RavenclawWizard) ThirdTask.getCurrentChamp()).getTraitCooldown();
		}
		if(ThirdTask.getCurrentChamp() instanceof SlytherinWizard){
			text+="<br>Special Trait: can teleport himself 2 cells <br>";
			text+= "Cooldown:"+((SlytherinWizard) ThirdTask.getCurrentChamp()).getTraitCooldown();
		}
		if(ThirdTask.getCurrentChamp() instanceof HufflepuffWizard){
			text+="<br>Special Trait:Attacks from other Champions will only deal half *ALWAYS ACTIVATD* <br>";
			text+= "Cooldown:"+((HufflepuffWizard) ThirdTask.getCurrentChamp()).getTraitCooldown();
		}
		Trait.setToolTipText(text);
		Trait.setPreferredSize(new Dimension(100, 100));
		useTraitListenerT3  useTraitListener= new  useTraitListenerT3(ThirdTask , Tournament , HarryPotterView);
		Trait.addActionListener(useTraitListener);
				for (int i = 0; i<ThirdTask.getMap().length; i++) {
					for (int j = 0; j<ThirdTask.getMap().length ; j++) {
						//JOptionPane.showMessageDialog(null, FirstTask.getMarkedCells());
						//JButton btnmap = new JButton();
						JLabel btnmap = new JLabel();
						Point z= new Point(i,j);
						if(ThirdTask.getMap()[i][j] instanceof ChampionCell ){
						if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp() instanceof GryffindorWizard){
							if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()==ThirdTask.getCurrentChamp()){
							ImageIcon image = new ImageIcon("currgryfft3.jpg");
							btnmap.setIcon(image);
							String st ="<html><html>";
							st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
							st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
							st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
							st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
							btnmap.setToolTipText(st);
							}
							else{
								ImageIcon image = new ImageIcon("normgryfft3.jpg");
								btnmap.setIcon(image);
								String st ="<html><html>";
								st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
								st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
								st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
								st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
								btnmap.setToolTipText(st);
																	}
						}
							if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp() instanceof HufflepuffWizard){
								if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()==ThirdTask.getCurrentChamp()){
									ImageIcon image = new ImageIcon("currhufflet3.jpg");
									btnmap.setIcon(image);
									String st ="<html><html>";
									st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
									st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
									st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
									st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
									btnmap.setToolTipText(st);
									}
									else{
										ImageIcon image = new ImageIcon("normhufflet3.jpg");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
									}
							}
								if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp() instanceof SlytherinWizard){
									if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()==ThirdTask.getCurrentChamp()){
										ImageIcon image = new ImageIcon("currslytherint3.jpg");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
										}
										else{
											ImageIcon image = new ImageIcon("normslytherint3.jpg");
											btnmap.setIcon(image);
											String st ="<html><html>";
											st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
											st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
											st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
											st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
											btnmap.setToolTipText(st);
										}
								}
									if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp() instanceof RavenclawWizard){
										if(((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()==ThirdTask.getCurrentChamp()){
											ImageIcon image = new ImageIcon("currravent3.jpg");
											btnmap.setIcon(image);
											String st ="<html><html>";
											st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
											st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
											st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
											st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
											btnmap.setToolTipText(st);
											}
											else{
												ImageIcon image = new ImageIcon("normravent3.jpg");
												btnmap.setIcon(image);
												String st ="<html><html>";
												st+="<br>Name: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getName()+" <br>";
												st+="<br>Hp: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
												st+="<br>Ip: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
												st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) ThirdTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
												btnmap.setToolTipText(st);
											}
									}
						
						
						}
						
						 if ( ThirdTask.getMap()[i][j] instanceof EmptyCell){
							ImageIcon image = new ImageIcon("emptyt3.jpg");
							btnmap.setIcon(image);
						 }
						 if (ThirdTask.getMap()[i][j] instanceof CollectibleCell ){
							 ImageIcon image = new ImageIcon("emptyt3.jpg");
								btnmap.setIcon(image);
						 }
						 if(ThirdTask.getMap()[i][j] instanceof WallCell ){
							 ImageIcon image = new ImageIcon("wall.jpg");
								btnmap.setIcon(image);
						 }
						 
						 if(ThirdTask.getMap()[i][j] instanceof CupCell ){
							 ImageIcon image = new ImageIcon("emptyt3.jpg");
								btnmap.setIcon(image);
						 }
						 
						 if(ThirdTask.getMap()[i][j] instanceof ObstacleCell   ){
							 ImageIcon image = new ImageIcon("emptyt3 - Copy.jpg");
								btnmap.setIcon(image);
								String st ="<html><html>";
								st+="<br>Hp: "+( ((ObstacleCell) ThirdTask.getMap()[i][j]).getObstacle()).getHp()+"<br>";
								btnmap.setToolTipText(st);
						 }
				 btnmap.setPreferredSize(new Dimension(130,90));
				HarryPotterView.getThirdTaskView().add(btnmap);
				 HarryPotterView.getThirdTaskWholeView().add(HarryPotterView.getSpellsT3());
					HarryPotterView.getThirdTaskWholeView().add(HarryPotterView.getThirdTaskView());
					HarryPotterView.getThirdTaskWholeView().add(HarryPotterView.getStatsT3());
					HarryPotterView.getThirdTaskWholeView().add(HarryPotterView.getButtonsT3());
			}
		}
				
					HarryPotterView.setFocusable(true);
					HarryPotterView.setFocusTraversalKeysEnabled(false);
				HarryPotterView.setContentPane(HarryPotterView.getThirdTaskWholeView());
				HarryPotterView.validate();
				HarryPotterView.revalidate();
				HarryPotterView.repaint();
			}
	
	public MovesActionTask3(ThirdTask ThirdTask, Tournament Tournament,HarryPotterView HarryPotterView ){
		this.ThirdTask=ThirdTask;
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		
	}


	


	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			try {
					ThirdTask.moveForward();
					generatemapT3();
			} catch (OutOfBordersException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemapT3();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemapT3();
				e1.printStackTrace();
			} catch (IOException e1) {
				generatemapT3();
				e1.printStackTrace();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			try {
				ThirdTask.moveBackward();
				generatemapT3();
				 
			} catch (OutOfBordersException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemapT3();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemapT3();
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			try {
				ThirdTask.moveLeft();
				generatemapT3();
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				generatemapT3();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				generatemapT3();
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			try {
				ThirdTask.moveRight();
				generatemapT3();
			} catch (OutOfBordersException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemapT3();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemapT3();
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
