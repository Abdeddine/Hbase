package hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

public class ScanTable{

   public static void main(String args[]) throws IOException{
      Configuration config = HBaseConfiguration.create();
      HTable table = new HTable(config, "student");
      // instantiate the Scan class
      Scan scan = new Scan();
      // scan the columns
      scan.addFamily(Bytes.toBytes("bigdata"));
      /*scan.addColumn(Bytes.toBytes("bigdata"), Bytes.toBytes("hadoop"));
      scan.addColumn(Bytes.toBytes("bigdata"), Bytes.toBytes("hive"));*/
      // get the ResultScanner
      ResultScanner scanner = table.getScanner(scan);
      for (Result result = scanner.next(); result != null; result=scanner.next())
      { 
    	  if(result.containsColumn(Bytes.toBytes("bigdata"), Bytes.toBytes("hadoop")))
    	  {
    	  if("hadoop couse".equals(new String(result.getValue(Bytes.toBytes("bigdata"), Bytes.toBytes("hadoop"))))){
    		  System.out.println("Found row : " + new String(result.getFamilyMap(Bytes.toBytes("bigdata")).get(Bytes.toBytes("hadoop"))));
    	    }
    	  }
      }
      System.out.print("hello");
      scanner.close();
   }
}
