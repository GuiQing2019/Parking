package net.com.parkingos.test;

import net.com.parkingos.bean.Card;
import net.com.parkingos.dao.CardDao;
import net.com.parkingos.dao.CardDaoImpl;
import org.junit.Test;

/**
 * @author ASUS
 * @date 2020/4/18
 */
public class Test03 {
    @Test
    public void test03(){
        CardDao dao=new CardDaoImpl();
        Card card=new Card("1","2","3","4","5","6");
        boolean b = dao.addCard(card);
        System.out.println(b);
    }
}
