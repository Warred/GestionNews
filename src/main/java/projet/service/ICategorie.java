package projet.service;

import java.util.List;

import projet.model.Categorie;

public interface ICategorie {

	List<Categorie> getAll();
	
	Categorie save(Categorie categorie);

	Categorie getById(Integer id);

	Categorie parDefaut();

}
