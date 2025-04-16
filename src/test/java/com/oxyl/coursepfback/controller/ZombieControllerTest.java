package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.model.Zombie;
import com.oxyl.coursepfback.service.ZombieService;
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

class ZombieControllerTest {

    private MockMvc mockMvc;
    private ZombieService zombieService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        zombieService = mock(ZombieService.class);
        ZombieController controller = new ZombieController();
        controller.setZombieService(zombieService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllZombies() throws Exception {
        Zombie zombie = new Zombie(1, "Zombie Basique", 100, 1.0, 10, 0.5, "/img.png", 1);
        when(zombieService.getAllZombies()).thenReturn(List.of(zombie));

        mockMvc.perform(get("/zombies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Zombie Basique"));
    }

    @Test
    void testGetZombieById_found() throws Exception {
        Zombie zombie = new Zombie(2, "Zombie Rapide", 80, 1.5, 15, 1.0, "/img.png", 1);
        when(zombieService.getZombieById(2)).thenReturn(Optional.of(zombie));

        mockMvc.perform(get("/zombies/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Zombie Rapide"));
    }

    @Test
    void testGetZombieById_notFound() throws Exception {
        when(zombieService.getZombieById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/zombies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddZombie_success() throws Exception {
        Zombie zombie = new Zombie(0, "Zombie Cône", 150, 0.8, 20, 0.6, "/img.png", 1);
        when(zombieService.addZombie(any())).thenReturn(true);

        mockMvc.perform(post("/zombies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(zombie)))
                .andExpect(status().isOk())
                .andExpect(content().string("Zombie ajouté !"));
    }

    @Test
    void testUpdateZombie_success() throws Exception {
        Zombie zombie = new Zombie(0, "Zombie Casque", 200, 0.7, 30, 0.4, "/img.png", 1);
        when(zombieService.updateZombie(any())).thenReturn(true);

        mockMvc.perform(put("/zombies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(zombie)))
                .andExpect(status().isOk())
                .andExpect(content().string("Zombie mis à jour !"));
    }

    @Test
    void testDeleteZombie_success() throws Exception {
        when(zombieService.deleteZombie(1)).thenReturn(true);

        mockMvc.perform(delete("/zombies/1"))
                .andExpect(status().isOk());
    }
}
