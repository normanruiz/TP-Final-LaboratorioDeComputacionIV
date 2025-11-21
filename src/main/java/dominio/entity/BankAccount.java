package dominio.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dominio.entity.Enums.BANKACCOUNTTYPE;

public class BankAccount {
	
	private int id;
	private int clientId;
	private BANKACCOUNTTYPE bankAccountType;
	private String accountNumber;
	private String CBU;
	private BigDecimal saldo;
	private boolean status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public BankAccount(int id, int clientId, BANKACCOUNTTYPE bankAccountType, String accountNumber, String CBU,
			BigDecimal saldo, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.bankAccountType = bankAccountType;
		this.accountNumber = accountNumber;
		this.CBU = CBU;
		this.saldo = saldo;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public BankAccount() {
		this(-1, -1, BANKACCOUNTTYPE.SAVINGSBANK, null, null, new BigDecimal("10000.00"), true, null, null);
	}
	
	public BankAccount(BankAccount bankAccount) {
		super();
		this.id = bankAccount.id;
		this.clientId = bankAccount.clientId;
		this.bankAccountType = bankAccount.bankAccountType;
		this.accountNumber = bankAccount.accountNumber;
		this.CBU = bankAccount.CBU;
		this.saldo = bankAccount.saldo;
		this.status = bankAccount.status;
		this.createdAt = bankAccount.createdAt;
		this.updatedAt = bankAccount.updatedAt;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public BANKACCOUNTTYPE getBankAccountType() {
		return this.bankAccountType;
	}

	public void setBankAccountType(BANKACCOUNTTYPE bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCBU() {
		return this.CBU;
	}

	public void setCBU(String CBU) {
		this.CBU = CBU;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
