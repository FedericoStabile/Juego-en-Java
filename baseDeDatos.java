
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BaseDeDatos {
	
	 private Connection conexion = null;
	 

	 public Connection getConexion()
	{
		return conexion;
	}

	public BaseDeDatos(String driver,String url,String usuario,String pass) {
		
			try
			{			
				
				//LEVANTO EL DRIVER
				Class.forName(driver);
				
				//establexco la conexion

				conexion = DriverManager.getConnection(url,usuario,pass);

			}
			catch(Exception ex1)
			{
				ex1.printStackTrace();
				throw new RuntimeException(ex1);
			}
			
		
	}
	 
	 
	 public void cerrarConexion(){
		 try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	
	
	 //te devuelvo un cero si no lo encontro
	public int estasEnLaTabla_Label(String unLabel) {

		CallableStatement consulta = null;

		try {
			// ejecuto el store
			System.out.println(this.getConexion()+",");
		    consulta = conexion.prepareCall("{call dbo.st_estasEnLaTabla_Label(?,?,?)}");
			consulta.setString(1, unLabel);
			consulta.registerOutParameter(2, java.sql.Types.INTEGER);
			consulta.registerOutParameter(3, java.sql.Types.INTEGER);
			consulta.execute();
			
			if(consulta.getBoolean(2)){
				return consulta.getInt(3);
				
			}
			else{
				return 0;
			}

		} catch (Exception e4) {
			e4.printStackTrace();
			throw new RuntimeException(e4);
		}
		

	}
	
	public int insert_FILTER(String unFiler){
		
		CallableStatement consulta = null;

		try {
			consulta = conexion.prepareCall("{call dbo.st_insert_FILTER(?,?)}");
			consulta.setString(1, unFiler);
			consulta.registerOutParameter(2, java.sql.Types.INTEGER);
			consulta.execute();
			
			return consulta.getInt(2);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	 
}

