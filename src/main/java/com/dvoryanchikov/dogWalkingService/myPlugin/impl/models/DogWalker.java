package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.impl.entities.IClient;
import com.dvoryanchikov.dogWalkingService.myPlugin.impl.entities.IDogWalker;
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

    public void toEntity(IDogWalker entity) {
        entity.setLastName(this.getLastName());
        entity.setName(this.getName());
        entity.setMiddleName(this.getMiddleName());
        entity.setBirthDate(this.getBirthDate());
        entity.setPhoneNumber(this.getPhoneNumber());
        entity.setEmail(this.getEmail());
        entity.setDogWalkerStatus(this.getDogWalkerStatus());
    }

    public static DogWalker formEntity(IDogWalker entity) {
        DogWalker dogWalker = new DogWalker();
        dogWalker.setLastName(entity.getLastName());
        dogWalker.setName(entity.getName());
        dogWalker.setMiddleName(entity.getMiddleName());
        dogWalker.setBirthDate(entity.getBirthDate());
        dogWalker.setPhoneNumber(entity.getPhoneNumber());
        dogWalker.setEmail(entity.getEmail());
        dogWalker.setDogWalkerStatus(entity.getDogWalkerStatus());
        return dogWalker;
    }
}
