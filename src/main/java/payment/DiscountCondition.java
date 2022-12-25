package payment;

public class DiscountCondition {

	private double discountValue;
	private boolean firstTimeOnly;
	
	public DiscountCondition(double discountValue, boolean firstTimeOnly) {
		super();
		this.discountValue = discountValue;
		this.firstTimeOnly = firstTimeOnly;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public boolean isFirstTimeOnly() {
		return firstTimeOnly;
	}
	
	
}
