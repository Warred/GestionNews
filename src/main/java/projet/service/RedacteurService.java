package projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.RedacteurRepository;
import projet.model.Redacteur;
import projet.model.Utilisateur;

@Service
public class RedacteurService implements IRedacteur {
	@Autowired
	RedacteurRepository redacteurDao;

	@Override
	public Redacteur inscrire(Utilisateur utilisateur) {		
		Redacteur redacteur = new Redacteur(utilisateur.getPseudo(),
				utilisateur.getPassword(),utilisateur.getNom(), utilisateur.getPrenom());
		redacteurDao.save(redacteur);
		return redacteur;
	}

}
