package net.com.parkingos.dao;

import net.com.parkingos.bean.Seat;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/15
 */
public interface SeatDao {
    boolean addSeat(Seat seat);

    List<Seat> queryAllSeat();

    boolean delSeat(String id);

    Seat findSeat(String id);

    boolean updateSeatId(Seat seat);
}
