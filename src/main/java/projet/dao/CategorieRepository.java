package projet.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

	Optional<Categorie> findByNom(String nom);
	
	boolean existsByNom(String nom);

}
