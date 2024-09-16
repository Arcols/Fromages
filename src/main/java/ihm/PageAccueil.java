package ihm;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import modele.Fromage;
import modele.Fromages;
import modele.GenerationFromages;
import modele.Panier;
import modele.TypeLait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class PageAccueil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<String> listModel = new DefaultListModel<>(); // Initialisation correcte du modèle de liste
    private JList<String> listeDesFromages = new JList<>(listModel); // Initialisation correcte de la JList avec le modèle

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PageAccueil frame = new PageAccueil();
                    frame.setSize(800,600);
                    frame.setLocationRelativeTo(frame.getParent());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PageAccueil() {
		DecimalFormat df = new DecimalFormat("#,##0.00#");
        Fromages fromages = GenerationFromages.générationBaseFromages(); // Tous les fromages de type Fromage
        LinkedList<Fromage> fromagesLinkedListe = (LinkedList<Fromage>) fromages.getFromages(); // Tous les fromages de type Fromage en linkedList
        Panier panier = new Panier();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        

        JScrollPane scrollPaneListeFromage = new JScrollPane();
        contentPane.add(scrollPaneListeFromage, BorderLayout.CENTER);
        scrollPaneListeFromage.setViewportView(listeDesFromages); // Ajout de la JList au JScrollPane

        JPanel panelTypeDesFromages = new JPanel();
        contentPane.add(panelTypeDesFromages, BorderLayout.SOUTH);

        JLabel labelTypesFromages = new JLabel("Types de fromages :");
        labelTypesFromages.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelTypesFromages.setHorizontalAlignment(SwingConstants.CENTER);
        panelTypeDesFromages.add(labelTypesFromages);

        // Ajouter les fromages au lait demandé dans la JList
        JComboBox<String> comboBoxLait = new JComboBox<String>();
        comboBoxLait.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomLait = (String) comboBoxLait.getSelectedItem(); 
                listModel.removeAllElements();
                if(TypeLait.getTypeLait(nomLait) != null) {
                	ajouterFromageAuLaitDe(fromages, nomLait);        
                } else {
                    Fromages fromagesCourants = GenerationFromages.générationBaseFromages();
                    LinkedList<Fromage> fromagesListeCourante = (LinkedList<Fromage>) fromagesCourants.getFromages();
                    afficherFromage(fromagesListeCourante, listModel);
                }
            }
        });
        comboBoxLait.setModel(new DefaultComboBoxModel<>(new String[] {"Tous les fromages", "Chèvre", "Brebis", "Vache"}));
        panelTypeDesFromages.add(comboBoxLait);
        
        JPanel panelEnTete = new JPanel();
        panelEnTete.setBorder(new EmptyBorder(4, 4, 4, 4));
        contentPane.add(panelEnTete, BorderLayout.NORTH);
        panelEnTete.setLayout(new BorderLayout(0, 0));
        
        JLabel labelPanier = new JLabel("Liste des fromages");
        labelPanier.setHorizontalAlignment(SwingConstants.CENTER);
        labelPanier.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JButton boutonPanier;
        panelEnTete.add(labelPanier, BorderLayout.CENTER);
        
        boutonPanier = new JButton("Panier Vide");
        
        boutonPanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(boutonPanier.getText().equals("Panier Vide")) {JOptionPane.showMessageDialog(contentPane,"Votre panier est vide");}
            	else{
            		FenetrePanier panierFrame = new FenetrePanier(panier);
            		panierFrame.setSize(700,600);
            		panierFrame.setVisible(true);
            	}
            		
            }
        });
        
        boutonPanier.setIcon(new ImageIcon("./src/main/resources/images/fromages/hauteur40/icon_panier.png"));
        boutonPanier.setHorizontalAlignment(SwingConstants.RIGHT);
        panelEnTete.add(boutonPanier, BorderLayout.EAST);

        listeDesFromages.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-clique pour ouvrir le détail
                    int index = listeDesFromages.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selectedFromageName = listModel.getElementAt(index);
                        Fromage selectedFromage = fromages.getFromage(selectedFromageName);
                        if (selectedFromage != null) {
                            FenetreDetailFromage detailFrame = new FenetreDetailFromage(selectedFromage,panier);
                            detailFrame.setSize(600,400);
                            detailFrame.setVisible(true);
                        }
                    }
                }
            }
        });
        
        contentPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		if(panier.prixAvantFraisDePort()!=0.0F){
        			boutonPanier.setText(String.valueOf(df.format(panier.prixAvantFraisDePort()))+" €"); 
        		}else {
        			boutonPanier.setText("Panier Vide"); 
        		}
        	}
        });

        // Initialisation de la page
        afficherFromage(fromagesLinkedListe, listModel);
    }

    // Remplir la JList des fromages voulus
    private static void afficherFromage(LinkedList<Fromage> listeFromages, DefaultListModel<String> listModel) {
        Set<String> uniqueStringsSet = new HashSet<>();
        for (Fromage f : listeFromages) {
            if (uniqueStringsSet.add(f.getDésignation())) {
                listModel.addElement(f.getDésignation());
            }
        }
    }
    private void ajouterFromageAuLaitDe(Fromages fromages, String nomLait) {
		TypeLait lait = TypeLait.getTypeLait(nomLait); // On initialise le type de fromage 
		Fromages fromagesCourants = new Fromages();  // Liste de tous les fromages
		fromagesCourants.addFromages(fromages.fromagesAuLaitDe(lait));
		LinkedList<Fromage> fromagesListeCourante = (LinkedList<Fromage>) fromagesCourants.getFromages();
		afficherFromage(fromagesListeCourante, listModel);
	}
}
