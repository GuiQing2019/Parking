package net.com.parkingos.service;

import net.com.parkingos.bean.Card;
import net.com.parkingos.dao.CardDao;
import net.com.parkingos.dao.CardDaoImpl;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/18
 */
public class CardServiceImpl implements CardService {

    CardDao dao=new CardDaoImpl();

    @Override
    public boolean addCard(Card card) {
        return dao.addCard(card);
    }

    @Override
    public List<Card> queryAllCard() {
        return dao.queryAllCard();
    }

    @Override
    public Card findCard(String id) {
        return dao.findCard(id);
    }

    @Override
    public boolean delCard(String id) {
        return dao.delCard(id);
    }
}
