package com.k2futrue.commons.util;

import org.springframework.util.Assert;

/**
 * mybatis plus分页工具
 *
 * @author West
 * @since  create in 2019/9/10
 */
public class PageUtil {

    private static final int MAX_PAGE = 2000;
    private static final int MAX_LIMIT = 10000;


    public static <T> Page page(int page, int size, Class<T> clazz) {
        Assert.isTrue(page <= MAX_PAGE, "pageNum number too large");
        Assert.isTrue(size <= MAX_LIMIT, "pageSize number too large");
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 10;
        }
        return new Page(page, size);
    }

    public static <T> Page page(PageProp pageProp, Class<T> clazz) {
        return page(pageProp.getPageNum(), pageProp.getPageSize(), clazz);
    }
    public static Page page(PageProp pageProp) {
        return page(pageProp.getPageNum(), pageProp.getPageSize(), Object.class);
    }
}
