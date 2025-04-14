package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.model.Map;
import com.oxyl.coursepfback.service.MapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maps")
public class MapController {

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);

    @Autowired
    private MapService mapService;

    // 🔍 GET all
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map>> getAllCases() {
        logger.info("📥 Récupération de toutes les cases de la map...");
        List<Map> cases = mapService.getAllCases();
        return ResponseEntity.ok(cases);
    }

    // 🔍 GET by ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> getCaseById(@PathVariable int id) {
        logger.info("📥 Récupération de la case avec ID: {}", id);
        Optional<Map> mapCase = mapService.getCaseById(id);
        return mapCase.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("❌ Case non trouvée avec ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // ➕ POST (ajout)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCase(@RequestBody Map mapCase) {
        logger.info("➕ Ajout d'une nouvelle case à la map (ligne: {}, colonne: {})", mapCase.getLigne(), mapCase.getColonne());
        boolean success = mapService.addCase(mapCase);
        if (success) {
            return ResponseEntity.status(201).body("Case ajoutée !");
        } else {
            logger.error("❌ Erreur lors de l'ajout de la case !");
            return ResponseEntity.status(500).body("Erreur lors de l'ajout");
        }
    }

    // 🔄 PUT (mise à jour)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCase(@PathVariable int id, @RequestBody Map mapCase) {
        logger.info("🔄 Mise à jour de la case avec ID: {}", id);
        mapCase.setIdMap(id);
        boolean success = mapService.updateCase(mapCase);
        if (success) {
            return ResponseEntity.ok("Case mise à jour !");
        } else {
            logger.error("❌ Erreur lors de la mise à jour de la case !");
            return ResponseEntity.status(500).body("Erreur lors de la mise à jour");
        }
    }

    // 🗑️ DELETE
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCase(@PathVariable int id) {
        logger.info("🗑️ Suppression de la case avec ID: {}", id);
        boolean success = mapService.deleteCase(id);
        if (success) {
            return ResponseEntity.ok("Case supprimée !");
        } else {
            logger.error("❌ Erreur lors de la suppression de la case !");
            return ResponseEntity.status(500).body("Erreur");
        }
    }
}
