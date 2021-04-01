package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;

public class ClientService {
    private ActiveObjects ao;
    private ClientManager clientManager;

    private ClientService(ActiveObjects ao) {
        this.ao = ao;
        this.clientManager = clientManager.create(ao);
    }

    public static ClientService create(ActiveObjects ao) {
        return new ClientService(ao);
    }

    public Client getClientByUniqueId(String uniqueId) {
        return clientManager.getClientByUniqueId(uniqueId);
    }

    public Client[] getAllClients() {
        return clientManager.getAllClients();
    }

    public boolean createClient(Client model) {
        return clientManager.createClient(model);
    }

    public boolean deleteClientByUniqueId(String uniqueId) {

        return clientManager.deleteClientByUniqueId(uniqueId);
    }

    public boolean updateClient(Client model) {

        return clientManager.updateClient(model);
    }
}
