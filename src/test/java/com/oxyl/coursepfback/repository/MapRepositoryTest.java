package com.oxyl.coursepfback.repository;

import com.oxyl.coursepfback.model.Map;
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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MapRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private MapRepository mapRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Map mockMap = new Map(1, 0, 0, "/images/maps/grass.png");
        List<Map> expectedList = List.of(mockMap);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedList);

        List<Map> result = mapRepository.findAll();

        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getLigne());
        assertEquals(0, result.get(0).getColonne());
    }

    @Test
    void testFindById_found() {
        Map mockMap = new Map(1, 1, 2, "/images/maps/water.png");
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(1)))
                .thenReturn(List.of(mockMap));

        Optional<Map> result = mapRepository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getLigne());
        assertEquals(2, result.get().getColonne());
    }

    @Test
    void testFindById_notFound() {
        when(jdbcTemplate.query(anyString(), any(RowMapper.class), eq(999)))
                .thenReturn(Collections.emptyList());

        Optional<Map> result = mapRepository.findById(999);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave_success() {
        Map map = new Map(0, 3, 4, "/images/maps/sand.png");

        when(jdbcTemplate.update(anyString(), eq(3), eq(4), eq("/images/maps/sand.png")))
                .thenReturn(1); // 1 ligne ajoutée

        boolean result = mapRepository.save(map);

        assertTrue(result);
    }

    @Test
    void testDelete_success() {
        when(jdbcTemplate.update(anyString(), eq(5))).thenReturn(1);

        boolean result = mapRepository.delete(5);

        assertTrue(result);
    }
}
