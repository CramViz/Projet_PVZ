package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MapRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Map> mapRowMapper = (rs, rowNum) -> new Map(
            rs.getInt("id_map"),
            rs.getInt("ligne"),
            rs.getInt("colonne"),
            rs.getString("chemin_image")
    );

    // 🔍 Get all cases
    public List<Map> findAll() {
        String sql = "SELECT * FROM Map";
        return jdbcTemplate.query(sql, mapRowMapper);
    }

    // 🔍 Get case by ID
    public Optional<Map> findById(int id) {
        String sql = "SELECT * FROM Map WHERE id_map = ?";
        List<Map> result = jdbcTemplate.query(sql, mapRowMapper, id);
        return result.stream().findFirst();
    }

    // ➕ Add case
    public boolean save(Map mapCase) {
        String sql = "INSERT INTO Map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                mapCase.getLigne(),
                mapCase.getColonne(),
                mapCase.getCheminImage()
        ) > 0;
    }

    // 🔄 Update case
    // 🔄 Update case
    public boolean update(Map mapCase) {
        // Vérifier si chemin_image est null, et utiliser une valeur par défaut si c'est le cas
        if (mapCase.getCheminImage() == null) {
            mapCase.setCheminImage("/images/maps/default.png"); // Valeur par défaut
        }

        String sql = "UPDATE Map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
        return jdbcTemplate.update(sql,
                mapCase.getLigne(),
                mapCase.getColonne(),
                mapCase.getCheminImage(),
                mapCase.getIdMap()
        ) > 0;
    }


    // 🗑️ Delete case
    public boolean delete(int id) {
        String sql = "DELETE FROM Map WHERE id_map = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    // 📍 Facultatif : récupérer par coordonnées ligne + colonne
    public Optional<Map> findByLigneAndColonne(int ligne, int colonne) {
        String sql = "SELECT * FROM Map WHERE ligne = ? AND colonne = ?";
        List<Map> result = jdbcTemplate.query(sql, mapRowMapper, ligne, colonne);
        return result.stream().findFirst();
    }
}
