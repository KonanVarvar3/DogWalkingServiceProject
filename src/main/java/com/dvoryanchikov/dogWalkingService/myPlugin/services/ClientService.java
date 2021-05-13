package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.common.StatusResponse;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.jira.ClientIssueService;

public class ClientService {
    private ActiveObjects ao;
    private ClientManager clientManager;
    private ClientIssueService clientIssueService;

    private ClientService(ActiveObjects ao) {
        this.ao = ao;
        clientManager = new ClientManager(ao);
        clientIssueService = new ClientIssueService(ao);
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
        model.setIssueId(clientIssueService.create(model).getId().toString());
        return clientManager.save(model);
    }

    public Boolean deleteClientByUniqueId(String uniqueId) {
        return clientIssueService.deleteIssue(uniqueId) && clientManager.deleteByUniqueId(uniqueId);
    }

    public boolean updateClient(Client model) {
        return clientManager.update(model) && clientIssueService.updateIssue(model);
    }
}
