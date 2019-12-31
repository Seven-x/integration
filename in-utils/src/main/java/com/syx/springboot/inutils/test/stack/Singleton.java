package com.syx.springboot.inutils.test.stack;

/**
 * @author shaoyx
 * @date 15:39  2019/12/30
 */
public class Singleton {
    private volatile static Singleton instance;

    public Singleton() {
    }

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }


}
enum SingletonEnum{
    INSTANCE;
    SingletonEnum() {
    }
}
