/**
 * volatile�ؼ��ֵĲ���
 * @author trytocatch
 * @date 2013-1-7
 */
public class Volatile {
    long time1;//test����ʱ��ʹ���������ı���������ʱ�䣬������ʹ��ͬ������t1,t2���Ӱ��
    long time2;
    volatile
    boolean boolValue=true;//volatile
    public static void main(StringStudy[] args) throws InterruptedException{
        int size=50;//���Ը���
        Volatile vs[]=new Volatile[size];
        long timeSum = 0;
        for(int n=0;n<size;n++)
            (vs[n]=new Volatile()).test();
//        Thread.sleep(1000);
        for(int n=0;n<size;n++){//ͳ�Ƴ��������̴߳�boolValue��Ϊfalse��while(boolValue)��������ʱ����ܺ�
            timeSum+=vs[n].time2 - vs[n].time1;
            System.out.print(n+"\t"+vs[n].time2 +'\t' + vs[n].time1+'\t'+(vs[n].time2 - vs[n].time1)+'\n');
        }
        System.out.println("��Ӧʱ���ܺ�(��΢��)��"+timeSum);
        long time1,time2;
        time1 = System.nanoTime();
//        Thread.yield();
        time2 = System.nanoTime();
        System.out.println(time2-time1);//˳��ִ����������ʱ���������ο�
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
        t1.join();//��֤һ��ֻ����һ�����ԣ��Դ˼��������̵߳ĵ��ȶ� t2��boolValue����Ӧʱ�� ��Ӱ��
        t2.join();
    }
}