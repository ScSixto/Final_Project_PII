package persistence;

import java.io.*;
import java.util.ArrayList;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.UnknownHostException;

public class JsonFile{
//
//	private static ArrayList<String> especies;
//
//	public JsonFile() {
//		especies = new ArrayList<String>();
//	}
//
//	public static BufferedReader readJSONWeb(String webServicePath) {
//		BufferedReader buffer = new BufferedReader(new InputStreamReader(getHttpURLConnection(false,webServicePath)));
//		return buffer;
//	}

	public static InputStream getHttpURLConnection(boolean isProxy, String filePath) {
		HttpURLConnection httpURLConnection;
		URL url = null;
		InputStream inputStream = null;
		try {
			url = new URL( filePath );
			if ( !isProxy ) {
				System.out.println("Sin proxy");
				httpURLConnection = (HttpURLConnection) url.openConnection();	
				inputStream = httpURLConnection.getInputStream();
			}else {
				System.out.println("Con proxy");
				Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
				httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
				inputStream = httpURLConnection.getInputStream();
				if(inputStream == null )
					System.out.println( "Este input no quiere funcionar" );
			}

		}catch(ConnectException connectException) {
			isProxy = true;
			return getHttpURLConnection( isProxy, filePath );
		} catch (UnknownHostException e) {
			isProxy = true;
			System.out.println( "Catch: " + e.getMessage()  );
			return getHttpURLConnection( isProxy, filePath );
		} catch (MalformedURLException e1) {
			System.out.println(  e1.getMessage()  );
		}catch (IOException e) {
			System.out.println(  e.getMessage()  );
		}
		return inputStream;
	}
//
//	public static ArrayList<Object[]> readJSONServices(String route, boolean isFile) throws DeserializationException, IOException{
//		ArrayList<Object[]> objectVector = new ArrayList<Object[]>();
//		JsonArray fishesList = new JsonArray();
//		if (isFile)
//			fishesList = (JsonArray) Jsoner.deserialize( new FileReader(route));
//		else
//			fishesList = (JsonArray) Jsoner.deserialize(readJSONWeb(route));
//		for (Object objectFishes : fishesList) {
//			JsonObject object = (JsonObject) objectFishes;
//			objectVector.add(new Object[] {object.getString("a_o"),object.getString("municipio"),object.getString("especie"),object.getString("animales_cosechados"),object.getString("peso_promedio_por_animal_al_cosechar_g")});
//		}
//		return objectVector;
//	}

//	public static void getEspecies() {
//		try {
//			int  i = 1;
//			ArrayList<Object[]> todo = readJSONServices("https://www.datos.gov.co/resource/yi68-jjgw.json",false);
//			for (Object[] objects : todo) {
//				if((!especies.contains((String)objects[1])) && (String)objects[2] != null) {
//					System.out.println( (i++) + " - " + (String)objects[1]);
//					especies.add((String)objects[1]);
//				}
//			}
////			for (String especie : especies) {
////				System.out.println(especie);
////			}
//		} catch (DeserializationException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
////
//	public static void getTowns() {
//		try {
//			int  i = 1;
//			ArrayList<Object[]> todo = readJSONServices("https://www.datos.gov.co/resource/yi68-jjgw.json",false);
//			for (Object[] objects : todo) {
//				if((!especies.contains((String)objects[1])) && (String)objects[2] != null) {
//					System.out.println( (i++) + " - " + (String)objects[1]);
//					especies.add((String)objects[1]);
//				}
//			}
////			for (String especie : especies) {
////				System.out.println(especie);
////			}
//		} catch (DeserializationException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public Reader getReader(String route, boolean isFile) throws FileNotFoundException{
		Reader reader;
		if(isFile)
			reader = new FileReader(route);
		else
			reader = new BufferedReader(new InputStreamReader(JsonFile.getHttpURLConnection(false, route)));
		return reader;
	}

	public ArrayList<Object[]> readCultiveList(String fileName, boolean isFile) throws FileNotFoundException, IOException, org.json.simple.DeserializationException{
		ArrayList<Object[]> cultiveList = new ArrayList<>();
		JsonArray jsonCultiveArray = (JsonArray) Jsoner.deserialize(this.getReader(fileName, isFile));
		for(Object object: jsonCultiveArray){
			JsonObject cultiveObj = (JsonObject) object;
			if(cultiveObj.size() >= 8)
			cultiveList.add(new Object[]{cultiveObj.getString("municipio"), cultiveObj.getInteger("a_o"), cultiveObj.getString("especie"), cultiveObj.getInteger("animales_sembrados"), cultiveObj.getInteger("animales_cosechados"), cultiveObj.getDouble("peso_promedio_por_animal_al_cosechar_g"), cultiveObj.getDouble("precio_al_productor_kg")});
		}
		return cultiveList;
	}

	public ArrayList<Object[]> readSpeciesList(String fileName, boolean isFile) throws FileNotFoundException, IOException, org.json.simple.DeserializationException{
		ArrayList<Object[]> speciesList = new ArrayList<>();
		JsonObject jsonObject = (JsonObject) Jsoner.deserialize(this.getReader(fileName, isFile));
		JsonArray listOfSpecies = (JsonArray) jsonObject.get("especies_de_peces");
		for(Object object: listOfSpecies){
			JsonObject speciesObj = (JsonObject) object;
			speciesList.add(new Object[]{speciesObj.getInteger("id_especie"),speciesObj.getString("especie"), speciesObj.getDouble("costo_libra"), speciesObj.getString("tipo_agua"), speciesObj.getString("alimento")});
		}
		return speciesList;
	}
}