package netgloo.models;

public class SendMoneyResponse {

	private boolean success;
	private Integer payer;
	private Integer payee;
	private Integer amount;
	private String comments;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	
	
	
}
