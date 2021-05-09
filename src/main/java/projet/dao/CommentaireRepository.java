package projet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Article;
import projet.model.Commentaire;
import projet.model.Utilisateur;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

	List<Commentaire> findAllByArticle(Article article);

	List<Commentaire> findAllByUtilisateurOrderByDate(Utilisateur utilisateur);

}
