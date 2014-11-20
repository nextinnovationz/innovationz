package QJava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Stream {
	public static void main(String[] args) {
		String input = "input.txt";
		String output = "output.txt";
		//createFile("input.txt", 10000000);
		//copyFile(input, output);
		System.out.println(isSame(input, output));
	}
	
	@SuppressWarnings("finally")
	public static boolean isSame(String file1, String file2) {
		FileInputStream in1 = null;  //read byte into system
		FileInputStream in2 = null;  //read byte into system
		
		boolean same = true;
		
		try {
			in1 = new FileInputStream(file1);
			in2 = new FileInputStream(file2);
			int data1 = 0, data2 = 0;
			while((data1 = in1.read()) != -1 && (data2 = in2.read()) != -1) {
				System.out.println("f1: " + data1 + " f2: " + data2);
				if(data1 != data2) {
					same = false;
					break;
				}
			}
			
			data2 = in2.read();
			if(data1 != data2) 
				same = false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in1 != null) {
					in1.close();
				} 
				
				if(in2 != null) {
					in2.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			return same;
		}
	}
	
	public static void copyFile(String input, String output) {		
		FileInputStream in = null;  //read byte from stream (from source)
		FileOutputStream out = null;  //write byte into stream (to sink)
		
		try {
			in = new FileInputStream(input);
			out = new FileOutputStream(output);
			int byteData;
			while((byteData = in.read()) != -1) {
				System.out.println("writing byte: " + byteData);
				out.write(byteData);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				} 
				
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createFile(String name, int numBytes) {
		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream(name);
			int byteCnt = 0;
			while(byteCnt < numBytes) {
				System.out.println("writing byte: " + byteCnt);
				out.write(byteCnt);
				++byteCnt;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
					System.out.println("done");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
