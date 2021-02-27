package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.impl.models.enums.DogWalkerStatus;

public class DogWalker extends Human{
    private DogWalkerStatus dogWalkerStatus;

    public DogWalker(){}

    public DogWalkerStatus getDogWalkerStatus() {
        return dogWalkerStatus;
    }

    public void setDogWalkerStatus(DogWalkerStatus dogWalkerStatus) {
        this.dogWalkerStatus = dogWalkerStatus;
    }
}
