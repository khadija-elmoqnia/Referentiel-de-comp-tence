package model;
import java.util.ArrayList;


public class Categorie {
    private int id;
    private String nom;
    private  ArrayList<Skill> Competences;

    // Constructeur
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.Competences=  new ArrayList<Skill>();
    }
    
 // Constructeur
    public Categorie(String nom) {
        this.nom = nom;
        this.Competences=  new ArrayList<Skill>();
    }
    
    
    
    

	public Categorie() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Skill> getCompetences() {
		return Competences;
	}

	public void setCompetences(ArrayList<Skill> competences) {
		Competences = competences;
	}

    // Getters et setters
    // (Même principe que la classe User)
}

