package projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.CategorieRepository;
import projet.model.Categorie;

@Service
public class CategorieService implements ICategorie {
	@Autowired
	CategorieRepository categorieDao;
	
	@Override
	public List<Categorie> getAll() {		
		return categorieDao.findAll();
	}

	@Override
	public Categorie save(Categorie categorie) {
		categorie = categorieDao.save(categorie);
		return categorie;
	}

	@Override
	public Categorie getById(Integer id) {
		return categorieDao.findById(id).get();
	}

	@Override
	public Categorie parDefaut() {
		if (categorieDao.existsByNom("Divers")) {
			return categorieDao.findByNom("Divers").get();
		} else {
			return save(new Categorie("Divers"));
		}
	}
	
}
