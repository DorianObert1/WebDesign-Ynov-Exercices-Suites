package com.ynov.exercice14.service;

import com.ynov.exercice14.model.Room;
import com.ynov.exercice14.repository.RoomRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Flux<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Mono<Room> createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Mono<Void> deleteRoom(Long id) {
        return roomRepository.deleteById(id);
    }
}
