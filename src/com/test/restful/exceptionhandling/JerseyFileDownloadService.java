package com.test.restful.exceptionhandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

// http://localhost:8080/RESTJersey/rest/download/none.pdf

/*
 * In this example, we will learn to handle custom exceptions using ExceptionMapper interface 
 * while developing Jersey RESTful web services. For demo purpose, I am modifying the sourcecode 
 * written for jersey download file example.
 */

 
@Path("/download")
public class JerseyFileDownloadService 
{
    @GET
    @Path("/{fileName}")
    public Response downloadPdfFile(final @PathParam("fileName") String fileName) throws MissingFileException
    {
        //final String fullFilePath = "C:/temp/" + fileName;
    	final String fullFilePath = "/Users/temp/" + fileName;
         
        File file = new File(fullFilePath);
         
        if(file.exists() == false){
            throw new MissingFileException(fileName + " does not existing on this server !!");
        }
         
        StreamingOutput fileStream =  new StreamingOutput()
        {
            @Override
            public void write(java.io.OutputStream output) throws IOException
            {
                try
                {
                    java.nio.file.Path path = Paths.get(fullFilePath);
                    byte[] data = Files.readAllBytes(path);
                    output.write(data);
                    output.flush();
                } 
                catch (IOException e) 
                {
                    throw new IOException("Error while reading file :: '"+fileName+"' !!");
                }
            }
        };
        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = '"+fileName)
                .build();
    }
}
