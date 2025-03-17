package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Plante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlanteRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PlanteRepository planteRepository;

    private Plante plante;

    @BeforeEach
    void setUp() {
        plante = new Plante(1, "Tournesol", 100, 0.0, 0, 50, 25.0, "normal", "image.png");
    }

    @Test
    void testFindAll() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(List.of(plante));

        List<Plante> plantes = planteRepository.findAll();

        assertFalse(plantes.isEmpty());
        assertEquals(1, plantes.size());
        assertEquals(plante.getNom(), plantes.get(0).getNom());

        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }

    @Test
    void testFindById() {
        when(jdbcTemplate.query(eq("SELECT * FROM Plante WHERE id = ?"),
                any(RowMapper.class),
                eq(new Object[]{1})))
                .thenReturn(List.of(plante));

        Optional<Plante> result = planteRepository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(plante.getNom(), result.get().getNom());

        verify(jdbcTemplate, times(1)).query(
                eq("SELECT * FROM Plante WHERE id = ?"),
                any(RowMapper.class),
                eq(new Object[]{1}));
    }

    @Test
    void testSave() {
        when(jdbcTemplate.update(
                eq("INSERT INTO Plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
                any(Object[].class)))
                .thenReturn(1);

        int saved = planteRepository.save(plante);
        assertTrue(saved > 0);

        verify(jdbcTemplate, times(1)).update(
                eq("INSERT INTO Plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),
                any(Object[].class));
    }

    @Test
    void testDeleteById() {
        when(jdbcTemplate.update(eq("DELETE FROM Plante WHERE id = ?"), eq(1))).thenReturn(1);

        planteRepository.deleteById(1);

        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM Plante WHERE id = ?"), eq(1));
    }
}
