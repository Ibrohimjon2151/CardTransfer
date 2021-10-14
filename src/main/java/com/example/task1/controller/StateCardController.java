package com.example.task1.controller;

import com.example.task1.entity.Card;
import com.example.task1.entity.StateCard;
import com.example.task1.peliod.KnowSate;
import com.example.task1.repository.CardRepositoryMy;
import com.example.task1.repository.StateRepository;
import com.example.task1.security.JwtToken;
import javafx.animation.Animation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stateCard")
public class StateCardController {

    @Autowired
    JwtToken jwtToken;

    @Autowired
    CardRepositoryMy cardRepositoryMy;

    @Autowired
    StateRepository stateRepository;
    @PostMapping
    public HttpEntity<?> getCardState(HttpServletRequest request , @RequestBody KnowSate knowState){
        String token = request.getHeader("Authorization");
        if ((token != null && token.startsWith("Bearer"))) {
            token = token.substring(7);
            String userNameByToken = jwtToken.getUserNameByToken(token);
            Optional<Card> cardByUserName = cardRepositoryMy.findCardByNummber(knowState.getCardNummber());

            int id = cardByUserName.get().getId();

            List<StateCard> allByCard_id = stateRepository.findAllByCard_Id(id);

            return ResponseEntity.ok(allByCard_id);
        }
        return ResponseEntity.ok("Not Found");
    }

}
