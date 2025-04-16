package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Zombie;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ZombieRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ZombieRepository zombieRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Zombie zombie = new Zombie(1, "Zombie de base", 100, 1.0, 10, 0.5, "/img/z.png", 1);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(List.of(zombie));

        List<Zombie> result = zombieRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("Zombie de base", result.get(0).getNom());
    }

    @Test
    void testFindById_found() {
        Zombie zombie = new Zombie(2, "Zombie rapide", 80, 1.5, 15, 1.0, "/img/z2.png", 2);
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(2))).thenReturn(List.of(zombie));

        Optional<Zombie> result = zombieRepository.findById(2);

        assertTrue(result.isPresent());
        assertEquals("Zombie rapide", result.get().getNom());
    }

    @Test
    void testFindById_notFound() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(999))).thenReturn(Collections.emptyList());

        Optional<Zombie> result = zombieRepository.findById(999);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_success() {
        Zombie zombie = new Zombie(0, "Zombie tank", 300, 0.5, 20, 0.3, "/img/z3.png", 3);
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

        boolean result = zombieRepository.save(zombie);

        assertTrue(result);
    }

    @Test
    void testDelete_success() {
        when(jdbcTemplate.update(anyString(), eq(5))).thenReturn(1);

        boolean result = zombieRepository.delete(5);

        assertTrue(result);
    }

    @Test
    void testUpdate_success() {
        Zombie zombie = new Zombie(4, "Zombie maj", 120, 1.0, 12, 0.6, null, 2); // null => valeur par défaut dans le repo
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(1);

        boolean result = zombieRepository.update(zombie);

        assertTrue(result);
    }

    @Test
    void testUpdate_fail() {
        Zombie zombie = new Zombie(4, "Zombie fail", 120, 1.0, 12, 0.6, null, 2);
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(0);

        boolean result = zombieRepository.update(zombie);

        assertFalse(result);
    }
}
