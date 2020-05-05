package net.com.parkingos.test;

import net.com.parkingos.bean.Seat;
import net.com.parkingos.dao.SeatDao;
import net.com.parkingos.dao.SeatDaoImpl;
import net.com.parkingos.dao.UserDao;
import org.junit.Test;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/15
 */
public class Test02 {

    SeatDao seatDao=new SeatDaoImpl();

    @Test
    public void testadd(){
        Seat seat=new Seat("158","ssss","ssss",1,"无敌");

        boolean b = seatDao.addSeat(seat);
        System.out.println(b);
    }

    @Test
    public void testqueryall(){
        List<Seat> seats = seatDao.queryAllSeat();
        for (Seat seat : seats) {
            System.out.println(seat);
        }
    }

    @Test
    public void testfindSeat(){
        Seat seat = seatDao.findSeat("20150521182316");

        seat.setSeat_section("B");
        boolean b = seatDao.updateSeatId(seat);
        System.out.println(b);
    }

}
