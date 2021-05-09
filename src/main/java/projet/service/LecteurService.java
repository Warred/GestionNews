package projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.LecteurRepository;
import projet.model.Lecteur;
import projet.model.Utilisateur;

@Service
public class LecteurService implements ILecteur {

	@Autowired
	LecteurRepository lecteurDao;
	
	@Override
	public Lecteur inscrire(Utilisateur u) {
		Lecteur lecteur = new Lecteur(u.getPseudo(), u.getPassword(), u.getNom(), u.getPrenom());
		lecteurDao.save(lecteur);
		return lecteur;
	}

}
