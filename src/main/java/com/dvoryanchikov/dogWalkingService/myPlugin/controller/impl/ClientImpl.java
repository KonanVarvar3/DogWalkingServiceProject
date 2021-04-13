package com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.ClientService;

public class ClientImpl {
    private ActiveObjects ao;
    private ClientService clientService;

    private ClientImpl(ActiveObjects ao){
        this.ao = ao;
        this.clientService = clientService.create(ao);
    }

    public static ClientImpl create(ActiveObjects ao){
        return new ClientImpl(ao);
    }

    public Client getClientByUniqueId(String uniqueId){
        return clientService.getClientByUniqueId(uniqueId);
    }

    public Client[] getAllClients(){
        return clientService.getAllClients();
    }

    public boolean createClient(Client model) {
        return clientService.createClient(model);
    }

    public boolean deleteClient(String uniqueId){
        return clientService.deleteClientByUniqueId(uniqueId);
    }

    public boolean updateClient(Client model){
        return clientService.updateClient(model);
    }
}
