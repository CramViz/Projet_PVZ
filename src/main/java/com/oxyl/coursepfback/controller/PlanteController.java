package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.model.Plante;
import com.oxyl.coursepfback.service.PlanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    private static final Logger logger = LoggerFactory.getLogger(PlanteController.class);

    @Autowired
    private PlanteService planteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Plante>> getAllPlantes() {
        logger.info("🔍 Récupération de toutes les plantes...");
        List<Plante> plantes = planteService.getAllPlantes();
        return ResponseEntity.ok(plantes);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plante> getPlanteById(@PathVariable("id") int id) {
        logger.info("🔍 Récupération de la plante avec ID: {}", id);
        Optional<Plante> plante = planteService.getPlanteById(id);
        return plante.map(ResponseEntity::ok).orElseGet(() -> {
            logger.warn(" Plante non trouvée avec ID: {}", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPlante(@RequestBody Plante plante) {
        logger.info("➕ Ajout d'une nouvelle plante: {}", plante.getNom());
        boolean success = planteService.addPlante(plante);
        if (success) {
            return ResponseEntity.status(201).body("Plante ajoutée !");
        } else {
            logger.error(" Erreur lors de l'ajout de la plante !");
            return ResponseEntity.status(500).body("Erreur lors de l'ajout");
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePlante(@PathVariable("id") int id, @RequestBody Plante plante) {
        logger.info("🔄 Mise à jour de la plante avec ID: {}", id);
        logger.info("🪴 Données reçues : {}", plante);
        plante.setId(id);
        boolean success = planteService.updatePlante(plante);
        if (success) {
            return ResponseEntity.ok("Plante mise à jour !");
        } else {
            logger.error(" Erreur lors de la mise à jour de la plante !");
            return ResponseEntity.status(500).body("Erreur lors de la mise à jour");
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletePlante(@PathVariable("id") int id) {
        logger.info(" Suppression de la plante avec ID: {}", id);
        boolean success = planteService.deletePlante(id);
        if (success) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            logger.error(" Erreur lors de la suppression de la plante !");
            return ResponseEntity.status(500).build();
        }
    }

    // Validation du format des plantes
    @GetMapping(value = "/validation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validatePlantes() {
        logger.info("🔍 Validation des données des plantes...");

        // Récupérer toutes les plantes
        List<Plante> plantes = planteService.getAllPlantes();

        // Liste pour stocker les erreurs de validation
        List<String> erreurs = new ArrayList<>();

        // Vérifier chaque plante
        for (int i = 0; i < plantes.size(); i++) {
            Plante plante = plantes.get(i);

            // Vérifier que chaque plante possède un champ 'id_plante'
            if (plante.getId() == null) {
                erreurs.add("Plante #" + i + ": champ 'id_plante' manquant");
            }
        }

        // Si des erreurs sont trouvées, retourner un message avec les erreurs
        if (!erreurs.isEmpty()) {
            return ResponseEntity.status(400).body("Erreurs de validation: " + String.join(", ", erreurs));
        }

        // Si tout est valide
        return ResponseEntity.ok("Toutes les plantes sont valides");
    }

    public void setPlanteService(PlanteService planteService) {
        this.planteService = planteService;
    }
}
