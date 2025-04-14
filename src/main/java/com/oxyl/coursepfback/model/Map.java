package com.oxyl.coursepfback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Map {

    @JsonProperty("id_map")
    private int idMap;

    @JsonProperty("ligne")
    private int ligne;

    @JsonProperty("colonne")
    private int colonne;

    @JsonProperty("chemin_image")
    private String cheminImage;

    // 🔹 Constructeur vide (requis pour Spring JDBC)
    public Map() {}

    // 🔹 Constructeur avec tous les paramètres
    public Map(int idMap, int ligne, int colonne, String cheminImage) {
        this.idMap = idMap;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }

    // 🔹 Getters et Setters
    public int getIdMap() {
        return idMap;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}
