package com.dvoryanchikov.dogWalkingService.myPlugin.controller.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;
import com.dvoryanchikov.dogWalkingService.myPlugin.services.DogService;

public class DogImpl {
    private ActiveObjects ao;
    private DogService dogService;

    private DogImpl(ActiveObjects ao){
        this.ao = ao;
        this.dogService = dogService.create(ao);
    }

    public static DogImpl create(ActiveObjects ao){
        return new DogImpl(ao);
    }

    public Dog getDogByUniqueId(String uniqueId){
        return dogService.getDogByUniqueId(uniqueId);
    }

    public Dog[] getAllDogs(){
        return dogService.getAllDogs();
    }

    public boolean createDog(Dog model){
        return dogService.createDog(model);
    }

    public boolean deleteDog(String uniqueId){
        return dogService.deleteDogByUniqueId(uniqueId);
    }

    public boolean updateDog(Dog model){
        return dogService.updateDog(model);
    }
}
