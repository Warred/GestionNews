package projet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Article;
import projet.model.Redacteur;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	List<Article> findByRedacteur(Redacteur redacteur);
	
	

}
