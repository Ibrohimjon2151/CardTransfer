package com.example.task1.repository;

import com.example.task1.entity.Card;
import com.example.task1.entity.StateCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StateRepository extends JpaRepository<StateCard, Integer> {

    List<StateCard> findAllByCard_Id(int card_id);
    List<StateCard>findAllByDate(Date date);
}
