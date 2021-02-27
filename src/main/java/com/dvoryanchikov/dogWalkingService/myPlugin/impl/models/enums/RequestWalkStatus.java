package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models.enums;

public enum RequestWalkStatus {
    SEARCHING_WALKER("Searching walker"),
    WALKING("Walking"),
    COMPLETED("Completed");

    String description;

    RequestWalkStatus(String description){
        this.description = description;
    }
}
