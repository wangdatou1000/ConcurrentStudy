/**
 * volatile关键字的测试
 * @author trytocatch
 * @date 2013-1-7
 */
public class Volatile {
    long time1;//test运行时，使用两独立的变量来保存时间，避免因使用同步而对t1,t2造成影响
    long time2;
    volatile
    boolean boolValue=true;//volatile
    public static void main(StringStudy[] args) throws InterruptedException{
        int size=50;//测试个数
        Volatile vs[]=new Volatile[size];
        long timeSum = 0;
        for(int n=0;n<size;n++)
            (vs[n]=new Volatile()).test();
//        Thread.sleep(1000);
        for(int n=0;n<size;n++){//统计出，所有线程从boolValue变为false到while(boolValue)跳出所花时间的总和
            timeSum+=vs[n].time2 - vs[n].time1;
            System.out.print(n+"\t"+vs[n].time2 +'\t' + vs[n].time1+'\t'+(vs[n].time2 - vs[n].time1)+'\n');
        }
        System.out.println("响应时间总和(毫微秒)："+timeSum);
        long time1,time2;
        time1 = System.nanoTime();
//        Thread.yield();
        time2 = System.nanoTime();
        System.out.println(time2-time1);//顺序执行两条语句的时间间隔，供参考
    }

    public void test() throws InterruptedException{
        Thread t2=new Thread(){
            public void run(){
                while(boolValue)
                    ;
                time2 = System.nanoTime();
            }
        };
        Thread t1=new Thread(){
            public void run(){
                time1 = System.nanoTime();
                boolValue=false;
            }
        };
        t2.start();
        Thread.yield();
        t1.start();
        t1.join();//保证一次只运行一个测试，以此减少其它线程的调度对 t2对boolValue的响应时间 的影响
        t2.join();
    }
}