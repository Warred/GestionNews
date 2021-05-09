package projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.UtilisateurRepository;
import projet.model.Lecteur;
import projet.model.Redacteur;
import projet.model.Utilisateur;

@Service
public class UtilisateurService implements IUtilisateur {
	@Autowired
	UtilisateurRepository utilisateurDao;
	
	public UtilisateurService() {}

	@Override
	public Utilisateur inscrire(Utilisateur utilisateur) {
		utilisateur = utilisateurDao.save(utilisateur);
		return utilisateur;
	}

	@Override
	public boolean verifConnexion(String pseudo, String password) {
		return utilisateurDao.existsByPseudoAndPassword(pseudo, password);
	}

	@Override
	public boolean isRedacteur(Utilisateur utilisateur) {
		boolean redac = utilisateur instanceof Redacteur;
		return redac;
	}
	@Override
	public boolean isLecteur(Utilisateur utilisateur) {
		boolean lect = utilisateur instanceof Lecteur;
		return lect;
	}

	@Override
	public Utilisateur getUtilisateur(String pseudo, String password) {
		return utilisateurDao.findByPseudoAndPassword(pseudo, password);
	}

	
}
