package api.login;

import Dominio.Usuario;
import Persistencia.UsuarioFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Recurso Login para la identificacion
 */
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

    /**
     * Metodo para la identificacion del usuario. A partir del String pasado
     * como parametro se contruye un JSONObject y comprueba de forma simple la
     * identifacion.
     *
     * @param content String con el contenido del JSONObject
     * @return true si el usuario existe en la bd, false en caso contrario
     * @throws ParseException si no se ha podido convertir el String en JSONObject
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String content) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(content);
        // Se recoge el usuario
        Usuario us = usuarioFacade.find((String) json.get("username"));
        // Se comprueba la password
        if (us == null) {
            return Response.status(400).build();
        } else if (us.getClave().equals((String) json.get("password"))) {
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
