package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlanteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Plante> planteRowMapper = (rs, rowNum) -> new Plante(
            rs.getInt("id"),
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
        String sql = "UPDATE Plante SET nom=?, point_de_vie=?, attaque_par_seconde=?, degat_attaque=?, cout=?, soleil_par_seconde=?, effet=?, chemin_image=? WHERE id=?";
        return jdbcTemplate.update(sql,
                plante.getNom(),
                plante.getPointDeVie(),
                plante.getAttaqueParSeconde(),
                plante.getDegatAttaque(),
                plante.getCout(),
                plante.getSoleilParSeconde(),
                plante.getEffet(),
                plante.getCheminImage(),
                plante.getId()
        );
    }


    public int deleteById(int id) {
        String sql = "DELETE FROM Plante WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
