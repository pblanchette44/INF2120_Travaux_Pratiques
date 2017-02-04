package tp3;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tda.Individu;
import util.Personne;

public class vueGenealogie {
	
	Gen<Personne> allPersonne;
	JFrame mainMenu;
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					vueGenealogie laVue = new vueGenealogie();
					laVue.mainMenu.setVisible(true);	
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public vueGenealogie()
	{
		allPersonne = new Gen<Personne>();		
		mainMenu = initFrame();
		mainMenu.setJMenuBar(initTopMenu(mainMenu));
		mainMenu.getContentPane().add(initDisplayList());
	}

	
	private JScrollPane initDisplayList() {
		
		
		JScrollPane displayPane = new JScrollPane();
		displayPane.setBounds(10, 10, 150, 350);
		
		JList<Individu> displayList = new JList<Individu>();
		displayList.setBounds(10, 10, 150, 350);
		displayPane.add(displayList);
		
		return displayPane;
	}

	//MAIN FRAME STUFF
	private JFrame initFrame() {
		
		JFrame mainMenu = new JFrame();
		mainMenu.setBounds(200,200,500,500);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.getContentPane().setLayout(null);
		return mainMenu;
	}
	
	
	
	//MENU BAR STUFF
	private JMenuBar initTopMenu(JFrame mainMenu) {
		JMenuBar menuBar = new JMenuBar();
		return initMenuElements(menuBar);
	}
	
	private JMenuBar initMenuElements(JMenuBar theMenuBar) {
		
		initGerer(theMenuBar);
		initExplorer(theMenuBar);
		initTester(theMenuBar);
		return theMenuBar;
	}

	private void initTester(JMenuBar theMenuBar) {
		JMenu Tester = new JMenu("Tester");
		Tester = initMenuTester(Tester);
		theMenuBar.add(Tester);
	}

	private void initExplorer(JMenuBar theMenuBar) {
		JMenu Explorer = new JMenu("Explorer");
		Explorer = initMenuExplorer(Explorer);
		theMenuBar.add(Explorer);
	}

	private void initGerer(JMenuBar theMenuBar) {
		JMenu Gerer = new JMenu("Gerer");
		Gerer = initMenuGerer(Gerer);
		theMenuBar.add(Gerer);
	}

	private JMenu initMenuTester(JMenu tester) {
		
		tester.add(new JMenuItem("Verifier"));	
		
		return tester;
	}

	private JMenu initMenuGerer(JMenu gerer) {
		
		gerer.add(new JMenuItem("Ajouter un Individu"));
		gerer.getItem(0).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			//open a dialog box
			
			 JDialog diag = new JDialog();
			 createAddDiagFrame(diag);
			 
			 JPanel contentPanel = new JPanel();
			 contentPanel.setBounds(0,0,250,250);
			 contentPanel.setLayout(null);
			 
			 JLabel labelNom = new JLabel();
			 labelNom.setBounds(5,5,150,30);
			 labelNom.setText("Nom de Famille");
			 contentPanel.add(labelNom);
			 
			 JTextField nom = new JTextField();
			 nom.setBounds(5, 35, 150, 30);
			 nom.setColumns(25);
			 contentPanel.add(nom);
			 
			 JLabel labelPrenom = new JLabel();
			 labelPrenom.setBounds(5,65,150,30);
			 labelPrenom.setText("Prénoms");
			 contentPanel.add(labelPrenom);
			 
			 JTextField prenoms = new JTextField();
			 prenoms.setBounds(5, 95, 150, 30);
			 prenoms.setColumns(25);
			 contentPanel.add(prenoms);
			 
			 JLabel labelDateNaissance = new JLabel();
			 labelDateNaissance.setBounds(5,125,150,30);
			 labelDateNaissance.setText("Date de Naissance");
			 contentPanel.add(labelDateNaissance);
			 
			 JTextField naissanceDate = new JTextField();
			 naissanceDate.setBounds(5, 155, 150, 30);
			 naissanceDate.setColumns(25);
			 contentPanel.add(naissanceDate);
			 
			 JButton entrer = initEnterButton(diag, nom, prenoms, naissanceDate);
			 JButton cancel = initCancelButton(diag);
			
			 contentPanel.add(cancel);
			 contentPanel.add(entrer);
			 
			 diag.add(contentPanel);		     
		     diag.setVisible(true);
			}

			private JButton initEnterButton(JDialog diag, JTextField nom, JTextField prenoms,
					JTextField naissanceDate) {
				JButton entrer = new JButton();
				 diag.getRootPane().setDefaultButton(entrer);
				 entrer.addActionListener(new ActionListener(){
				      public void actionPerformed(ActionEvent e){
				        if(!nom.getText().isEmpty() && !prenoms.getText().isEmpty())
				        {
				        	SimpleDateFormat theDate = new SimpleDateFormat(naissanceDate.getText());
				        	try {
								Date bob = theDate.parse(naissanceDate.getText());
								ArrayList<String> prenomList = new ArrayList<String>(Arrays.asList(prenoms.getText().split(" ")));
								Personne tempPersonne = new Personne(nom.getText(),prenomList,bob,0);
					        	allPersonne.ajout(tempPersonne);
					        	diag.dispose();
					        	
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
				        }
				      }
				    });
				 entrer.setBounds(5, 200, 100, 25);
				 entrer.setText("Ok");
				return entrer;
			}

			private JButton initCancelButton(JDialog diag) {
				JButton cancel = new JButton();
				 cancel.setBounds(125, 200,100, 25);
				 cancel.setText("annuler");
				 cancel.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						
						diag.dispose();
					}
				 });
				return cancel;
			}

			private void createAddDiagFrame(JDialog diag) {
				diag.setBounds(10,10,250,250);
				 diag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				 diag.setModal(false);
			}			
		});
		gerer.add(new JMenuItem("Ajouter un parent"));
		gerer.getItem(1).addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			
				
				
			}
		});
	    
		
		gerer.add(new JMenuItem("Afficher les individus"));
		
		gerer.getItem(2).addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
			
				JList<Individu> individuList = new JList<Individu>();
				
				mainMenu.add(individuList);
			}	
		});
		
		gerer.add(new JMenuItem("Quitter"));
		
		return gerer;
	}
	
	private JMenu initMenuExplorer(JMenu Explorer) {
		
		Explorer.add(new JMenuItem("Les enfants"));
		Explorer.add(new JMenuItem("Les parents"));
		Explorer.add(new JMenuItem("Les frères"));
		Explorer.add(new JMenuItem("Les petits enfants"));
		
		return Explorer;
	}
	

}
