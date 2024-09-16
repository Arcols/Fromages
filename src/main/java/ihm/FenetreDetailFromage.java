package ihm;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modele.Article;
import modele.Fromage;
import modele.Panier;
import modele.QuantitéEtArticle;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;


public class FenetreDetailFromage extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JComboBox<String> comboBoxListeArticles;
    private JSpinner spinnerQuantite;
    private Article articleCourant;
    private JLabel fromageEnStock;

    
	public FenetreDetailFromage(Fromage fromage,Panier panier) {
		// Initialisation de l'article courant
		articleCourant = fromage.getArticles().get(0);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel nomDuFromage = new JLabel(fromage.getDésignation());
		nomDuFromage.setForeground(new Color(0, 0, 0));
		nomDuFromage.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		nomDuFromage.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0)));
		nomDuFromage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(nomDuFromage, BorderLayout.NORTH);
		
		JPanel panelDescrpitonEtImage = new JPanel();
		contentPane.add(panelDescrpitonEtImage);
		panelDescrpitonEtImage.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDescrpitonEtImage.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel ImageFromage = new JLabel();
		ImageFromage.setIcon(new ImageIcon("./src/main/resources/images/fromages/hauteur200/"+fromage.getNomImage()+".jpg"));
		ImageFromage.setHorizontalAlignment(SwingConstants.CENTER);
		panelDescrpitonEtImage.add(ImageFromage);
		JPanel panelDescrption = new JPanel();
		panelDescrption.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDescrpitonEtImage.add(panelDescrption);
		panelDescrption.setLayout(new BorderLayout(0, 0));
		
        JTextArea textArea = new JTextArea();
        textArea.setText(fromage.getDescription());
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);  // Pour rendre le texte non éditable
        textArea.setCaretPosition(0); // Pour faire commencer le scroll en haut
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescrption.add(scrollPane, BorderLayout.CENTER);
		
		JLabel labelDesc = new JLabel("Déscription");
		panelDescrption.add(labelDesc, BorderLayout.NORTH);
		
		JPanel panelArticleEtNbArticle = new JPanel();
		contentPane.add(panelArticleEtNbArticle, BorderLayout.SOUTH);
		panelArticleEtNbArticle.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelSelection = new JPanel();
		panelArticleEtNbArticle.add(panelSelection);
		
		JLabel listeArticles = new JLabel("Selectionnez l'Article");
		panelSelection.add(listeArticles);
		
		
		comboBoxListeArticles = new JComboBox<>();
        comboBoxListeArticles.setModel(remplireListeArticle(fromage));
        panelSelection.add(comboBoxListeArticles);
        
        comboBoxListeArticles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	articleCourant = fromage.getArticles().get(comboBoxListeArticles.getSelectedIndex());
                updateSpinnerModel(fromage,panier);
                updatenombreenstock();
            }
        });
        
        // Utiliser la variable d'instance spinnerQuantite
        spinnerQuantite = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1));
        updateSpinnerModel(fromage,panier);

		JPanel panelNbArticles = new JPanel();
		panelArticleEtNbArticle.add(panelNbArticles);
		JLabel nombreArticle = new JLabel("Selectionnez le nombre de fromage(s) que vous désirez");
		fromageEnStock = new JLabel("en stock : "+MAJquantiteenstock());
		panelNbArticles.add(nombreArticle);
		panelNbArticles.add(spinnerQuantite);
		panelNbArticles.add(fromageEnStock);
		
		JPanel panelConfirmation = new JPanel();
		panelArticleEtNbArticle.add(panelConfirmation);
		
		JButton btnAjouterAuPanier = new JButton("Ajouter au panier");
		btnAjouterAuPanier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuantitéEtArticle articleAjouté = new QuantitéEtArticle(articleCourant, (int) spinnerQuantite.getValue());
				panier.ajouterAuPanier(articleAjouté);
				updateSpinnerModel(fromage,panier);	
				dispose();
			}
		});
		panelConfirmation.add(btnAjouterAuPanier);
		
		JButton btnAnnulerCommande = new JButton("Annuler");
		btnAnnulerCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panelConfirmation.add(btnAnnulerCommande);
		updateSpinnerModel(fromage, panier);
	}
	
	private void updateSpinnerModel(Fromage fromage,Panier panier) {
        int selectedIndex = comboBoxListeArticles.getSelectedIndex();
        if (selectedIndex >= 0) {
            Article selectedArticle = fromage.getArticles().get(selectedIndex);

            int maxQuantity = selectedArticle.getQuantitéEnStock();
            
            
            if(maxQuantity == 0) {
            	spinnerQuantite.setModel(new SpinnerNumberModel(0, 0, 0, 1));
            } else {
            	spinnerQuantite.setModel(new SpinnerNumberModel(1, 1, maxQuantity, 1));
            }
        }
    }
	
	public DefaultComboBoxModel<String> remplireListeArticle(Fromage fromage) {
		List<String> listeArticles = new LinkedList<>();
		if (fromage.getArticles().size()==1){
			listeArticles.add("Prix TTC Unitaire : " + fromage.getArticles().get(0).getPrixTTC() + " €");
			return new DefaultComboBoxModel<>(listeArticles.toArray(new String[0])); 
		}else {
			for (int i = 0; i < fromage.getArticles().size(); i++) {
				Article article = fromage.getArticles().get(i);
				String articleString = article.getClé() + ", Prix TTC Unitaire : " + article.getPrixTTC() + " €";
				listeArticles.add(articleString);
			}
			return new DefaultComboBoxModel<>(listeArticles.toArray(new String[0]));
		}
	}
	private int MAJquantiteenstock() {
		return articleCourant.getQuantitéEnStock();
		
	}
	
	private void updatenombreenstock() {
		fromageEnStock.setText("en stock : "+MAJquantiteenstock());
	}

}
