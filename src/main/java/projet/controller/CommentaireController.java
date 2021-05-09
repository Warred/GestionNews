package projet.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import projet.model.Commentaire;
import projet.model.Utilisateur;
import projet.service.ICommentaire;

@Controller
public class CommentaireController {

	@Autowired
	private ICommentaire commentaireServ;
	
	@PostMapping(value="/ajoutCommentaire")
	public ModelAndView ajouteCommentaire(
			@ModelAttribute("commentaire") @Valid Commentaire commentaire,
			BindingResult result,
			ModelAndView mv, WebRequest request) {
				
		if (!result.hasErrors()) {
			commentaire.setDate(new Date());
			commentaireServ.enregistrer(commentaire);
			
			Commentaire newComm = new Commentaire();
			Utilisateur utilisateur = (Utilisateur) request.getAttribute("user", 1);
			newComm.setArticle(commentaire.getArticle());
			newComm.setUtilisateur(utilisateur);
			
			mv.addObject("commentaire", newComm);
			mv.addObject("listCommentaires", commentaireServ.chercherListeParArticle(commentaire.getArticle()));
			mv.addObject("art", commentaire.getArticle());
			mv.setViewName("commentaireArticle");		
		}
		
		return mv;
	}
	
	@PostMapping(value="/editMesCommentaires")
	public ModelAndView editMesCommentaires(
			@ModelAttribute("commentaire") @Valid Commentaire comment,
			BindingResult result,
			@RequestParam(value="buttonModifier", required=false) String buttonModifier,
			@RequestParam(value="buttonSupprimer", required=false) String buttonSupprimer,
			@RequestParam(value="buttonValider", required=false) String buttonValider,
			ModelAndView mv, WebRequest request) {
		
		if(buttonModifier != null) {
			Integer id = Integer.valueOf(buttonModifier);
			Commentaire commentaire = commentaireServ.getById(id);
			mv.addObject("commentaire", commentaire);
			mv.addObject("listCommentaireOwned", commentaireServ.getCommentaireOwned(commentaire.getUtilisateur()));
			mv.setViewName("mesCommentaires");
		} else if (buttonSupprimer != null) {
			Integer id = Integer.valueOf(buttonSupprimer);
			Commentaire commentaire = commentaireServ.getById(id);
			commentaireServ.effacerById(id);
			mv.addObject("listCommentaireOwned", commentaireServ.getCommentaireOwned(commentaire.getUtilisateur()));
			mv.setViewName("mesCommentaires");
		} else if (buttonValider != null) {
			if (!result.hasErrors()) {
				commentaireServ.enregistrer(comment);
				Commentaire newComm = new Commentaire();
				Utilisateur utilisateur = (Utilisateur) request.getAttribute("user", 1);
				newComm.setArticle(comment.getArticle());
				newComm.setUtilisateur(utilisateur);
				
				mv.addObject("commentaire", newComm);
				mv.addObject("listCommentaireOwned", commentaireServ.getCommentaireOwned(comment.getUtilisateur()));
				mv.setViewName("mesCommentaires");
			}
		}
		return mv;
	}
}
