package projet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import projet.service.IArticle;
import projet.service.ICategorie;
import projet.service.ILecteur;
import projet.service.IRedacteur;
import projet.service.IUtilisateur;
import projet.model.Lecteur;
import projet.model.Redacteur;
import projet.model.Utilisateur;

@Controller
public class UtilisateurController {
	
	@Autowired
	private IUtilisateur utilisateurServ;	
	@Autowired
	private IRedacteur redacteurServ;
	@Autowired
	private ILecteur lecteurServ;
	@Autowired
	private ICategorie categorieServ;
	@Autowired
	private IArticle articleServ;
	
	@PostMapping(value="/authentification")
	public ModelAndView checkLogin(
			@ModelAttribute("utilisateur") @Valid Utilisateur utilisateur,
			BindingResult result,
			@RequestParam(value="buttonConnexion", required=false) String connexion,
			@RequestParam(value="buttonInscription", required=false) String inscription,
			ModelAndView mv, WebRequest request) {
		
		if (connexion!=null) {
			if (( utilisateur = utilisateurServ.getUtilisateur(utilisateur.getPseudo(), utilisateur.getPassword()) ) != null) {
				if (utilisateurServ.isRedacteur(utilisateur)) {
					Redacteur redacteur = (Redacteur) utilisateur;
					request.setAttribute("user", redacteur, 1); // 1 = sessionScope
					request.setAttribute("listArticleOwned", articleServ.getArticlesByRedacteur(redacteur), 1);
					mv.setViewName("accueilRedacteur");
				} else if (utilisateurServ.isLecteur(utilisateur)) {
					Lecteur lecteur = (Lecteur) utilisateur;
					request.setAttribute("user", lecteur, 1);
					mv.setViewName("accueilLecteur");
				}
				request.setAttribute("listArticle", articleServ.getAll(), 1);
				request.setAttribute("listCategorie", categorieServ.getAll(), 1);
			} else {
				result.rejectValue("pseudo", "authentificationFailed", "L'authentification a échoué.");
				mv.setViewName("connexion");
			}
		} else if (inscription!=null) {
			mv.addObject("utilisateur", new Utilisateur());
			mv.setViewName("inscription");
		}		
		return mv;
	}
	
	@PostMapping(value="/inscription")
	public ModelAndView inscrireUtilisateur(
			@ModelAttribute("utilisateur") @Valid Utilisateur utilisateur,
			BindingResult result,
			@RequestParam(value="rang", required=false) String rang,
			@RequestParam(value="buttonSInscrire", required=false) String sinscrire,
			@RequestParam(value="buttonRetour", required=false) String retour,
			ModelAndView mv) {
		
		if (sinscrire!=null) {
			if (rang.isEmpty()) {
				result.rejectValue("pseudo", "rangIsNull", "Sélectionnez un rang pour valider.");
			}
			if (result.hasErrors()) {
				System.out.println(result.toString());
				mv.setViewName("inscription");
			} else {
				try {
					if ( rang.equals("redacteur") ) {						
						redacteurServ.inscrire(utilisateur);
					} else if (rang.equals("lecteur")) {
						lecteurServ.inscrire(utilisateur);
					}
					mv.setViewName("connexion");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					result.rejectValue("pseudo", e.getClass().toString(), "Le pseudonyme est déjà utilisé.");
					mv.setViewName("inscription");
				}
			}
		} else if (retour!=null) {
			mv.addObject("utilisateur", new Utilisateur());
			mv.setViewName("connexion");
		}
		return mv;
	}

	@PostMapping(value="/test")
	public ModelAndView test(
			@RequestParam(value="test", required=false) String test,
			@RequestParam(value="button", required=false) String button,
			ModelAndView mv) {
			
		mv.setViewName("accueil");
		return mv;
		
	}
}
