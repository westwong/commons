package com.k2futrue.commons.util;

/**
 * @author West
 * @date create in 2019/10/11
 */
public class PriceUtil {

    /**
     * 保留2位小数
     */
    public static int wrapPrice(String price) {
        return wrapPrice(price, 2);
    }

    /**
     * 将小数形式的价格保存为整数形式, 四舍五入
     *
     * 价格为空 视为0
     *
     * @param price 价格
     * @param scale 价格保留的有效小数位数
     * @return int
     */
    public static int wrapPrice(String price, int scale) {

        if (org.apache.commons.lang3.StringUtils.isEmpty(price)) {
            price = "0";
        }

        double pow = Math.pow(10, scale);

        Double p;
        try {
            p = Double.valueOf(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("输入不正确, 应是整数或小数: " + price);
        }

        return (int) Math.round(p * pow);
    }

    /**
     * 转换字符串数字类型, 对异常统一处理
     *
     * @param scale 对原数值的放大倍数, 0(原值返回), 2=100(保留2位), 3=1000(保留3位), 其他值均抛出异常
     */
    public static int string2Int(String num, int scale) {
        num.trim();
        StringBuilder builder = new StringBuilder();
        int i = num.indexOf(".");
        if (i < 0) {
            builder.append(num);
            for (int j = 0; j < scale; j++) {
                builder.append("0");
            }
        } else {
            builder.append(num, 0, i);
            for (int j = i + 1; j < i + 1 + scale; j++) {
                if (num.length() > j) {
                    builder.append(num.charAt(j));
                } else {
                    builder.append("0");
                }
            }
        }
        try {
            return Integer.valueOf(builder.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(builder.toString()+"不是数字");
        }
    }
}
