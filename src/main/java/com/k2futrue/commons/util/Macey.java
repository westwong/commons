package com.k2futrue.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 参考雪花算法设计的id生成器。
 * 不过效率就比起雪花的每秒26万个这个每秒大概只能12000个
 * 不过 id 可读性比较高，"yyMMddHHmmssSSS+机房+机器+序列号"
 * 
 * @author West
 * @date create in 2019/9/23
 */
public class Macey {



    private long MAX_SEQUENCE = 99;

    private String datacenterId;  //数据中心
    private String machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    public Macey(long datacenterId, long machineId) {
        if (datacenterId > 100 || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > 100 || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId  < 10 ? "0"+datacenterId : ""+datacenterId;
        this.machineId = machineId  < 10 ? "0"+machineId : ""+machineId;

    }

    /**
     * 产生下一个ID
     */
    public synchronized String nextKey() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增

            sequence = sequence+1 ;
            if (sequence > MAX_SEQUENCE) {
                sequence = 0;
            }
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;
        Date date = new Date(currStmp);
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String time = format.format(date);
        StringBuffer sb = new StringBuffer();
        return  sb.append(time).append(datacenterId).append(machineId).append(sequence).toString() ;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

//    public static void main(String[] args) {
//        Macey westId = new Macey(1,1);
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            String x = westId.nextKey();
//            System.out.println( x);
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
//
//
//    }

}
