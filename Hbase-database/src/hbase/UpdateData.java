package hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class UpdateData{

   public static void main(String[] args) throws IOException {
      // instantiate Configuration class
      Configuration config = HBaseConfiguration.create();
      // instantiate HTable class
      HTable hTable = new HTable(config, "student");
      // instantiate Put class
      Put p1 = new Put(Bytes.toBytes("row2")); 
      Put p2 = new Put(Bytes.toBytes("row3")); 
      // update value using add() method
      p1.add(Bytes.toBytes("bigdata"),Bytes.toBytes("pig"),Bytes.toBytes("pig installation 2"));
      p2.add(Bytes.toBytes("bigdata"),Bytes.toBytes("hadoop"),Bytes.toBytes("exame hadoop 2011"));
      p2.add(Bytes.toBytes("bigdata"),Bytes.toBytes("hbase"),Bytes.toBytes("exame hbase 2011"));
      // save the put Instance to the HTable.
      hTable.put(p1);
      hTable.put(p2);
      System.out.println("data updated successfully");
      
      hTable.close();
   }
}