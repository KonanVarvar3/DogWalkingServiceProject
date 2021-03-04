package com.dvoryanchikov.dogWalkingService.myPlugin.services;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;

public class DogService {
    private ActiveObjects ao;
    private DogManager dogManager;

    private DogService(ActiveObjects ao){
        this.ao = ao;
        this.dogManager = new DogManager(ao);
    }

    public static DogService create(ActiveObjects ao){
        return new DogService(ao);
    }

    public Dog getDogByUniqueId(String uniqueId){
        return dogManager.getDogByUniqueId(uniqueId);
    }

    public boolean createDog(Dog model){
        return dogManager.createDog(model);
    }

    public boolean deleteDogByUniqueId(String uniqueId){
        return dogManager.deleteDogByUniqueId(uniqueId);
    }

    public boolean updateDog(Dog model){
        return dogManager.updateDog(model);
    }
}
