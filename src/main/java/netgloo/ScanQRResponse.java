package netgloo;

public class ScanQRResponse {

		private boolean status;
		private String comments;
		private Integer receiverAccountNumber;
		private String amount;
		private String currency_Code;
		
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		
		
		
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public Integer getReceiverAccountNumber() {
			return receiverAccountNumber;
		}
		public void setReceiverAccountNumber(Integer receiverAccountNumber) {
			this.receiverAccountNumber = receiverAccountNumber;
		}
		
		
		
		public String getAmount() {
			return amount;
		}
		public void setAmount(Integer amount) {
			this.amount = Integer.toString(amount);
		}
		public String getCurrency_Code() {
			return currency_Code;
		}
		public void setCurrency_Code(String currency_Code) {
			this.currency_Code = currency_Code;
		}
		
		
		
	}

