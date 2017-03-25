package netgloo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import netgloo.ScanQRResponse;
import netgloo.SendMoneyResource;
import netgloo.models.SendMoneyResponse;
import netgloo.models.UserDao;

@Controller
public class UserController {

	SendMoneyResponse sendMoneyResponse = new SendMoneyResponse();
	ScanQRResponse scanQRResponse = new ScanQRResponse();
	String filePath =new java.io.File("").getAbsolutePath();
	private String amount;

	@RequestMapping(path = "/update", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestBody SendMoneyResource sendMoneyResource) {
		try {
			sendMoneyResponse.setSuccess(true);
			sendMoneyResponse.setPayer(sendMoneyResource.getPayer());
			sendMoneyResponse.setPayee(sendMoneyResource.getPayee());
			sendMoneyResponse.setAmount(sendMoneyResource.getAmount());
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

	@RequestMapping("/getQRCode")
	@ResponseBody
	public SendMoneyResponse getQRCode(@RequestBody SendMoneyResource sendMoneyResource) {
		String userId;
		try {
			if (sendMoneyResource.getPayer() != null && sendMoneyResource.getAmount() > 0) {
				sendMoneyResponse.setSuccess(true);
				sendMoneyResponse.setPayer(sendMoneyResource.getPayer());
				sendMoneyResponse.setPayee(sendMoneyResource.getPayee());
				sendMoneyResponse.setComments("QR created for the requested amount");
				sendMoneyResponse.setAmount(sendMoneyResource.getAmount());
				System.out.println("file path is :: " + filePath);
				createQRCode(sendMoneyResponse, filePath + "\\qrcode.jpeg", "UTF-8", 200, 200);
			}
		} catch (Exception ex) {
			if(sendMoneyResource.getPayer() != null){
			sendMoneyResponse.setComments("Invalid User name");
			sendMoneyResponse.setSuccess(false);
			}else{
				sendMoneyResponse.setComments("Invalid amount!! Please enter a valid amount");
				sendMoneyResponse.setSuccess(false);
			}
			return sendMoneyResponse;
		}
		return sendMoneyResponse;
	}
	
	public void createQRCode(SendMoneyResponse qrCodeData, String filePath,
			String charset,  int qrCodeheight, int qrCodewidth ) throws WriterException, IOException{
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getAmount().toString().getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}
	
	@RequestMapping("/scanQRCode")
	@ResponseBody
	public ScanQRResponse scanQRCode() {
		String userId;
		try {
				amount = readQRCode(filePath + "\\qrcode.jpeg", "UTF-8");
				scanQRResponse.setStatus(true);
				scanQRResponse.setAmount(amount);
				scanQRResponse.setComments("Amount Recived!!");
				scanQRResponse.setCurrency_Code("INR");
				System.out.println("file path is :: " + filePath);
				
		} catch (Exception ex) {
			scanQRResponse.setComments("User not found");
			scanQRResponse.setStatus(false);
			return scanQRResponse;
		}
		return scanQRResponse;
	}
	
	public String readQRCode(String filePath, String charset)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
		return qrCodeResult.getText();
	}

	
	public void validate() {

	}

	@Autowired
	private UserDao userDao;

} // class UserController
