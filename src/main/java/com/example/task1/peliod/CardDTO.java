package com.example.task1.peliod;

import com.example.task1.entity.template.AbcIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String UserName;
    private String nummber;
    private double balance;
//    private Date experiDate;
//    private boolean active;
}
