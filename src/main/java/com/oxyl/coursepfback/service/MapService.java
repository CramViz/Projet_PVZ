package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Map;
import com.oxyl.coursepfback.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    // 🔹 Récupérer toutes les cases
    public List<Map> getAllCases() {
        return mapRepository.findAll();
    }

    // 🔹 Récupérer une case par ID
    public Optional<Map> getCaseById(int id) {
        return mapRepository.findById(id);
    }

    // 🔹 Ajouter une nouvelle case
    public boolean addCase(Map mapCase) {
        return mapRepository.save(mapCase);
    }

    // 🔹 Mettre à jour une case existante
    public boolean updateCase(Map mapCase) {
        return mapRepository.update(mapCase);
    }

    // 🔹 Supprimer une case par ID
    public boolean deleteCase(int id) {
        return mapRepository.delete(id);
    }

    // 🔹 (Facultatif) Rechercher une case par coordonnées
    public Optional<Map> getCaseByCoord(int ligne, int colonne) {
        return mapRepository.findByLigneAndColonne(ligne, colonne);
    }
}
