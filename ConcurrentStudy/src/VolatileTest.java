
public class VolatileTest {
	   static int i = 0, j = 0;
	  //static volatile int i = 0, j = 0;
	 
	    static synchronized void one() {
	        i++;
	        j++;    
	    }
	 
	    static void two() {
	        if (i != j) {
	            System.out.println("i=" + i + " j=" + j);
	        }
	    }
	public static void main(StringStudy[] args) throws InterruptedException {
		  for (int i = 0; i < 20000; i++) {
	            Thread t1 = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	try {
							Thread.sleep(60);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    one();
	                }
	            });
	            Thread t2 = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    two();
	                }
	            });
	 
	            t1.start();
	            t2.start();
	        }
	 
	        Thread.sleep(6000);
	 
	    }

	}

