package application;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

public class App {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		DateTimeFormatter dtfBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Entre os dados do contrato");
			System.out.print("Numero : ");
			Integer number = input.nextInt();
			System.out.print("Data (dd/MM/yyyy): ");
			LocalDate date = LocalDate.parse(input.next(), dtfBR);
			System.out.print("Valor do contrato: ");
			Double contractValue = input.nextDouble();
			
			Contract contract = new Contract(number, date, contractValue);
			
			System.out.print("Entre com o numero de parcelas : ");
			Integer installments = input.nextInt();
			ContractService contractService = new ContractService(new PaypalService());
			contractService.processContract(contract, installments);
			
			System.out.println(contract);
			
		}  catch (InputMismatchException e) {
			System.out.println("Parse date exception : " + e.getMessage());
		}
	}
}
