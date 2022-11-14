package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Contract {
	private Integer number;
	private LocalDate date;
	private Double totalValue;
	
	private List<Installment> installmetns = new ArrayList<>();
	
	DateTimeFormatter dtfBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Contract(Integer number, LocalDate date, Double totalValue) {
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public List<Installment> getInstallmetns() {
		return installmetns;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		 sb.append("\n");
		 sb.append("Parcelas: ");
		 sb.append("\n");
		 for(Installment inst : this.getInstallmetns()) {
			 sb.append(dtfBR.format(inst.getDueDate()) + " - " + String.format("%.2f", inst.getAmount()));
			 sb.append("\n");
		 }
		return sb.toString();
	}
	
	
	
	
}
