package projet.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.CommentaireRepository;
import projet.model.Article;
import projet.model.Commentaire;
import projet.model.Utilisateur;

@Service
public class CommentaireService implements ICommentaire {
	@Autowired
	CommentaireRepository commentaireDao;
	
	@Override
	public List<Commentaire> chercherListeParArticle(Article article) {
		return commentaireDao.findAllByArticle(article);
	}

	@Override
	public Commentaire enregistrer(@Valid Commentaire commentaire) {
		commentaire = commentaireDao.save(commentaire);
		return commentaire;
	}

	@Override
	public List<Commentaire> getCommentaireOwned(Utilisateur utilisateur) {
		return commentaireDao.findAllByUtilisateurOrderByDate(utilisateur);
	}

	@Override
	public Commentaire getById(Integer id) {		
		return commentaireDao.findById(id).get();
	}

	@Override
	public void effacerById(Integer id) {
		commentaireDao.deleteById(id);		
	}

}
