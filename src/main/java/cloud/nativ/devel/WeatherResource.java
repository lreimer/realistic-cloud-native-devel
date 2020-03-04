package cloud.nativ.devel;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;

@ApplicationScoped
@Path("weather")
public class WeatherResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeather(@QueryParam("q") @DefaultValue("Rosenheim") String q) {
        Map<String, String> data = Collections.singletonMap(q, "sunshine");
        return Response.ok(data).build();
    }
}
