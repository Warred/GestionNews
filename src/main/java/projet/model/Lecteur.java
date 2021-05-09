package projet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="LECTEUR")
public class Lecteur extends Utilisateur {
	
	public Lecteur() {}
	
	
	public Lecteur(String pseudo, String password, String nom, String prenom) {
		this.setPseudo(pseudo);
		this.setPassword(password);
		this.setNom(nom);
		this.setPrenom(prenom);
	}


	@OneToMany (mappedBy="lecteur", cascade=CascadeType.PERSIST)
	private List<Abonnement> abonnements = new ArrayList<Abonnement>();
}
