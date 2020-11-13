package test;


import java.util.*;
import java.io.*;
import java.security.MessageDigest;

public class hash_test {		//InputStream是一个输入流的抽象类
    public static byte[] SHA1Checksum( InputStream is ) throws Exception {
        
        byte[] buffer = new byte[1024];    // 用于计算hash值的文件缓冲区
        
        MessageDigest complete = MessageDigest.getInstance("SHA-1");  // 使用SHA1哈希/摘要算法
        int numRead = 0;
        do {
            
            numRead = is.read(buffer);   // 从is输入流中读取buffer.length个（这个是读取的上限）字节，存到buffer中
            							//并将实际读取到的字节数返回给numRead。
            							//当读取到文件末尾时，返回-1；
            if (numRead > 0) {
                
                complete.update(buffer, 0, numRead);   
                	//该函数update（第一个参数表示所要增加到complete的内容，
                	//第二个参数代表buffer的读取起始点，第三个参数代表buffer读取多长）
            }
           
        } while (numRead != -1);   // numRead == -1代表文件读取完毕了
      
        is.close();      // 关闭输入流 
        return complete.digest();  // 返回SHA1哈希值，结果为字符数组
    }
    
    public static String convertToHexString(byte data[]) { 
    	// 由于MessageDigest对象的digest()方法返回的是字符数组，
    	//要得到十六进制的sha值还需要转为字符串    
    	//这里采用的是十六进制，如果愿意，还可以设计成八进制等
    	StringBuffer strBuffer = new StringBuffer(); 
    	for (int i = 0; i < data.length; i++) {
    		strBuffer.append(Integer.toHexString(0xff & data[i])); // 用十六进制数oxff与某个字节值做按位与运算，
										// 只保留了32位的最后8位，保证负数转换成十六进制不会出错
    		}
    	return strBuffer.toString();
}
        
    public static String dfs(String path) { 
    	// 传入一个路径，路径可能是一个目录或者是一个文件
		// 用深度优先遍历该目录下所有文件和文件夹，并计算相应的子文件和子文件夹里的子文件。
    	
    	File dir = new File(path); // 以Path路径新建一个File对象，路径名可能是一个目录或是一个文件
    	File[] fs = dir.listFiles(); // 实例对象的listfiles()方法生成一个文件数组，里面放了dir下面的全部二级文件
    	
    	try {
    		MessageDigest m=MessageDigest.getInstance("SHA1"); 
    		
    		for(int i = 0; i < fs.length; i++) { 
    			
    			if(fs[i].isFile()) { 
    				System.out.print("file " + fs[i].getName());
    				FileInputStream is = new FileInputStream(fs[i]); 
    				byte[] sha1 = SHA1Checksum(is);
    				String result = convertToHexString(sha1);  
    				System.out.println("，其哈希值为： " + result);
    				m.update(result.getBytes()); 
    				m.update(fs[i].getName().getBytes()); 
    			}
    			if(fs[i].isDirectory()) { 
    				System.out.println("directory " + path + File.separator + fs[i].getName());
    				m.update(fs[i].getName().getBytes());  
    				String treekey = dfs(path + File.separator + fs[i].getName());   //递归
    				
    				m.update(treekey.getBytes()); 
    			}	
    			} 

    		return convertToHexString(m.digest());
    		}
    		catch(Exception e) { 
    			// 有可能输入的PATH只是一个文件，而不是文件夹，导致listFiles()方法报错
    	
    			try {
    				FileInputStream is = new FileInputStream(dir);
    				byte[] sha1 = SHA1Checksum(is);
    				String result = convertToHexString(sha1);
    				return result;
    			}	
    			
    			catch(FileNotFoundException cd) {       
    				return "error! 路径不存在";
    			}
    		}
    
    }

    public static void main(String[] args) {
    	System.out.println("请输入文件路径：");
    	Scanner input = new Scanner(System.in);
    	System.out.println("该路径下所有文件的哈希值依次为： " + dfs(input.nextLine()));
    	input.close();
    }
}

