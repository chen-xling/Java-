package test;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;


public class hash_test{
	/*private String path;
	public hash_test( String filePath  ) {
		path = filePath;
	} */
	
	//1 ����blob�Ĺ�ϣֵ�ġ��ַ����顱��ʾ
	 public static byte[] SHA1Checksum( InputStream is ) throws NoSuchAlgorithmException, IOException  {   //�β����ļ�������
	        
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
	        } while (numRead != -1);    // numRead == -1�����ļ���ȡ�����
	        is.close();      			// �ر������� ??�����ܹأ����滹Ҫ�õ� 
	        return complete.digest(); 
	        // ����SHA1��ϣֵ,����MessageDigest�����digest()�������ص����ֽ����飬����ȥ��Ҫת����16����
	    }
	 
	 //2 ���ַ���������ʮ�����ƹ�ϣֵ�ĺ���:
	 public static String convertToHexString(byte data[]) { 
	    	// ����MessageDigest�����digest()�������ص����ַ����飬
	    	//Ҫ�õ�ʮ�����Ƶ�sha1ֵ����ҪתΪ�ַ���  
	    	StringBuffer strBuffer = new StringBuffer(); 
	    	for ( int i = 0; i < data.length; i++ ) {
	    		strBuffer.append(Integer.toHexString(0xff & data[i])); // ��ʮ��������oxff��ĳ���ֽ�ֵ����λ�����㣬
											// ֻ������32λ�����8λ����֤����ת����ʮ�����Ʋ������
	    		}
	    	return strBuffer.toString();
	}
	 
//3 path�������ļ��У���Ŀ¼tree����Ҳ�������ļ���blob), ����ֵΪĿ¼���ļ��Ĺ�ϣֵ	 
    public static String hash( String path  ) { 
    	
    	 
    	try {
    		File dir = new File(path); // ��Path·���½�һ��File����·����������һ��Ŀ¼����һ���ļ�
		
    		if ( dir.isFile() ) {
    			FileInputStream is = new FileInputStream( dir );
    			byte[] data = SHA1Checksum( is ) ;
    			String result = convertToHexString ( data );
    			return result;
				}
		
    		MessageDigest m = MessageDigest.getInstance("SHA-1");
    		if ( dir.isDirectory()) {
    			File[] fs = dir.listFiles(); 
			
    			for(int i = 0; i < fs.length; i++) { 
    				if(fs[i].isFile()) { 
    					System.out.print("file " + fs[i].getName());
    					FileInputStream is = new FileInputStream(fs[i]); 
    					byte[] sha1 = SHA1Checksum(is);
    					String result = convertToHexString(sha1);  
    					System.out.println("�����ϣֵΪ�� " + result);
    					m.update(result.getBytes());   //�ѹ�ϣ��浽��һ����������ȥ��Ч������ǹ�ϣ��Ĺ�ϣ��
    					m.update(fs[i].getName().getBytes()); 
    				}
    				if(fs[i].isDirectory()) { 
    					System.out.println("directory " + path + File.separator + fs[i].getName());
    					m.update(fs[i].getName().getBytes());    //���ļ������ּӽ�ȥ
    					String treekey = hash(path + File.separator + fs[i].getName());   //�õݹ飬���ļ����ݼӽ�ȥ
    				
    					m.update(treekey.getBytes()); 
    				}	
    		
    			}
			}
			return convertToHexString( m.digest() );
		
		} 
    	catch (Exception e) {
			e.printStackTrace();
			return  "error! ·��������";
		}
		
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
    	System.out.println("�������ļ�·����");
    	Scanner input = new Scanner(System.in);
    	System.out.println("��·���������ļ����ܹ�ϣֵΪ�� " + hash( input .nextLine()));
    	input.close();
    }
}

