package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.RequestWalkManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;

public class RequestWalkService {
    private ActiveObjects ao;
    private RequestWalkManager requestWalkManager;

    private RequestWalkService(ActiveObjects ao){
        this.ao = ao;
        this.requestWalkManager = new RequestWalkManager(ao);
    }

    public static RequestWalkService create(ActiveObjects ao){
        return new RequestWalkService(ao);
    }

    public RequestWalk getRequestWalkByUniqueId(String uniqueId){
        return requestWalkManager.getRequestWalkByUniqueId(uniqueId);
    }

    public boolean createRequestWalk(RequestWalk model){
        return requestWalkManager.createRequestWalk(model);
    }

    public boolean deleteRequestWalkByUniqueId(String uniqueId){
        return requestWalkManager.deleteRequestWalkByUniqueId(uniqueId);
    }

    public boolean updateRequestWalk(RequestWalk model){
        return requestWalkManager.updateRequestWalk(model);
    }
}
