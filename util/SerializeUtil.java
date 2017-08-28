package com.suyaoxing.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class SerializeUtil {

	public static byte[] serialize(Object object)
	{
		byte[] bytes = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;			
		try
		{
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			bytes = baos.toByteArray();
			return bytes;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(baos != null){
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(oos != null){
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
		return null;
	}
	
	public static Object deserialize(byte[] bytes)
	{
		Object object = null;
		
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try
		{
			System.out.println("bytes="+bytes);
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			object = ois.readObject();
			return object;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(bais != null){
				try {
					bais.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		return null;		
	}
}
