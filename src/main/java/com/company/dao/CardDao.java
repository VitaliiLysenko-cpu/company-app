package com.company.dao;

import com.company.model.Card;

public interface CardDao extends CRUDDao<Card> {
    public void create(Card card);

    public Card read(Long id);

    public void update(Card card);

    public void delete(Card card);
}
