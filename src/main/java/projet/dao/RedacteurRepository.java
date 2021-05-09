package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Redacteur;

public interface RedacteurRepository extends JpaRepository<Redacteur, Integer> {
	
}
