package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.jira.ClientIssueService;

public class ClientService {
    private ActiveObjects ao;
    private ClientManager clientManager;
    private ClientIssueService clientIssueService = new ClientIssueService();

    private ClientService(ActiveObjects ao) {
        this.ao = ao;
        this.clientManager = clientManager.create(ao);
    }

    public static ClientService create(ActiveObjects ao) {
        return new ClientService(ao);
    }

    public Client getClientByUniqueId(String uniqueId) {
        return clientManager.getByUniqueId(uniqueId);
    }

    public Client[] getAllClients() {
        return clientManager.getAll();
    }

    public boolean createClient(Client model) {

        return (clientManager.save(model) && clientIssueService.create(model));
    }

    public boolean deleteClientByUniqueId(String uniqueId) {

        return clientManager.deleteByUniqueId(uniqueId);
    }

    public boolean updateClient(Client model) {

        return clientManager.update(model);
    }
}
