package com.dvoryanchikov.dogWalkingService.myPlugin.managers;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.entities.IRequestWalk;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;
import net.java.ao.Query;

public class RequestWalkManager {
    private ActiveObjects ao;

    public RequestWalkManager(ActiveObjects ao) {
        this.ao = ao;
    }

    public boolean createRequestWalk(RequestWalk model) {
        try {
            IRequestWalk entity = ao.create(IRequestWalk.class);
            model.toEntity(entity);
            entity.save();
            return true;
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public boolean deleteRequestWalkByUniqueId(String uniqueId) {
        try {
            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            IRequestWalk[] entities = ao.find(IRequestWalk.class, query);
            if (entities != null && entities.length > 0) {
                ao.delete(entities);
            }

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public RequestWalk getRequestWalkByUniqueId(String uniqueId) {
        try {
            Query query = Query.select().where("UNIQUE_ID = '" + uniqueId + "'");
            IRequestWalk[] entities = ao.find(IRequestWalk.class, query);
            if (entities != null && entities.length > 0) {
                return RequestWalk.fromEntity(entities[0]);
            }
        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return null;
    }

    public boolean updateRequestWalk(RequestWalk model) {
        try {
            if (model != null) {
                IRequestWalk entity = (IRequestWalk) getRequestWalkByUniqueId(model.getUniqueId());
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
