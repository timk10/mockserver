package com.mockserver.resources;

/**
 * Created by tim on 2/20/16.
 */
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/exchanges")
public class ExchangeResource {
    private static int i = 0;

    private static List<Data> list;

    private class Data {
        private final String d;
        private final int timeout;

        public Data(String d, int timeout) {
            this.d = d;
            this.timeout = timeout;
        }

        public String getData() {
            return d;
        }

        public int getTimeout() {
            return timeout;
        }
    }

    public ExchangeResource() {
        list = new LinkedList<Data>();
    }

    @POST @Path("/reset")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response reset() throws Exception {
        list = new LinkedList<Data>();
        i = 0;
        return Response.status(200).build();
    }

    @GET
    @Path("/{id}")
    public String getByIndex(@PathParam("id") int idx) {
        if (idx < list.size()) {
            Data d = list.get(idx);
            return d.getData();
        }
        return "";
    }

    @POST @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(
            @FormDataParam("file") InputStream in,
            @FormDataParam("file") FormDataContentDisposition file,
            @QueryParam("timeout") String timeoutStr) throws Exception {
        String str = IOUtils.toString(in, StandardCharsets.UTF_8);
        int timeout = 0;
        if (timeoutStr != null) {
            try {
                timeout = Integer.parseInt(timeoutStr);
            } catch (Exception e) { }
        }
        list.add(new Data(str, timeout));
        return Response.status(200).build();
    }

    @GET
    @Path("/request/{subResources:.*}")
    public String get(@PathParam("subResources") String subResources) {
        Data data = list.get(i);
        i = (i + 1) % list.size();
        try {
            Thread.sleep(data.getTimeout());
        } catch (InterruptedException e) { }
        return data.getData();
    }
}

