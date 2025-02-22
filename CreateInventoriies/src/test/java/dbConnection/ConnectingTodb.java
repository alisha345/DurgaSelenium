package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import Utilities.Readpropertiesfile;

public class ConnectingTodb {

	public static Connection conn;
	public static Statement stmt;
	public static ResultSet resultSet;

	public  static  void ConnectingToDb(String host, String db, String username, String password) {

		try {
			Class.forName("org.postgresql.Driver");

			// Reading jdbc-config file
			Properties prop = Readpropertiesfile.readPropertiesFile("E:\\workspace-webservicesproject-supuat1\\CreateInventoriies\\src\\test\\java\\dbConnection\\Jdbc-config.properties");
			// Object of Connection from the Database
			conn = DriverManager.getConnection(prop.getProperty("Url"),prop.getProperty("user"), prop.getProperty("password"));
			System.out.println(db+ " db Connection established");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Occured while connecting to DB "+db);

		}
	}

	public static void ClosingDbConnection() {
		try {
			if (resultSet != null) 
				resultSet.close();

			if (stmt != null) 
				stmt.close();

			if (conn != null) 
				conn.close();

			System.out.println("DbConnection closed");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Occured while closing DB connection");
		}
	}

	public static void InsertInven() {

		try {

			Properties prop1 = Readpropertiesfile.readPropertiesFile("E:\\workspace-webservicesproject-supuat1\\CreateInventoriies\\src\\test\\java\\dbConnection\\Inventories.properties");

			// the postgres insert statement
			String inventory_barcodes = " insert into inventory_barcodes (inventory_code, barcode)"
					+ " values (?, ?);";

			String inventory_stocking_factors1 = " insert into inventory_stocking_factors"
					+ "(inventory_code,unit_of_measure,pack_size,description,barcode,pick_type_id,evexso_error,"
					+ "error_status,erp_only,height,weight,depth,volume,length,erp_box_reference,random_measure,dg_weight)\r\n" 
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? ,? , ? , ? , ? , ?);";
			String inventory_stocking_factors2 = " insert into inventory_stocking_factors"
					+ "(inventory_code,unit_of_measure,pack_size,description,barcode,pick_type_id,evexso_error,"
					+ "error_status,erp_only,height,weight,depth,volume,length,erp_box_reference,random_measure,dg_weight)\r\n" 
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? ,? , ? , ? , ? , ?);";

			String inventory_warehouse_information = "insert into inventory_warehouse_information"
					+ "(inventory_code,warehouse,pick_face,check_flag,last_check_date,check_reason,evexso_error,"
					+ "error_status,replenishment_priority,replenishment_level,average_cost,replacement_cost,"
					+ "average_daily_sales,average_daily_picks_from_pick_face,last_full_check_date,next_check_date,"
					+ "pallet_qty,layer_qty)"
					+" values (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? ,? , ? , ? , ? , ?, ?)";
			
			String inventories = "insert into inventories\r\n" + 
					"(inventory_code,inventory_description,random_measure,tracking,track_mode,"
					+ "date_sensitive,dangerous_item,bonded_item,evexso_action,\r\n" + 
					"erp_action,erp_id,evexso_error,error_status,storage,erp_type,category,group1,erp_business_unit,erp_attempts,\r\n" + 
					"log_record_id,supplier_catalogue_code,dg_description,dg_code)\r\n" + 
					"values(?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? ,? , ? , ? , ? , ?, ?, ? , ?, ?, ?, ?)";
			
			// create the postgres insert preparedstatement

			//Inserting inventory_barcodes

			PreparedStatement preparedStmt = conn.prepareStatement(inventory_barcodes);
			preparedStmt.setString (1, prop1.getProperty("inven_code"));
			preparedStmt.setString (2, prop1.getProperty("barcode"));

			//Inserting inventory_stocking_factors1
			PreparedStatement preparedStmt2 = conn.prepareStatement(inventory_stocking_factors1);
			preparedStmt2.setString (1, prop1.getProperty("inven_code"));
			preparedStmt2.setString (2, prop1.getProperty("unitofMeasure1"));
			preparedStmt2.setString (3, prop1.getProperty("pack_size1"));
			preparedStmt2.setString (4, prop1.getProperty("description"));
			preparedStmt2.setString (5, prop1.getProperty("barcode"));
			preparedStmt2.setString (6, prop1.getProperty("pick_type_id1"));
			preparedStmt2.setString (7, "'0'");
			preparedStmt2.setString (8, "NULL");
			preparedStmt2.setBoolean(9, false);
			preparedStmt2.setString (10, "'0'");			
			preparedStmt2.setString (11, "'0'");
			preparedStmt2.setString (12, "'0'");
			preparedStmt2.setString (13, "'0'");
			preparedStmt2.setString (14, "NULL");
			preparedStmt2.setBoolean(15, false);
			preparedStmt2.setString (16, "'0'");

			//Inserting inventory_stocking_factors2
			PreparedStatement preparedStmt3 = conn.prepareStatement(inventory_stocking_factors2);
			preparedStmt3.setString (1, prop1.getProperty("inven_code"));
			preparedStmt3.setString (2, prop1.getProperty("unitofMeasure2"));
			preparedStmt3.setString (3, prop1.getProperty("pack_size2"));
			preparedStmt3.setString (4, prop1.getProperty("description"));
			preparedStmt3.setString (5, prop1.getProperty("barcode"));
			preparedStmt3.setString (6, prop1.getProperty("pick_type_id2"));
			preparedStmt3.setString (7, "'0'");
			preparedStmt3.setString (8, "NULL");
			preparedStmt3.setBoolean(9, false);
			preparedStmt3.setString (10, "'0'");			
			preparedStmt3.setString (11, "'0'");
			preparedStmt3.setString (12, "'0'");
			preparedStmt3.setString (13, "'0'");
			preparedStmt3.setString (14, "NULL");
			preparedStmt3.setBoolean(15, false);
			preparedStmt3.setString (16, "'0'");


			//Inserting inventory_warehouse_information
			PreparedStatement preparedStmt4 = conn.prepareStatement(inventory_warehouse_information);
			preparedStmt4.setString (1, prop1.getProperty("inven_code"));
			preparedStmt4.setString (2, prop1.getProperty("warehouse"));
			preparedStmt4.setString (3, prop1.getProperty("pickFace"));
			preparedStmt4.setBoolean(4, false);
			preparedStmt4.setString (5, "NULL");
			preparedStmt4.setString (6, "NULL");
			preparedStmt4.setString (7, "'0'");
			preparedStmt4.setString (8, "NULL");
			preparedStmt4.setString (9, "'0'");
			preparedStmt4.setString (10, "'0'");			
			preparedStmt4.setString (11, "'0'");
			preparedStmt4.setString (12, "'0'");
			preparedStmt4.setString (13, "'0'");
			preparedStmt4.setString (14, "'0'");
			preparedStmt4.setString (15, "NULL");
			preparedStmt4.setString(16, "NULL");
			preparedStmt4.setString (17, "'0'");
			preparedStmt4.setString (18, "'0'");

			//Inserting inventories
			PreparedStatement preparedStmt5 = conn.prepareStatement(inventories);
			preparedStmt5.setString (1, prop1.getProperty("inven_code"));
			preparedStmt5.setString (2, prop1.getProperty("description"));
			
			if(prop1.getProperty("tracking") == "SERIAL")
				preparedStmt5.setString (3, "'0'");
			else {
				preparedStmt5.setString (3, prop1.getProperty("randomWeigt"));
			}
			preparedStmt5.setString (4, prop1.getProperty("tracking"));
			
			if(prop1.getProperty("tracking").isEmpty())
				preparedStmt5.setString (5, "NULL");
			else {
			preparedStmt5.setString (5, prop1.getProperty("trackMode"));
			}
			preparedStmt5.setString (6, prop1.getProperty("dateSensitive"));
			preparedStmt5.setString (7, "NULL");
			preparedStmt5.setString (8, "'0'");
			preparedStmt5.setString (9, "UPDATE");
			preparedStmt5.setString (10, "");			
			preparedStmt5.setString (11, prop1.getProperty("inven_code"));
			preparedStmt5.setString (12, "'0'");
			preparedStmt5.setString (13, "NULL");
			preparedStmt5.setString (14, "NULL");
			preparedStmt5.setString (15, "NULL");
			preparedStmt5.setString (16, "NULL");
			preparedStmt5.setString (17, "NULL");
			preparedStmt5.setString (18, "NULL");
			preparedStmt5.setString (19, "'0'");
			preparedStmt5.setString (20, "NULL");
			preparedStmt5.setString(21, "NULL");
			preparedStmt5.setString (22, "NULL");
			preparedStmt5.setString(23, "NULL");


			 if(prop1.getProperty("stockingFactor2").isEmpty() || prop1.getProperty("stockingFactor2") == "NULL"  || prop1.getProperty("tracking").isEmpty()) {


				// execute the preparedstatement
				preparedStmt.execute();
				preparedStmt2.execute();
				preparedStmt4.execute();
				preparedStmt5.execute();
			}

			else
			{
				preparedStmt.execute();
				preparedStmt2.execute();
				preparedStmt3.execute();
				preparedStmt4.execute();
				preparedStmt5.execute();

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while executing Insert statement");

		}
	}


}
