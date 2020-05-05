package net.com.parkingos.dao;

import net.com.parkingos.bean.Seat;
import net.com.parkingos.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/15
 */
public class SeatDaoImpl implements SeatDao {
    @Override
    public boolean addSeat(Seat seat) {

        boolean result=false;

        Connection con = JdbcUtils.getcon();
        String sql="insert into seat(seat_id,seat_num,seat_section,seat_state,seat_tag) values(?,?,?,?,?);";
        QueryRunner qr=new QueryRunner();
        Object[] params={seat.getSeat_id(),seat.getSeat_num(),seat.getSeat_section(),seat.getSeat_state(),seat.getSeat_tag()};

        try {
            int result1 = qr.update(con, sql, params);
            if (result1>0){
                result=true;
            }else {
                System.out.println("dao层出错");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.closeAll(con);
        }
        return result;
    }

    @Override
    public List<Seat> queryAllSeat() {

        QueryRunner qr=new QueryRunner();
        String sql="select * from seat";
        BeanListHandler<Seat> beanListHandler=new BeanListHandler<Seat>(Seat.class);
        Connection con = JdbcUtils.getcon();

        try {
            List<Seat> seats = qr.query(con, sql, beanListHandler);
            return seats;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public boolean delSeat(String id) {
        QueryRunner qr=new QueryRunner();
        String sql="delete from seat where seat_id = ?";
        Object[] params={id};
        Connection con = JdbcUtils.getcon();
        try {
            int update = qr.update(con, sql, params);
            if (update>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Seat findSeat(String id) {
        QueryRunner qr=new QueryRunner();
        String sql="select * from seat where seat_id=?";
        Object[] params={id};
        BeanHandler<Seat> bh=new BeanHandler<Seat>(Seat.class);
        Connection con = JdbcUtils.getcon();
        try {
            Seat seat = qr.query(con, sql, bh, params);
            return seat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateSeatId(Seat seat) {
        QueryRunner qr=new QueryRunner();
        String sql="update seat set seat_num =?,seat_section = ?,seat_state =?,seat_tag =? where seat_id =?";
        Object[] params={seat.getSeat_num(),seat.getSeat_section(),seat.getSeat_state(),seat.getSeat_tag(),seat.getSeat_id()};
        Connection con = JdbcUtils.getcon();

        try {
            int result = qr.update(con, sql, params);
            if (result>0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
