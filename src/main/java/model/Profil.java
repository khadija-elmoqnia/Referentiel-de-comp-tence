package model;

import java.math.BigDecimal;
import java.util.List;

public class Profil {
    private int id;
    private String titre;
    private String description;
    private int salaire;
    private List<Skill> listskill ;

    public List<Skill> getListskill() {
		return listskill;
	}

	public void setListskill(List<Skill> listskill) {
		this.listskill = listskill;
	}

	public Profil() {
    }

    public Profil(String titre, String description, int salaire) {
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
    }

    public Profil(int id, String titre, String description, int salaire) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
}

