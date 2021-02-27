package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.impl.models.enums.RequestWalkStatus;

import java.util.Date;

public class RequestWalk {
    private String requestPlace;
    private Date timeWalk;
    private String pet;
    private int walkDuration;
    private RequestWalkStatus requestWalkStatus;

    public RequestWalk(){}

    public String getRequestPlace() {
        return requestPlace;
    }

    public void setRequestPlace(String requestPlace) {
        this.requestPlace = requestPlace;
    }

    public Date getTimeWalk() {
        return timeWalk;
    }

    public void setTimeWalk(Date timeWalk) {
        this.timeWalk = timeWalk;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public int getWalkDuration() {
        return walkDuration;
    }

    public void setWalkDuration(int walkDuration) {
        this.walkDuration = walkDuration;
    }

    public RequestWalkStatus getRequestWalkStatus() {
        return requestWalkStatus;
    }

    public void setRequestWalkStatus(RequestWalkStatus requestWalkStatus) {
        this.requestWalkStatus = requestWalkStatus;
    }
}
