package dominio.dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IDatabaseConnection {

	public boolean Connect();
	
	public boolean Disconnect();
	
	public ResultSet executeQuery(String query);
	
	public int executeUpdate(String query);
	
	public int executeUpdate();
	
	public boolean setPreparedStatement(String query);
	
	public boolean setParameter(int pos, int param);
	
	public boolean setParameter(int pos, String param);
	
	public boolean setParameter(int pos, Boolean param);
	
	public boolean setParameter(int pos, LocalDate param);
	
	public boolean setParameter(int pos, LocalDateTime param);
	
	public boolean setPrepareCall(String call);
	
	public boolean setParameterPC(int pos, int param);
	
	public boolean setParameterPC(int pos, String param);
	
	public boolean setParameterPC(int pos, Boolean param);
	
	public boolean setParameterPC(int pos, LocalDate param);
	
	public boolean setParameterPC(int pos, LocalDateTime param);
	
	public boolean execute();
	
}
