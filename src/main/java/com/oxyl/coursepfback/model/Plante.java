package com.oxyl.coursepfback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plante {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("point_de_vie")
    private Integer pointDeVie;

    @JsonProperty("attaque_par_seconde")
    private Double attaqueParSeconde;

    @JsonProperty("degat_attaque")
    private Integer degatAttaque;

    @JsonProperty("cout")
    private Integer cout;

    @JsonProperty("soleil_par_seconde")
    private Double soleilParSeconde;

    @JsonProperty("effet")
    private String effet;

    @JsonProperty("chemin_image")
    private String cheminImage;

    public Plante() {}

    public Plante(Integer id, String nom, Integer pointDeVie, Double attaqueParSeconde, Integer degatAttaque, Integer cout, Double soleilParSeconde, String effet, String cheminImage) {
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

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Integer getPointDeVie() { return pointDeVie; }
    public void setPointDeVie(Integer pointDeVie) { this.pointDeVie = pointDeVie; }

    public Double getAttaqueParSeconde() { return attaqueParSeconde; }
    public void setAttaqueParSeconde(Double attaqueParSeconde) { this.attaqueParSeconde = attaqueParSeconde; }

    public Integer getDegatAttaque() { return degatAttaque; }
    public void setDegatAttaque(Integer degatAttaque) { this.degatAttaque = degatAttaque; }

    public Integer getCout() { return cout; }
    public void setCout(Integer cout) { this.cout = cout; }

    public Double getSoleilParSeconde() { return soleilParSeconde; }
    public void setSoleilParSeconde(Double soleilParSeconde) { this.soleilParSeconde = soleilParSeconde; }

    public String getEffet() { return effet; }
    public void setEffet(String effet) { this.effet = effet; }

    public String getCheminImage() { return cheminImage; }
    public void setCheminImage(String cheminImage) { this.cheminImage = cheminImage; }

    @Override
    public String toString() {
        return "Plante{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pointDeVie=" + pointDeVie +
                ", attaqueParSeconde=" + attaqueParSeconde +
                ", degatAttaque=" + degatAttaque +
                ", cout=" + cout +
                ", soleilParSeconde=" + soleilParSeconde +
                ", effet='" + effet + '\'' +
                ", cheminImage='" + cheminImage + '\'' +
                '}';
    }
}
