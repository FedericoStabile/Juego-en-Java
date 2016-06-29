package algoII.tp.Imples;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import algoII.tp.DAOs.FilterDAO;
import algoII.tp.DAOs.LabelDAO;
import algoII.tp.DAOs.TitleDAO;
import algoII.tp.def.Filter;
import algoII.tp.def.Label;
import algoII.tp.def.Title;


public class BaseDeDatos {
	
	
//	private static File baseDatos = new File("DirBD");//revisar properties para que
//	private long modBD = baseDatos.lastModified();
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

	
	 public int buscar_filter(String nombreFilter){
		 
			CallableStatement consulta = null;

			try {
				// ejecuto el store
			    consulta = conexion.prepareCall("{call dbo.st_buscar_filter(?,?)}");
				consulta.setString(1, nombreFilter);
				consulta.registerOutParameter(2, java.sql.Types.INTEGER);
				consulta.execute();
				
			return consulta.getInt(2);


			} catch (Exception e4) {
				e4.printStackTrace();
				throw new RuntimeException(e4);
			}
	 }
	 
	 public List<String> todos_filters(){
		 
			CallableStatement consulta = null;
			ResultSet resultados = null;
			String filter_nombre;
			List<String> listaFilters = new ArrayList<>();
			
			
			try {
				// ejecuto el store
			    consulta = conexion.prepareCall("{call dbo.st_buscar_filter}");
				consulta.execute();
				resultados = consulta.executeQuery();
				
				while( resultados.next() ){
					filter_nombre =resultados.getString("filter_nombre");
					listaFilters.add(filter_nombre);
					
				}

			return listaFilters;


			} catch (Exception e4) {
				e4.printStackTrace();
				throw new RuntimeException(e4);
			}
	 }
	 
	 public List<String> buscar_filters_album(int id_album){
		 
			CallableStatement consulta = null;
			ResultSet resultados = null;
			String filter_nombre;
			List<String> listaFilters = new ArrayList<>();
			
			
			try {
				// ejecuto el store
			    consulta = conexion.prepareCall("{call dbo.st_buscar_filters_album(?)}");
			    consulta.setInt(1, id_album);
				consulta.execute();
				resultados = consulta.executeQuery();
				
				while( resultados.next() ){
					filter_nombre =resultados.getString("filter_nombre");
					listaFilters.add(filter_nombre);
					
				}

			return listaFilters;


			} catch (Exception e4) {
				e4.printStackTrace();
				throw new RuntimeException(e4);
			}
	 }
	 
	 
	 
	 //te devuelvo un cero si no lo encontro,si lo encuentra de da el id_label el id corespondiente
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
	
	
	 public List<String> todos_Labels(){
		 
			CallableStatement consulta = null;
			ResultSet resultados = null;
			String laber_nombre;
			List<String> listaLabels = new ArrayList<>();
			
			
			try {
				// ejecuto el store
			    consulta = conexion.prepareCall("{call dbo.st_todos_Labels}");
				consulta.execute();
				resultados = consulta.executeQuery();
				
				while( resultados.next() ){
					laber_nombre =resultados.getString("label_nombre");
					listaLabels.add(laber_nombre);
					
				}

			return listaLabels;


			} catch (Exception e4) {
				e4.printStackTrace();
				throw new RuntimeException(e4);
			}
	 }
	 
	 public List<String> todos_Labels_de_un_filter(String unLabel){
		 
			CallableStatement consulta = null;
			ResultSet resultados = null;
			String laber_nombre;
			List<String> listaLabels = new ArrayList<>();
			
			
			try {
				// ejecuto el store
			    consulta = conexion.prepareCall("{call dbo.st_todos_Labels_de_un_filter(?)}");
			    consulta.setString(1, unLabel);
				consulta.execute();
				resultados = consulta.executeQuery();
				
				while( resultados.next() ){
					laber_nombre =resultados.getString("label_nombre");
					listaLabels.add(laber_nombre);
					
				}

			return listaLabels;


			} catch (Exception e4) {
				e4.printStackTrace();
				throw new RuntimeException(e4);
			}
		 
	 }
	
	
	//seter FILTER. Devuelve su id correspondiente (@id_filter)
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
	
	//Funciones que faltan
//		public boolean seModifico(File file)
//		{
//			long ms = file.lastModified();
//			return (ms > modBD);
//			
//		}
//		
		public void arregloDeLinea(File file, ArrayList<String> lineaInfo)
		{
			int idFilter = this.verExistenciaFilter(lineaInfo.remove(0)); //hacerlo int, y revisar estasEnLaTabla (cambiar estasEnLaTabla para insertar)
			int idAlbum = this.verExistenciaTitulo(file.getParent());      //lo mismo que arriba
			
			this.enlazarAlbumFilter(idAlbum, idFilter);                        //directamente paso el ID
			
			for(String name: lineaInfo)
			{
				int idLabel = this.verExistenciaLabel(name);
				enlazarFilterLabel(idFilter, idLabel);		
				enlazarAalbumLabel(idAlbum, idLabel);
			}
		}

		public void liberarRelaciones(File file)		 //preguntar a leo
		{
			// TODO Auto-generated method stub	         // Metodo formatear tablas (antes de cerrar el programa)
		}
		
		//---------------------
		//--funciones enlazar-- 
		//---------------------
		
//		public void enlazarAlbumLabel(int idAlbum, int idLabel)
//		{
//			//se ingresa directamente en sql
//		}
		public void enlazarFilterLabel(int idFilter, int idLabel)
		{
			//se ingresa directamente en sql
		}	
		public void enlazarAlbumFilter(int idAlbum, int idFilter)
		{
			//se ingresa directamente en sql
		}
		
		public void enlazarAalbumLabel(int idAlbum, int idLabel)
		{
			//se ingresa directamente en sql
		}
		
//		//------------------------
//		//--funciones existencia--  Va a ser reemplazado por estaEnTabla 
//		//------------------------
//		
//		public Title verExistenciaTitulo(String path)
//		{
//			TitleDAO titleDAO = (TitleDAO) Factory.getObject("TitleDAO");
//			
//			Title title = titleDAO.findByPath(path);
//			
//			if (title == null)		title = titleDAO.setAlbum(path);
//			
//			return title;
//		}
//		public Label verExistenciaLabel(String name)
//		{
//			LabelDAO labelDAO = (LabelDAO) Factory.getObject("LabelDAO");		
//			return labelDAO.setLabel(name);
//		}
//		public Filter verExistenciaFilter(String name)
//		{
//			FilterDAO filterDAO = (FilterDAO) Factory.getObject("FilterDAO");
//			return filterDAO.setFilter(name);
//		}
//	
		}


