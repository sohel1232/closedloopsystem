package com.card91.closedloopsystem.service;

import com.card91.closedloopsystem.entity.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    Card findCardById(Long cardId);

    void save(Card card);
}
