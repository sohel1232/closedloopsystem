package com.card91.closedloopsystem.service;

import com.card91.closedloopsystem.entity.Card;
import com.card91.closedloopsystem.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardManager implements CardService{
    CardRepository cardRepository;

    @Autowired
    public CardManager(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card findCardById(Long cardId) {
        return cardRepository.findById(cardId).get();
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
