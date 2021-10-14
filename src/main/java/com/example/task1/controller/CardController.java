package com.example.task1.controller;

import com.example.task1.entity.Card;
import com.example.task1.entity.StateCard;
import com.example.task1.peliod.StateCardDTO;
import com.example.task1.repository.CardRepositoryMy;
import com.example.task1.repository.StateRepository;
import com.example.task1.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    JwtToken jwtToken;

    @Autowired
    CardRepositoryMy cardRepositoryMy;

    @Autowired
    StateRepository stateRepository;

    @PostMapping
    public HttpEntity<?> add(HttpServletRequest servletResponse) {
        String token = servletResponse.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
            String userNameFromToken = jwtToken.getUserNameByToken(token);
            Card card = new Card();
            card.setUserName(userNameFromToken);
            card.setBalance(0d);
            card.setNummber(UUID.randomUUID().toString().substring(0, 4));
            card.setExperiDate(new Date(System.currentTimeMillis() + (1000L * 86400 * 365 * 2)));
            cardRepositoryMy.save(card);
            return ResponseEntity.ok("Karta ochildi");
        }
        return ResponseEntity.ok("Karta ochilmadi");
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> outOrInCome(HttpServletRequest request, @RequestBody StateCardDTO stateCardDTO) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
            String userNameFromToken = jwtToken.getUserNameByToken(token);
            Optional<Card> byUserName = cardRepositoryMy.findCardByUserName(userNameFromToken);

            if (byUserName.get().getNummber().equals(stateCardDTO.getOutput())) {
                StateCard stateCard = new StateCard();
                stateCard.setOutput("from " + userNameFromToken);
                Optional<Card> cardByNummber = cardRepositoryMy.findCardByNummber(stateCardDTO.getIncom());
                stateCard.setIncom("to " + cardByNummber.get().getUserName());
                stateCard.setAmount(stateCardDTO.getAmount());
                stateCard.setDate(new Date(System.currentTimeMillis()));
                stateCard.setCard(byUserName.get());
                double summa = stateCardDTO.getAmount() - (stateCardDTO.getAmount() / 100);
                if ((summa > byUserName.get().getBalance())) {
                    return ResponseEntity.ok("Yetarli mabla' yo'q");
                }
                byUserName.get().setBalance(byUserName.get().getBalance() - stateCardDTO.getAmount());
                cardRepositoryMy.save(byUserName.get());

                Optional<Card> byNummber = cardRepositoryMy.findCardByNummber(stateCardDTO.getIncom());
                Card card = byNummber.get();
                card.setBalance(card.getBalance() + summa);
                stateCard.setState(userNameFromToken + " dan " + card.getUserName() + " ga");
                cardRepositoryMy.save(card);


                stateRepository.save(stateCard);
            }
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.ok("Error!!!");
    }
}

