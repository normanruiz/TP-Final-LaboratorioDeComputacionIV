package dominio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.sql.Timestamp;

public class DatabaseConnectionMySQL implements IDatabaseConnection{
	
	private String host;
	private String port;
	private String database;
	private String usr;
	private String pwd;
	private String cadenaDeConexion;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private CallableStatement callableStatement;

	private Connection conexion = null;
	
	public DatabaseConnectionMySQL() {
		super();
		host = "172.17.0.2";
		port = "3306";
		database = "TPFinalLaboratorioDeComputacionIV";
		usr = "root";
		pwd = "MySQL1234";
		cadenaDeConexion = "jdbc:mysql://" + host + ":" + port + "/";
	}

	@Override
	public boolean Connect() {
		try {
			System.out.println("Abriendo conexion a base de datos...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			if( this.conexion == null || this.conexion.isClosed() ) {
				this.conexion = DriverManager.getConnection(cadenaDeConexion + database, usr, pwd );
			}
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Fallo al intentar conectar...");
			System.out.println("Mensaje de error: algo se choteo con el driver");
			e.printStackTrace();
			return false;
		} catch (Exception exception) {
			System.out.println("Fallo al intentar conectar...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}
	}

	@Override
	public boolean Disconnect() {
		try {
			System.out.println("Cerrando conexion a base de datos...");
			if( this.conexion != null ) {
				if ( !this.conexion.isClosed() ) {
					this.conexion.close();
				}
			}
			return true;
			
		} catch (Exception exception) {
			System.out.println("Fallo al intentar desconectar...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}
	}

	@Override
	public ResultSet executeQuery(String query) {
		try {
			System.out.println("Consultando base de datos...");
			this.statement = this.conexion.createStatement();
			this.resultSet = this.statement.executeQuery(query);
			return this.resultSet;
		}
		catch(Exception exception) {
			System.out.println("Fallo al intentar ejecutar una query...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return null;
		}	
	}
	
	@Override	
	public int executeUpdate(String query) {
		try {
			System.out.println("Modificando datos en base de datos...");
			this.preparedStatement = this.conexion.prepareStatement(query);
			int result = this.preparedStatement.executeUpdate(query);
			if (result == 1) {
				return result;
			} else {
				return 0;
			}
		
		} catch(Exception exception) {
			System.out.println("Fallo al intentar modificar los datos...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return 0;
		}	
	}

	@Override
	public int executeUpdate() {
		try {
			System.out.println("Modificando datos en base de datos...");
			int result = this.preparedStatement.executeUpdate();
			if (result == 1) {
				return result;
			} else {
				return 0;
			}
		
		} catch(Exception exception) {
			System.out.println("Fallo al intentar modificar los datos...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return 0;
		}	
	}

	@Override
	public boolean setPreparedStatement(String query) {
		try {
			System.out.println("Seteando query para recibir parametros...");
			this.preparedStatement = this.conexion.prepareStatement(query);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear la query con parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	@Override
	public boolean setParameter(int pos, int param) {
		try {
			System.out.println("Seteando parametro de tipo int...");
			this.preparedStatement.setInt(pos, param);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}

	@Override
	public boolean setParameter(int pos, String param) {
		try {
			System.out.println("Seteando parametro de tipo string...");
			this.preparedStatement.setString(pos, param);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}

	@Override
	public boolean setParameter(int pos, Boolean param) {
		try {
			System.out.println("Seteando parametro de tipo boolean...");
			this.preparedStatement.setBoolean(pos, param);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	@Override
	public boolean setParameter(int pos, LocalDate param) {
		try {
			System.out.println("Seteando parametro de tipo LocalDate...");
			this.preparedStatement.setDate(pos, Date.valueOf(param));
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	@Override
	public boolean setParameter(int pos, LocalDateTime param) {
		try {
			System.out.println("Seteando parametro de tipo LocalDateTime...");
			this.preparedStatement.setTimestamp(pos, Timestamp.valueOf(param));
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	// de aca para abajo 
	
	@Override 
	public boolean setPrepareCall(String call) {
		try {
			System.out.println("Seteando llamada a procedimiento almacenado...");
			this.callableStatement = this.conexion.prepareCall(call);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear la llamada a procedimiento almacenado...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	@Override
	public boolean setParameterPC(int pos, int param) {
		try {
			System.out.println("Seteando parametro de tipo int...");
			this.callableStatement.setInt(pos, param);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}

	@Override
	public boolean setParameterPC(int pos, String param) {
		try {
			System.out.println("Seteando parametro de tipo string...");
			this.callableStatement.setString(pos, param);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}

	@Override
	public boolean setParameterPC(int pos, Boolean param) {
		try {
			System.out.println("Seteando parametro de tipo boolean...");
			this.callableStatement.setBoolean(pos, param);
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	@Override
	public boolean setParameterPC(int pos, LocalDate param) {
		try {
			System.out.println("Seteando parametro de tipo LocalDate...");
			this.callableStatement.setDate(pos, Date.valueOf(param));
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}
	
	@Override
	public boolean setParameterPC(int pos, LocalDateTime param) {
		try {
			System.out.println("Seteando parametro de tipo LocalDateTime...");
			this.callableStatement.setTimestamp(pos, Timestamp.valueOf(param));
			return true;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar setear parametros...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}

	@Override
	public boolean execute() {
		try {
			System.out.println("Ejecutando prosedimiento almacenado...");
			boolean result = this.callableStatement.execute();
			return result;
		} catch(Exception exception) {
			System.out.println("Fallo al intentar ejecutar el prosedimiento almacenado...");
			System.out.println("Mensaje de error: " + exception.getMessage());
			return false;
		}	
	}

} 

