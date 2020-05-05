package net.com.parkingos.service;

import net.com.parkingos.bean.Seat;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/15
 */
public interface SeatService {
    /**添加车位信息
     * @param seat
     * @return
     */
    boolean addSeat(Seat seat);

    /** 查询所有车位信息
     * @return
     */
    List<Seat> queryAllSeat();

    /** 删除车位信息
     * @param id
     * @return
     */
    boolean delSeat(String id);

    /** 查找车位信息
     * @param id
     * @return
     */
    Seat findSeat(String id);

    /**修改车位信息
     * @param seat
     * @return
     */
    boolean updateSeatId(Seat seat);
}
