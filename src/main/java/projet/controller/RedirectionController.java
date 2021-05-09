package projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import projet.model.Article;
import projet.model.Utilisateur;
import projet.service.ICommentaire;

@Controller
public class RedirectionController {
	@Autowired
	private ICommentaire commentaireServ;
	
	@GetMapping(value="/")
	public String index(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		return "connexion";
	}
	
	@GetMapping(value="/redirect")
	public ModelAndView redirect(
			@RequestParam (value="page") String page,
			ModelAndView mv, WebRequest request) {
		Utilisateur utilisateur = (Utilisateur) request.getAttribute("user", 1);
		if (page!=null) {
			if (page.equals("ajouterArticle")) {
				mv.addObject("article", new Article());
				mv.setViewName("ajouterArticle");
				
			} else if (page.equals("accueilRedacteur")) {
				mv.setViewName("accueilRedacteur");
				
			}else if (page.equals("accueilLecteur")) {
				mv.setViewName("accueilLecteur");
				
			} else if (page.equals("listArticles")) {
				mv.setViewName("listArticles");
				
			} else if (page.equals("mesArticles")) {
				mv.setViewName("mesArticles");
				
			} else if (page.equals("mesCommentaires")) {
				mv.addObject("listCommentaireOwned", commentaireServ.getCommentaireOwned(utilisateur));
				mv.setViewName("mesCommentaires");
				
			} else if (page.equals("deco")) {
				for (String attribut : request.getAttributeNames(1)) { request.removeAttribute(attribut, 1); }
				mv.addObject("utilisateur", new Utilisateur());
				mv.setViewName("connexion");
			}
		}
		return mv;
	}

}
