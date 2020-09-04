package com.ego.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. 本地线程设置和获取数据源信息
 *
 * @author liuweiwei
 * @since 2020-08-28
 */
public class DBHolder {
    /**
     * SLF4J 骚粉日志必备技能
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(DBHolder.class);

    /**
     * 对线程进行隔离(使用本地线程)
     */
    private static final ThreadLocal<DBEnum> HOLDER = new ThreadLocal<>();

    /**
     * 保证线程原子性(JUC特性)
     */
    private static final AtomicInteger ATOMIC = new AtomicInteger(-1);

    public static DBEnum get() {
        return HOLDER.get();
    }

    public static void set(DBEnum dbEnum) {
        HOLDER.set(dbEnum);
    }

    public static void clear() {
        HOLDER.remove();
    }

    public static void master() {
        set(DBEnum.WRITE);
        LOGGER.info("Master branch -> " + DBEnum.WRITE);
        System.out.println("切换到master");
    }

    public static void slave0() {
        int index = ATOMIC.getAndIncrement() % 2;
        if (index == 0) {
            set(DBEnum.READ);
            LOGGER.info("Slave0 branch -> " + DBEnum.READ);
            System.out.println("切换到slave0");
        }
    }
}
