package harrypotter.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Painter;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
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
import harrypotter.model.tournament.*;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.HarryPotterView;

public class MovesAction implements KeyListener{
	
	
	
	private SecondTask SecondTask;
	private FirstTask FirstTask;
	private Tournament Tournament;
	private HarryPotterView HarryPotterView;
	
	public void generatemapT2(){
		SecondTask SecondTask =Tournament.getSecondTask();
		
		HarryPotterView.removeKeyListener(HarryPotterView.getKeyListener());
		HarryPotterView.getTest().setVisible(false);
		HarryPotterView.remove(HarryPotterView.getTest());
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
			btn2.addActionListener(new InvlistenerT2(Tournament, HarryPotterView, SecondTask));
		HarryPotterView.getStatsT2().add(btn2);
		}
		HarryPotterView.getButtonsT2().add(turn,BorderLayout.WEST);
		 JReferencingButton<Spell> Spell1 = new JReferencingButton<Spell>();
		 JReferencingButton<Spell> Spell2 = new JReferencingButton<Spell>();
		 JReferencingButton<Spell> Spell3 = new JReferencingButton<Spell>();
		 JLabel winners = new JLabel("winners so far for this Task: "+FirstTask.getWinners());
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
					MovesActionTask2 MovesAction = new MovesActionTask2(SecondTask , Tournament , HarryPotterView);
					HarryPotterView.setKeyListener(MovesAction);
					HarryPotterView.addKeyListener(MovesAction);
				HarryPotterView.setContentPane(HarryPotterView.getSecondTaskWholeView());
				HarryPotterView.validate();
				HarryPotterView.revalidate();
				HarryPotterView.repaint();
			}



	
	public void generatemap(){
		
		HarryPotterView.getTest().removeAll();
		HarryPotterView.getButtons().removeAll();
		 HarryPotterView.getFirstTaskView().removeAll();
		 HarryPotterView.getStats().removeAll();
		 HarryPotterView.getSpellstest().removeAll();
			
		 JButton Trait = new JButton();
			FirstTask FirstTask = Tournament.getFirstTask();

			if(FirstTask.getCurrentChamp() instanceof GryffindorWizard){
				if(FirstTask.isTraitActivated()){
				 ImageIcon image = new ImageIcon("Kayle_W - Copy.png");
				 Trait.setIcon(image);
				}
				else{
					ImageIcon image = new ImageIcon("Kayle_W.png");
					((JButton) Trait).setIcon(image);
				}
			}
			if(FirstTask.getCurrentChamp() instanceof RavenclawWizard){
				if(FirstTask.isTraitActivated()){
				 ImageIcon image = new ImageIcon("Kalista_W.png");
				 Trait.setIcon(image);
				}else{
					ImageIcon image = new ImageIcon("Kalista_W - Copy.png");
					((JButton) Trait).setIcon(image);
					
				}
			}
			if(FirstTask.getCurrentChamp() instanceof SlytherinWizard){
				if(FirstTask.isTraitActivated()){
				 ImageIcon image = new ImageIcon("BCDaFSy - Copy.png");
				 Trait.setIcon(image);
				}
				else{
					ImageIcon image = new ImageIcon("BCDaFSy.png");
					((JButton) Trait).setIcon(image);
					
				}
			}
			if(FirstTask.getCurrentChamp() instanceof HufflepuffWizard){
				if(FirstTask.isTraitActivated()){
				 ImageIcon image = new ImageIcon("Annie_E - Copy.pngg");
				 Trait.setIcon(image);
				}else{
					ImageIcon image = new ImageIcon("Annie_E.png");
					((JButton) Trait).setIcon(image);
					
				}
			}
			JLabel turn = new JLabel("its "+((Wizard) FirstTask.getCurrentChamp()).getName()+ " Turn " +"with allowed moves "+FirstTask.getAllowedMoves() ,SwingConstants.RIGHT );
			HarryPotterView.getButtons().add(turn,BorderLayout.WEST);
			JProgressBar health = new JProgressBar(((Wizard) FirstTask.getCurrentChamp()).getHp(),((Wizard) FirstTask.getCurrentChamp()).getDefaultHp());
			if(((Wizard) FirstTask.getCurrentChamp()).getIp()<((Wizard) FirstTask.getCurrentChamp()).getDefaultIp() || ((Wizard) FirstTask.getCurrentChamp()).getIp()==((Wizard) FirstTask.getCurrentChamp()).getDefaultIp()){
				JProgressBar ip = new JProgressBar(((Wizard) FirstTask.getCurrentChamp()).getIp(),((Wizard) FirstTask.getCurrentChamp()).getDefaultIp());
				ip.setBackground(Color.BLUE);
				ip.setStringPainted(true);
				HarryPotterView.getButtons().add(ip);
				ip.setString("IP :"+((Wizard) FirstTask.getCurrentChamp()).getIp()+"/"+((Wizard) FirstTask.getCurrentChamp()).getDefaultIp()+"");
				}
				else if(((Wizard) FirstTask.getCurrentChamp()).getIp()>((Wizard) FirstTask.getCurrentChamp()).getDefaultIp()){
					((Wizard) FirstTask.getCurrentChamp()).setDefaultIp(((Wizard) FirstTask.getCurrentChamp()).getIp());
					JProgressBar ip = new JProgressBar(((Wizard) FirstTask.getCurrentChamp()).getIp(),((Wizard) FirstTask.getCurrentChamp()).getDefaultIp());
					ip.setString("IP :"+((Wizard) FirstTask.getCurrentChamp()).getIp()+"/"+((Wizard) FirstTask.getCurrentChamp()).getDefaultIp()+"");
					ip.setBackground(Color.BLUE);
					ip.setStringPainted(true);
					HarryPotterView.getButtons().add(ip);
				}
			UIManager.put("ProgressBar.foreground", Color.BLUE);
			health.setStringPainted(true);

			HarryPotterView.getButtons().add(health);
			health.setString("HP :"+((Wizard) FirstTask.getCurrentChamp()).getHp()+"/"+((Wizard) FirstTask.getCurrentChamp()).getDefaultHp()+"");
			health.setStringPainted(true);
			health.setBackground(Color.green);
			JTextArea inv1 = new JTextArea("INVENTORY");
			inv1.setEditable(false);
			HarryPotterView.getStats().add(inv1);
			if(((Wizard) FirstTask.getCurrentChamp()).getInventory().size()==0){
				JTextArea inv2=new JTextArea("EMPTY");
				HarryPotterView.getStats().add(inv2);
				inv2.setEditable(false);
			}
			for(Collectible x:((Wizard) FirstTask.getCurrentChamp()).getInventory()){
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
				btn2.addActionListener(new InvListener(Tournament, HarryPotterView, FirstTask));
			HarryPotterView.getStats().add(btn2);
			}
			HarryPotterView.getButtons().add(turn,BorderLayout.WEST);
			 JReferencingButton<Spell> Spell1 = new JReferencingButton<Spell>();
			 JReferencingButton<Spell> Spell2 = new JReferencingButton<Spell>();
			 JReferencingButton<Spell> Spell3 = new JReferencingButton<Spell>();
			 JLabel winners = new JLabel("winners so far for this Task: "+FirstTask.getWinners());
			 HarryPotterView.getButtons().add(winners,BorderLayout.WEST);
			 String x1 ="<html>1<html>";
			 x1+="<html>Name:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0).getName()+"<br><html>";
			  if(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0) instanceof DamagingSpell){
				  ImageIcon image = new ImageIcon("VelKoz_E.png");
					Spell1.setIcon(image);
				 x1+="Damage:"+((DamagingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0)).getDamageAmount()+"<br>";
			 }
			 else if (((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0) instanceof RelocatingSpell){
				 x1+="Range:"+((RelocatingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0)).getRange()+"<br>";
				 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
					Spell1.setIcon(image);
			 }
			 else if (((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0) instanceof HealingSpell){
				 ImageIcon image = new ImageIcon("Soraka_W.png");
				 Spell1.setIcon(image);
				 x1+="Healing:"+((HealingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0)).getHealingAmount()+"<br>";
			 }
			  x1+="Cost:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0).getCost()+"<br>";
			  x1+="CoolDown:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0).getCoolDown()+"/"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0).getDefaultCooldown()+"<br>";
			 Spell1.setToolTipText(x1);
			 String x2 ="<html>1<html>";
			 x2+="<html>Name:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1).getName()+"<br><html>";
			  if(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1) instanceof DamagingSpell){
				  ImageIcon image = new ImageIcon("VelKoz_E.png");
					Spell2.setIcon(image);
				  x2+="Damage:"+((DamagingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1)).getDamageAmount()+"<br>";
			 }
			 else if (((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1) instanceof RelocatingSpell){
				 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
				 x2+="Range:"+((RelocatingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1)).getRange()+"<br>";
				 Spell2.setIcon(image);
			 }
			 else if (((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1) instanceof HealingSpell){
				 ImageIcon image = new ImageIcon("Soraka_W.png");
				 Spell2.setIcon(image);
				 x2+="Healing:"+((HealingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1)).getHealingAmount()+"<br>";
			 }
			  x2+="Cost:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1).getCost()+"<br>";
			  x2+="CoolDown:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1).getCoolDown()+"/"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
			 Spell2.setToolTipText(x2);
			 String x3 ="<html>1<html>";
			 x3+="<html>Name:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2).getName()+"<br><html>";
			  if(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2) instanceof DamagingSpell){
				  ImageIcon image = new ImageIcon("VelKoz_E.png");
					Spell3.setIcon(image);
				  x3+="Damage:"+((DamagingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2)).getDamageAmount()+"<br>";
			 }
			 else if (((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2) instanceof RelocatingSpell){
				 ImageIcon image = new ImageIcon("Illaoi_E_Debuff.png");
				 x3+="Range:"+((RelocatingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2)).getRange()+"<br>";
				 Spell3.setIcon(image);
			 }
			 else if (((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2) instanceof HealingSpell){
				 ImageIcon image = new ImageIcon("Soraka_W.png");
				 Spell3.setIcon(image);
				 x3+="Healing:"+((HealingSpell) ((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2)).getHealingAmount()+"<br>";
			 }
			  x3+="Cost:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2).getCost()+"<br>";
			  x3+="CoolDown:"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2).getCoolDown()+"/"+((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1).getDefaultCooldown()+"<br>";
			 Spell3.setToolTipText(x3);
				Spell2.setText(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1).getName());
				Spell3.setText(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2).getName());
				Spell1.setValue(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(0));
				Spell2.setValue(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(1));
				Spell3.setValue(((Wizard) FirstTask.getCurrentChamp()).getSpells().get(2));
				Spell1.setPreferredSize(new Dimension(100, 100));
				Spell2.setPreferredSize(new Dimension(100, 100));
				Spell3.setPreferredSize(new Dimension(100, 100));
				Spell1.addActionListener(new useSpellsListener2(FirstTask,Tournament,HarryPotterView));
				Spell2.addActionListener(new useSpellsListener2(FirstTask,Tournament,HarryPotterView));
				Spell3.addActionListener(new useSpellsListener2(FirstTask,Tournament,HarryPotterView));
			HarryPotterView.getSpellstest().add(Spell1);
			HarryPotterView.getSpellstest().add(Spell2);
			HarryPotterView.getSpellstest().add(Spell3);
			HarryPotterView.getSpellstest().add(Trait);
			String text="<html><html>";
			if(FirstTask.getCurrentChamp() instanceof GryffindorWizard){
				text+= "<br>Special Trait: can do two moves instead of one this turn<br>";
				text+= "Cooldown:"+((GryffindorWizard) FirstTask.getCurrentChamp()).getTraitCooldown();
			}
			if(FirstTask.getCurrentChamp() instanceof RavenclawWizard){
				text+="<br>Special Trait: can see Dragen's marked cells before it's attack <br>";
				text+= "Cooldown:"+((RavenclawWizard) FirstTask.getCurrentChamp()).getTraitCooldown();
			}
			if(FirstTask.getCurrentChamp() instanceof SlytherinWizard){
				text+="<br>Special Trait: can teleport himself 2 cells <br>";
				text+= "Cooldown:"+((SlytherinWizard) FirstTask.getCurrentChamp()).getTraitCooldown();
			}
			if(FirstTask.getCurrentChamp() instanceof HufflepuffWizard){
				text+="<br>Special Trait: Dragon doesn't attack this Turn <br>";
				text+= "Cooldown:"+((HufflepuffWizard) FirstTask.getCurrentChamp()).getTraitCooldown();
			}
			Trait.setToolTipText(text);
			Trait.setPreferredSize(new Dimension(100, 100));
			useTraitListener  useTraitListener= new  useTraitListener(FirstTask , Tournament , HarryPotterView);
			Trait.addActionListener(useTraitListener);
			for (int i = 0; i<FirstTask.getMap().length; i++) {
				for (int j = 0; j<FirstTask.getMap().length ; j++) {
					//JOptionPane.showMessageDialog(null, FirstTask.getMarkedCells());
					//JButton btnmap = new JButton();
					JLabel btnmap = new JLabel();
					Point z= new Point(i,j);
					if(FirstTask.getMap()[i][j] instanceof ChampionCell ){
					if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof GryffindorWizard){
						if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
						ImageIcon image = new ImageIcon("normgryff - Copy.jpg");
						btnmap.setIcon(image);
						String st ="<html><html>";
						st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
						st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
						st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
						st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
						btnmap.setToolTipText(st);
						}
						else{
							ImageIcon image = new ImageIcon("normgryff.jpg");
							btnmap.setIcon(image);
							String st ="<html><html>";
							st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
							st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
							st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
							st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
							btnmap.setToolTipText(st);
																}
					}
						if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof HufflepuffWizard){
							if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
								ImageIcon image = new ImageIcon("currhuffle.jpg");
								btnmap.setIcon(image);
								String st ="<html><html>";
								st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
								st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
								st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
								st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
								btnmap.setToolTipText(st);
								}
								else{
									ImageIcon image = new ImageIcon("normhuffle.jpg");
									btnmap.setIcon(image);
									String st ="<html><html>";
									st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
									st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
									st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
									st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
									btnmap.setToolTipText(st);
								}
						}
							if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof SlytherinWizard){
								if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
									ImageIcon image = new ImageIcon("currslyth.jpg");
									btnmap.setIcon(image);
									String st ="<html><html>";
									st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
									st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
									st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
									st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
									btnmap.setToolTipText(st);
									}
									else{
										ImageIcon image = new ImageIcon("normslyth.jpg");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
									}
							}
								if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof RavenclawWizard){
									if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
										ImageIcon image = new ImageIcon("currraven.jpg");
										btnmap.setIcon(image);
										String st ="<html><html>";
										st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
										st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
										st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
										st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
										btnmap.setToolTipText(st);
										}
										else{
											ImageIcon image = new ImageIcon("normraven.jpg");
											btnmap.setIcon(image);
											String st ="<html><html>";
											st+="<br>Name: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getName()+" <br>";
											st+="<br>Hp: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getHp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultHp()+"<br> ";
											st+="<br>Ip: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getIp()+"/"+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getDefaultIp()+"<br> ";
											st+="<br>Trait CoolDown: "+((Wizard) ((ChampionCell) FirstTask.getMap()[i][j]).getChamp()).getTraitCooldown()+"<br> ";
											btnmap.setToolTipText(st);
										}
								}
					
						//btnmap.setPreferredSize(new Dimension(200, 200));
					
					}
					
					 if (i ==4 && j ==4){
						ImageIcon image = new ImageIcon("egg.jpg");
						btnmap.setIcon(image);
						
					}
					 if ( FirstTask.getMap()[i][j] instanceof EmptyCell){
						ImageIcon image = new ImageIcon("emptycell.jpg");
						btnmap.setIcon(image);
					}
					 if (i ==4 && j ==4){
							ImageIcon image = new ImageIcon("egg.jpg");
							btnmap.setIcon(image);
							
						}
					 if (FirstTask.getMap()[i][j] instanceof CollectibleCell ){
						 ImageIcon image = new ImageIcon("emptycell.jpg");
							btnmap.setIcon(image);
					 }
					 if(FirstTask.getMap()[i][j] instanceof WallCell ){
						 ImageIcon image = new ImageIcon("wall.png");
							btnmap.setIcon(image);
					 }
					 
					 if(FirstTask.getMap()[i][j] instanceof ObstacleCell && ((ObstacleCell) FirstTask.getMap()[i][j]).getObstacle() instanceof PhysicalObstacle  ){
						 ImageIcon image = new ImageIcon("obst norm .jpg");
							btnmap.setIcon(image);
							String st ="";
							st+="Hp: "+( ((ObstacleCell) FirstTask.getMap()[i][j]).getObstacle()).getHp()+"";
							btnmap.setToolTipText(st);
					 }
					/* if(FirstTask.getMarkedCells().contains(new Point(i,j))){
						 if(FirstTask.getMap()[i][j] instanceof ChampionCell ){
								if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof GryffindorWizard){
									if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
									ImageIcon image = new ImageIcon("currentgryff.jpg");
									btnmap.setIcon(image);
									}
									else{
										ImageIcon image = new ImageIcon("gryffonfire.png");
										btnmap.setIcon(image);
									}
								}
									if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof HufflepuffWizard){
										if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
											ImageIcon image = new ImageIcon("currenthuffleonfire.png");
											btnmap.setIcon(image);
											}
											else{
												ImageIcon image = new ImageIcon("huffleonfire.png");
												btnmap.setIcon(image);
											}
									}
										if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof SlytherinWizard){
											if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
												ImageIcon image = new ImageIcon("currentslythonfire.png");
												btnmap.setIcon(image);
												}
												else{
													ImageIcon image = new ImageIcon("slythonfire.png");
													btnmap.setIcon(image);
												}
										}
											if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp() instanceof RavenclawWizard){
												if(((ChampionCell) FirstTask.getMap()[i][j]).getChamp()==FirstTask.getCurrentChamp()){
													ImageIcon image = new ImageIcon("currentravenonfire.png");
													btnmap.setIcon(image);
													}
													else{
														ImageIcon image = new ImageIcon("ravenonfire.png");
														btnmap.setIcon(image);
													}
											}
						 }
											if(FirstTask.getMap()[i][j] instanceof EmptyCell ){
												 ImageIcon image = new ImageIcon("tileable_old_school_video_game_grass - Copy (2).jpg");
													btnmap.setIcon(image);
											 }
					 
					 }*/
			 btnmap.setPreferredSize(new Dimension(130,90));
			 MovesAction MovesAction = new MovesAction(FirstTask , Tournament , HarryPotterView);
			 //HarryPotterView.addKeyListener(MovesAction);
			HarryPotterView.getFirstTaskView().add(btnmap);
			//MovesAction MovesAction = new MovesAction(y , Tournament , HarryPotterView);
			 HarryPotterView.getTest().add(HarryPotterView.getSpellstest());
				HarryPotterView.getTest().add(HarryPotterView.getFirstTaskView());
				HarryPotterView.getTest().add(HarryPotterView.getStats());
				HarryPotterView.getTest().add(HarryPotterView.getButtons());
		}
	}
			if(!FirstTask.getWinners().isEmpty() && FirstTask.getChampions().isEmpty()){
				JOptionPane.showMessageDialog(null, "winners of the first task are"+FirstTask.getWinners());
				generatemapT2();
				HarryPotterView.validate();
				HarryPotterView.revalidate();
				HarryPotterView.repaint();
			}
			else if (FirstTask.getWinners().isEmpty() && FirstTask.getChampions().isEmpty()){
				HarryPotterView.remove(HarryPotterView.getTest());
				ImagePanel GAMEOVER= new ImagePanel(null);
				//GAMEOVER.setVisible(false);
				GAMEOVER.setImage("GAME OVER.jpg");
				HarryPotterView.setContentPane(GAMEOVER);
				HarryPotterView.validate();
				HarryPotterView.revalidate();
				HarryPotterView.repaint();
				
			}
			else{
			
				HarryPotterView.setFocusable(true);
				HarryPotterView.setFocusTraversalKeysEnabled(false);
			HarryPotterView.setContentPane(HarryPotterView.getTest());
			}
			
		}
	

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			try {
				FirstTask.moveForward();
				 generatemap();
			} catch (OutOfBordersException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				generatemap();
				e1.printStackTrace();
			} catch (IOException e1) {
				generatemap();
				e1.printStackTrace();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			try {
				FirstTask.moveBackward();
				generatemap();
				 
			} catch (OutOfBordersException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			try {
				FirstTask.moveLeft();
				 generatemap();
			} catch (OutOfBordersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			try {
				FirstTask.moveRight();
				generatemap();
			} catch (OutOfBordersException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (InvalidTargetCellException e1) {
				 JOptionPane.showMessageDialog(null, e1.getMessage());
				 generatemap();
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		}
		

	
	

	@Override
	public void keyReleased(KeyEvent e) {
		//int keyCode = e.getKeyCode();
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public MovesAction(FirstTask FirstTask, Tournament Tournament,HarryPotterView HarryPotterView ){
		this.FirstTask=FirstTask;
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		
	}
	
	public MovesAction(SecondTask SecondTask, Tournament Tournament,HarryPotterView HarryPotterView ){
		this.SecondTask=SecondTask;
		this.Tournament=Tournament;
		this.HarryPotterView=HarryPotterView;
		
	}



}
