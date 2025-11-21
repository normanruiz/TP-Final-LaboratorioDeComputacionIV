package exceptions;

public class AcumulatedException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public AcumulatedException() {
		super();
	}

	@Override
	public String getMessage() {
		return "No se pudo recuperar la cantidad de cuentas pertenecientes al cliente.";
	}
	
	

}
