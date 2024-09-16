package modele;

public class Client {
	private String nom;
	private String prenom;
	private String tel;
	private String mail;
	private String adresse1;
	private String adresse2;
	private String CP;
	private String Ville;
	private String Moyen_de_Paiement;
	private boolean abonnement;
	private Panier panier;
	
	public Client() {
		this.Moyen_de_Paiement = "carte de crÃ©dit";
		this.abonnement = true;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}
	
	public String getVille() {
		return Ville;
	}
	
	public void setVille(String ville) {
		Ville = ville;
	}

	public boolean isAbonnement() {
		return abonnement;
	}

	public void setAbonnement(boolean abonnement) {
		this.abonnement = abonnement;
	}

	public String getMoyenDePaiement() {
		return Moyen_de_Paiement;
	}
	
	public void setMoyenDePaiement(String moyen) {
		this.Moyen_de_Paiement = moyen;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	
	
	
	
}