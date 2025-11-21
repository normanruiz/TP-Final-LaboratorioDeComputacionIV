package dominio.entity;

import java.math.BigDecimal;
import dominio.entity.Enums.MOVEMENTSTYPE;
import java.time.LocalDateTime;

public class BankMovement {

	  private int id;
	  private Client client;
	  private BankAccount bankAccount;
	  private BigDecimal amount;
	  private MOVEMENTSTYPE typeMovements;
	  private String detail;
	  private LocalDateTime createdAt;
	  
	  public BankMovement(int id, Client client, BankAccount bankAccount, BigDecimal amount, MOVEMENTSTYPE typeMovements, String detail, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.client = client;
		this.bankAccount = bankAccount;
		this.amount = amount;
		this.typeMovements = typeMovements;
		this.detail = detail;
		this.createdAt = createdAt;
	  }
	  
	  public BankMovement() {
		this(-1, null, null, new BigDecimal("0.00"), null, "N/A", null);
	  }

	  public BankMovement( BankMovement bankMovement ) {
		super();
		this.id = bankMovement.id;
		this.client = bankMovement.client;
		this.bankAccount = bankMovement.bankAccount;
		this.amount = bankMovement.amount;
		this.typeMovements = bankMovement.typeMovements;
		this.detail = bankMovement.detail;
		this.createdAt = bankMovement.createdAt;
	  }

	  public int getId() {
		  return id;
	  }

	  public void setId(int id) {
		  this.id = id;
	  }

	  public Client getClient() {
		  return client;
	  }

	  public void setClient(Client client) {
		  this.client = client;
	  }

	  public BankAccount getBankAccount() {
		  return bankAccount;
	  }

	  public void setBankAccount(BankAccount bankAccount) {
		  this.bankAccount = bankAccount;
	  }

	  public BigDecimal getAmount() {
		  return amount;
	  }

	  public void setAmount(BigDecimal amount) {
		  this.amount = amount;
	  }

	  public MOVEMENTSTYPE getTypeMovements() {
		  return typeMovements;
	  }

	  public void setTypeMovements(MOVEMENTSTYPE typeMovements) {
		  this.typeMovements = typeMovements;
	  }

	  public String getDetail() {
		  return detail;
	  }

	  public void setDetail(String detail) {
		  this.detail = detail;
	  }

	  public LocalDateTime getCreatedAt() {
		  return createdAt;
	  }

	  public void setCreatedAt(LocalDateTime createdAt) {
		  this.createdAt = createdAt;
	  }
	
}
