package com.example.task1.repository;

import com.example.task1.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepositoryMy extends JpaRepository<Card , Integer> {
    Optional <Card> findCardByUserName(String userName);

    Optional <Card> findCardByNummber(String toNummber);
}
