package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogWalkerManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;

public class DogWalkerService {
    private ActiveObjects ao;
    private DogWalkerManager dogWalkerManager;

    private DogWalkerService(ActiveObjects ao){
        this.ao = ao;
        this.dogWalkerManager = new DogWalkerManager(ao);
    }

    public static DogWalkerService create(ActiveObjects ao){
        return new DogWalkerService(ao);
    }

    public DogWalker getDogWalkerByUniqueId(String uniqueId){
        return dogWalkerManager.getDogWalkerByUniqueId(uniqueId);
    }

    public boolean createDogWalker(DogWalker model){
        return dogWalkerManager.createDogWalker(model);
    }

    public boolean deleteDogWalkerByUniqueId(String uniqueId){
        return dogWalkerManager.deleteDogWalkerByUniqueId(uniqueId);
    }

    public boolean updateDogWalker(DogWalker model){
        return dogWalkerManager.updateDogWalker(model);
    }
}
