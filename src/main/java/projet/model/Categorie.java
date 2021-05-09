package projet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Veuillez saisir un nom de catégorie.")
	private String nom;
	
	@OneToMany(mappedBy="categorie", cascade=CascadeType.PERSIST)
	private List<Article> articles = new ArrayList<Article>();
	
	@OneToMany(mappedBy="categorie", cascade=CascadeType.PERSIST)
	private List<Abonnement> abonnements = new ArrayList<Abonnement>();

	public Categorie() {}
	public Categorie(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Abonnement> getAbonnements() {
		return abonnements;
	}

	public void setAbonnements(List<Abonnement> abonnements) {
		this.abonnements = abonnements;
	}

}
