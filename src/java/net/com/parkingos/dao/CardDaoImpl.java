package net.com.parkingos.dao;

import net.com.parkingos.bean.Card;
import net.com.parkingos.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/18
 */
public class CardDaoImpl implements CardDao {
    @Override
    public boolean addCard(Card card) {
        QueryRunner qr=new QueryRunner();
        String sql="insert into card(card_id,seat_id,user_name,user_gender,user_addr,car_num) values(?,?,?,?,?,?)";
        Object[] params={card.getCard_id(),card.getSeat_id(),card.getUser_name(),card.getUser_gender(),card.getUser_addr(),card.getCar_num()};
        Connection con = JdbcUtils.getcon();

        try {

            int update = qr.update(con, sql, params);
            if (update>0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public List<Card> queryAllCard() {
        QueryRunner qr=new QueryRunner();
        String sql="select * from card";
        Connection con = JdbcUtils.getcon();
        BeanListHandler<Card> bh=new BeanListHandler<>(Card.class);

        try {
            List<Card> query = qr.query(con, sql, bh);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Card findCard(String id) {
        QueryRunner qr=new QueryRunner();
        String sql="select * from card where card_id =?";
        Object[] params={id};
        Connection con = JdbcUtils.getcon();
        BeanHandler<Card> bh=new BeanHandler<>(Card.class);

        try {
            Card card = qr.query(con, sql, bh, params);
            return card;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public boolean delCard(String id) {
        QueryRunner qr=new QueryRunner();
        String sql="delete from card where card_id =?";
        Object[] parms={id};
        Connection con = JdbcUtils.getcon();

        try {
            int result = qr.update(con, sql, parms);
            if (result>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateCard(Card card) {
        QueryRunner qr=new QueryRunner();
        String sql="update card set seat_id=?,user_name=?,user_gender=?,user_addr=?,car_num=?where card_id=?";
        Connection con = JdbcUtils.getcon();
        Object[] parms={card.getSeat_id(),card.getUser_name(),card.getUser_gender(),card.getUser_addr(),card.getCar_num(),card.getCard_id()};

        try {
            int result = qr.update(con, sql, parms);
            if (result>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
