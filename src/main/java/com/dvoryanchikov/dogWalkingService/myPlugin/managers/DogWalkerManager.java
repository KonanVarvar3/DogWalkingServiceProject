package com.dvoryanchikov.dogWalkingService.myPlugin.managers;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.entities.IDogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import net.java.ao.Query;

public class DogWalkerManager {
    private ActiveObjects ao;

    public DogWalkerManager(ActiveObjects ao) {
        this.ao = ao;
    }

    public boolean createDogWalker(DogWalker model) {
        try {
            IDogWalker entity = ao.create(IDogWalker.class);
            model.toEntity(entity);
            entity.save();
            return true;
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public boolean deleteDogWalkerByUniqueId(String uniqueId) {
        try {
            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            IDogWalker[] entities = ao.find(IDogWalker.class, query);
            if (entities != null && entities.length > 0) {
                ao.delete(entities);
            }

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public DogWalker getDogWalkerByUniqueId(String uniqueId) {
        try {
            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            IDogWalker[] entities = ao.find(IDogWalker.class, query);
            if (entities != null && entities.length > 0) {
                return DogWalker.fromEntity(entities[0]);
            }
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return null;
    }

    public boolean updateDogWalker(DogWalker model) {
        try {
            if (model != null) {
                IDogWalker entity = (IDogWalker) getDogWalkerByUniqueId(model.getUniqueId());
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
