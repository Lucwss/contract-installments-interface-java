package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	// ****************************** other methods ******************************

	public void processContract(Contract contract, Integer months) {
		Double totalValuePerMonth = contract.getTotalValue() / months;
		
		for(Integer i = 0; i < months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i+1);
			Double interest = totalValuePerMonth + getInterest(totalValuePerMonth, (i+1));
			Double fee = interest + getPaymentFee(interest); 
			contract.getInstallmetns().add(new Installment(dueDate, fee));
		}
	}
	
	private Double getInterest(Double totalValuePerMonth, Integer month) {
		return this.getOnlinePaymentService().interest(totalValuePerMonth, month);
	}
	
	private Double getPaymentFee(Double interest) {
		return this.getOnlinePaymentService().paymentFee(interest);
	}
}	
