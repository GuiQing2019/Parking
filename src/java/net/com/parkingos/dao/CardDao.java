package net.com.parkingos.dao;

import net.com.parkingos.bean.Card;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/18
 */
public interface CardDao {
    boolean addCard(Card card);

    List<Card> queryAllCard();

    Card findCard(String id);

    boolean delCard(String id);

    boolean updateCard(Card card);
}
