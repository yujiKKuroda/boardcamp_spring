package com.boardcamp.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.RentalModel;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long>{
  @Query("SELECT r FROM RentalModel r WHERE r.game.id = :gameId AND r.returnDate IS NOT NULL")
  List<RentalModel> findCompletedRentalsByGameId(@Param("gameId") Long gameId);
}
