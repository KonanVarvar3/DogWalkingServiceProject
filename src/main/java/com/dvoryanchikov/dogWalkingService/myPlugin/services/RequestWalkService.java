package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.RequestWalkManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.jira.RequestWalkIssueService;

public class RequestWalkService {
    private ActiveObjects ao;
    private RequestWalkManager requestWalkManager;
    private RequestWalkIssueService requestWalkIssueService;

    private RequestWalkService(ActiveObjects ao){
        this.ao = ao;
        this.requestWalkManager = new RequestWalkManager(ao);
        this.requestWalkIssueService = new RequestWalkIssueService(ao);
    }

    public static RequestWalkService create(ActiveObjects ao){
        return new RequestWalkService(ao);
    }

    public RequestWalk[] getAllRequestWalks(){
        return requestWalkManager.getAll();
    }

    public RequestWalk getRequestWalkByUniqueId(String uniqueId){
        return requestWalkManager.getByUniqueId(uniqueId);
    }

    public boolean createRequestWalk(RequestWalk model){
        return requestWalkManager.save(model) && requestWalkIssueService.create(model);
    }

    public boolean deleteRequestWalkByUniqueId(String uniqueId){
        return requestWalkManager.deleteByUniqueId(uniqueId);
    }

    public boolean updateRequestWalk(RequestWalk model){
        return requestWalkManager.update(model);
    }
}
