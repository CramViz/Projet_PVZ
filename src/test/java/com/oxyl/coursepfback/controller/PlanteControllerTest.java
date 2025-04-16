package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.model.Plante;
import com.oxyl.coursepfback.service.PlanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlanteControllerTest {

    private MockMvc mockMvc;
    private PlanteService planteService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        planteService = mock(PlanteService.class);
        PlanteController controller = new PlanteController();
        controller.setPlanteService(planteService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllPlantes() throws Exception {
        Plante plante = new Plante(1, "Tournesol", 100, 0.0, 0, 50, 1.0, "soleil", "/img.png");
        when(planteService.getAllPlantes()).thenReturn(List.of(plante));

        mockMvc.perform(get("/plantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Tournesol"));
    }

    @Test
    void testGetPlanteById_found() throws Exception {
        Plante plante = new Plante(2, "Pisto-pois", 200, 1.5, 25, 100, 0.0, "tir", "/img.png");
        when(planteService.getPlanteById(2)).thenReturn(Optional.of(plante));

        mockMvc.perform(get("/plantes/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Pisto-pois"));
    }

    @Test
    void testGetPlanteById_notFound() throws Exception {
        when(planteService.getPlanteById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/plantes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddPlante_success() throws Exception {
        Plante plante = new Plante(null, "Mur-Noix", 300, 0.0, 0, 50, 0.0, "bouclier", "/img.png");
        when(planteService.addPlante(any())).thenReturn(true);

        mockMvc.perform(post("/plantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plante)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Plante ajoutée !"));
    }

    @Test
    void testUpdatePlante_success() throws Exception {
        Plante plante = new Plante(null, "Tournesol+", 120, 0.0, 0, 60, 1.2, "soleil+", "/img.png");
        when(planteService.updatePlante(any())).thenReturn(true);

        mockMvc.perform(put("/plantes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plante)))
                .andExpect(status().isOk())
                .andExpect(content().string("Plante mise à jour !"));
    }

    @Test
    void testDeletePlante_success() throws Exception {
        when(planteService.deletePlante(1)).thenReturn(true);

        mockMvc.perform(delete("/plantes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testValidatePlantes_valid() throws Exception {
        Plante plante = new Plante(1, "Tournesol", 100, 0.0, 0, 50, 1.0, "soleil", "/img.png");
        when(planteService.getAllPlantes()).thenReturn(List.of(plante));

        mockMvc.perform(get("/plantes/validation"))
                .andExpect(status().isOk())
                .andExpect(content().string("Toutes les plantes sont valides"));
    }
}
