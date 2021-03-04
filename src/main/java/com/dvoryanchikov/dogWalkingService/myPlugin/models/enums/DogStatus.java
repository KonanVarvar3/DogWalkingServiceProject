package com.dvoryanchikov.dogWalkingService.myPlugin.models.enums;

public enum DogStatus {
    WALKING("Walking"),
    AT_HOME("Stay indoors");

    String description;

    DogStatus(String description){
        this.description = description;
    }
}
