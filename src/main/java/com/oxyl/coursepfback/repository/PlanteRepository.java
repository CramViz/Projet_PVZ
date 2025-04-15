package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
public class PlanteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Plante> planteRowMapper = (rs, rowNum) -> new Plante(
            rs.getInt("id_plante"),
            rs.getString("nom"),
            rs.getInt("point_de_vie"),
            rs.getDouble("attaque_par_seconde"),
            rs.getInt("degat_attaque"),
            rs.getInt("cout"),
            rs.getDouble("soleil_par_seconde"),
            rs.getString("effet"),
            rs.getString("chemin_image")
    );


    public List<Plante> findAll() {
        String sql = "SELECT * FROM Plante";
        return jdbcTemplate.query(sql, planteRowMapper);
    }


    public Optional<Plante> findById(int id) {
        String sql = "SELECT * FROM Plante WHERE id = ?";
        List<Plante> plantes = jdbcTemplate.query(sql, planteRowMapper, id);
        return plantes.stream().findFirst(); // Retourne une plante si elle existe
    }


    public int save(Plante plante) {
        String sql = "INSERT INTO Plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPointDeVie(),
                plante.getAttaqueParSeconde(),
                plante.getDegatAttaque(),
                plante.getCout(),
                plante.getSoleilParSeconde(),
                plante.getEffet(),
                plante.getCheminImage()
        );
    }


    public int update(Plante plante) {
        StringBuilder sql = new StringBuilder("UPDATE Plante SET ");
        List<Object> params = new ArrayList<>();

        if (plante.getNom() != null) {
            sql.append("nom = ?, ");
            params.add(plante.getNom());
        }
        if (plante.getPointDeVie() != null) {
            sql.append("point_de_vie = ?, ");
            params.add(plante.getPointDeVie());
        }
        if (plante.getAttaqueParSeconde() != null) {
            sql.append("attaque_par_seconde = ?, ");
            params.add(plante.getAttaqueParSeconde());
        }
        if (plante.getDegatAttaque() != null) {
            sql.append("degat_attaque = ?, ");
            params.add(plante.getDegatAttaque());
        }
        if (plante.getCout() != null) {
            sql.append("cout = ?, ");
            params.add(plante.getCout());
        }
        if (plante.getSoleilParSeconde() != null) {
            sql.append("soleil_par_seconde = ?, ");
            params.add(plante.getSoleilParSeconde());
        }
        if (plante.getEffet() != null) {
            sql.append("effet = ?, ");
            params.add(plante.getEffet());
        }
        if (plante.getCheminImage() != null) {
            sql.append("chemin_image = ?, ");
            params.add(plante.getCheminImage());
        }

        if (params.isEmpty()) {
            return 0; // Rien à mettre à jour
        }

        sql.setLength(sql.length() - 2); // Supprime la dernière virgule
        sql.append(" WHERE id_plante = ?");
        params.add(plante.getId());

        try {
            System.out.println("🛠️ SQL UPDATE => " + sql);
            return jdbcTemplate.update(sql.toString(), params.toArray());
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'update partiel : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }



    public int deleteById(int id) {
        String sql = "DELETE FROM Plante WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
