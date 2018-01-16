package com.wyg.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {
		public static String getPoperties(String name)
		{
			FileInputStream fis=null;
			String result=null;
			try {
				fis=new FileInputStream("C:/Projects/BookStore/WebContent/webapp.properties");
				Properties properties=new Properties();
				properties.load(fis);
				if(properties.getProperty(name)!=null)
					result=properties.getProperty(name);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(fis!=null)try {fis.close();} catch (IOException e) {e.printStackTrace();}
			}
			return result;
		}
}
