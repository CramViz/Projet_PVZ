package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Zombie;
import com.oxyl.coursepfback.repository.ZombieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZombieServiceTest {

    @Mock
    private ZombieRepository zombieRepository;

    @InjectMocks
    private ZombieService zombieService;

    private Zombie zombie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        zombie = new Zombie(1, "Zombie de base", 100, 0.80, 10, 0.50, "images/zombie/zombie.png", 1);
    }

    @Test
    void testGetZombieById() {
        when(zombieRepository.findById(1)).thenReturn(Optional.of(zombie));

        Optional<Zombie> result = zombieService.getZombieById(1);

        assertTrue(result.isPresent());
        assertEquals("Zombie de base", result.get().getNom());
        verify(zombieRepository, times(1)).findById(1);
    }

    @Test
    void testAddZombie() {
        when(zombieRepository.save(zombie)).thenReturn(true);



        boolean result = zombieService.addZombie(zombie);

        assertTrue(result);
        verify(zombieRepository, times(1)).save(zombie);
    }

    @Test
    void testDeleteZombie() {
        when(zombieRepository.delete(anyInt())).thenReturn(true);


        boolean result = zombieService.deleteZombie(1);

        assertTrue(result);
        verify(zombieRepository, times(1)).delete(1);
    }
}
