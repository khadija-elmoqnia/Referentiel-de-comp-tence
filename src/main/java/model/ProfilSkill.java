package model;

public class ProfilSkill {
    private Skill skill;
    private int niveauRequis;

    public ProfilSkill(Skill skill, int niveauRequis) {
        this.skill = skill;
        this.niveauRequis = niveauRequis;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getNiveauRequis() {
        return niveauRequis;
    }

    public void setNiveauRequis(int niveauRequis) {
        this.niveauRequis = niveauRequis;
    }
}
