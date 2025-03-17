package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Plante;
import com.oxyl.coursepfback.repository.PlanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanteServiceTest {

    @Mock
    private PlanteRepository planteRepository;

    @InjectMocks
    private PlanteService planteService;

    private Plante plante;

    @BeforeEach
    void setUp() {
        plante = new Plante(1, "Tournesol", 100, 0, 0, 50, 25, "normal", "images/plante/tournesol.png");
    }

    @Test
    void testGetAllPlantes() {
        when(planteRepository.findAll()).thenReturn(Arrays.asList(plante));
        List<Plante> plantes = planteService.getAllPlantes();
        assertFalse(plantes.isEmpty());
        assertEquals(1, plantes.size());
        verify(planteRepository, times(1)).findAll();
    }

    @Test
    void testGetPlanteById() {
        when(planteRepository.findById(1)).thenReturn(Optional.of(plante));
        Optional<Plante> result = planteService.getPlanteById(1);
        assertTrue(result.isPresent());
        assertEquals("Tournesol", result.get().getNom());
        verify(planteRepository, times(1)).findById(1);
    }

    @Test
    void testAddPlante() {
        when(planteRepository.save(any(Plante.class))).thenReturn(1);

        boolean result = planteService.addPlante(plante);

        assertTrue(result);
        verify(planteRepository, times(1)).save(any(Plante.class));
    }

    @Test
    void testDeletePlante() {
        when(planteRepository.deleteById(anyInt())).thenReturn(1);

        boolean result = planteService.deletePlante(1);

        assertTrue(result);
        verify(planteRepository, times(1)).deleteById(anyInt());
    }
}
