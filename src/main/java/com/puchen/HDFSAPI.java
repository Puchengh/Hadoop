package com.puchen;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class HDFSAPI {


    private FileSystem fileSystem  =null;



    public void before() throws Exception {
        Configuration conf = new Configuration();
//        System.setProperty("HADOOP_USER_NAME","root");
//        conf.set("fs.defaultFS","hdfs://pseudoDistributed:8020");
//        fileSystem = FileSystem.get(conf);

        fileSystem = FileSystem.get(new URI("hdfs://pseudoDistributed:8020"),conf,"root");
    }


    public void testUpload() throws Exception{
        InputStream in = new FileInputStream(("D:\\study\\item\\study\\Hadoop\\pom.xml"));
        FSDataOutputStream out= fileSystem.create(new Path("/pom1.xml"));
        IOUtils.copyBytes(in,out,1024,true);

        fileSystem.close();

    }

    public void testMkdir() throws Exception{
        fileSystem.mkdirs(new Path("/a/b"));

        fileSystem.close();

    }


    public void testRm() throws  Exception{
        boolean flag = fileSystem.delete(new Path("/a/b"),false);
        fileSystem.delete(new Path("/pom1.xml"),false);
        System.out.print(flag);
        fileSystem.close();
    }


    public void testExists() throws Exception{
        boolean flag = fileSystem.exists(new Path("/a.txt"));
        System.out.print(flag);
    }

    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.1.9:9000");
        FileSystem fileSystem = FileSystem.get(conf);

        InputStream in =  fileSystem.open(new Path("/a.txt"));
        OutputStream out = new FileOutputStream("d://123.txt");

        IOUtils.copyBytes(in,out,1024,true);
        fileSystem.close();
    }
}
