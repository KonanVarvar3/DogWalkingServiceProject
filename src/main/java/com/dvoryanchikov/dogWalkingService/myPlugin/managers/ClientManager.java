package com.dvoryanchikov.dogWalkingService.myPlugin.managers;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.entities.IClient;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import net.java.ao.Query;

public class ClientManager {
    private ActiveObjects ao;

    public ClientManager(ActiveObjects ao) {
        this.ao = ao;
    }

    public boolean createClient(Client model) {
        try {
            IClient entity = ao.create(IClient.class);
            model.toEntity(entity);
            entity.save();
            return true;
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public boolean deleteClientByUniqueId(String uniqueId) {
        try {
            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            IClient[] entities = ao.find(IClient.class, query);
            if (entities != null && entities.length > 0) {
                ao.delete(entities);
            }

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public Client getClientByUniqueId(String uniqueId) {
        try {
            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            IClient[] entities = ao.find(IClient.class, query);
            if (entities != null && entities.length > 0) {
                return Client.fromEntity(entities[0]);
            }
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return null;
    }

    public boolean updateClient(Client model) {
        try {
            if (model != null) {
                IClient entity = (IClient) getClientByUniqueId(model.getUniqueId());
                if (entity != null) {
                    model.toEntity(entity);
                    entity.save();
                    return true;
                }
            }
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

}
