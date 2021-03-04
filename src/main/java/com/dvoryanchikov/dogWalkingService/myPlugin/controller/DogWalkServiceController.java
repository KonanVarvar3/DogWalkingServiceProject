package com.dvoryanchikov.dogWalkingService.myPlugin.controller;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl.ClientImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl.DogImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl.DogWalkerImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl.RequestWalkImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/dogWalkingService")
public class DogWalkServiceController {

    private ActiveObjects ao;

    public DogWalkServiceController(ActiveObjects ao) {
        this.ao = ao;
    }

    ////////////////////////////////////////////
    //Rest for clients
    private ClientImpl clientImpl;

    public ClientImpl getClientImpl() {
        if (clientImpl == null) {
            clientImpl = ClientImpl.create(ao);
        }
        return clientImpl;
    }

    @POST
    @Path("/createClient")
    public Response createClient(@Valid @RequestBody Client client) {
        return Response.ok(getClientImpl().createClient(client)).build();
    }

    @GET
    @Path("/client/{uniqueId}")
    public Response getClientByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getClientImpl().getClientByUniqueId(uniqueId)).build();
    }

    @PUT
    @Path("/updateClient")
    public Response updateClient(@Valid @RequestBody Client client) {
        return Response.ok(getClientImpl().updateClient(client)).build();
    }

    @DELETE
    @Path("/deleteClient/{uniqueId}")
    public Response deleteClientByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getClientImpl().deleteClient(uniqueId)).build();
    }

    ///////////////////////////////////////
    //Rest for dog
    private DogImpl dogImpl;

    public DogImpl getDogImpl() {
        if (dogImpl == null) {
            dogImpl = DogImpl.create(ao);
        }
        return dogImpl;
    }

    @POST
    @Path("/createDog")
    public Response createDog(@Valid @RequestBody Dog dog) {
        return Response.ok(getDogImpl().createDog(dog)).build();
    }

    @GET
    @Path("/dog/{uniqueId}")
    public Response getDogByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getDogImpl().getDogByUniqueId(uniqueId)).build();
    }

    @PUT
    @Path("/updateDog")
    public Response updateDog(@Valid @RequestBody Dog dog) {
        return Response.ok(getDogImpl().updateDog(dog)).build();
    }

    @DELETE
    @Path("/deleteDog/{uniqueId}")
    public Response deleteDogByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getDogImpl().deleteDog(uniqueId)).build();
    }

    ////////////////////////////////////////////////////////////
    //Rest for dog-walker
    private DogWalkerImpl dogWalkerImpl;

    public DogWalkerImpl getDogWalkerImpl() {
        if (dogWalkerImpl == null) {
            dogWalkerImpl = DogWalkerImpl.create(ao);
        }
        return dogWalkerImpl;
    }

    @POST
    @Path("/createDogWalker")
    public Response createDogWalker(@Valid @RequestBody DogWalker dogWalker) {
        return Response.ok(getDogWalkerImpl().createDogWalker(dogWalker)).build();
    }

    @GET
    @Path("/dogWalker/{uniqueId}")
    public Response getDogWalkerByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getDogWalkerImpl().getDogWalkerByUniqueId(uniqueId)).build();
    }

    @PUT
    @Path("/updateDogWalker")
    public Response updateDogWalker(@Valid @RequestBody DogWalker dogWalker) {
        return Response.ok(getDogWalkerImpl().updateDogWalker(dogWalker)).build();
    }

    @DELETE
    @Path("/deleteDogWalker/{uniqueId}")
    public Response deleteDogWalkerByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getDogWalkerImpl().deleteDogWalker(uniqueId)).build();
    }

    /////////////////////////////////////////////////////////////
    //Rest for request walk
    private RequestWalkImpl requestWalkImpl;

    public RequestWalkImpl getRequestWalkImpl() {
        if (requestWalkImpl == null) {
            requestWalkImpl = RequestWalkImpl.create(ao);
        }
        return requestWalkImpl;
    }

    @POST
    @Path("/createRequestWalk")
    public Response createRequestWalk(@Valid @RequestBody RequestWalk requestWalk) {
        return Response.ok(getRequestWalkImpl().createRequestWalk(requestWalk)).build();
    }

    @GET
    @Path("/requestWalk/{uniqueId}")
    public Response getRequestWalkByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getRequestWalkImpl().getRequestWalkByUniqueId(uniqueId)).build();
    }

    @PUT
    @Path("/updateRequestWalk")
    public Response updateRequestWalk(@Valid @RequestBody RequestWalk requestWalk) {
        return Response.ok(getRequestWalkImpl().updateRequestWalk(requestWalk)).build();
    }

    @DELETE
    @Path("/deleteRequestWalk/{uniqueId}")
    public Response deleteRequestWalkByUniqueId(@PathVariable(value = "uniqueId") String uniqueId) {
        return Response.ok(getRequestWalkImpl().deleteRequestWalk(uniqueId)).build();
    }
}
