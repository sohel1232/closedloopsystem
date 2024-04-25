package com.card91.closedloopsystem.restcontroller;

import com.card91.closedloopsystem.entity.Card;
import com.card91.closedloopsystem.entity.User;
import com.card91.closedloopsystem.service.CardService;
import com.card91.closedloopsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public UserController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @RequestMapping(value = "/{userId}/cards",method = RequestMethod.GET)
    public ResponseEntity<?> getCards(@PathVariable Long userId){
        try{
            User user = userService.findUserById(userId);
            List<Card> cards = user.getCards();
            return new ResponseEntity<>(cards,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{userId}/cards/{cardId}",method = RequestMethod.GET)
    public ResponseEntity<?> getCardDetail(@PathVariable Long userId,
                                           @PathVariable Long cardId,
                                           Authentication authentication){
        try{
            //still have to implement to confirm whether the card belongs to user or not
            //if belongs to user only then retrieve the card info
            //once user authentication is implemented fetch card throught user like this
//            Card card = user.getCards().stream()
//                    .filter(c -> c.getCardId().equals(cardId))
//                    .findFirst()
//                    .orElse(null);

            System.out.println("HELLLOOOG " + authentication.getPrincipal());


            Card card = cardService.findCardById(cardId);
            System.out.println("Card is " + card);
            return new ResponseEntity<>(card,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/{userId}/cards/{cardId}",method = RequestMethod.PUT )
    public ResponseEntity<?> updateCard(@PathVariable Long userId,
                                        @PathVariable Long cardId,
                                        @RequestBody Card updateCardInfo){
        try{
            //still have to implement to confirm whether the card belongs to user or not
            //if belongs to user only then update the card info
            Card card = cardService.findCardById(cardId);
            card.setCardStatus(updateCardInfo.getCardStatus());
            card.setDailySpentMaxLimit(updateCardInfo.getDailySpentMaxLimit());
            cardService.save(card);
            return new ResponseEntity<>(card,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.FORBIDDEN);
        }
    }

}
