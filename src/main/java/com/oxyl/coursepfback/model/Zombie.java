package com.oxyl.coursepfback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Zombie {

    @JsonProperty("id")
    private int id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("point_de_vie")
    private int pointDeVie;

    @JsonProperty("attaque_par_seconde")
    private double attaqueParSeconde;

    @JsonProperty("degat_attaque")
    private int degatAttaque;

    @JsonProperty("vitesse_de_deplacement")
    private double vitesseDeplacement;

    @JsonProperty("chemin_image")
    private String cheminImage;

    @JsonProperty("id_map")
    private int idMap;


    public Zombie() {}


    public Zombie(int id, String nom, int pointDeVie, double attaqueParSeconde, int degatAttaque, double vitesseDeplacement, String cheminImage, int idMap) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeplacement = vitesseDeplacement;
        this.cheminImage = cheminImage;
        this.idMap = idMap;
    }

    //  Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getPointDeVie() { return pointDeVie; }
    public void setPointDeVie(int pointDeVie) { this.pointDeVie = pointDeVie; }

    public double getAttaqueParSeconde() { return attaqueParSeconde; }
    public void setAttaqueParSeconde(double attaqueParSeconde) { this.attaqueParSeconde = attaqueParSeconde; }

    public int getDegatAttaque() { return degatAttaque; }
    public void setDegatAttaque(int degatAttaque) { this.degatAttaque = degatAttaque; }

    public double getVitesseDeplacement() { return vitesseDeplacement; }
    public void setVitesseDeplacement(double vitesseDeplacement) { this.vitesseDeplacement = vitesseDeplacement; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }

    public int getIdMap() { return idMap; }
    public void setIdMap(int idMap) { this.idMap = idMap; }
}
