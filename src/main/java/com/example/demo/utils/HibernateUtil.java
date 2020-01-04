package com.example.demo.utils;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public class HibernateUtil {
    /**
     * @Description 强制加载hibrtnate的代理对象
     * @Author wanxin
     * @Date 2020/1/3 18:01
     * @Param [entity]
     * @return T
     **/
    public static <T> T initializeAndUnproxy(T entity){
        if (entity == null) {
            throw new NullPointerException("Entity passed for initialization is null");
        }
        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}
