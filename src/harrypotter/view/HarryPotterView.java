package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import harrypotter.controller.ActionListenerSpells2;
import harrypotter.controller.ActionListenerSpells3;
import harrypotter.controller.ActionListenerSpells4;
import harrypotter.controller.ImagePanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import harrypotter.controller.JReferencingButton;
import harrypotter.controller.SomeActionListener;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.EmptyCell;
import java.awt.event.*;

public class HarryPotterView  extends JFrame implements KeyListener{
	private JPanel pnl;
	private JTextArea txt;
	private JPanel Spells;
	private JPanel test;
	private ActionListener SomeActionListener;
	private JPanel pnlSpells1;
	private JPanel pnlSpells2;
	private JPanel pnlSpells3;
	private ImagePanel pnlSpells4;
	private JPanel FirstTaskView;
	private ActionListenerSpells2 ActionListenerSpells2;
	private ActionListenerSpells3 ActionListenerSpells3;
	private ActionListenerSpells4 ActionListenerSpells4;
	private Tournament Tournament;
	private JTextArea info1;
	private JPanel Buttons;
	private ImagePanel Stats;
	private JLabel testlab;
	private JLabel testlab1;
	private JLabel testlab2;
	private JLabel testlab3;
	private ImagePanel Spellstest;
	private JPanel SecondTaskView;
	private JPanel SecondTaskWholeView;
	private JPanel ButtonsT2;
	private JPanel StatsT2;
	private JPanel SpellsT2;
	private JPanel ThirdTaskView;
	private JPanel ThirdTaskWholeView;
	private JPanel ButtonsT3;
	private JPanel StatsT3;
	private JPanel SpellsT3;
	private KeyListener KeyListener;
	private ImagePanel  Spells4Container;
	private ImagePanel  Spells3Container;
	private ImagePanel  Spells2Container;
	private ImagePanel  Spells1Container;
	private ImagePanel GAMEOVER;
	
	private JPanel Statstest1; 
	
	
	
	
	
	
	public JPanel getSpellstest() {
		return Spellstest;
	}

	public JTextArea getInfo1() {
		return info1;
	}

	public JPanel getTest() {
		return test;
	}

	public JPanel getPnl() {
		return pnl;
	}

	public HarryPotterView( Tournament Tournament) throws IOException{
		this.Tournament=Tournament;
		pnl = new JPanel();
		
		
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();

		setTitle("Harry Potter: The Return of the Triwizard Tournament");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		setBounds(50, 50, 1900, 1000);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setResizable(false);
		
		addKeyListener(new KeyAdapter() {
			 public void keyPressed(KeyEvent ke) {
			    if(ke.getKeyCode() == ke.VK_ESCAPE) {
			    	JFrame frame = new JFrame();
					 int reply = JOptionPane.showConfirmDialog(frame, "R U KIDDING ME?", "DO YOU WANT TO EXIT THIS AWESOME GAME ??", JOptionPane.YES_NO_OPTION);
				        if (reply == JOptionPane.YES_OPTION) {
				        	 System.exit(0);
				        }
				        else {
				           frame.dispose();
				        }

			      } 
			     } 
			});
		
		
		pnl.setLayout(new GridLayout(1, 5));
		
		add(pnl, BorderLayout.CENTER);
		
		txt = new JTextArea();
		txt.setPreferredSize(new Dimension(350, getHeight()));
		txt.setEditable(false);
		txt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		add(txt, BorderLayout.EAST);
		updateChampions(new ArrayList<String>());
		
		SomeActionListener = new SomeActionListener(Tournament,  this);
		ActionListenerSpells2 = new ActionListenerSpells2(Tournament , this);
		ActionListenerSpells3 = new ActionListenerSpells3(Tournament , this);
		ActionListenerSpells4 = new ActionListenerSpells4(Tournament , this);
		 pnlSpells1 = new JPanel();
		 pnlSpells2 = new JPanel();
		 pnlSpells3 = new JPanel();
		 pnlSpells4 = new ImagePanel("Blank_Canvas_on_Transparent_Background.png");
		 FirstTaskView = new JPanel();
		 Font font = new Font("Courier", Font.BOLD,12);
		 JLabel arear = new JLabel();
		 
			arear.setText("Please Select 3 spells for the 1st Champion");
			arear.setFont(arear.getFont().deriveFont(64f));
			arear.setSize(1280, 800);
			pnlSpells1.add(arear,BorderLayout.PAGE_START);
			pnlSpells1.setVisible(true);
			
			JLabel arear1 = new JLabel();
			arear1.setText("Please Select 3 spells for the 2nd Champion");
			//arear1.setEditable(false);
			arear1.setFont(arear1.getFont().deriveFont(64f));
			arear1.setSize(1280, 800);
			pnlSpells2.add(arear1,BorderLayout.PAGE_START);
			pnlSpells2.setVisible(true);
			
			JLabel arear11 = new JLabel();
			arear11.setText("Please Select 3 spells for the 3rd Champion");
			arear11.setFont(arear11.getFont().deriveFont(64f));
			arear11.setSize(1280, 800);
			pnlSpells3.add(arear11,BorderLayout.PAGE_START);
			pnlSpells3.setVisible(true);
			
			JLabel arear111 = new JLabel("Please select 3 spells for the 4th Champion");
			//arear111.setText("Please Select 3 spells for 4th");
			//arear111.setEditable(false);
			arear111.setFont(arear111.getFont().deriveFont(64f));
			//arear111.setFont(font);
			arear111.setSize(1280, 800);
			pnlSpells4.setVisible(true);
			Spells1Container = new ImagePanel(null);
			Spells1Container.add(arear,BorderLayout.NORTH);

		for(Spell x:Tournament.getSpells()){
			JReferencingButton btn2 = new JReferencingButton();
			btn2.setValue(x);
			if(x instanceof DamagingSpell){
				ImageIcon image1 = new ImageIcon("VelKoz_E.png");
				 String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Damage: "+((DamagingSpell) x).getDamageAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof RelocatingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Range: "+((RelocatingSpell) x).getRange()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Illaoi_E_Debuff.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof HealingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Healing Amount: "+((HealingSpell) x).getHealingAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Soraka_W.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			btn2.setText(x.getName()+" "+x.getClass().getSimpleName());
			btn2.setPreferredSize(new Dimension(100, 100));
			
			pnlSpells1.add(btn2);
			//btnProduct.setText(btn2.toString());
			btn2.addActionListener(SomeActionListener);
			
		}
		pnlSpells1.setPreferredSize(new Dimension(1280,900));
		//pnlSpells4.setLayout(new BorderLayout());
		Spells1Container.add(pnlSpells1,BorderLayout.CENTER);
		
		Tournament Tournament1 = new Tournament(this);
		
		Spells2Container = new ImagePanel(null);
		Spells2Container.add(arear1,BorderLayout.NORTH);
		
		for(Spell x:Tournament1.getSpells()){
			JReferencingButton btn2 = new JReferencingButton();
			btn2.setValue(x);
			
			btn2.setText(x.getName()+" "+x.getClass().getSimpleName());

			if(x instanceof DamagingSpell){
				ImageIcon image1 = new ImageIcon("VelKoz_E.png");
				 String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Damage: "+((DamagingSpell) x).getDamageAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof RelocatingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Range: "+((RelocatingSpell) x).getRange()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Illaoi_E_Debuff.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof HealingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Healing Amount: "+((HealingSpell) x).getHealingAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Soraka_W.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			btn2.setPreferredSize(new Dimension(100, 100));
			pnlSpells2.add(btn2);
			//btnProduct.setText(btn2.toString());
			btn2.addActionListener(ActionListenerSpells2 );
			
		}
		pnlSpells2.setPreferredSize(new Dimension(1280,900));
		//pnlSpells4.setLayout(new BorderLayout());
		Spells2Container.add(pnlSpells2,BorderLayout.CENTER);
		Tournament Tournament2 = new Tournament(this);
		
		Spells3Container = new ImagePanel(null);
		Spells3Container.add(arear11,BorderLayout.NORTH);
		
		for( Spell x:Tournament2.getSpells()){
			JReferencingButton btn2 = new JReferencingButton();
			btn2.setValue(x);
			btn2.setText(x.getName()+" "+x.getClass().getSimpleName());
			if(x instanceof DamagingSpell){
				ImageIcon image1 = new ImageIcon("VelKoz_E.png");
				 String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Damage: "+((DamagingSpell) x).getDamageAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof RelocatingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Range: "+((RelocatingSpell) x).getRange()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Illaoi_E_Debuff.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof HealingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Healing Amount: "+((HealingSpell) x).getHealingAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Soraka_W.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			btn2.setPreferredSize(new Dimension(100, 100));
			pnlSpells3.add(btn2);
			//btnProduct.setText(btn2.toString());
			btn2.addActionListener(ActionListenerSpells3);
			
		}
		pnlSpells3.setPreferredSize(new Dimension(1280,900));
		//pnlSpells4.setLayout(new BorderLayout());
		Spells3Container.add(pnlSpells3,BorderLayout.CENTER);
		Tournament Tournament3 = new Tournament(this);
		Spells4Container = new ImagePanel(null);
		Spells4Container.add(arear111,BorderLayout.NORTH);
		for(Spell x:Tournament3.getSpells()){
			JReferencingButton btn2 = new JReferencingButton();
			btn2.setValue(x);
			btn2.setText(x.getName().toString());
			if(x instanceof DamagingSpell){
				ImageIcon image1 = new ImageIcon("VelKoz_E.png");
				 String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Damage: "+((DamagingSpell) x).getDamageAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof RelocatingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Range: "+((RelocatingSpell) x).getRange()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Illaoi_E_Debuff.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			if(x instanceof HealingSpell){
				String x1 ="<html>*<html>";
				 x1+="Name: "+x.getName()+"<br>";
				 x1+="Healing Amount: "+((HealingSpell) x).getHealingAmount()+"<br>";
				 x1+="Cost : "+x.getCost()+"<br>";
				 x1+="CoolDown : "+( x).getDefaultCooldown()+"<br>";
				ImageIcon image1 = new ImageIcon("Soraka_W.png");
				btn2.setToolTipText(x1);
				btn2.setIcon(image1);
			}
			btn2.setPreferredSize(new Dimension(100, 100));
			//pnlSpells4.setLayout( new GridLayout(5,0));
			pnlSpells4.add(btn2,BorderLayout.CENTER);
			
			btn2.addActionListener(ActionListenerSpells4);
			
		}
		pnlSpells4.setPreferredSize(new Dimension(1280,900));
		//pnlSpells4.setLayout(new BorderLayout());
		Spells4Container.add(pnlSpells4,BorderLayout.CENTER);
		//pnlSpells4.setLayout(new BoxLayout(pnlSpells4, defaultCloseOperation));

		
		FirstTaskView.setPreferredSize(new Dimension(1500,900));
		FirstTaskView.setLayout(new GridLayout(10,10));
		 test = new JPanel();
		 test.setLayout( new BorderLayout());
		  Buttons = new JPanel();
		  Stats = new ImagePanel("518079-background-hd.jpg");
		  String txt1 = "<html>Hello World!<br>blahblahblah</html>";
		  Spellstest = new ImagePanel("760721.png");
		  
		 test.add(Stats,BorderLayout.EAST);
		 Stats.setPreferredSize(new Dimension(100,150));
		test.add(FirstTaskView,BorderLayout.CENTER);
		Spellstest.setPreferredSize(new Dimension(350,100));
		test.add(Spellstest,BorderLayout.SOUTH);
		test.add(Buttons, BorderLayout.PAGE_START);
		Statstest1 = new JPanel();
		//Statstest1.setLayout();
		Buttons.add(Statstest1, BorderLayout.WEST);
		//test.add(new JLabel("FIIIRST"),BorderLayout.EAST);
		
		
		SecondTaskView = new JPanel();
		SecondTaskView.setPreferredSize(new Dimension(1500,900));
		SecondTaskView.setLayout(new GridLayout(10,10));
		SecondTaskWholeView = new JPanel();

		
		SecondTaskWholeView.setLayout( new BorderLayout());
		 info1 = new JTextArea();
		   ButtonsT2 = new JPanel();
		    StatsT2 = new JPanel();
		    SpellsT2 = new JPanel();
		    StatsT2.setPreferredSize(new Dimension(100,150));
		  SecondTaskWholeView.add(StatsT2,BorderLayout.EAST);
		 SecondTaskWholeView.add(SecondTaskView,BorderLayout.CENTER);
		 SpellsT2.setPreferredSize(new Dimension(350,100));
		SecondTaskWholeView.add(SpellsT2,BorderLayout.SOUTH);
		SecondTaskWholeView.add(ButtonsT2, BorderLayout.PAGE_START);
		
		ThirdTaskView = new JPanel();
		ThirdTaskView.setPreferredSize(new Dimension(1500,900));
		ThirdTaskView.setLayout(new GridLayout(10,10));
		ThirdTaskWholeView = new JPanel();

		
		ThirdTaskWholeView.setLayout( new BorderLayout());
		   ButtonsT3 = new JPanel();
		    StatsT3 = new JPanel();
		    SpellsT3 = new JPanel();
		    StatsT3.setPreferredSize(new Dimension(100,150));
		  ThirdTaskWholeView.add(StatsT3,BorderLayout.EAST);
		 ThirdTaskWholeView.add(ThirdTaskView,BorderLayout.CENTER);
		 SpellsT2.setPreferredSize(new Dimension(350,100));
		ThirdTaskWholeView.add(SpellsT3,BorderLayout.SOUTH);
		ThirdTaskWholeView.add(ButtonsT3, BorderLayout.PAGE_START);
		
		
		//SecondTaskWholeView.setVisible(false);	
	}
	
	public JPanel getFirstTaskView() {
		return FirstTaskView;
	}

	public void updateChampions(ArrayList<String> Wizards) {
		String cart = "";
		cart += "Champions:*THE TYPE OF THE WIZARD UNDER IT'S NAME*\n";
		cart += "'''''\n";
		for (String product : Wizards) {          
			cart += "- " + product +"-" + "\n";
		}
		//cart += String.format("\nTotal: %21s", String.format("$%.2f", total));
		txt.setText(cart);
	}
	
	public void addWizard(JButton Wizard) {
		pnl.add(Wizard);
	}
	
	public void addWizard1(JPanel Wizard) {
		pnl.add(Wizard);
	}
	public JPanel getPnlSpells1() {
		return pnlSpells1;
	}

	public JPanel getPnlSpells2() {
		return pnlSpells2;
	}

	public JPanel getPnlSpells3() {
		return pnlSpells3;
	}

	public ImagePanel getPnlSpells4() {
		return pnlSpells4;
	}

	public Tournament getTournament() {
		return Tournament;
	}

	public void setTournament(Tournament tournament) {
		Tournament = tournament;
	}

	public JPanel getButtons() {
		return Buttons;
	}

	public void setButtons(JPanel buttons) {
		Buttons = buttons;
	}

	public JPanel getStats() {
		return Stats;
	}

	public void setStats(ImagePanel stats) {
		Stats = stats;
	}

	public JLabel getTestlab() {
		return testlab;
	}

	public JLabel getTestlab1() {
		return testlab1;
	}


	public JLabel getTestlab2() {
		return testlab2;
	}

	public void setTestlab2(JLabel testlab2) {
		this.testlab2 = testlab2;
	}

	public JLabel getTestlab3() {
		return testlab3;
	}

	public void setTestlab3(JLabel testlab3) {
		this.testlab3 = testlab3;
	}

	public JPanel getSecondTaskView() {
		return SecondTaskView;
	}

	public void setSecondTaskView(JPanel secondTaskView) {
		SecondTaskView = secondTaskView;
	}

	public JPanel getSecondTaskWholeView() {
		return SecondTaskWholeView;
	}

	public void setSecondTaskWholeView(JPanel secondTaskWholeView) {
		SecondTaskWholeView = secondTaskWholeView;
	}

	public JPanel getButtonsT2() {
		return ButtonsT2;
	}

	public void setButtonsT2(JPanel buttonsT2) {
		ButtonsT2 = buttonsT2;
	}

	public JPanel getStatsT2() {
		return StatsT2;
	}

	public void setStatsT2(JPanel statsT2) {
		StatsT2 = statsT2;
	}

	public JPanel getSpellsT2() {
		return SpellsT2;
	}

	public void setSpellsT2(JPanel spellsT2) {
		SpellsT2 = spellsT2;
	}

	public KeyListener getKeyListener() {
		return KeyListener;
	}

	public void setKeyListener(KeyListener keyListener) {
		KeyListener = keyListener;
	}

	public ImagePanel getSpells4Container() {
		return Spells4Container;
	}

	public void setSpells4Container(ImagePanel spells4Container) {
		Spells4Container = spells4Container;
	}

	public JPanel getStatstest1() {
		return Statstest1;
	}

	public void setStatstest1(JPanel statstest1) {
		Statstest1 = statstest1;
	}

	public ImagePanel getSpells3Container() {
		return Spells3Container;
	}

	public void setSpells3Container(ImagePanel spells3Container) {
		Spells3Container = spells3Container;
	}

	public ImagePanel getSpells2Container() {
		return Spells2Container;
	}

	public void setSpells2Container(ImagePanel spells2Container) {
		Spells2Container = spells2Container;
	}

	public ImagePanel getSpells1Container() {
		return Spells1Container;
	}

	public void setSpells1Container(ImagePanel spells1Container) {
		Spells1Container = spells1Container;
	}

	public ImagePanel getGAMEOVER() {
		return GAMEOVER;
	}

	public void setGAMEOVER(ImagePanel gAMEOVER) {
		GAMEOVER = gAMEOVER;
	}

	public JPanel getThirdTaskWholeView() {
		return ThirdTaskWholeView;
	}

	public void setThirdTaskWholeView(JPanel thirdTaskWholeView) {
		ThirdTaskWholeView = thirdTaskWholeView;
	}

	public JPanel getThirdTaskView() {
		return ThirdTaskView;
	}

	public void setThirdTaskView(JPanel thirdTaskView) {
		ThirdTaskView = thirdTaskView;
	}

	public JPanel getStatsT3() {
		return StatsT3;
	}

	public void setStatsT3(JPanel statsT3) {
		StatsT3 = statsT3;
	}

	public JPanel getSpellsT3() {
		return SpellsT3;
	}

	public void setSpellsT3(JPanel spellsT3) {
		SpellsT3 = spellsT3;
	}

	public JPanel getButtonsT3() {
		return ButtonsT3;
	}

	public void setButtonsT3(JPanel buttonsT3) {
		ButtonsT3 = buttonsT3;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() ==KeyEvent.VK_ESCAPE){
			JFrame frame = new JFrame();
			 int reply = JOptionPane.showConfirmDialog(frame, "R U KIDDING ME?", "DO YOU WANT TO EXIT THIS AWESOME GAME ??", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	 System.exit(0);
		        }
		        else {
		           frame.dispose();
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
