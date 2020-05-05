package net.com.parkingos.service;

import net.com.parkingos.bean.Seat;
import net.com.parkingos.dao.SeatDao;
import net.com.parkingos.dao.SeatDaoImpl;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/15
 */
public class SeatServiceImpl  implements SeatService{
    SeatDao dao=new SeatDaoImpl();
    @Override
    public boolean addSeat(Seat seat) {
        return dao.addSeat(seat);
    }

    @Override
    public List<Seat> queryAllSeat() {
        return dao.queryAllSeat();
    }

    @Override
    public boolean delSeat(String id) {
        return dao.delSeat(id);
    }

    @Override
    public Seat findSeat(String id) {
        return dao.findSeat(id);
    }

    @Override
    public boolean updateSeatId(Seat seat) {
        return dao.updateSeatId(seat);
    }
}
