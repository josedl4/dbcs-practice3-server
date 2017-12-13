/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.login;

import Dominio.Usuario;
import Persistencia.UsuarioFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("login")
public class LoginResource extends Application {
    UsuarioFacadeLocal usuarioFacade = lookupUsuarioFacadeLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(content);
        
        Usuario us = usuarioFacade.find((String)json.get("username"));
        if(us == null){
            return Response.status(400).build();
        } else if(us.getClave().equals((String)json.get("password"))){
            return Response.status(200).build();
        } else {
            return Response.status(402).build();
        }
    }

    private UsuarioFacadeLocal lookupUsuarioFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/dbcs-practice3-server/UsuarioFacade!Persistencia.UsuarioFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
