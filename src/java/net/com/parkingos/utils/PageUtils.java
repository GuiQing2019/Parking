package net.com.parkingos.utils;

import java.util.List;

/**
 * @author ASUS
 * @date 2020/4/9
 */
public class PageUtils {

    /**
     * @author ASUS
     * @date 2020/4/2
     * 分页的实现：
     * 1. 前端页面
     * 2. 服务器代码（封装一个分页类，把分页所有数据进行封装。把这个对象发送到页面展示）
     */

    //分页数据的封装

    //当前页面
    private int currentPage;

    //页面数据大小
    private int pageSize;

    //数据的总数
    private int totalCount;

    //分页导航条个数（模仿百度，分页导航条）
    private int navCount;

    //分页查询从哪个位置开始
    private int startIndex;

    //第一页
    private int firestPage;
    //最后一页
    private int lastPage;

    //上一页
    private int prePage;

    //下一页
    private int nextPage;

    //导航条开始
    private int statNav;
    private int endNav;

    //页面存放数据的集合
    private List pageData;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getNavCount() {
        return navCount;
    }

    public void setNavCount(int navCount) {
        this.navCount = navCount;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getFirestPage() {
        return firestPage;
    }

    public void setFirestPage(int firestPage) {
        this.firestPage = firestPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getStatNav() {
        return statNav;
    }

    public void setStatNav(int statNav) {
        this.statNav = statNav;
    }

    public int getEndNav() {
        return endNav;
    }

    public void setEndNav(int endNav) {
        this.endNav = endNav;
    }

    public List getPageData() {
        return pageData;
    }

    public void setPageData(List pageData) {
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "currentPage=" + currentPage +
                ", statNav=" + statNav +
                ", endNav=" + endNav +
                '}';
    }

    /**
     * 初始化数据
     *
     * @param currentPage 当前页面
     * @param pageSize    页面大小
     * @param totalCount  总数
     * @param navCount    导航条个数
     *                    有些数据是必须的
     *                    其他数据可以通过计算得出
     */
    public PageUtils(int currentPage, int pageSize, int totalCount, int navCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.navCount = navCount;
        //计算其他数据 所有数据
        this.startIndex = (currentPage - 1) * pageSize;
        //第一页
        this.firestPage = (1);

        //最后一页  总数/页面大小
        if (totalCount % pageSize == 0) {

            this.lastPage = totalCount / pageSize;
        } else {
            this.lastPage = totalCount / pageSize + 1;
        }

        //上一页
        this.prePage = this.currentPage - 1;

        //下一页
        this.nextPage = this.currentPage + 1;

        //导航条开始位置和结束位置


        if (this.lastPage <= this.navCount) {
            //数据页面不够navCount

            this.statNav = 1;
            this.endNav = this.lastPage;

        } else {

            //情况1：1-6的时候，导航条开始位置是1，结束位置是10
            if (currentPage >= 1 && currentPage <= 6) {
                this.statNav = 1;
                this.endNav = 10;
                //情况2 72-76 5页导航条开始位置67，结束位置76
            } else if (currentPage >= (this.lastPage - this.navCount / 2 + 1) && currentPage <= this.lastPage) {
                this.statNav = this.lastPage - this.navCount + 1;
                this.endNav = this.lastPage;

                //情况3 左边有5个，右边有4个
            } else {
                this.statNav = this.currentPage - this.navCount / 2;
                this.endNav = this.currentPage + this.navCount / 2 - 1;
            }

        }


    }


}
