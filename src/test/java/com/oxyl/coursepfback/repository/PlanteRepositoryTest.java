package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Plante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;



class PlanteRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PlanteRepository planteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Plante plante = new Plante(1, "Tournesol", 100, 0.0, 0, 50, 1.0, "soleil", "/img.png");
        when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                .thenReturn(List.of(plante));

        List<Plante> result = planteRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("Tournesol", result.get(0).getNom());
    }

    @Test
    void testFindById_found() {
        Plante plante = new Plante(2, "Pisto-pois", 200, 1.5, 25, 100, 0.0, "tir", "/img.png");
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(2)))
                .thenReturn(List.of(plante));

        Optional<Plante> result = planteRepository.findById(2);

        assertTrue(result.isPresent());
        assertEquals("Pisto-pois", result.get().getNom());
    }

    @Test
    void testFindById_notFound() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(999)))
                .thenReturn(Collections.emptyList());

        Optional<Plante> result = planteRepository.findById(999);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_success() {
        Plante plante = new Plante(null, "Mur-Noix", 300, 0.0, 0, 50, 0.0, "bouclier", "/img.png");

        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(1);

        int result = planteRepository.save(plante);

        assertEquals(1, result);
    }

    @Test
    void testDelete_success() {
        when(jdbcTemplate.update(anyString(), eq(5))).thenReturn(1);

        int result = planteRepository.deleteById(5);

        assertEquals(1, result);
    }


    @Test
    void testUpdate_partial_success() {
        Plante plante = new Plante(3, "Tournesol", null, null, null, null, null, null, null);

        when(jdbcTemplate.update(anyString(), (Object[]) any())).thenReturn(1);

        int result = planteRepository.update(plante);

        assertEquals(1, result);
    }





    @Test
    void testUpdate_nothingToUpdate() {
        Plante plante = new Plante(3, null, null, null, null, null, null, null, null);

        int result = planteRepository.update(plante);

        assertEquals(0, result); // car rien à mettre à jour
    }
}
