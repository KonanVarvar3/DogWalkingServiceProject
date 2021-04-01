package com.dvoryanchikov.dogWalkingService.myPlugin.models;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.UUID;

public class UniqID {

    @JsonProperty("uniqueId")
    private String uniqueId;

    public UniqID(){
        this.uniqueId = UUID.randomUUID().toString();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
