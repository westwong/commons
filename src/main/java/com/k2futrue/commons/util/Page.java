package com.k2futrue.commons.util;



/**
 * @author West
 * @since  create in 2020/2/20
 */
public class Page implements PageProp {

    public int pageNum;

    public int pageSize;

    @Override
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page(){

    }
    public Page(int pageNum,int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
