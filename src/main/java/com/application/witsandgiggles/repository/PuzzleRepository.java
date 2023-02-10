package com.application.witsandgiggles.repository;

import com.application.witsandgiggles.domain.Puzzle;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface PuzzleRepository extends CrudRepository<Puzzle, Long> {
}
