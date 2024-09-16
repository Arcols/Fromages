package modele;

public class QuantitéEtArticle {
	
	private Article article;
	private int quantité;
	
	public QuantitéEtArticle(Article article, int quantité) {
		this.article = article;
		this.quantité = quantité;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQuantité() {
		return this.quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}
	
	

}