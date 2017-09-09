package com.test.restful;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  

public class JSONUserDao { 
	public List<JSONUser> getAllJSONUsers(){
	      List<JSONUser> userList = null;
	      try {
	         File file = new File("/Users/rahamathullahrahamathullah/Documents/workspace/RESTFULTEST/src/com/test/restful/Users1.dat");
	         if (!file.exists()) {
	        	 System.out.println("####### if.......");

	            userList = new ArrayList<JSONUser>();
	            userList.add(new JSONUser(1, "Rahamath", "Teacher"));
	            userList.add(new JSONUser(2, "John", "Java"));
	            userList.add(new JSONUser(3, "Peter", "SAP"));
	            userList.add(new JSONUser(4, "Mugan", "C++"));
	            saveJSONUserList(userList);		
	         }
	         else{
	        	 System.out.println("####### else.......");
	            FileInputStream fis = new FileInputStream(file);
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            userList = (List<JSONUser>) ois.readObject();
	            ois.close();
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }		
	      return userList;
	   }

	   public JSONUser getJSONUser(int id){
	      List<JSONUser> users = getAllJSONUsers();

	      for(JSONUser user: users){
	         if(user.getId() == id){
	            return user;
	         }
	      }
	      return null;
	   }

	   public int addJSONUser(JSONUser pJSONUser){
	      List<JSONUser> userList = getAllJSONUsers();
	      boolean userExists = false;
	      for(JSONUser user: userList){
	         if(user.getId() == pJSONUser.getId()){
	            userExists = true;
	            break;
	         }
	      }		
	      if(!userExists){
	         userList.add(pJSONUser);
	         saveJSONUserList(userList);
	         return 1;
	      }
	      return 0;
	   }

	   public int updateJSONUser(JSONUser pJSONUser){
	      List<JSONUser> userList = getAllJSONUsers();

	      for(JSONUser user: userList){
	         if(user.getId() == pJSONUser.getId()){
	            int index = userList.indexOf(user);			
	            userList.set(index, pJSONUser);
	            saveJSONUserList(userList);
	            return 1;
	         }
	      }		
	      return 0;
	   }

	   public int deleteJSONUser(int id){
	      List<JSONUser> userList = getAllJSONUsers();

	      for(JSONUser user: userList){
	         if(user.getId() == id){
	            int index = userList.indexOf(user);			
	            userList.remove(index);
	            saveJSONUserList(userList);
	            return 1;   
	         }
	      }		
	      return 0;
	   }

	   private void saveJSONUserList(List<JSONUser> userList){
	      try {
	         File file = new File("JSONUsers.dat");
	         FileOutputStream fos;

	         fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(userList);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	}