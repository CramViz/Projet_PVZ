package com.oxyl.coursepfback;

import com.oxyl.coursepfback.config.DatabaseConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Test de connexion à MySQL...");


        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);


        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM plantes", Integer.class);
            System.out.println("✅ Connexion réussie ! Nombre de plantes en base : " + count);
        } catch (Exception e) {
            System.out.println(" Erreur de connexion : " + e.getMessage());
        }
    }
}
