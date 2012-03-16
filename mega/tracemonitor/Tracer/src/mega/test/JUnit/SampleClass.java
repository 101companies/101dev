package mega.test.JUnit;

public class SampleClass {
	
	public int x;

	protected static int c;
	
	public SampleClass(int i){
		x=i;
	}
	
	
	
	public static void main(){
		
		c = 5;
	
		new SampleClass(c).mult(2);

	}
	

	
	public void mult(int y){
	
		int result;
		
		result=x*y;
		
		result=addone(result);
		
		System.out.println("Result:"+result);

		
	}
	
	private int addone(int x){
		return x+1;
	}
	
   

}


