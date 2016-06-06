
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDeDatos {
	
	 private Connection conexion;
	 

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
			
			
			finally
			{
				try
				{

					if(conexion!= null) conexion.close();
				}
				catch(Exception ex2)
				{
					ex2.printStackTrace();
					throw new RuntimeException(ex2);
				}
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


	 public void getConsulta(String unaConsulta){
		
		PreparedStatement consulta = null;
		ResultSet resultados = null;
		
		//preparo y ejecuto la sentencia
		try {
			consulta = conexion.prepareStatement(unaConsulta);
			resultados = consulta.executeQuery();
			
			while( resultados.next() )
			{
				System.out.print(resultados.getInt("id_label")+",");
				System.out.print(resultados.getString("label_nombre")+",");
				System.out.println(resultados.getInt("filter")+",");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	 }
	
	
	 //te devuelvo un cero si no lo encontro
	public int estasEnLaTabla_Label(String unLabel) {

		CallableStatement consulta = null;

		try {
			// ejecuto el store

			consulta = conexion.prepareCall("{call dbo.st_estasEnLaTabla_Label(?)}");
			consulta.setString(1, unLabel);
			consulta.registerOutParameter(2, java.sql.Types.BOOLEAN);
			consulta.registerOutParameter(3, java.sql.Types.INTEGER);
			consulta.execute();
			
			if(consulta.getBoolean(2)){
				return consulta.getInt(3);
				
			}
			else{
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

	}
	
	public int insert_FILTER(String unFiler){
		
		CallableStatement consulta = null;

		try {
			consulta = conexion.prepareCall("{call dbo.st_insert_FILTER(?,?)}");
			consulta.setString(1, unFiler);
			consulta.registerOutParameter(2, java.sql.Types.INTEGER);
			
			return consulta.getInt(3);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	 
}

