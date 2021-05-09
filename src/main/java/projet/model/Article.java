package projet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn (name="idRedacteur", nullable=false)
	private Redacteur redacteur;
	
	@ManyToOne
	@JoinColumn (name="idCategorie")
	private Categorie categorie;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH-mm-ss")
	private Date date;
	
	@Column(unique=true)
	@NotEmpty(message="Le champ titre est obligatoire.")
	private String titre;
	
	@Length(max=65000)
	@NotEmpty(message="Le champ contenu est obligatoire.")
	private String contenu;
	
	@OneToMany(mappedBy="article", cascade=CascadeType.PERSIST)
	private List<Commentaire> commentaires = new ArrayList<Commentaire>();
	

	public Article() {}
	public Article(Redacteur redacteur, String titre, String contenu) {
		this.redacteur = redacteur;
		this.titre = titre;
		this.contenu = contenu;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Redacteur getRedacteur() {
		return redacteur;
	}
	public void setRedacteur(Redacteur redacteur) {
		this.redacteur = redacteur;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

}
