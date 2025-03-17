package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.model.Plante;
import com.oxyl.coursepfback.service.PlanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanteControllerTest {

    @Mock
    private PlanteService planteService;

    @InjectMocks
    private PlanteController planteController;

    private Plante plante1;
    private Plante plante2;

    @BeforeEach
    void setUp() {
        plante1 = new Plante(1, "Tournesol", 100, 0, 0, 50, 25.0, "normal", "images/plante/tournesol.png");
        plante2 = new Plante(2, "Pois Tireur", 150, 1.5, 20, 100, 0.0, "normal", "images/plante/poistireur.png");
    }

    @Test
    void testGetAllPlantes() {
        List<Plante> plantes = Arrays.asList(plante1, plante2);
        when(planteService.getAllPlantes()).thenReturn(plantes);

        ResponseEntity<List<Plante>> response = planteController.getAllPlantes();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetPlanteById() {
        when(planteService.getPlanteById(1)).thenReturn(Optional.of(plante1));

        ResponseEntity<Plante> response = planteController.getPlanteById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tournesol", response.getBody().getNom());
    }

    @Test
    void testAddPlante() {
        when(planteService.addPlante(plante1)).thenReturn(true);

        ResponseEntity<String> response = planteController.addPlante(plante1);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Plante ajoutée !", response.getBody());
    }
}
