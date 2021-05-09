package projet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="REDACTEUR")
public class Redacteur extends Utilisateur {
	
	public Redacteur() {}
	public Redacteur(String pseudo, String password, String nom, String prenom) {
		this.setPseudo(pseudo);
		this.setPassword(password);
		this.setNom(nom);
		this.setPrenom(prenom);
	}

	@OneToMany (mappedBy="redacteur", cascade=CascadeType.PERSIST)
	private List<Article> articles = new ArrayList<Article>();

	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		articles.add(article);
	}
	
	public void removeArticle(Article article) {
		articles.remove(article);
	}
}
