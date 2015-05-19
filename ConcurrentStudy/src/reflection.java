import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class reflection {
	private String name;
	public reflection(String name){
		this.name=name;
	}

	public static void main(String[] args) {
		String a="test";
		Class ac=a.getClass();
		Method[] methods = a.getClass().getMethods();
        System.out.println(ac.getName()+"---"+ac.getSimpleName());
        System.out.println(ac.getSuperclass());
        Class[] interfaces = ac.getInterfaces();
        
        
        Constructor constructor;
		try {
			constructor = reflection.class.getConstructor(String.class);
			reflection myObject = (reflection)constructor.newInstance("constructor-arg1");
	        System.out.println(myObject.name);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
        for(Class cls : interfaces){
 		   System.out.println("cls = " + cls.getName());
 		}
        
        
		for(Method method : methods){
		   // System.out.println("method = " + method.getName());
		}

	}

}
