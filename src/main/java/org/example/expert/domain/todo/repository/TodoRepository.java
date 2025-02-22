package org.example.expert.domain.todo.repository;

import com.querydsl.core.QueryFactory;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "WHERE t.weather = :weather " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByWeatherOrderByModifiedAtDesc(@Param("weather") String weather, Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "WHERE t.modifiedAt BETWEEN :startDateTime AND :endDateTime " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByModifiedAtBetween(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime")LocalDateTime endDateTime,
            Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "WHERE t.modifiedAt >= :startDateTime " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByModifiedAtAfter(@Param("startDateTime") LocalDateTime startDateTime, Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "WHERE t.modifiedAt <= :endDateTime " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByModifiedAtBefore(@Param("endDateTime") LocalDateTime endDateTime, Pageable pageable);

}
