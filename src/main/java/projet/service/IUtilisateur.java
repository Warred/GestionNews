package projet.service;

import projet.model.Utilisateur;

public interface IUtilisateur {
	
	Utilisateur inscrire(Utilisateur utilisateur);
	
	boolean verifConnexion(String pseudo, String password);	
	Utilisateur getUtilisateur(String pseudo, String password);
	
	boolean isRedacteur(Utilisateur utilisateur);
	boolean isLecteur(Utilisateur utilisateur);
}
