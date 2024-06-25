
public class Main {
	public static void main(String[] args) {
		int a=4,b=3,c=5;
		f(a,b); f(a,c); f(b,c);
		System.out.println(a+"  "+b+" "+c);
	}
	static void f(int x,int y) {
		int t;
		if(x<y) {
			t=x;
			x=y;
			y=t;
		}
	}
}
