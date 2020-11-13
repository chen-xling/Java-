package test;


import java.util.*;
import java.io.*;
import java.security.MessageDigest;

public class hash_test {		//InputStream��һ���������ĳ�����
    public static byte[] SHA1Checksum( InputStream is ) throws Exception {
        
        byte[] buffer = new byte[1024];    // ���ڼ���hashֵ���ļ�������
        
        MessageDigest complete = MessageDigest.getInstance("SHA-1");  // ʹ��SHA1��ϣ/ժҪ�㷨
        int numRead = 0;
        do {
            
            numRead = is.read(buffer);   // ��is�������ж�ȡbuffer.length��������Ƕ�ȡ�����ޣ��ֽڣ��浽buffer��
            							//����ʵ�ʶ�ȡ�����ֽ������ظ�numRead��
            							//����ȡ���ļ�ĩβʱ������-1��
            if (numRead > 0) {
                
                complete.update(buffer, 0, numRead);   
                	//�ú���update����һ��������ʾ��Ҫ���ӵ�complete�����ݣ�
                	//�ڶ�����������buffer�Ķ�ȡ��ʼ�㣬��������������buffer��ȡ�೤��
            }
           
        } while (numRead != -1);   // numRead == -1�����ļ���ȡ�����
      
        is.close();      // �ر������� 
        return complete.digest();  // ����SHA1��ϣֵ�����Ϊ�ַ�����
    }
    
    public static String convertToHexString(byte data[]) { 
    	// ����MessageDigest�����digest()�������ص����ַ����飬
    	//Ҫ�õ�ʮ�����Ƶ�shaֵ����ҪתΪ�ַ���    
    	//������õ���ʮ�����ƣ����Ը�⣬��������Ƴɰ˽��Ƶ�
    	StringBuffer strBuffer = new StringBuffer(); 
    	for (int i = 0; i < data.length; i++) {
    		strBuffer.append(Integer.toHexString(0xff & data[i])); // ��ʮ��������oxff��ĳ���ֽ�ֵ����λ�����㣬
										// ֻ������32λ�����8λ����֤����ת����ʮ�����Ʋ������
    		}
    	return strBuffer.toString();
}
        
    public static String dfs(String path) { 
    	// ����һ��·����·��������һ��Ŀ¼������һ���ļ�
		// ��������ȱ�����Ŀ¼�������ļ����ļ��У���������Ӧ�����ļ������ļ���������ļ���
    	
    	File dir = new File(path); // ��Path·���½�һ��File����·����������һ��Ŀ¼����һ���ļ�
    	File[] fs = dir.listFiles(); // ʵ�������listfiles()��������һ���ļ����飬�������dir�����ȫ�������ļ�
    	
    	try {
    		MessageDigest m=MessageDigest.getInstance("SHA1"); 
    		
    		for(int i = 0; i < fs.length; i++) { 
    			
    			if(fs[i].isFile()) { 
    				System.out.print("file " + fs[i].getName());
    				FileInputStream is = new FileInputStream(fs[i]); 
    				byte[] sha1 = SHA1Checksum(is);
    				String result = convertToHexString(sha1);  
    				System.out.println("�����ϣֵΪ�� " + result);
    				m.update(result.getBytes()); 
    				m.update(fs[i].getName().getBytes()); 
    			}
    			if(fs[i].isDirectory()) { 
    				System.out.println("directory " + path + File.separator + fs[i].getName());
    				m.update(fs[i].getName().getBytes());  
    				String treekey = dfs(path + File.separator + fs[i].getName());   //�ݹ�
    				
    				m.update(treekey.getBytes()); 
    			}	
    			} 

    		return convertToHexString(m.digest());
    		}
    		catch(Exception e) { 
    			// �п��������PATHֻ��һ���ļ����������ļ��У�����listFiles()��������
    	
    			try {
    				FileInputStream is = new FileInputStream(dir);
    				byte[] sha1 = SHA1Checksum(is);
    				String result = convertToHexString(sha1);
    				return result;
    			}	
    			
    			catch(FileNotFoundException cd) {       
    				return "error! ·��������";
    			}
    		}
    
    }

    public static void main(String[] args) {
    	System.out.println("�������ļ�·����");
    	Scanner input = new Scanner(System.in);
    	System.out.println("��·���������ļ��Ĺ�ϣֵ����Ϊ�� " + dfs(input.nextLine()));
    	input.close();
    }
}

