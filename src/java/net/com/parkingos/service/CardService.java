package net.com.parkingos.service;

import net.com.parkingos.bean.Card;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/18
 */
public interface CardService {
    /**添加IC卡信息
     * @param card
     * @return
     */
    boolean addCard(Card card);

    /** 返回所有IC卡信息
     * @return
     */
    List<Card> queryAllCard();

    /** 返回特定用户ID卡信息
     * @param id
     * @return
     */
    Card findCard(String id);

    /**删除特定用户信息
     * @param id
     * @return
     */
    boolean delCard(String id);
}
