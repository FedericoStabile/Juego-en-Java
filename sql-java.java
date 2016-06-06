//-----------------------HACER CONSULTAS SUELTAS-------------------------------------------------------------------

package canectar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PedroConectar
{

	public static void main(String[] args)
	{
		
		Connection con = null;
		PreparedStatement consulta = null;
		ResultSet resultados = null;
		
		try
		{			
			//parametros de conexion
			String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url="jdbc:sqlserver://LAPTOP-IH6ET2PC\\SQLSERVER2012:1433;databaseName=algoritmos2";
			String usr="testjava8";
			String pwd ="testjava8";
			
			//LEVANTO EL DRIVER
			Class.forName(driver);
			
			//establexco la conexion

			con = DriverManager.getConnection(url,usr,pwd);

			//Preparo la consulta
			String sql ="";
			sql+="select * from LABEL";


			//preparo y ejecuto la sentencia
			consulta = con.prepareStatement(sql);
			resultados = consulta.executeQuery();

			while( resultados.next() )
			{
				System.out.print(resultados.getInt("id_label")+",");
				System.out.print(resultados.getString("label_nombre")+",");
				System.out.println(resultados.getInt("filter")+",");

			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(resultados != null) resultados.close();
				if(consulta!= null) consulta.close();
				if(con!= null) con.close();
			}
			catch(Exception ex2)
			{
				ex2.printStackTrace();
				throw new RuntimeException(ex2);
			}
		}
	

}
}

//------------------------INVOCAR A UN STORE PROCEDURE  ------------------------------------------------------------------------------------------------

package canectar;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class PedroConectar
{

	public static void main(String[] args)
	{
		
		Connection con = null;
		CallableStatement consulta = null;
		ResultSet resultados = null;
		
		try
		{			
			//parametros de conexion
			String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url="jdbc:sqlserver://LAPTOP-IH6ET2PC\\SQLSERVER2012:1433;databaseName=algoritmos2";
			String usuario="testjava8";
			String pass ="testjava8";
			
			//LEVANTO EL DRIVER
			Class.forName(driver);
			
			//establexco la conexion

			con = DriverManager.getConnection(url,usuario,pass);
			
			//ejecuto el store

		      consulta = con.prepareCall("{call dbo.st_MostrarFiltros(?)}");
		      consulta.setString(1,"Genero");
		      consulta.execute();
		     
		      
				resultados = consulta.executeQuery();
			while( resultados.next() )
			{
				System.out.println(resultados.getString("label_nombre")+",");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		finally
		{
			try
			{
				if(resultados != null) resultados.close();
				if(consulta != null) consulta.close();
				if(con!= null) con.close();
			}
			catch(Exception ex2)
			{
				ex2.printStackTrace();
				throw new RuntimeException(ex2);
			}
		}
	

}
}
