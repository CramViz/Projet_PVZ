package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.config.DatabaseConfig;
import com.oxyl.coursepfback.model.Zombie;
import org.junit.jupiter.api.*;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ZombieRepositoryTest {

    private ZombieRepository zombieRepository;

    @BeforeEach
    void setUp() {
        // ✅ Utiliser DatabaseConfig pour obtenir la connexion
        DatabaseConfig databaseConfig = new DatabaseConfig();
        DataSource dataSource = databaseConfig.dataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // ✅ Créer une instance de ZombieRepository en passant jdbcTemplate
        zombieRepository = new ZombieRepository(jdbcTemplate);
    }

    @Test
    void testFindById() {
        Optional<Zombie> result = zombieRepository.findById(1);
        assertTrue(result.isPresent(), "Le zombie avec ID 1 devrait exister en base.");
        System.out.println("Zombie trouvé : " + result.get().getNom());
    }

    @Test
    void testSave() {
        Zombie newZombie = new Zombie(2, "Zombie Rapide", 120, 1.2, 15, 0.8, "rapide.png", 1);
        boolean saved = zombieRepository.save(newZombie);
        assertTrue(saved, "Le zombie aurait dû être inséré.");

        Optional<Zombie> result = zombieRepository.findById(2);
        assertTrue(result.isPresent(), "Le zombie inséré devrait être récupérable.");
        assertEquals("Zombie Rapide", result.get().getNom());
    }
}
