package com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.DogWalkerService;

public class DogWalkerImpl {
    private ActiveObjects ao;
    private DogWalkerService dogWalkerService;

    private DogWalkerImpl(ActiveObjects ao){
        this.ao = ao;
        this.dogWalkerService = dogWalkerService.create(ao);
    }

    public static DogWalkerImpl create(ActiveObjects ao){
        return new DogWalkerImpl(ao);
    }

    public DogWalker getDogWalkerByUniqueId(String uniqueId){
        return dogWalkerService.getDogWalkerByUniqueId(uniqueId);
    }

    public DogWalker[] getAllDogWalkers(){
        return dogWalkerService.getAllDogWalkers();
    }

    public boolean createDogWalker(DogWalker model){
        return dogWalkerService.createDogWalker(model);
    }

    public boolean deleteDogWalker(String uniqueId){
        return dogWalkerService.deleteDogWalkerByUniqueId(uniqueId);
    }

    public boolean updateDogWalker(DogWalker model){
        return dogWalkerService.updateDogWalker(model);
    }
}
