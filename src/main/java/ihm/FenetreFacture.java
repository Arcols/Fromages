package ihm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Article;
import modele.Client;
import modele.Panier;
import modele.QuantitéEtArticle;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class FenetreFacture extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Client client;
	private int transporteur;
	private String stringFacture;

	

	/**
	 * Create the frame.
	 */
	public FenetreFacture(Client client,int transporteur) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        
        this.client = client;
        this.transporteur = transporteur;
        stringFacture="";
        // Récupérer la date et l'heure actuelles
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Définir le format de date et heure désiré
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy à HH:mm:ss", Locale.FRENCH );

        // Formater la date et l'heure
        String formattedDateTime = currentDateTime.format(formatter);

        // Afficher la date et l'heure
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_centre = new JPanel();
        contentPane.add(panel_centre, BorderLayout.CENTER);
        panel_centre.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel_1 = new JLabel("Merci de votre visite !");
        lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
        lblNewLabel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 128, 0)));
        panel_centre.add(lblNewLabel_1, BorderLayout.NORTH);
        
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html"); // Pour permettre le formatage HTML

        // Récupération des informations du client
        String prenom = this.client.getPrenom();
        String nom = this.client.getNom().toUpperCase();
        String adresse = this.client.getAdresse1();
        String codePostal = this.client.getCP();
        String ville = this.client.getVille();
        String telephone = this.client.getTel();
        String mail = this.client.getMail();
        String totalTTC = String.format("%.2f", this.client.getPanier().prixAvantFraisDePort());
        String moyenPaiement = this.client.getMoyenDePaiement();
        String fraisTransport = String.format("%.2f", this.client.getPanier().prixFraisDePort(this.client.getPanier().prixAvantFraisDePort(), transporteur));
        String nomTransporteur = NomTransporteur(transporteur);
        String totalAvecFrais = String.format("%.2f", this.client.getPanier().prixAprèsFraisDePort(this.client.getPanier().prixAvantFraisDePort(), transporteur));


        // Formatage du texte avec HTML pour inclure des styles
        stringFacture = 
        		         " <span style='color: orange; font-size: 14px;'> <b> Fromagerie BlancJus pour vous servir et resservir en fromages </b> </span> <br> <br>"+
                         "Commande du "+formattedDateTime + " heure d'été d'Europe centrale <br>" + "<br>"+
                         "<strong>" + prenom + " " + nom + "</strong> <br>" +
                         "Adresse : "+ adresse + " " + codePostal + " " + ville + "<br>" + 
                         "Téléphone : "+ telephone + "<br>" + 
                         "Mail : "+ mail + "<br> <br> " +
                         remplirDonnees(this.client.getPanier())+
                         "TOTAL TTC COMMANDE : &emsp &nbsp " + totalTTC + " € par " + moyenPaiement + "<br>" + 
                         "FRAIS DE TRANSPORT : &emsp &ensp &nbsp " + fraisTransport + " € par " + nomTransporteur + "<br>" + 
                         "<strong>TOTAL TTC : &emsp &emsp &emsp &emsp &emsp &ensp &nbsp " + totalAvecFrais + " € </strong> <br>";
        
        JLabel lblNewLabel = new JLabel("Facture de la commande");
        lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
        
        JPanel panel_bas = new JPanel();
        contentPane.add(panel_bas, BorderLayout.SOUTH);
        panel_bas.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setHgap(10);
        panel_bas.add(panel, BorderLayout.EAST);
        
        textPane.setText(stringFacture);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setViewportBorder(new EmptyBorder(10, 15, 10, 15));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel_centre.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnNewButton = new JButton("Imprimer");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					textPane.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        	
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Quitter");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		for (Window window : Window.getWindows()) {
        			if(window instanceof JFrame) {
        				window.dispose();
        			}
        		}
        	}
        });
        		
        panel.add(btnNewButton_1);
	}
	
	private String NomTransporteur(int tran) {
		String[] trans = {"Colissimo","Chronorelais","Chronofresh"};
		return trans[tran];
	}

	
	private String remplirDonnees(Panier panier) {
		List<QuantitéEtArticle> listeArticles = panier.getPanier();
		String stringArticles ="<br> <div style='font-family: Lucida Console, Monaco, monospace;font-size: 10px;' > <strong> &emsp &emsp Produit &emsp &emsp / Prix unitaire / Quantité / Prix TTC</strong> </div> <div style='font-family: Lucida Console, Monaco, monospace;font-size: 8px;' > <br>";

		for (int i = 0; i < listeArticles.size(); i++) {
        	QuantitéEtArticle quantiteEtArticle = listeArticles.get(i);
        	Article article = quantiteEtArticle.getArticle();
        	stringArticles+=designationarticle(article);
        	stringArticles+=PrixUnitaire(quantiteEtArticle);
        	stringArticles+=quantitéecart(quantiteEtArticle);
        	stringArticles+=Prixtotal(quantiteEtArticle);
        }
        return stringArticles+" </div> <br> <br> <br> <br> <br> <br>";
    }
	
	private String designationarticle(Article article) {
		String res = "";
		res = article.getFromage().getDésignation();
		if(article.getFromage().getArticles().size()!=1) {
    		res+=" -- "+article.getClé()+" ";
    	}
    	else {
    		res+=" -- à l'unité ";
    	}
		int l = res.length();
		if(l > 35 ) {
			res += " <br>";
			l = 0;
		}
		for (int i = l; 35 > i; i++) {
			res += "&nbsp;";
		}
		return res;
	}
	
	private String PrixUnitaire(QuantitéEtArticle quantiteEtArticle) {
		String res = "";
		if (quantiteEtArticle.getArticle().getPrixTTC() > 10.0F) {
			res += String.format("%.2f",quantiteEtArticle.getArticle().getPrixTTC())+" € &nbsp &emsp &emsp &emsp " ;
		}
		else { res += String.format("%.2f",quantiteEtArticle.getArticle().getPrixTTC())+" € &emsp &emsp &emsp &emsp " ;}
		return res;
	}
	
	private String Prixtotal(QuantitéEtArticle quantiteEtArticle) {
		String res = "";
		if (quantiteEtArticle.getArticle().getPrixTTC()*quantiteEtArticle.getQuantité() < 10.0F) {
			res += String.format("%.2f",quantiteEtArticle.getArticle().getPrixTTC()*quantiteEtArticle.getQuantité())+" € &nbsp &nbsp <br>";
		}
		else if (quantiteEtArticle.getArticle().getPrixTTC()*quantiteEtArticle.getQuantité() < 100.0F) {
			res += String.format("%.2f",quantiteEtArticle.getArticle().getPrixTTC()*quantiteEtArticle.getQuantité())+" €  &nbsp  <br>";
		}
		else {
			res +=String.format("%.2f",quantiteEtArticle.getArticle().getPrixTTC()*quantiteEtArticle.getQuantité())+" €  <br>";
		}
		return res;
		
	}
	
	private String quantitéecart(QuantitéEtArticle quantiteEtArticle) {
		String res = "";
		if (quantiteEtArticle.getQuantité() < 10) {
			res += quantiteEtArticle.getQuantité()+" &emsp &emsp &emsp &emsp ";
		}
		else {
			res +=quantiteEtArticle.getQuantité()+" &emsp &emsp &emsp &emsp ";
		}
		return res;
			
			
	}
	
	
}