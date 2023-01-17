package APINEXTproject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import mAPI.ReadAPI;

public class NEWmain {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Scanner sa = new Scanner(System.in);
			boolean Menu = true;
			while (Menu) {
				System.out.println("SELECT ONE OPTION:");
				System.out.println("1.READ API");
				System.out.println("2.CREATE TABLE");
				System.out.println("3.Insert  INTO TABLE");
				
				String bb = sa.next();
				int option = Integer.parseInt(bb);

				switch (option) {
				
				
				
				
				
				
				
				
				
				case 1:
				
				
				try {
					URL url = new URL("https://restcountries.com/v3.1/all");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					StringBuilder informationString = new StringBuilder();
					int responseCode = conn.getResponseCode();
					if (responseCode != 200) {
						throw new RuntimeException("HttpresponseCode" + responseCode);
					} else {
						Scanner scanner = new Scanner(url.openStream());
						while (scanner.hasNext()) {
							informationString.append(scanner.nextLine());
						}
						scanner.close();
						System.out.println(informationString);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
		
				break;
				
				
				
				
				
				
				case 2:
					
						 String url = "jdbc:mysql://localhost:3306/Sqldb2";
							String user = "root";
							String pass = "root";
							String sqlDB = "CREATE TABLE API2 " + "(id INTEGER NOT NULL AUTO_INCREMENT, " + " cca2 VARCHAR(80), "
									+ " ccn3 VARCHAR(80), " +"cioc VARCHAR(80),"+ " independent VARCHAR(80), "
									+ "status VARCHAR(80) , " + " subregion VARCHAR(80),"+ " PRIMARY KEY ( id ))";
							java.sql.Connection conn = null;
							try {
								Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
								DriverManager.registerDriver(driver);
								conn = DriverManager.getConnection(url, user, pass);
								java.sql.Statement st = conn.createStatement();
								int m = st.executeUpdate(sqlDB);
								if (m >= 1) {
									System.out.println("Created table in given database...");
									
								} else {
									System.out.println(" table already Created in given database...");
								}
								conn.close();
							} catch (Exception ex) {
								System.err.println(ex);
							}
							
						
				
				
			       break;
			
		
					
				
				case 3:
					
					 String url1 = "jdbc:mysql://localhost:3306/sqldb2";
					    String user1 = "root";
					    String pass1 = "root";
				
				    HttpClient client = HttpClient.newHttpClient();
					HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create("https://restcountries.com/v3.1/all")).build();
				
					HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
					String uglyJsonString = response.body();

					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					JsonParser jp = new JsonParser();
					JsonElement je = jp.parse(uglyJsonString);
					String prettyJsonString = gson.toJson(je);
					System.out.println(prettyJsonString);
					BigClass[] M = gson.fromJson(prettyJsonString, BigClass[].class);
					for (BigClass A : M) {
			
						String cca2 =A.getCca2();
						String ccn3 =A.getCcn3();
						String independent = A.getIndependent();
						String cioc = A.getCioc();
					String status = A.getStatus();
						String subregion = A.getSubregion();
				
						String SQLqueryForInserting = "insert into API2(cca2,ccn3,cioc,independent, status,subregion)"
								+ " values('" + cca2 + "' ,'" + ccn3 + "', '" + cioc
								+ "','" + independent + "' ,'" +status  +  "','" +subregion+"')";
					
						System.out.println(SQLqueryForInserting);
						Connection con = null;
						try {
							Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
						DriverManager.registerDriver(driver);
						con = DriverManager.getConnection(url1, user1, pass1);
							java.sql.Statement st = con.createStatement();
				
							int m = st.executeUpdate(SQLqueryForInserting);
							if (m >= 0)
								System.out.println("inserted successfully : " + SQLqueryForInserting);
						else 
								System.out.println("insertion failed");
						
							con.close();
						} catch (Exception ex) {
					
							System.err.println(ex);
				}
					}
					
				
					
					
					break;
					
					
					
	}
		 
		 
				}Menu=false;
		}
		 
	}
	


