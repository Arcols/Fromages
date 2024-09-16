package modele;

import java.util.LinkedList;
import java.util.List;

public class Panier {
	
	private static final float PRIX_REDUCTION = 120.0F;
	private List<QuantitéEtArticle> panier;
	private int NombreArticle;
	private static final String [][] transporteur = {
			{"Colissimo","4.90","9.90","14.90"},
			{"Chronorelais","4.90","9.90","14.90"},
			{"Chronofresh","9.90","17.80","23.80"}
	};
	
	public Panier() {
		this.panier = new LinkedList<QuantitéEtArticle>();
		this.NombreArticle = 0;
	}
	
	public List<QuantitéEtArticle> getPanier() {
		return panier;
	}
	public void setPanier(List<QuantitéEtArticle> panier) {
		this.panier = panier;
	}
	public String[][] getTransporteur() {
		return transporteur;
	}
	
	public int getNombreArticle() {
		return this.NombreArticle;
	}
	
	public QuantitéEtArticle getQuantitéEtArticle(int index) throws IllegalArgumentException{
		if(index < 0 || index >= this.NombreArticle) {
			throw new IllegalArgumentException("index invalide");
		}
		return this.panier.get(index);
	}
	
	public void ajouterAuPanier(QuantitéEtArticle article) throws IllegalArgumentException{
		if(this.NombreArticle==0) {
			this.panier.add(article);
			this.NombreArticle ++;
			article.getArticle().setQuantitéEnStock(article.getArticle().getQuantitéEnStock()-article.getQuantité());
		}
		else {
			boolean trouve = false;
			int index = -1;
			for (int i = 0; i < this.NombreArticle; i++) {
				if(this.getQuantitéEtArticle(i).getArticle().equals(article.getArticle())) {
					trouve = true;
					index = i;
				}
			}
			if (trouve) {
				article.getArticle().setQuantitéEnStock(article.getArticle().getQuantitéEnStock()-article.getQuantité());
				this.getQuantitéEtArticle(index).setQuantité(this.getQuantitéEtArticle(index).getQuantité()+article.getQuantité());
				
			}
			else {
				this.panier.add(article);
				this.NombreArticle ++;
				article.getArticle().setQuantitéEnStock(article.getArticle().getQuantitéEnStock()-article.getQuantité());
			}		
		}
	}
		
	
	public void viderPanier() {
		for (QuantitéEtArticle a : this.panier) {
			a.getArticle().setQuantitéEnStock(a.getArticle().getQuantitéEnStock()+a.getQuantité());
		}
		this.panier.clear();
		this.NombreArticle = 0;
	}
	
	public float prixAvantFraisDePort() {
		float prix = 0.0F;
		for (QuantitéEtArticle a : this.panier) {
			prix += (float)a.getQuantité()*a.getArticle().getPrixTTC();
		}
		return prix;
	}
	
	public float prixAprèsFraisDePort(float prix, int i) {
		float prixTotal = prix;
		if (prix > PRIX_REDUCTION) {
		}
		else if (prix <= 120 && prix > 90) {
			prixTotal += Float.parseFloat(transporteur [i][1]);
		}
		else if (prix <= 90 && prix > 60) {
			prixTotal += Float.parseFloat(transporteur [i][2]);
		}
		else {
			prixTotal += Float.parseFloat(transporteur [i][3]);
		}
		return prixTotal;
	}
	
	public float prixFraisDePort(float prix,int i) {
		if (prix > PRIX_REDUCTION) {
			return 0.0F;
		}
		else if (prix <= 120 && prix > 90) {
			return Float.parseFloat(transporteur [i][1]);
		}
		else if (prix <= 90 && prix > 60) {
			return Float.parseFloat(transporteur [i][2]);
		}
		else {
			return Float.parseFloat(transporteur [i][3]);
		}
	}
	
	@Override
	public String toString() {
		String string = new String();
		for(int i=0;i<this.NombreArticle;i++) {
			QuantitéEtArticle articleCourant = this.getQuantitéEtArticle(i);
			string+=" Le panier contient  "+articleCourant.getQuantité()+" "+articleCourant.getArticle().toString()+" \n";
		}
		return string ;
	}
}