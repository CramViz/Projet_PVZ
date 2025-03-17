package com.oxyl.coursepfback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plante {
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

    @JsonProperty("cout")
    private int cout;

    @JsonProperty("soleil_par_seconde")
    private double soleilParSeconde;

    @JsonProperty("effet")
    private String effet;

    @JsonProperty("chemin_image")
    private String cheminImage;


    // 🔹 Constructeur vide (obligatoire pour Spring JDBC)
    public Plante() {}

    // 🔹 Constructeur avec tous les paramètres
    public Plante(int id, String nom, int pointDeVie, double attaqueParSeconde, int degatAttaque, int cout, double soleilParSeconde, String effet, String cheminImage) {
        this.id = id;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.cout = cout;
        this.soleilParSeconde = soleilParSeconde;
        this.effet = effet;
        this.cheminImage = cheminImage;
    }

    // 🔹 Getters et Setters
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

    public int getCout() { return cout; }
    public void setCout(int cout) { this.cout = cout; }

    public double getSoleilParSeconde() { return soleilParSeconde; }
    public void setSoleilParSeconde(double soleilParSeconde) { this.soleilParSeconde = soleilParSeconde; }

    public String getEffet() { return effet; }
    public void setEffet(String effet) { this.effet = effet; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }
}
