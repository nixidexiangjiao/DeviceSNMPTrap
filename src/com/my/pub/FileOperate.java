package com.my.pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileOperate {     

	public static void main(String[] args) {
		String filelist = "";
		List<String> files = listSubFolder("E:\\oa");
		for (String file : files) {
			filelist = filelist + file + "\n";
		}
		System.out.println(filelist);
	}

	/** 
	 * 新建目录 
	 * @param folderPath String 如 c:/fqf 
	 * @return boolean 
	 */ 
	public static boolean newFolder(String folderPath) {
		boolean status = false;
		try { 
			String filePath = folderPath; 
			filePath = filePath.toString(); 
			java.io.File myFilePath = new java.io.File(filePath); 
			if (!myFilePath.exists()) { 
				myFilePath.mkdir(); 
				status = true;
			}
			if (!myFilePath.isDirectory()) {
				status = false;
				System.out.println("新建目录操作出错");
			}
		} 
		catch (Exception e) { 
			System.out.println("新建目录操作出错 "); 
			e.printStackTrace(); 
			status = false;
		}
		return status;
	}
	
	/** 
	 * 新建目录(递归新建) 
	 * @param folderPath String 如 c:/asd/fqf 
	 * @return boolean 
	 */ 
	public static boolean newFolderForRecursion(String folderPath) {
		boolean status = false;
		String temp = "";
		try { 
			String filePath = folderPath; 
			filePath = filePath.toString(); 
			filePath = filePath.replace(File.separator, "/");
			String[] filePaths = filePath.split("/");
			for(String sf : filePaths){
				temp = temp + sf + File.separator;
				java.io.File myFilePath = new java.io.File(temp); 
				if (!myFilePath.exists()) { 
					myFilePath.mkdir(); 
					status = true;
				}
				if (!myFilePath.isDirectory()) {
					status = false;
					System.out.println("新建目录操作出错");
				}
			}
		} 
		catch (Exception e) { 
			System.out.println("新建目录操作出错 "); 
			e.printStackTrace(); 
			status = false;
		}
		return status;
	} 

	/** 
	 * 新建文件 
	 * @param filePathAndName String 文件路径及名称 如c:/fqf.txt 
	 * @param fileContent String 文件内容 
	 * @return boolean 
	 */ 
	public static void newFile(String filePathAndName, String fileContent) { 

		try { 
			String filePath = filePathAndName; 
			filePath = filePath.toString(); 
			File myFilePath = new File(filePath); 
			if (!myFilePath.exists()) { 
				myFilePath.createNewFile(); 
			} 
			FileWriter resultFile = new FileWriter(myFilePath); 
			PrintWriter myFile = new PrintWriter(resultFile); 
			String strContent = fileContent; 
			myFile.println(strContent); 
			resultFile.close(); 

		} 
		catch (Exception e) { 
			System.out.println( "新建目录操作出错 "); 
			e.printStackTrace(); 

		} 

	} 

	/** 
	 * 删除文件 
	 * @param filePathAndName String 文件路径及名称 如c:/fqf.txt 
	 * @param fileContent String 
	 * @return boolean 
	 */ 
	public static boolean delFile(String filePathAndName) { 
		boolean result = false;
		try { 
			String filePath = filePathAndName; 
			filePath = filePath.toString(); 
			java.io.File myDelFile = new java.io.File(filePath); 
			myDelFile.delete(); 
			result = true;
		} 
		catch (Exception e) { 
			System.out.println( "删除文件操作出错 "); 
			e.printStackTrace(); 

		} 
		return result;
	} 

	/** 
	 * 删除文件夹 
	 * @param filePathAndName String 文件夹路径及名称 如c:/fqf 
	 * @param fileContent String 
	 * @return boolean 
	 */ 
	public static boolean delFolder(String folderPath) { 
		try { 
			delAllFile(folderPath); //删除完里面所有内容 
			String filePath = folderPath; 
			filePath = filePath.toString(); 
			java.io.File myFilePath = new java.io.File(filePath); 
			myFilePath.delete(); //删除空文件夹
			return true;

		} 
		catch (Exception e) { 
			System.out.println( "删除文件夹操作出错 "); 
			e.printStackTrace(); 
			return false;
		} 

	} 

	/** 
	 * 删除文件夹里面的所有文件 
	 * @param path String 文件夹路径 如 c:/fqf 
	 */ 
	public static void delAllFile(String path) { 
		File file = new File(path); 
		if (!file.exists()) { 
			return; 
		} 
		if (!file.isDirectory()) { 
			return; 
		} 
		String[] tempList = file.list(); 
		File temp = null; 
		for (int i = 0; i < tempList.length; i++) { 
			if (path.endsWith(File.separator)) { 
				temp = new File(path + tempList[i]); 
			} 
			else { 
				temp = new File(path + File.separator + tempList[i]); 
			} 
			if (temp.isFile()) { 
				temp.delete(); 
			} 
			if (temp.isDirectory()) { 
				delAllFile(path+ File.separator+ tempList[i]);//先删除文件夹里面的文件 
				delFolder(path+ File.separator+ tempList[i]);//再删除空文件夹 
			} 
		} 
	} 

	/** 
	 * 复制单个文件 
	 * @param oldPath String 原文件路径 如：c:/fqf.txt 
	 * @param newPath String 复制后路径 如：f:/fqf.txt 
	 * @return boolean 
	 */ 
	public static void copyFile(String oldPath, String newPath) { 
		try { 
			int bytesum = 0; 
			int byteread = 0;
			String createfile = newPath;
			File oldfile = new File(oldPath);
			File newfile = new File(newPath);
			if (newfile.isDirectory()) {
				createfile = createfile + File.separator + oldfile.getName();
			}
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(createfile); 
				byte[] buffer = new byte[1444]; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					bytesum += byteread; //字节数 文件大小 
					System.out.println(bytesum); 
					fs.write(buffer, 0, byteread); 
				} 
				inStream.close(); 
			} 
		} 
		catch (Exception e) { 
			System.out.println( "复制单个文件操作出错 "); 
			e.printStackTrace(); 

		} 

	} 

	/** 
	 * 复制整个文件夹内容 
	 * @param oldPath String 原文件路径 如：c:/fqf 
	 * @param newPath String 复制后路径 如：f:/fqf/ff 
	 * @return boolean 
	 */ 
	public static void copyFolder(String oldPath, String newPath) { 

		try { 
			(new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
			File a=new File(oldPath); 
			String[] file=a.list(); 
			File temp=null; 
			for (int i = 0; i < file.length; i++) { 
				if(oldPath.endsWith(File.separator)){ 
					temp=new File(oldPath+file[i]); 
				} 
				else{ 
					temp=new File(oldPath+File.separator+file[i]); 
				} 

				if(temp.isFile()){ 
					FileInputStream input = new FileInputStream(temp); 
					FileOutputStream output = new FileOutputStream(newPath + File.separator + 
							(temp.getName()).toString()); 
					byte[] b = new byte[1024 * 5]; 
					int len; 
					while ( (len = input.read(b)) != -1) { 
						output.write(b, 0, len); 
					} 
					output.flush(); 
					output.close(); 
					input.close(); 
				} 
				if(temp.isDirectory()){//如果是子文件夹 
					copyFolder(oldPath+ File.separator+file[i],newPath+ File.separator+file[i]); 
				} 
			} 
		} 
		catch (Exception e) { 
			System.out.println( "复制整个文件夹内容操作出错 "); 
			e.printStackTrace(); 

		} 

	} 

	/**
	 * 列出路径下所有文件属性
	 * @param folder
	 * @return
	 */
	public static List<FileAttribute> list(String folder) {
		List<FileAttribute> result = new ArrayList<FileAttribute>();
		File a = new File(folder);
		if (a.isDirectory()) {
			for (String file : a.list()) {
				FileAttribute fa = new FileAttribute();
				File f = new File(file);
				fa.setFilename(file);
				fa.setSize(f.length());
				fa.setIsdir(f.isDirectory());
				result.add(fa);
			}
		}
		return result;
	}

	/**
	 * 列出路径下所有文件的不带后缀文件名
	 * 
	 * @author chwj
	 * @param folder
	 * @param suffix
	 * @return
	 */
	public static List<String> listFiles(String folder, String suffix) {
		class FileAccept implements FilenameFilter {
			String str;

			FileAccept(String str) {
				this.str = str;
			}

			public boolean accept(File dir, String name) {
				return name.endsWith(str);
			}
		}

		class dateComparator implements Comparator<String> {
			public int compare(String s1, String s2) {
				long l1, l2;
				try {
					l1 = Long.parseLong(s1);
				} catch (NumberFormatException nfe) {
					l1 = 0;
				}
				try {
					l2 = Long.parseLong(s2);
				} catch (NumberFormatException nfe) {
					l2 = 0;
				}
				// 顺序
				// return (int) (l1 - l2);
				// 倒序
				return (int) (l2 - l1);
			}
		}

		List<String> result = new ArrayList<String>();
		File dir = new File(folder);
		FileAccept filter = new FileAccept(suffix);
		if (dir.isDirectory()) {
			for (String filename : dir.list(filter)) {
				result.add(filename.substring(0, filename.lastIndexOf(suffix)));
			}
			Collections.sort(result, new dateComparator());
		} else {
			System.out.println("目录" + folder + "不存在");
		}
		return result;
	}

	/**
	 * 列出路径下所有文件及子目录
	 * @param folder
	 * @return
	 */
	public static String[] listFolder(String folder) {
		File a = new File(folder);
		if (a.isDirectory()) {
			return a.list();
		} else {
			return null;
		}
	}

	/**
	 * 列出路径下所有层次子目录
	 * @param folder
	 * @return
	 */
	public static List<String> listSubFolder(String folder) {
		List<String> result = null;
		File a = new File(folder);
		if (a.isDirectory()) {
			try{
				result = new ArrayList<String>();
				result.add(folder);
				for (String file : a.list()) {
					String subFolder = folder + File.separator + file;
					File f = new File(subFolder);
					if (f.isDirectory()) {
						result.addAll(listSubFolder(subFolder));
					}
				}
			}catch(Exception e){

			}

		}
		return result;
	}

	/**
	 * 获取文件路径
	 * @param file
	 * @return
	 */
	public static String getFilePath(String file) {
		File f = new File(file);
		if (f.isDirectory()) {
			return file;
		} else {
			return f.getPath();
		}
	}

	/** 
	 * 移动文件到指定目录 
	 * @param oldPath String 如：c:/fqf.txt 
	 * @param newPath String 如：d:/fqf.txt 
	 */ 
	public static void moveFile(String oldPath, String newPath) { 
		copyFile(oldPath, newPath); 
		delFile(oldPath); 

	} 

	/** 
	 * 移动文件到指定目录 
	 * @param oldPath String 如：c:/fqf.txt 
	 * @param newPath String 如：d:/fqf.txt 
	 */ 
	public static void moveFolder(String oldPath, String newPath) { 
		copyFolder(oldPath, newPath); 
		delFolder(oldPath); 

	} 

	/**
	 * 检查文件（路径）是否存在
	 * @param folder
	 * @return
	 */
	public static boolean checkPath(String folder) { 
		return new File(folder).exists(); 
	}

	/**
	 * 检查目录是否存在
	 * @param folder
	 * @return
	 */
	public static boolean checkDirectory(String folder) {
		return new File(folder).isDirectory();
	}

	/**
	 * 读取文件到字符串
	 * @param strFileName
	 * @return
	 */
	public static String readFileToString(String strFileName){
		StringBuffer buf = null;	//the intermediary, mutable buffer   
		BufferedReader breader = null;	//reader for the template files   
		try {   
			breader = new BufferedReader(new InputStreamReader(new FileInputStream((strFileName)),Charset.forName("utf-8")));
			buf = new StringBuffer();   
			while(breader.ready())     
				buf.append((char)breader.read());   
			breader.close();   
		} catch (Exception e) {   
			// e.printStackTrace();
		}   
		return buf == null ? "" : buf.toString();   
	}

	/**
	 * 写字符串到文件
	 * @param str
	 * @param strFileName
	 * @return
	 */
	public static boolean writeStringToFile(String str, String strFileName) {
		boolean result = false;
		OutputStreamWriter osw;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(strFileName));
			osw.write(str,0,str.length()); 
			osw.close();
			result = true;
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		}   
		return result;
	}

	/**
     * 向文本文件中写入内容或追加新内容,如果append为true则直接追加新内容,<br>
     * 如果append为false则覆盖原来的内容<br>
     * 
     * @param path
     * @param content
     * @param append
     */
    public static void writeFile(String path, String content, boolean append) {
        File writefile;
        FileOutputStream fw = null;
        try {
            // 通过这个对象来判断是否向文本文件中追加内容
            // boolean addStr = append;
        	if(path.contains(File.separator)){//检查文件夹是否存在，不存在则创建
        		String dirpath = path.substring(0, path.lastIndexOf(File.separator));
        		File dir = new File(dirpath);
        		if(!dir.exists())
        			dir.mkdirs();
        	}
        	
            writefile = new File(path);

            // 如果文本文件不存在则创建它
            if (writefile.exists() == false) {
                writefile.createNewFile();
                writefile = new File(path); // 重新实例化
            }

            fw = new FileOutputStream(writefile, append);
//            System.out.println("###content:" + content);
            fw.write(content.getBytes("UTF-8"));
            fw.flush();
//            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        	if(fw != null){
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }

    }

	/**
	 * 修改文件权限, sudo命令需要输入密码时使用
	 * @param fileName 文件名或文件夹名
	 * @param chmod 权限(仅限于Linux系统)
	 * @param recursion 递归
	 */
	public static void changeFileLimit(String fileName, String chmod, String recursion, String osPasswd){
		if(!fileName.contains("/media")){//限制赋权限
			return;
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1)
			return;
		try {
			Runtime.getRuntime().exec(
					new String[] { "sh", "-c", "echo \"" + osPasswd + "\" | sudo -S chmod " + recursion + " " + chmod + " " + fileName }).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找zip里的文件
	 * @param file
	 * @param filename
	 * @return
	 */
	public static boolean findFileInZipFile(File file, String filename) {
		boolean result = false;
		java.util.zip.ZipInputStream zin;
		try {
			zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(file));
			java.util.zip.ZipEntry entry = zin.getNextEntry();
			while(entry!=null){
				String name = entry.getName();
				if(name.contains("/"))
					name = name.substring(name.lastIndexOf("/") + 1, name.length());
				if(name.equals(filename)){
					result = true;
					break;
				}
				entry = zin.getNextEntry();
				
			}
			zin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查找zip里的文件
	 * @param file
	 * @param filename
	 * @return
	 */
	public static boolean findFileInZipFile(File file, String[] filenames) {
		boolean result = true;
		java.util.zip.ZipInputStream zin;
		try {
			for(String filename : filenames){
				zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(file));
				java.util.zip.ZipEntry entry = zin.getNextEntry();
				if(result){
					while(entry!=null){
						String name = entry.getName();
						if(name.contains("/"))
							name = name.substring(name.lastIndexOf("/") + 1, name.length());
						if(name.equals(filename)){
							break;
						}
						entry = zin.getNextEntry();
					}
					if(entry==null){
						result = false;
					}
				}else{
					break;
				}
				zin.close();
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 取得根路径
	 * @return
	 */
	public static String getRootPath(Class clazz){
		String root = clazz.getResource("/").getPath();
		String[] roots = root.substring(1, root.length() - 1).split("/");
		root = "";
		for (int i = 0; i < roots.length - 2; i++) {
			root = root + "/" + roots[i];
		}
		root = root.substring(1, root.length()).replace("%20", " ");//取得根路径
		return root;
	}
	
	/**
	 * 读取ini配置文件
	 * @param filename
	 * @return
	 */
	public static Map<String, String> readIniFile(String filename){
		Map<String, String> result = null;
		File inifile = new File(filename);
    	if (inifile.exists()) {
			Properties ini = new Properties();
			try {
				FileInputStream inputStream = new FileInputStream(inifile);
				ini.load(inputStream);
				inputStream.close();
				result = new HashMap<String, String>();
				Set keyValue = ini.keySet();
				for (Object key : keyValue) {
					if (ini.containsKey(key)) {
						result.put((String) key, ini.getProperty((String) key));
					}
				}
			} catch (Exception e) {
				result = null;
			}
    	} else {
    		System.out.println("配置文件" + filename + "不存在");
    	}
    	return result;
	}
}