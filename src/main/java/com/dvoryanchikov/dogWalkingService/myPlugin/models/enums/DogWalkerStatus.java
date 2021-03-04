package com.dvoryanchikov.dogWalkingService.myPlugin.models.enums;

public enum DogWalkerStatus {
        FREE("Free"),
        BUSY("Busy");

        String description;

        DogWalkerStatus(String description){
            this.description = description;
        }
}
