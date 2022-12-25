package payment;

public class Discount extends ReceiptDecorator{

	private double discountValue;

	public Discount(BaseReciept reciept, double discountValue) {
		super(reciept);
		this.discountValue = discountValue;
	}

	@Override
	public Order getOrderDetails() {
		// TODO Auto-generated method stub
		double initialPrice = Double.valueOf(super.reciept.getOrderDetails().getServiceePrice());
		double finalPrice = initialPrice - (initialPrice*discountValue);
		Order order = new Order(super.reciept.getOrderDetails().getEmail(),super.reciept.getOrderDetails().getServiceName(), String.valueOf(finalPrice));
		return order;
	}

}
