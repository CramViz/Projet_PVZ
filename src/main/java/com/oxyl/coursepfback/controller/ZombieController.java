package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.model.Zombie;
import com.oxyl.coursepfback.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zombies")
public class ZombieController {

    @Autowired
    private ZombieService zombieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Zombie>> getAllZombies() {
        return ResponseEntity.ok(zombieService.getAllZombies());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Zombie> getZombieById(@PathVariable int id) {
        return zombieService.getZombieById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addZombie(@RequestBody Zombie zombie) {
        return zombieService.addZombie(zombie) ? ResponseEntity.ok("Zombie ajouté !") : ResponseEntity.status(500).body("Erreur");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateZombie(@PathVariable int id, @RequestBody Zombie zombie) {
        zombie.setId(id);
        return zombieService.updateZombie(zombie) ? ResponseEntity.ok("Zombie mis à jour !") : ResponseEntity.status(500).body("Erreur");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZombie(@PathVariable int id) {
        return zombieService.deleteZombie(id) ? ResponseEntity.ok("Zombie supprimé !") : ResponseEntity.status(500).body("Erreur");
    }
}
