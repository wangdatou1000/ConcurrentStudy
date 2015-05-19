
public class VolatileTestv2 {
	private static volatile long time = System.currentTimeMillis();
	
	  static  void one() {
		  time = System.currentTimeMillis();  
	    }
	 
	    static void two() {
	            System.out.println("time:"+time);
	    }
	public static void main(StringStudy[] args) {
		Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
            	while(true){
                one();
                }
            }
        });
		t1.start();
		 for (int i = 0; i < 20000; i++) {
	            Thread t2 = new Thread(new Runnable() {
	                @Override
	                public void run() {
	                	while(true){
	                    two();
	                    try {
	        				Thread.sleep(600);
	        			} catch (InterruptedException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	        	 
	                    }
	                }
	            });
	            t2.start();
	        }
	 
	        try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	    }


}
