package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Zombie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ZombieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final RowMapper<Zombie> zombieRowMapper = (rs, rowNum) -> new Zombie(
            rs.getInt("id_zombie"),
            rs.getString("nom"),
            rs.getInt("point_de_vie"),
            rs.getDouble("attaque_par_seconde"),
            rs.getInt("degat_attaque"),
            rs.getDouble("vitesse_de_deplacement"),
            rs.getString("chemin_image"),
            rs.getInt("id_map")
    );


    public List<Zombie> findAll() {
        String sql = "SELECT * FROM zombie";
        return jdbcTemplate.query(sql, zombieRowMapper);
    }


    public Optional<Zombie> findById(int id) {
        String sql = "SELECT * FROM zombie WHERE id = ?";
        List<Zombie> zombies = jdbcTemplate.query(sql, zombieRowMapper, id);
        return zombies.stream().findFirst();
    }


    public boolean save(Zombie zombie) {
        String sql = "INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getAttaqueParSeconde(), zombie.getDegatAttaque(), zombie.getVitesseDeplacement(), zombie.getCheminImage(), zombie.getIdMap()) > 0;
    }


    public boolean update(Zombie zombie) {
        // Si chemin_image est null, on lui attribue une valeur par défaut
        if (zombie.getCheminImage() == null) {
            zombie.setCheminImage("/images/zombies/default.png");
        }

        String sql = "UPDATE zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
        return jdbcTemplate.update(sql,
                zombie.getNom(),
                zombie.getPointDeVie(),
                zombie.getAttaqueParSeconde(),
                zombie.getDegatAttaque(),
                zombie.getVitesseDeplacement(),
                zombie.getCheminImage(),
                zombie.getIdMap(),
                zombie.getId()) > 0;
    }




    public boolean delete(int id) {
        String sql = "DELETE FROM zombie WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
