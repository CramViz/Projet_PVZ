package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Zombie;
import com.oxyl.coursepfback.repository.ZombieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZombieServiceTest {

    @Mock
    private ZombieRepository zombieRepository;

    @InjectMocks
    private ZombieService zombieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllZombies() {
        Zombie zombie = new Zombie(1, "Zombie Classique", 100, 1.0, 20, 0.5, "/zombie.png", 1);
        when(zombieRepository.findAll()).thenReturn(List.of(zombie));

        List<Zombie> result = zombieService.getAllZombies();

        assertEquals(1, result.size());
        assertEquals("Zombie Classique", result.get(0).getNom());
    }

    @Test
    void testGetZombieById_found() {
        Zombie zombie = new Zombie(2, "Gargantuar", 500, 0.5, 100, 0.2, "/garg.png", 1);
        when(zombieRepository.findById(2)).thenReturn(Optional.of(zombie));

        Optional<Zombie> result = zombieService.getZombieById(2);

        assertTrue(result.isPresent());
        assertEquals("Gargantuar", result.get().getNom());
    }

    @Test
    void testGetZombieById_notFound() {
        when(zombieRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Zombie> result = zombieService.getZombieById(999);

        assertFalse(result.isPresent());
    }

    @Test
    void testAddZombie_success() {
        Zombie zombie = new Zombie(3, "Cônehead", 200, 1.0, 25, 0.6, "/conehead.png", 1);
        when(zombieRepository.save(zombie)).thenReturn(true);

        boolean result = zombieService.addZombie(zombie);

        assertTrue(result);
    }

    @Test
    void testUpdateZombie_success() {
        Zombie zombie = new Zombie(3, "Cônehead+", 250, 1.2, 30, 0.6, "/conehead+.png", 1);
        when(zombieRepository.update(zombie)).thenReturn(true);

        boolean result = zombieService.updateZombie(zombie);

        assertTrue(result);
    }

    @Test
    void testDeleteZombie_success() {
        when(zombieRepository.delete(3)).thenReturn(true);

        boolean result = zombieService.deleteZombie(3);

        assertTrue(result);
    }
}
