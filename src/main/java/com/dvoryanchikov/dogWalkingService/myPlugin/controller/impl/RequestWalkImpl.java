package com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.RequestWalkService;

public class RequestWalkImpl {
    private ActiveObjects ao;
    private RequestWalkService requestWalkService;

    private RequestWalkImpl(ActiveObjects ao){
        this.ao = ao;
        this.requestWalkService = requestWalkService.create(ao);
    }

    public static RequestWalkImpl create(ActiveObjects ao){
        return new RequestWalkImpl(ao);
    }

    public RequestWalk getRequestWalkByUniqueId(String uniqueId){
        return requestWalkService.getRequestWalkByUniqueId(uniqueId);
    }

    public boolean createRequestWalk(RequestWalk model){
        return requestWalkService.createRequestWalk(model);
    }

    public boolean deleteRequestWalk(String uniqueId){
        return requestWalkService.deleteRequestWalkByUniqueId(uniqueId);
    }

    public boolean updateRequestWalk(RequestWalk model){
        return requestWalkService.updateRequestWalk(model);
    }
}
