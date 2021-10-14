package com.example.task1.entity;

import com.example.task1.entity.template.AbcIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateCard extends AbcIdEntity {

    private Date date;
    private String incom;
    private String output;
    private String state;
    private double amount;
    @ManyToOne
    private Card card;

}
