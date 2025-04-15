package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Plante;
import com.oxyl.coursepfback.repository.PlanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanteService {

    @Autowired
    private PlanteRepository planteRepository;

    //  Récupérer toutes les plantes
    public List<Plante> getAllPlantes() {
        return planteRepository.findAll();
    }

    //  Récupérer une plante par ID
    public Optional<Plante> getPlanteById(int id) {
        return planteRepository.findById(id);
    }

    //  Ajouter une nouvelle plante
    public boolean addPlante(Plante plante) {
        return planteRepository.save(plante) > 0;  // Retourne true si insertion réussie
    }

    //  Mettre à jour une plante existante
    public boolean updatePlante(Plante plante) {
        return planteRepository.update(plante) > 0; // Retourne true si mise à jour réussie
    }

    //  Supprimer une plante par ID
    public boolean deletePlante(int id) {
        return planteRepository.deleteById(id) > 0; // Retourne true si suppression réussie
    }
}
