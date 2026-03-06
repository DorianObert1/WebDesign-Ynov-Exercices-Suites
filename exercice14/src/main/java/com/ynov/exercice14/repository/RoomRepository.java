package com.ynov.exercice14.repository;

import com.ynov.exercice14.model.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
}
