package ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Client;
import modele.Panier;

import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class FenetreCoordonne extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField textNom;
	private JTextField textTel;
	private JTextField textPrenom;
	private JTextField textAd1;
	private JTextField textAd2;
	private JTextField textCP;
	private JTextField textVille;
	private JTextField textMail;
	
	private JRadioButton rdbtnNewRadioButton_carte_fidelite;
	private JRadioButton rdbtnNewRadioButton_Paypal;
	private JRadioButton rdbtnNewRadioButton_cheque;
	private JRadioButton rdbtnNewRadioButton_O;
	private JRadioButton rdbtnNewRadioButton_N;
	
	private ButtonGroup Groupepaiement;
	private ButtonGroup GroupeAbo;
	
	private Client client;
	/**
	 * Launch the application.
	 */

	

	/**
	 * Create the frame.
	 */
	public FenetreCoordonne(Panier panier,int transporteur) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitreEtSaisie = new JPanel();
		contentPane.add(panelTitreEtSaisie, BorderLayout.NORTH);
		panelTitreEtSaisie.setLayout(new BorderLayout(0, 0));
		
		JPanel panelentete = new JPanel();
		FlowLayout fl_panelentete = (FlowLayout) panelentete.getLayout();
		panelentete.setMaximumSize(new Dimension(20, 20));
		panelTitreEtSaisie.add(panelentete, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Vos Coordonnées");
		lblNewLabel_2.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
		panelentete.add(lblNewLabel_2);
		
		JPanel panelsaisidonnee = new JPanel();
		panelTitreEtSaisie.add(panelsaisidonnee);
		panelsaisidonnee.setLayout(new GridLayout(8, 1, 0, 0));
		
		JPanel nom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) nom.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(nom);
		
		//crée les zones de saisies
		JLabel lb_nom = new JLabel("Nom :         ");
		nom.add(lb_nom);
		
		textNom = new JTextField();
		textNom.setColumns(35);
		nom.add(textNom);
		
		JPanel prenom = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) prenom.getLayout();
		flowLayout_2.setHgap(15);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(prenom);
		
		JLabel lb_prenom = new JLabel("Prénom :     ");
		prenom.add(lb_prenom);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(35);
		prenom.add(textPrenom);
		
		JPanel adresse1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) adresse1.getLayout();
		flowLayout_3.setHgap(15);
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(adresse1);
		
		JLabel lb_A1 = new JLabel("Adresse 1 :    ");
		adresse1.add(lb_A1);
		
		textAd1 = new JTextField();
		textAd1.setColumns(35);
		adresse1.add(textAd1);
		
		JPanel adresse2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) adresse2.getLayout();
		flowLayout_4.setHgap(15);
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(adresse2);
		
		JLabel lb_A2 = new JLabel("Adresse 2 :    ");
		adresse2.add(lb_A2);
		
		textAd2 = new JTextField();
		textAd2.setColumns(35);
		adresse2.add(textAd2);
		
		JPanel CP = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) CP.getLayout();
		flowLayout_5.setHgap(15);
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(CP);
		
		JLabel lb_CP = new JLabel("Code Postal : ");
		CP.add(lb_CP);
		
		textCP = new JTextField();
		textCP.setColumns(35);
		CP.add(textCP);
		
		JPanel ville = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) ville.getLayout();
		flowLayout_6.setHgap(15);
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(ville);
		
		JLabel lb_ville = new JLabel("Ville : ");
		ville.add(lb_ville);
		
		textVille = new JTextField();
		textVille.setColumns(35);
		ville.add(textVille);
		
		JPanel telephone = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) telephone.getLayout();
		flowLayout_7.setHgap(15);
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(telephone);
		
		JLabel lb_tel= new JLabel("Téléphone : ");
		telephone.add(lb_tel);
		
		textTel = new JTextField();
		textTel.setColumns(35);
		telephone.add(textTel);
		
		JPanel mail = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) mail.getLayout();
		flowLayout_8.setHgap(15);
		flowLayout_8.setAlignment(FlowLayout.RIGHT);
		panelsaisidonnee.add(mail);
		
		JLabel lb_mail = new JLabel("Mail : ");
		mail.add(lb_mail);
		
		textMail = new JTextField();
		textMail.setColumns(35);
		mail.add(textMail);
		
		JPanel panelBas = new JPanel();
		contentPane.add(panelBas);
		panelBas.setLayout(new GridLayout(3, 1, 0, 0));
		
		//crée les boutons radio pour le mode de paiement
		
		JPanel panelmoyenpaiement = new JPanel();
		panelmoyenpaiement.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 64), 2, true), "Moyen de paiement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 64)));
		panelBas.add(panelmoyenpaiement);
		
		rdbtnNewRadioButton_carte_fidelite = new JRadioButton("carte de crédit");
		rdbtnNewRadioButton_carte_fidelite.setSelected(true);
		panelmoyenpaiement.add(rdbtnNewRadioButton_carte_fidelite);
		
		rdbtnNewRadioButton_Paypal = new JRadioButton("Paypal");
		panelmoyenpaiement.add(rdbtnNewRadioButton_Paypal);
		
		rdbtnNewRadioButton_cheque = new JRadioButton("paiement par chèque");
		panelmoyenpaiement.add(rdbtnNewRadioButton_cheque);
		
		Groupepaiement = new ButtonGroup();
		Groupepaiement.add(rdbtnNewRadioButton_carte_fidelite);
		Groupepaiement.add(rdbtnNewRadioButton_Paypal);
		Groupepaiement.add(rdbtnNewRadioButton_cheque);
		
		
		//crée les boutons radio pour s'abonner a la newsletter
		JPanel panelabonnement = new JPanel();
		panelabonnement.setMaximumSize(new Dimension(30, 30));
		panelabonnement.setBorder(new TitledBorder(new LineBorder(new Color(255, 128, 64), 2), "Abonnement \u00E0 notre Newsletter", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 128, 64)));
		panelBas.add(panelabonnement);
		
		rdbtnNewRadioButton_O = new JRadioButton("Oui");
		rdbtnNewRadioButton_O.setSelected(true);
		panelabonnement.add(rdbtnNewRadioButton_O);
		
		rdbtnNewRadioButton_N = new JRadioButton("Non");
		panelabonnement.add(rdbtnNewRadioButton_N);
		
		GroupeAbo = new ButtonGroup();
		GroupeAbo.add(rdbtnNewRadioButton_O);
		GroupeAbo.add(rdbtnNewRadioButton_N);
		
		
		JPanel panelnextquitter = new JPanel();
		panelnextquitter.setBorder(new EmptyBorder(15, 10, 10, 15));
		panelBas.add(panelnextquitter);
		panelnextquitter.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("OK");
		panelnextquitter.add(btnNewButton, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		panelnextquitter.add(btnNewButton_1, BorderLayout.EAST);
		
		this.client = new Client();
		this.client.setPanier(panier);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				client = new Client();
				client.setNom(textNom.getText());
				client.setPrenom(textPrenom.getText());
				client.setAdresse1(textAd1.getText());
				client.setAdresse2(textAd2.getText());
				client.setCP(textCP.getText());
				client.setVille(textVille.getText());
				client.setMail(textMail.getText());
				client.setTel(textTel.getText());
				client.setMoyenDePaiement(MAJmoyendepaiement());
				client.setAbonnement(MAJabonnement());
				client.setPanier(panier);
				FenetreFacture framefacture = new FenetreFacture(client,transporteur);
				framefacture.setSize(600,500);
				framefacture.setVisible(true);

			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				dispose();
			}
		});
	}
	
	private String MAJmoyendepaiement() {
		if (rdbtnNewRadioButton_carte_fidelite.isSelected()) {
			return rdbtnNewRadioButton_carte_fidelite.getText();
		}
		else if (rdbtnNewRadioButton_Paypal.isSelected()) {
			return rdbtnNewRadioButton_Paypal.getText();
		}
		else {
			return rdbtnNewRadioButton_cheque.getText();
		}
	}
	
	private boolean MAJabonnement() {
		return rdbtnNewRadioButton_O.isSelected();
		
		
	}
}