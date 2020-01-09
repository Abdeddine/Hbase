package hbase;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class DeleteData {
   public static void main(String[] args) throws IOException {
      Configuration conf = HBaseConfiguration.create();
      System.out.print(conf);
      HTable table = new HTable(conf, "student");
      // instantiate Delete class
      Delete delete1 = new Delete(Bytes.toBytes("row3"));
      delete1.deleteFamily(Bytes.toBytes("bigdata"));
      Delete delete2 = new Delete(Bytes.toBytes("row2"));
      delete2.deleteColumn(Bytes.toBytes("bigdata"), Bytes.toBytes("pig"));
      // delete the data
      table.delete(delete1);
      table.delete(delete2);
      table.close();
      System.out.println("data deleted successfully.....");
   }
}