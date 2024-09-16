package modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPanier {

	private Panier unPanier;
	
	@Before
	public void setUp() throws Exception {
		this.unPanier = new Panier();
	}
	
	@After
	public void tearDown() throws Exception {
		this.unPanier = null;
	}
	
	@Test
	public void TestConstructeur() {
		assertEquals(this.unPanier.getNombreArticle(), 0);
	}
	
	@Test
	public void testGetPanier() {
		List<QuantitéEtArticle> panier = new LinkedList<QuantitéEtArticle>();
		assertEquals(this.unPanier.getPanier(),panier);
	}
	
	@Test
	public void testGetPanier2() {
		List<QuantitéEtArticle> panier = new LinkedList<QuantitéEtArticle>();
		this.unPanier.setPanier(panier);
		assertEquals(this.unPanier.getPanier(), panier);
	}
	
	@Test
	public void testGetTransporteur() {
		assertEquals(this.unPanier.getTransporteur()[0][0], "Colissimo");
		
	}
	
	@Test
	public void testAjouterPanier() {
		//on ajoute un article au panier
		//on teste que le nombre d'article dans la liste s'incrémente
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(this.unPanier.getNombreArticle(), 1);
	}
	
	
	@Test
	public void testGetQuantité() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(this.unPanier.getQuantitéEtArticle(0).getQuantité(),3);
	}
	
	@Test
	public void testAjouterAuPanierExistant() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		quantite.setQuantité(6);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(this.unPanier.getNombreArticle(),1);
	}
	
	@Test
	public void testAjouterAuPanierExistant2() {
		//on ajoute 2 fois le meme article avec 2 quantités différentes
		//on verifie que le nombre d'article s'est mis à jour
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		Article article1 = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite1 = new QuantitéEtArticle(article1, 4);
		this.unPanier.ajouterAuPanier(quantite1);
		assertEquals(this.unPanier.getQuantitéEtArticle(0).getQuantité(),7);
	}
	
	
	@Test
	public void testAjouterAuPanierfromage() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(this.unPanier.getQuantitéEtArticle(0).getArticle(), article);
	}
	
	
	@Test
	public void testException () {
		assertThrows(IllegalArgumentException.class, () -> {
            this.unPanier.getQuantitéEtArticle(1);
        });
	}
	
	@Test
	public void testException1 () {
		assertThrows(IllegalArgumentException.class, () -> {
            this.unPanier.getQuantitéEtArticle(-2);
        });
	}
	
	
	@Test
	public void testviderpanier() {
		//on ajoute un article dans un panier et on le vide
		//on verifie que le nombre d'article est bien 0
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		this.unPanier.viderPanier();
		assertEquals(this.unPanier.getNombreArticle(), 0);
	}
	

	@Test
	public void testPrixAvant() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(this.unPanier.prixAvantFraisDePort(), 6.0F,0);
	}
	
	@Test
	public void testPrixApres() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		float prix = this.unPanier.prixAvantFraisDePort();
		assertEquals(this.unPanier.prixAprèsFraisDePort(prix, 0), 20.90F,0);
	}
	
	public void testPrixApres1() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 75.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 1);
		this.unPanier.ajouterAuPanier(quantite);
		float prix = this.unPanier.prixAvantFraisDePort();
		assertEquals(this.unPanier.prixAprèsFraisDePort(prix, 1), 84.90F,0);
	}
	
	public void testPrixApres2() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 100.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 1);
		this.unPanier.ajouterAuPanier(quantite);
		float prix = this.unPanier.prixAvantFraisDePort();
		assertEquals(this.unPanier.prixAprèsFraisDePort(prix, 2), 109.90F,0);
	}
	
	@Test
	public void testPrixApres3() {
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 50.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		float prix = this.unPanier.prixAvantFraisDePort();
		assertEquals(this.unPanier.prixAprèsFraisDePort(prix, 0), 150.0F,0);
	}
	
	@Test
	public void testAjouterDifferent2() {
		//ajout de 2 articles différents
		//on teste qu'il y est 2 articles dans le panier
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		article = new Article(new Fromage("brie"), "A_LA_COUPE_AU_POIDS", 5.0F);
		quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(this.unPanier.getNombreArticle(), 2);
		
	}
	
	@Test
	public void testAjouterDifferent3() {
		//ajout de 3 articles dans le panier tous différents
		//on test qu'il y est 3 articles dans le panier
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		
		article = new Article(new Fromage("brie"), "A_LA_COUPE_AU_POIDS", 3.0F);
		quantite = new QuantitéEtArticle(article, 1);
		this.unPanier.ajouterAuPanier(quantite);
		
		article = new Article(new Fromage("emmental"), "A_LA_COUPE_AU_POIDS", 5.0F);
		quantite = new QuantitéEtArticle(article, 5);
		this.unPanier.ajouterAuPanier(quantite);
		
		assertEquals(3,this.unPanier.getNombreArticle());
		
	}
	
	@Test
	public void testAjouterPareil2() {
		//ajout de 3 articles dans le panier dont 2 qui sont identiques
		//on test que le nombre d'articles soit egale à 2
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		article.setQuantitéEnStock(50);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		article = new Article(new Fromage("brie"), "A_LA_COUPE_AU_POIDS", 3.0F);
		quantite = new QuantitéEtArticle(article, 1);
		this.unPanier.ajouterAuPanier(quantite);
		article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		quantite = new QuantitéEtArticle(article, 5);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(2,this.unPanier.getNombreArticle());
	}
	
	@Test
	public void testQuantitéEnStock() {
		//on ajoute un article dans le panier
		//on verifie que sa quantité en stock se met à jour
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		article.setQuantitéEnStock(50);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(47, quantite.getArticle().getQuantitéEnStock());
	}
	
	@Test
	public void testQuantitéEnStock2() {
		//on ajoute 2 articles pareil dans le panier
		//on verifie que sa quantité en stock se met à jour
		Article article = new Article(new Fromage("compte"), "A_LA_COUPE_AU_POIDS", 2.0F);
		article.setQuantitéEnStock(50);
		QuantitéEtArticle quantite = new QuantitéEtArticle(article, 3);
		this.unPanier.ajouterAuPanier(quantite);
		this.unPanier.ajouterAuPanier(quantite);
		assertEquals(44, quantite.getArticle().getQuantitéEnStock());
	}
	
	

}