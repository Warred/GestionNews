package projet.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import projet.model.Article;
import projet.model.Categorie;
import projet.model.Commentaire;
import projet.model.Redacteur;
import projet.model.Utilisateur;
import projet.service.IArticle;
import projet.service.ICategorie;
import projet.service.ICommentaire;

@Controller
public class ArticleController {

	@Autowired
	private ICategorie categorieServ;
	
	@Autowired
	private IArticle articleServ;
	
	@Autowired
	private ICommentaire commentaireServ;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH-mm-ss")
	private Date date;
	
	@PostMapping(value="/ajoutArticle")
	public ModelAndView ajouteArticle(
			@ModelAttribute("article") @Valid Article article,
			BindingResult result,
			@RequestParam(value="newCat", required=false) String newCat,
			@RequestParam(value="nomCat", required=false) String nomCat,
			@RequestParam(value="selectCat", required=false) String selectCat,
			ModelAndView mv, WebRequest request) {
		
		Categorie categorie = new Categorie();
		Redacteur redacteur = (Redacteur) request.getAttribute("user", 1);
		
		if (newCat==null) {
			categorie = categorieServ.parDefaut();
		} else if (newCat != null) {
			if (newCat.equals("oui") && nomCat==null || newCat.equals("non") && selectCat.isEmpty()) {
				categorie = categorieServ.parDefaut();
			} else {
				
				if (newCat.equals("oui") ) {
					categorie.setNom(nomCat);
					categorie = categorieServ.save(categorie);
					request.setAttribute("listCategorie", categorieServ.getAll(), 1);
				} else if (newCat.equals("non") ){
					Integer catId = Integer.valueOf(selectCat);
					categorie = categorieServ.getById(catId);
				}
			}				
		}
		
		if (result.hasErrors()) {
			System.out.println(result.toString());
			mv.setViewName("ajouterArticle");
			return mv;
		}

		if (article.getId() == null) {
			article.setRedacteur(redacteur);
			date = new Date();
			article.setDate(date);
		}
		
		article.setCategorie(categorie);
		articleServ.enregistrer(article);
		request.setAttribute("listArticle", articleServ.getAll(), 1);
		request.setAttribute("listArticleOwned", articleServ.getArticlesByRedacteur(redacteur), 1);
		mv.setViewName("mesArticles");
		return mv;
	}
	
	@PostMapping(value="/listArticles")
	public ModelAndView listArticles(
			@RequestParam(value="buttonCommentaires", required=false) String buttonCommentaires,
			@RequestParam(value="buttonModifier", required=false) String buttonModifier,
			@RequestParam(value="buttonSupprimer", required=false) String buttonSupprimer,
			ModelAndView mv, WebRequest request) {
		
		if (buttonSupprimer!=null) {
			Integer idArt = Integer.valueOf(buttonSupprimer);
			articleServ.supprimeParId(idArt);
			Redacteur redacteur = (Redacteur) request.getAttribute("user", 1);
			request.setAttribute("listArticleOwned", articleServ.getArticlesByRedacteur(redacteur), 1);
			
			mv.setViewName("mesArticles");
		} else if (buttonModifier!=null) {
			Integer idArt = Integer.valueOf(buttonModifier);
			Article article = articleServ.getArticleById(idArt);
			mv.addObject("article", article);
			mv.addObject("thisCat", article.getCategorie().getId());
			mv.addObject("newCat", "non");
			mv.setViewName("ajouterArticle");
		} else if (buttonCommentaires !=null) {
			Integer idArt = Integer.valueOf(buttonCommentaires);
			Article article = articleServ.getArticleById(idArt);
			mv.addObject("art", article);
			
			Commentaire commentaire = new Commentaire();
			Utilisateur utilisateur = (Utilisateur) request.getAttribute("user", 1);			
			commentaire.setArticle(article);
			commentaire.setUtilisateur(utilisateur);			
			mv.addObject("commentaire", commentaire);
			
			mv.addObject("listCommentaires", commentaireServ.chercherListeParArticle(article));
			mv.setViewName("commentaireArticle");
		}
		return mv;
	}
}
