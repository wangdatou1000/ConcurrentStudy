
public class StringStudy {
	private static void test1() {
		String a = "a" + "b" + 1;
		String b = "ab1";
		System.out.println(a == b);
	}
	private static String getA() {return"a";}
	public static void test2() {
		String a = "a";
		final String c = "a";

		String b = a + "b";
		String d = c + "b";
		String e = getA() + "b";

		String compare = "ab";
		System.out.println(b == compare);
		System.out.println(d == compare);
		System.out.println(e == compare);
		}
	public static void test3() {
		String a = "a";
		String b = a + "b";
		String c = "ab";
		String d = new String(b);
		System.out.println(b == c);
		System.out.println(c == d);
		System.out.println(c == d.intern());
		System.out.println(b.intern() == d.intern());
	}
	public static  void main(String[] a){
		StringStudy.test1();
		StringStudy.test2();
		StringStudy.test3();
	}
}
