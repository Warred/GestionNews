package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Lecteur;

public interface LecteurRepository extends JpaRepository<Lecteur, Integer> {

}
