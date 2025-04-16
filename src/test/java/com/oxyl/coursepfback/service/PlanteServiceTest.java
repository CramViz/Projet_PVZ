package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Plante;
import com.oxyl.coursepfback.repository.PlanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlanteServiceTest {

    @Mock
    private PlanteRepository planteRepository;

    @InjectMocks
    private PlanteService planteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlantes() {
        Plante plante1 = new Plante(1, "Tournesol", 100, 0.0, 0, 50, 1.0, "soleil", "/img.png");
        Plante plante2 = new Plante(2, "Pisto-pois", 200, 1.5, 25, 100, 0.0, "tir", "/img.png");

        when(planteRepository.findAll()).thenReturn(Arrays.asList(plante1, plante2));

        var result = planteService.getAllPlantes();

        assertEquals(2, result.size());
        verify(planteRepository).findAll();
    }

    @Test
    void testGetPlanteById_found() {
        Plante plante = new Plante(1, "Tournesol", 100, 0.0, 0, 50, 1.0, "soleil", "/img.png");
        when(planteRepository.findById(1)).thenReturn(Optional.of(plante));

        Optional<Plante> result = planteService.getPlanteById(1);

        assertTrue(result.isPresent());
        assertEquals("Tournesol", result.get().getNom());
    }

    @Test
    void testGetPlanteById_notFound() {
        when(planteRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Plante> result = planteService.getPlanteById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void testAddPlante_success() {
        Plante plante = new Plante(null, "Mur-Noix", 300, 0.0, 0, 50, 0.0, "bouclier", "/img.png");
        when(planteRepository.save(plante)).thenReturn(1);

        boolean result = planteService.addPlante(plante);

        assertTrue(result);
    }

    @Test
    void testUpdatePlante_success() {
        Plante plante = new Plante(1, "Tournesol", 150, null, null, null, null, null, null);
        when(planteRepository.update(plante)).thenReturn(1);

        boolean result = planteService.updatePlante(plante);

        assertTrue(result);
    }

    @Test
    void testDeletePlante_success() {
        when(planteRepository.deleteById(1)).thenReturn(1);

        boolean result = planteService.deletePlante(1);

        assertTrue(result);
    }

    @Test
    void testDeletePlante_failure() {
        when(planteRepository.deleteById(999)).thenReturn(0);

        boolean result = planteService.deletePlante(999);

        assertFalse(result);
    }
}
