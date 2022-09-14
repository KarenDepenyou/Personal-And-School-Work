package file;

public class ComputeInterest {
	
	public static double simpleInterest(double principal, double rate, double year) {
		double simpleIntr= principal+(principal*(rate/100)*year);
		return simpleIntr;
	}
	public static double compoundInterest(double principal, double rate, double year) {
		double calc1= 1+(rate/100);
		double calc2= Math.pow(calc1, year);
		double compoundIntr= principal*calc2;
		return compoundIntr;
	}

}
