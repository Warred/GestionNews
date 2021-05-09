package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	boolean existsByPseudoAndPassword(String pseudo, String password);
	
	Utilisateur findByPseudoAndPassword(String pseudo, String password);

}
