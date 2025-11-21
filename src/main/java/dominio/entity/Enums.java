package dominio.entity;

public class Enums {
	
	public static enum PROFILE { ADMIN, CLIENT };
	public static enum SEX { MALE, FEMALE };
	public static enum BANKACCOUNTTYPE { SAVINGSBANK, CURRENTACCOUNT };
	public static enum BANKLOANSSTATUS { PENDING, AUTHORIZED, CLOSE, REFUSED };
	public static enum MOVEMENTSTYPE { CREATEDBANKACCOUNT, CREATEDBANKLOAN, PAYMENTBANKLOAN, TRANSFER };

}
