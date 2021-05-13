package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogWalkerManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.jira.DogWalkerIssueService;

public class DogWalkerService {

    private ActiveObjects ao;
    private DogWalkerManager dogWalkerManager;
    private DogWalkerIssueService dogWalkerIssueService;

    private DogWalkerService(ActiveObjects ao) {
        this.ao = ao;
        dogWalkerManager = new DogWalkerManager(ao);
        dogWalkerIssueService = new DogWalkerIssueService(ao);
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
        model.setIssueId(dogWalkerIssueService.create(model).getId().toString());
        return dogWalkerManager.save(model);
    }

    public boolean deleteDogWalkerByUniqueId(String uniqueId) {
        return dogWalkerIssueService.deleteIssue(uniqueId) && dogWalkerManager.deleteByUniqueId(uniqueId);
    }

    public boolean updateDogWalker(DogWalker model) {
        dogWalkerManager.update(model);
        return dogWalkerIssueService.changeIssueStatus(model);
    }

    public boolean allUpdateDogWalker(DogWalker model) {
        return dogWalkerManager.allUpdate(model) && dogWalkerIssueService.updateIssue(model);
    }
}
