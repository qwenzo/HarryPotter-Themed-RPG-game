package harrypotter.controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import harrypotter.exceptions.fullException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.tournament.TournamentListener;
import harrypotter.view.HarryPotterView;

public class HarryPotterGUI implements TournamentListener,ActionListener{
	private Tournament Tournament;
	public Tournament getTournament() {
		return Tournament;
	}

	private HarryPotterView HarryPotterView;
	public HarryPotterView getHarryPotterView() {
		return HarryPotterView;
	}
	

	public void setHarryPotterView(HarryPotterView harryPotterView) {
		HarryPotterView = harryPotterView;
	}

	private ArrayList<JButton> btns;
	private ActionListener GUI1;
	//private harrypotter.view.HarryPotterViewSpells HarryPotterViewSpells;
	private ActionListener actionListener ;
	private MouseListener actionListener1 ;
	//private ActionListener  ActionListener;
	
	public HarryPotterGUI() throws IOException{
		Tournament = new Tournament();
		HarryPotterView = new HarryPotterView(Tournament);
		btns=new ArrayList<>();
		
		
		 //actionListener = new SomeActionListener(this.Tournament);
		
		JTextArea arear = new JTextArea();
		HarryPotterView.add(arear,BorderLayout.PAGE_START);
		Font font = new Font("Courier", Font.BOLD,12);
		arear.setText("Please Select a House");
		arear.setEditable(false);
		arear.setFont(font);
		arear.setSize(1280, 800);
		
		//InputStream test = new FileInputStream("Harry_Potter_and_the_Sorcerer_39_s_Stone_Soundtrac.wav");

		
		JButton btnWizard1 = new JButton();
		JButton btnWizard2 = new JButton();
		JButton btnWizard3 = new JButton();
		JButton btnWizard4 = new JButton();
		JPanel textPanel = new JPanel();
		btnWizard1.setSize(new Dimension(40, 40));
		//btnWizard1= new JButton("GryffindorWizard",imageForOne);
		//ImageIcon imageForOne = new ImageIcon(getClass().getResource("il_fullxfull.378436932_o2mc.jpg"));
		btnWizard1= new JButton("GryffindorWizard");
		ImageIcon image = new ImageIcon("il_fullxfull.378436932_o2mc.jpg");
		btnWizard1.setIcon(image);
		btnWizard1.setSize(new Dimension(40, 40));
		btnWizard1.addActionListener(this);
		btnWizard1.setPreferredSize(new Dimension(200, 200));
		btnWizard2.setText("SlytherinWizard");
		ImageIcon image1 = new ImageIcon("Slyth.jpg");
		btnWizard2.setIcon(image1);
		btnWizard2.setPreferredSize(new Dimension(200, 200));
		btnWizard2.addActionListener(this);
		btnWizard3.setText("HufflepuffWizard");
		ImageIcon image2 = new ImageIcon("Huffle.jpg");
		btnWizard3.setIcon(image2);
		btnWizard3.setPreferredSize(new Dimension(200, 200));
		btnWizard3.addActionListener(this);
		btnWizard4.setText("RavenclawWizard");
		ImageIcon image3 = new ImageIcon("Raven.png");
		btnWizard4.setIcon(image3);
		btnWizard4.setPreferredSize(new Dimension(200, 200));
		btnWizard4.addActionListener(this);
		HarryPotterView.addWizard(btnWizard1);
		HarryPotterView.addWizard(btnWizard2);
		HarryPotterView.addWizard(btnWizard3);
		HarryPotterView.addWizard(btnWizard4);
		 JTextArea field = new JTextArea();
		 btns.add(btnWizard4);
		 btns.add(btnWizard3);
		 btns.add(btnWizard2);
		 btns.add(btnWizard1);
		HarryPotterView.setVisible(true);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e){
		JPanel m = new JPanel();
		JButton btn1 = new JButton();
		JButton btnProduct = (JButton) e.getSource();
		int productIndex = btns.indexOf(btnProduct);
		if(btnProduct.getText() =="GryffindorWizard"){
			 JFrame frame = new JFrame();
			String path = JOptionPane.showInputDialog(frame,"Enter a name for the Champion");
			if(path==null){
				frame.dispose();
			}
			GryffindorWizard x = new GryffindorWizard(path);
			Tournament.getChampions().add(x);
			onChampUpdated(Tournament);
		}
		 if(btnProduct.getText() =="SlytherinWizard"){
			 String path = JOptionPane.showInputDialog("Enter a name for the Champion");
				SlytherinWizard x = new SlytherinWizard(path);
				Tournament.getChampions().add(x);
				onChampUpdated(Tournament);
			}
			
			 if(btnProduct.getText() =="HufflepuffWizard"){
				 String path = JOptionPane.showInputDialog("Enter a name for the Champion");
				HufflepuffWizard x = new HufflepuffWizard(path);
				Tournament.getChampions().add(x);
				onChampUpdated(Tournament);
			}
			
			 if(btnProduct.getText() =="RavenclawWizard"){
				 String path = JOptionPane.showInputDialog("Enter a name for the Champion");
				RavenclawWizard x = new RavenclawWizard(path);
				//HarryPotterView.remove(btnProduct);
				Tournament.getChampions().add(x);
				onChampUpdated(Tournament);
			
			}
			 if(Tournament.getChampions().size()>4){
				 JOptionPane.showMessageDialog(null, "YOU CAN'T ADD MORE THAN 4 CHAMPIONS");
				 Tournament.getChampions().remove( Tournament.getChampions().get(Tournament.getChampions().size()-1));
				}
			 if(Tournament.getChampions().size()==4){
				  HarryPotterView.setContentPane(HarryPotterView.getSpells1Container());
				HarryPotterView.validate();
				HarryPotterView.revalidate();
				HarryPotterView.repaint();
			 }
				}
	public void onChampUpdated(Tournament Tournament) {
		// create a list of products info
		ArrayList<String> products = new ArrayList<>();
		for (Champion product : Tournament.getChampions()) {
			//products.add(product.toString());
			products.add(((Wizard) product).getName().toString()+" "+((Wizard) product).getClass().getSimpleName());
			//products.add(((Wizard) product).getClass().getSimpleName());
		}
		// set the list along with the total to the supermarketView's updateCart method
		HarryPotterView.updateChampions(products);
	}
	/*public void onFinishing1(Tournament Tournament){
		 HarryPotterViewSpells= new harrypotter.view.HarryPotterViewSpells();
		 HarryPotterViewSpells.setListenerViewSpells(this);
	}*/
	
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

	
	public static void main(String[] args) throws IOException {
		new HarryPotterGUI();
	}
	


	

}


