package com.k2futrue.commons.util;

/**
 * @author West
 * @date create in 2020/2/20
 */
public interface PageProp {

    default int getPageNum(){
        return 1;
    }
    default int getPageSize(){
        return 10;
    }
}
