package projet.service;

import java.util.List;

import projet.model.Article;
import projet.model.Redacteur;

public interface IArticle {

	Article enregistrer(Article article);

	List<Article> getArticlesByRedacteur(Redacteur redacteur);

	List<Article> getAll();

	void supprimeParId(Integer id);

	Article getArticleById(Integer idArt);

}
