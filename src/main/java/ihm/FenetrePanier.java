package ihm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import modele.Article;
import modele.Panier;
import modele.QuantitéEtArticle;

import java.awt.GridLayout;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;

public class FenetrePanier extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 * @param  
	 */
	public FenetrePanier(Panier panier) {
		DecimalFormat df = new DecimalFormat("#,##0.00#");
		String strTransporteurCourant = panier.getTransporteur()[0][0];
		int indiceTransporteurCourant = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel labelCoutExpédition = new JLabel(String.valueOf(df.format(panier.prixFraisDePort(panier.prixAvantFraisDePort(), indiceTransporteurCourant))));
		labelCoutExpédition.setBackground(new Color(192, 192, 192));
		JLabel labelCoutTotal = new JLabel(String.valueOf(df.format(panier.prixAprèsFraisDePort(panier.prixAvantFraisDePort(),indiceTransporteurCourant))));
		labelCoutTotal.setForeground(new Color(128, 0, 128));

		JLabel labelCoutSousTotal = new JLabel(String.valueOf(df.format(panier.prixAvantFraisDePort())));
		
		JPanel panelEnTete = new JPanel();
		panelEnTete.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.add(panelEnTete, BorderLayout.NORTH);
		panelEnTete.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label labelPanier = new Label("Votre Panier");
		labelPanier.setFont(new Font("Dialog", Font.BOLD, 16));
		labelPanier.setAlignment(Label.CENTER);
		panelEnTete.add(labelPanier);
		
		JPanel panelPrixEtActions = new JPanel();
		contentPane.add(panelPrixEtActions, BorderLayout.SOUTH);
		panelPrixEtActions.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTransporteurEtPrix = new JPanel();
		panelTransporteurEtPrix.setBorder(new EmptyBorder(4, 4, 4, 4));
		panelPrixEtActions.add(panelTransporteurEtPrix, BorderLayout.CENTER);
		panelTransporteurEtPrix.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTransporteur = new JPanel();
		panelTransporteur.setBorder(new TitledBorder(new EmptyBorder(2, 2, 2, 2), "Transporteur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTransporteurEtPrix.add(panelTransporteur);
		panelTransporteur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelChoixTransporteurEtTexte = new JPanel();
		panelTransporteur.add(panelChoixTransporteurEtTexte);
		panelChoixTransporteurEtTexte.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel labelTextFP = new JLabel("Les frais de ports sont offerts à partir de 120€ d'achat !");
		panelChoixTransporteurEtTexte.add(labelTextFP);
		
		JPanel panelChoixTransporteur = new JPanel();
		panelChoixTransporteurEtTexte.add(panelChoixTransporteur);
		
		JLabel imageTransporteur = new JLabel("");
		imageTransporteur.setIcon(new ImageIcon("./src/main/resources/images/fromages/hauteur40/"+strTransporteurCourant+".png"));
		imageTransporteur.setHorizontalAlignment(SwingConstants.RIGHT);
		panelChoixTransporteur.add(imageTransporteur);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {panier.getTransporteur()[0][0], panier.getTransporteur()[1][0], panier.getTransporteur()[2][0]}));
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                affichagePrix(panier, df, labelCoutExpédition, labelCoutTotal, imageTransporteur, comboBox,labelCoutSousTotal);
            }
		});
		panelChoixTransporteur.add(comboBox);
		
		JPanel panelPrixTotal = new JPanel();
		panelPrixTotal.setBackground(new Color(192, 192, 192));
		panelTransporteurEtPrix.add(panelPrixTotal);
		panelPrixTotal.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTxtSousTotal = new JLabel("Sous-Total : ");
		lblTxtSousTotal.setBackground(new Color(255, 255, 255));
		lblTxtSousTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPrixTotal.add(lblTxtSousTotal);
		
		labelCoutSousTotal.setBackground(new Color(192, 192, 192));
		labelCoutSousTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPrixTotal.add(labelCoutSousTotal);
		
		JLabel lblTxtExpedition = new JLabel("Expédition :");
		lblTxtExpedition.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPrixTotal.add(lblTxtExpedition);
		
		labelCoutExpédition.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPrixTotal.add(labelCoutExpédition);
		
		JLabel lblTxtTotal = new JLabel("TOTAL : ");
		lblTxtTotal.setForeground(new Color(128, 0, 128));
		lblTxtTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTxtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPrixTotal.add(lblTxtTotal);
		
		labelCoutTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCoutTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panelPrixTotal.add(labelCoutTotal);
		
		JPanel panelActionsValidations = new JPanel();
		panelPrixEtActions.add(panelActionsValidations, BorderLayout.SOUTH);
		
		JButton btnAnnuler = new JButton("Vider mon panier");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panier.viderPanier();
				dispose();
			}
		});
		panelActionsValidations.add(btnAnnuler);
		
		JButton btnContinuer = new JButton("Continuer mes achats");
		btnContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelActionsValidations.add(btnContinuer);
		
		JButton btnValider = new JButton("Valider mes achats");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenetreCoordonne detailFrame = new FenetreCoordonne(panier, comboBox.getSelectedIndex());
                detailFrame.setSize(600,500);
                detailFrame.setVisible(true);
			}
		});
		panelActionsValidations.add(btnValider);
		
		String[] columnNames = {"Produit", "Prix", "Quantité", "Total"};

        Object[][] data =remplirDonnees(panier);
        
        DefaultTableModel modelTablePrix = new DefaultTableModel(data, columnNames);
        JTable tableFromages = new JTable(modelTablePrix) {
			public boolean isCellEditable(int row, int col) {
				if(col!=2) 
					return false;
				else 
					return true;
			}
		};
		TableCellEditor editor = tableFromages.getDefaultEditor(Object.class);
		
		editor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                int row = tableFromages.getSelectedRow();
                int col = tableFromages.getSelectedColumn();
                QuantitéEtArticle article = panier.getQuantitéEtArticle(row);
                int value = Integer.parseInt((String) tableFromages.getValueAt(row, col));
     
                if(value>article.getArticle().getQuantitéEnStock()+article.getQuantité()) { 
                	DefaultTableModel modelTablePrix = (DefaultTableModel)tableFromages.getModel();
                	modelTablePrix.setValueAt(article.getQuantité(), row, col);
                	JOptionPane.showMessageDialog(contentPane,"Vous avez inséré trop d'articles, vous pouvez en insérer au maximum : "+String.valueOf(article.getArticle().getQuantitéEnStock()+article.getQuantité()));
                }else if(value<0) {
                	DefaultTableModel modelTablePrix = (DefaultTableModel)tableFromages.getModel();
                	modelTablePrix.setValueAt(article.getQuantité(), row, col);
                	JOptionPane.showMessageDialog(contentPane,"Vous avez inséré un nombre négatif d'articles, vous pouvez en insérer au maximum : "+String.valueOf(article.getArticle().getQuantitéEnStock()+article.getQuantité()));
                }
                else {
                	article.getArticle().setQuantitéEnStock(article.getArticle().getQuantitéEnStock()+article.getQuantité()-value);
            		article.setQuantité(value);                	
            		affichagePrix(panier, df, labelCoutExpédition, labelCoutTotal, imageTransporteur, comboBox,labelCoutSousTotal);
            		DefaultTableModel modelTablePrix = (DefaultTableModel)tableFromages.getModel();
                	modelTablePrix.setValueAt(article.getArticle().getPrixTTC()*article.getQuantité(), row, col+1);
                }
            }

			@Override
			public void editingCanceled(ChangeEvent e) {
				int row = tableFromages.getSelectedRow();
                int col = tableFromages.getSelectedColumn();
                QuantitéEtArticle article = panier.getQuantitéEtArticle(row);
				tableFromages.setValueAt(row, col, article.getQuantité());
			}
        });
		tableFromages.setRowHeight(30);
		tableFromages.getColumnModel().getColumn(1).setMaxWidth(100);
		tableFromages.getColumnModel().getColumn(2).setMaxWidth(100);
		tableFromages.getColumnModel().getColumn(3).setMaxWidth(100);
        JScrollPane scrollPane = new JScrollPane(tableFromages);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}

	private Object[][] remplirDonnees(Panier panier) {
		List<QuantitéEtArticle> listeArticles = panier.getPanier();
        Object[][] donnees = new Object[listeArticles.size()][4];
        for (int i = 0; i < listeArticles.size(); i++) {
        	QuantitéEtArticle quantiteEtArticle = listeArticles.get(i);
        	Article article = quantiteEtArticle.getArticle();
        	
        	donnees[i][0] = article.getFromage().getDésignation();
        	if(article.getFromage().getArticles().size()!=1) {
        		donnees[i][0] += " -- "+article.getClé();
        	}
            donnees[i][1] = article.getPrixTTC();
            donnees[i][2] = quantiteEtArticle.getQuantité();
            donnees[i][3] = article.getPrixTTC()*quantiteEtArticle.getQuantité();
        }
        return donnees;
    }
	
	private void affichagePrix(Panier panier, DecimalFormat df, JLabel labelCoutExpédition,
			JLabel labelCoutTotal, JLabel imageTransporteur, JComboBox<String> comboBox, JLabel labelCoutSousTotal) {
		int indiceTransporteurCourant = (int) comboBox.getSelectedIndex(); 
		String strTransporteurCourant = panier.getTransporteur()[indiceTransporteurCourant][0];
        labelCoutExpédition.setText(String.valueOf(df.format(panier.prixFraisDePort(panier.prixAvantFraisDePort(),indiceTransporteurCourant))));
		labelCoutTotal.setText(String.valueOf(df.format(panier.prixAprèsFraisDePort(panier.prixAvantFraisDePort(),indiceTransporteurCourant))));
		labelCoutSousTotal.setText(String.valueOf(df.format(panier.prixAvantFraisDePort())));
	}
}