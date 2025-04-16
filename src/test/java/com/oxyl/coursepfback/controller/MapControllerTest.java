package com.oxyl.coursepfback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxyl.coursepfback.model.Map;
import com.oxyl.coursepfback.service.MapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MapControllerTest {

    private MockMvc mockMvc;
    private MapService mapService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mapService = mock(MapService.class);
        MapController mapController = new MapController();
        // on injecte manuellement le mock (car pas d’@Autowired ici)
        mapController.setMapService(mapService);

        mockMvc = MockMvcBuilders.standaloneSetup(mapController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCases() throws Exception {
        when(mapService.getAllCases()).thenReturn(List.of(new Map(1, 0, 0, "/img.png")));

        mockMvc.perform(get("/maps"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_map").value(1));
    }

    @Test
    void testGetCaseById_found() throws Exception {
        when(mapService.getCaseById(1)).thenReturn(Optional.of(new Map(1, 1, 2, "/img.png")));

        mockMvc.perform(get("/maps/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ligne").value(1));
    }

    @Test
    void testGetCaseById_notFound() throws Exception {
        when(mapService.getCaseById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/maps/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddCase_success() throws Exception {
        Map newMap = new Map(0, 2, 3, "/new.png");
        when(mapService.addCase(any(Map.class))).thenReturn(true);

        mockMvc.perform(post("/maps")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMap)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Case ajoutée !"));
    }

    @Test
    void testUpdateCase_success() throws Exception {
        Map updatedMap = new Map(1, 4, 5, "/updated.png");
        when(mapService.updateCase(any(Map.class))).thenReturn(true);

        mockMvc.perform(put("/maps/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMap)))
                .andExpect(status().isOk())
                .andExpect(content().string("Case mise à jour !"));
    }

    @Test
    void testDeleteCase_success() throws Exception {
        when(mapService.deleteCase(1)).thenReturn(true);

        mockMvc.perform(delete("/maps/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Case supprimée !"));
    }
}
