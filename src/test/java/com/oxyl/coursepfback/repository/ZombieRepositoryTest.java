package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ZombieRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ZombieRepository zombieRepository;

    private Zombie zombie;

    @BeforeEach
    void setUp() {
        zombie = new Zombie(1, "Zombie Basique", 100, 0.8, 10, 0.5, "image.png", 1);
    }

    @Test
    void testFindAll() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(List.of(zombie));

        List<Zombie> zombies = zombieRepository.findAll();

        assertFalse(zombies.isEmpty());
        assertEquals(1, zombies.size());
        assertEquals(zombie.getNom(), zombies.get(0).getNom());
    }

    @Test
    void testFindById() {
        when(jdbcTemplate.query(eq("SELECT * FROM zombie WHERE id = ?"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenReturn(List.of(zombie));

        Optional<Zombie> result = zombieRepository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(zombie.getNom(), result.get().getNom());

        // Vérification que la méthode a bien été appelée avec les bons arguments
        verify(jdbcTemplate, times(1)).query(eq("SELECT * FROM zombie WHERE id = ?"),
                any(Object[].class),
                any(RowMapper.class));
    }


    @Test
    void testSave() {
        when(jdbcTemplate.update(eq("INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)"),
                any(Object[].class))).thenReturn(1);

        boolean saved = zombieRepository.save(zombie);

        assertTrue(saved);

        // Vérifier que la méthode est bien appelée
        verify(jdbcTemplate, times(1)).update(eq("INSERT INTO zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)"),
                any(Object[].class));
    }

    @Test
    void testDeleteById() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);


        zombieRepository.delete(1);


        verify(jdbcTemplate, times(1)).update(anyString(), anyInt());
    }
}
