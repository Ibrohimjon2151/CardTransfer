package com.example.task1.entity;

import com.example.task1.entity.template.AbcIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbcIdEntity {
    private String userName;
    private String nummber;
    private double balance;
    private Date experiDate;
    private boolean active=true;
}
