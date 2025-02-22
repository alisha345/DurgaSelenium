package SO_printing;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SO {

	public static void main(String[] args)   {


		try{
			So_Creation();
		}
		catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}




	public static void So_Creation () throws IOException {
	    FileReader reader=new FileReader(System.getProperty("user.dir")+"\\src\\SO_printing\\SO.properties");  

		
		Properties po = new Properties();
		po.load(reader);
		

		FileWriter writer = new FileWriter(System.getProperty("user.dir")+"\\src\\SO_printing\\Myfile.txt", true);
		String[] inv_code = po.getProperty("Inventory_codes").split(",");
		int SO = Integer.parseInt(po.getProperty("first_SO_number"));
		int N_SO = Integer.parseInt(po.getProperty("total_no_So_required"));
		writer.flush();
		for( int j= (SO-1); j<(SO+N_SO-1);j++) {
			String  H = "('"+(j+1)+"','MULTIZONE','C-1','CHBNPL','UPDATE','2021-04-01','2020-12-28'," + 
					" 'Require pick check 1',"+(j+1)+",false,100,'MAIN','eveXso');";
			String Header = "insert into sales_order_headers "+"\n"
					+ "(sales_order,warehouse,customer_code,run_code,evexso_action,order_date,required_date,sales_order_name,evexso_id,hold,customer_payment_amount,\r\n" + 
					"business_unit,operator)"+"\n"
					+ "values "+"\n" +H;


			writer.write(Header);
			writer.write("\n\n");   // write new line
			
			// lines logic


			for( int i = 0; i<inv_code.length; i++) {
				String S = "('"+(j+1)+"','"+(i+1)+"','"+inv_code[i]+"','"+inv_code[i]+"',5,'xx',0,0,false),";
				String LS = "('"+(j+1)+"','"+inv_code.length+"','"+inv_code[i]+"','"+inv_code[i]+"',5,'xx',0,0,false);";

				String Lines = "insert into sales_order_lines"+"\n"
						+ "(sales_order,sales_order_line,inventory_code,description,quantity_ordered,unit_of_measure,\r\n" + 
						"quantity_supplied,rejected_qty,hard_allocation)"+"\n"
						+ " values "+"\n"  +S;
				String Last_Lines = LS;

				
				if(inv_code.length > 1) {
					
				
				 if(i==((inv_code.length)-1) ){
					writer.write(LS);
					writer.write("\n");

				}
				else if (i==0)
				{
					writer.write(Lines);
					writer.write("\n");
				}
				else
				{
					writer.write(S);
					writer.write("\n");

				}
				}
				else
				{
					if(i==((inv_code.length)-1) ){
						writer.write(Last_Lines);
						writer.write("\n");

					}
					else 
					{
						writer.write(Lines);
						writer.write("\n");
					}

					
				}


			}			
			writer.write("\n\n");   // write new line


		}

		writer.close();

	}









}	
