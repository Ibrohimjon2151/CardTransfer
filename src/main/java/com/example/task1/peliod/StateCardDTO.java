package com.example.task1.peliod;

import com.example.task1.entity.Card;
import com.example.task1.entity.template.AbcIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateCardDTO {

    private String incom;
    private String output;
    private double amount;
}
