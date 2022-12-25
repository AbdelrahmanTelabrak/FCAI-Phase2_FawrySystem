package payment;



public abstract class ReceiptDecorator implements BaseReciept{

	protected BaseReciept reciept;

	public ReceiptDecorator(BaseReciept reciept) {
		super();
		this.reciept = reciept;
	}

	public abstract Order getOrderDetails() ;
	
	
}
