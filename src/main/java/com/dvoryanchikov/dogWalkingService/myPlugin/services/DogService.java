package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.jira.DogIssueService;

public class DogService {
    private ActiveObjects ao;
    private DogManager dogManager;
    private DogIssueService dogIssueService;

    private DogService(ActiveObjects ao) {
        this.ao = ao;
        this.dogManager = new DogManager(ao);
        this.dogIssueService = new DogIssueService(ao);
    }

    public static DogService create(ActiveObjects ao) {
        return new DogService(ao);
    }

    public Dog getDogByUniqueId(String uniqueId) {
        return dogManager.getByUniqueId(uniqueId);
    }

    public Dog[] getDogByOwnerId(String ownerId){
        return dogManager.getByOwnerId(ownerId);
    }

    public Dog[] getAllDogs() {
        return dogManager.getAll();
    }

    public boolean createDog(Dog model) {
        return (dogManager.save(model) && dogIssueService.create(model));
    }

    public boolean deleteDogByUniqueId(String uniqueId) {
        return dogManager.deleteByUniqueId(uniqueId);
    }

    public boolean updateDog(Dog model) {
        return dogManager.update(model);
    }

    public boolean fullUpdateDog(Dog model) {
        return dogManager.fullUpdate(model);
    }
}
