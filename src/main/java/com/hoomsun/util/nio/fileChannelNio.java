package com.hoomsun.util.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class fileChannelNio {
	
	public static void main(String[] args) {
		
		RandomAccessFile accessFile=null;
		FileChannel channel=null;
		try {
			long start=System.currentTimeMillis();
			accessFile=new RandomAccessFile("E:\\home\\ywzou\\logs\\HSFS_2019-05-16.log","rw");
			channel=accessFile.getChannel();
			MappedByteBuffer byteBuffer=channel.map(FileChannel.MapMode.READ_ONLY, 0, accessFile.length());
			long end=System.currentTimeMillis();
			System.err.println((end-start)+"ms");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				accessFile.close();
				channel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
