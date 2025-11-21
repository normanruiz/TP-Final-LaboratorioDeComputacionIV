package dominio.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dominio.entity.Enums.BANKLOANSSTATUS;

public class BankLoans {

	private int id;
	private Client client;
	private BankAccount bankAccount;
	private BigDecimal requestedAmount;
	private BigDecimal bankInterest;
	private BigDecimal amountWithInterest;
	private int quotas;
	private BigDecimal amountQuota;
	private BANKLOANSSTATUS status;
	private LocalDateTime applyDate;
	private LocalDateTime approvalDate;
	private LocalDateTime updatedAt;
	
	public BankLoans(int id, Client client, BankAccount bankAccount, BigDecimal requestedAmount, BigDecimal bankInterest, BigDecimal amountWithInterest, int quotas,
			BigDecimal amountQuota, BANKLOANSSTATUS status, LocalDateTime applyDate, LocalDateTime approvalDate,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.client = client;
		this.bankAccount = bankAccount;
		this.requestedAmount = requestedAmount;
		this.bankInterest = bankInterest;
		this.amountWithInterest = amountWithInterest;
		this.quotas = quotas;
		this.amountQuota = amountQuota;
		this.status = status;
		this.applyDate = applyDate;
		this.approvalDate = approvalDate;
		this.updatedAt = updatedAt;
	}
	
	public BankLoans() {
		this(-1, null, null, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), 0, 	new BigDecimal("0.00"), null, null, null, null);
	}
	
	public BankLoans(BankLoans newBankLoans) {
		super();
		this.id = newBankLoans.id;
		this.client = newBankLoans.client;
		this.bankAccount = newBankLoans.bankAccount;
		this.requestedAmount = newBankLoans.requestedAmount;
		this.bankInterest = newBankLoans.bankInterest;
		this.amountWithInterest = newBankLoans.amountWithInterest;
		this.quotas = newBankLoans.quotas;
		this.amountQuota = newBankLoans.amountQuota;
		this.status = newBankLoans.status;
		this.applyDate = newBankLoans.applyDate;
		this.approvalDate = newBankLoans.approvalDate;
		this.updatedAt = newBankLoans.updatedAt;
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

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public BigDecimal getBankInterest() {
		return bankInterest;
	}

	public void setBankInterest(BigDecimal bankInterest) {
		this.bankInterest = bankInterest;
	}

	public BigDecimal getAmountWithInterest() {
		return amountWithInterest;
	}

	public void setAmountWithInterest(BigDecimal amountWithInterest) {
		this.amountWithInterest = amountWithInterest;
	}

	public int getQuotas() {
		return quotas;
	}

	public void setQuotas(int quotas) {
		this.quotas = quotas;
	}

	public BigDecimal getAmountQuota() {
		return amountQuota;
	}

	public void setAmountQuota(BigDecimal amountQuota) {
		this.amountQuota = amountQuota;
	}

	public BANKLOANSSTATUS getStatus() {
		return status;
	}

	public void setStatus(BANKLOANSSTATUS status) {
		this.status = status;
	}

	public LocalDateTime getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDateTime applyDate) {
		this.applyDate = applyDate;
	}

	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
