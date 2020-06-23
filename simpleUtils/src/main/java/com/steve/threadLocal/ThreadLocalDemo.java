package com.steve.threadLocal;

/**
 * @Description:  线程缓存数据工具类 ，这是个使用案例
 *   threadlocal 可以用来放本次request里面的东西， 或者管理链接等，具体看场景
 *          可以用来解决部分线程并发问题，共享资源的多线程访问
 * @Author: SteveTao
 * @Date: 2020/3/617:11
 **/
public class ThreadLocalDemo {

    //private static ThreadLocal<T> threadCache=new ThreadLocal<T>();
    private static ThreadLocal<String> threadCache=new ThreadLocal<String>();

    private ThreadLocalDemo() {
    }

//============== 这里的线程指的是当前线程，如果在main方法中即为main线程 ============
    //往线程存储中放值
    public static void setValues(String value){
        threadCache.set(value);
    }

    //从线程存储中取值
    public static String getValues(){
        return threadCache.get();
    }

    //释放线程缓存
    public static void  cleanCache(){
        threadCache.remove();
    }




    public static void main(String[] args) {
        ThreadLocalDemo.setValues("main-thread");

        Thread t1=new Thread(new Runnable() {
            public void run() {
                ThreadLocalDemo.setValues("t1-tread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String mainStr = ThreadLocalDemo.getValues();
                System.out.println(Thread.currentThread().getName()+": "+mainStr);
            }
        },"t1");
        t1.start();

        String mainStr = ThreadLocalDemo.getValues();
        System.out.println(Thread.currentThread().getName()+": "+mainStr);
    }

}
