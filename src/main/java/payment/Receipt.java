package payment;

import java.util.ArrayList;

public class Receipt implements BaseReciept{

	private Order order;
	
	public Receipt(Order o) {
		this.order = o;
	}
	@Override
	public Order getOrderDetails() {
		// TODO Auto-generated method stub
		return order;
	}

}
