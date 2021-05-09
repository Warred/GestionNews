package projet.service;

import java.util.List;

import javax.validation.Valid;

import projet.model.Article;
import projet.model.Commentaire;
import projet.model.Utilisateur;

public interface ICommentaire {

	List<Commentaire> chercherListeParArticle(Article article);

	Commentaire enregistrer(@Valid Commentaire commentaire);

	List<Commentaire> getCommentaireOwned(Utilisateur utilisateur);

	Commentaire getById(Integer id);

	void effacerById(Integer id);
}
