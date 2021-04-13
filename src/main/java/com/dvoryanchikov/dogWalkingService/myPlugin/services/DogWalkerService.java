package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogWalkerManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.jira.DogWalkerIssueService;

public class DogWalkerService {

    private ActiveObjects ao;
    private DogWalkerManager dogWalkerManager;
    private DogWalkerIssueService dogWalkerIssueService = new DogWalkerIssueService();

    private DogWalkerService(ActiveObjects ao) {

        this.ao = ao;
        this.dogWalkerManager = new DogWalkerManager(ao);
    }

    public static DogWalkerService create(ActiveObjects ao) {

        return new DogWalkerService(ao);
    }

    public DogWalker getDogWalkerByUniqueId(String uniqueId) {

        return dogWalkerManager.getByUniqueId(uniqueId);
    }

    public DogWalker[] getAllDogWalkers(){

        return dogWalkerManager.getAll();
    }

    public boolean createDogWalker(DogWalker model) {

        return (dogWalkerManager.save(model) && dogWalkerIssueService.create(model));
    }

    public boolean deleteDogWalkerByUniqueId(String uniqueId) {

        return dogWalkerManager.deleteByUniqueId(uniqueId);
    }

    public boolean updateDogWalker(DogWalker model) {

        return dogWalkerManager.update(model);
    }
}
