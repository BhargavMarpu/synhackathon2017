package netgloo;

public class SendMoneyResource {

	private Integer payer;
	private Integer payee;
	private Integer amount;
	private String currency_Code;
	private String message;
	
	public Integer getPayer() {
		return payer;
	}
	public void setPayer(Integer payer) {
		this.payer = payer;
	}
	public Integer getPayee() {
		return payee;
	}
	public void setPayee(Integer payee) {
		this.payee = payee;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCurrency_Code() {
		return currency_Code;
	}
	public void setCurrency_Code(String currency_Code) {
		this.currency_Code = currency_Code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
