package com.oxyl.coursepfback.service;

import com.oxyl.coursepfback.model.Zombie;
import com.oxyl.coursepfback.repository.ZombieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZombieService {

    @Autowired
    private ZombieRepository zombieRepository;

    public List<Zombie> getAllZombies() {
        return zombieRepository.findAll();
    }

    public Optional<Zombie> getZombieById(int id) {
        return zombieRepository.findById(id);
    }

    public boolean addZombie(Zombie zombie) {
        return zombieRepository.save(zombie);
    }

    public boolean updateZombie(Zombie zombie) {
        return zombieRepository.update(zombie);
    }

    public boolean deleteZombie(int id) {
        return zombieRepository.delete(id);
    }
}
