package hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class InsertData{

   public static void main(String[] args) throws IOException {

      // instantiate Configuration class
      Configuration config = HBaseConfiguration.create();
      // instantiate HTable class
      HTable hTable = new HTable(config, "student");
      // instantiate Put class
      Put p = new Put(Bytes.toBytes("row3")); 
      // add values using add() method
      p.add(Bytes.toBytes("bigdata"),
		Bytes.toBytes("hadoop"),Bytes.toBytes("exame hadoop"));
      p.add(Bytes.toBytes("bigdata"),
		Bytes.toBytes("hbase"),Bytes.toBytes("exame hbase"));
      // save the put Instance to the HTable.
      hTable.put(p);
      System.out.println("data inserted successfully");
      // close HTable instance
      hTable.close();
   }
}