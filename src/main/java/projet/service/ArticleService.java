package projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.ArticleRepository;
import projet.model.Article;
import projet.model.Redacteur;

@Service
public class ArticleService implements IArticle {
	@Autowired
	ArticleRepository articleDao;

	@Override
	public Article enregistrer(Article article) {		
		return articleDao.save(article);
	}

	@Override
	public List<Article> getArticlesByRedacteur(Redacteur redacteur) {
		return articleDao.findByRedacteur(redacteur);
	}

	@Override
	public List<Article> getAll() {
		return articleDao.findAll();
	}

	@Override
	public void supprimeParId(Integer id) {
		articleDao.deleteById(id);		
	}

	@Override
	public Article getArticleById(Integer idArt) {
		
		return articleDao.findById(idArt).get();
	}


}
