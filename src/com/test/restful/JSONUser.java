package com.test.restful;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 

@XmlRootElement(name = "user") 
public class JSONUser implements Serializable {

	   private static final long serialVersionUID = 1L;
	   private int id;
	   private String name;
	   private String profession;

	   public JSONUser(){}

	   public JSONUser(int id, String name, String profession){
	      this.id = id;
	      this.name = name;
	      this.profession = profession;
	   }

	   public int getId() {
	      return id;
	   }
	   @XmlElement
	   public void setId(int id) {
	      this.id = id;
	   }
	   public String getName() {
	      return name;
	   }
	   @XmlElement
	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getProfession() {
	      return profession;
	   }
	   @XmlElement
	   public void setProfession(String profession) {
	      this.profession = profession;
	   }	
	   
	   @Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + "/" + this.name + "/" + this.profession;
	}

	   @Override
	   public boolean equals(Object object){
	      if(object == null){
	         return false;
	      }else if(!(object instanceof JSONUser)){
	         return false;
	      }else {
	         JSONUser user = (JSONUser)object;
	         if(id == user.getId()
	            && name.equals(user.getName())
	            && profession.equals(user.getProfession())
	         ){
	            return true;
	         }			
	      }
	      return false;
	   }	
	}