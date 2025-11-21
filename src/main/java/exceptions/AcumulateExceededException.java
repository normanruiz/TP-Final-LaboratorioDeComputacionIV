package exceptions;

public class AcumulateExceededException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AcumulateExceededException() {
		super();
	}

	@Override
	public String getMessage() {
		return "Limite maximo de cuentas alcanzado.";
	}
	
	
	
}
