package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Map;
import com.oxyl.coursepfback.repository.MapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MapServiceTest {

    @Mock
    private MapRepository mapRepository;

    @InjectMocks
    private MapService mapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCases() {
        Map mockMap = new Map(1, 0, 0, "/images/maps/grass.png");
        when(mapRepository.findAll()).thenReturn(List.of(mockMap));

        List<Map> result = mapService.getAllCases();

        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getLigne());
    }

    @Test
    void testGetCaseById_found() {
        Map mockMap = new Map(2, 1, 2, "/images/maps/water.png");
        when(mapRepository.findById(2)).thenReturn(Optional.of(mockMap));

        Optional<Map> result = mapService.getCaseById(2);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getLigne());
    }

    @Test
    void testGetCaseById_notFound() {
        when(mapRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Map> result = mapService.getCaseById(999);

        assertFalse(result.isPresent());
    }

    @Test
    void testAddCase() {
        Map map = new Map(0, 3, 4, "/images/maps/sand.png");
        when(mapRepository.save(map)).thenReturn(true);

        boolean result = mapService.addCase(map);

        assertTrue(result);
    }

    @Test
    void testUpdateCase() {
        Map map = new Map(1, 3, 4, "/images/maps/stone.png");
        when(mapRepository.update(map)).thenReturn(true);

        boolean result = mapService.updateCase(map);

        assertTrue(result);
    }

    @Test
    void testDeleteCase() {
        when(mapRepository.delete(5)).thenReturn(true);

        boolean result = mapService.deleteCase(5);

        assertTrue(result);
    }

    @Test
    void testGetCaseByCoord() {
        Map mockMap = new Map(3, 2, 2, "/images/maps/wood.png");
        when(mapRepository.findByLigneAndColonne(2, 2)).thenReturn(Optional.of(mockMap));

        Optional<Map> result = mapService.getCaseByCoord(2, 2);

        assertTrue(result.isPresent());
        assertEquals("/images/maps/wood.png", result.get().getCheminImage());
    }
}
