package com.test.restful;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

// http://localhost:8080/RESTJersey/rest/JSONUserService/ping

@Path("/JSONUserService")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class JSONUserService {
	
   JSONUserDao userDao = new JSONUserDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";

   @GET
   @Path("ping")
   public String getServerTime() {
       System.out.println("RESTful Service 'MessageService' is running ==> ping");
       return "received ping on "+new Date().toString();
   }

   @GET
   @Path("/users")
   @Produces({MediaType.APPLICATION_JSON})
   public List<JSONUser> getJSONUsers() {
	   System.out.println("JSONUserService|getJSONUsers|begin.....");
      return userDao.getAllJSONUsers();
   }

   @GET
   @Path("/users/{userid}")
   @Produces({MediaType.APPLICATION_JSON})
   public JSONUser getJSONUser(@PathParam("userid") int userid){
      return userDao.getJSONUser(userid);
   }

   @POST
   @Path("/users")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createJSONUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
      JSONUser user = new JSONUser(id, name, profession);
      int result = userDao.addJSONUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @PUT
   @Path("/users")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateJSONUser(@FormParam("id") int id,
      @FormParam("name") String name,
      @FormParam("profession") String profession,
      @Context HttpServletResponse servletResponse) throws IOException{
      JSONUser user = new JSONUser(id, name, profession);
      int result = userDao.updateJSONUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @DELETE
   @Path("/users/{userid}")
   @Produces({MediaType.APPLICATION_JSON})
   public String deleteJSONUser(@PathParam("userid") int userid){
      int result = userDao.deleteJSONUser(userid);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }

   @OPTIONS
   @Path("/users")
   @Produces({MediaType.APPLICATION_JSON})
   public String getSupportedOperations(){
      return "<operations>GET, PUT, POST, DELETE</operations>";
   }
}