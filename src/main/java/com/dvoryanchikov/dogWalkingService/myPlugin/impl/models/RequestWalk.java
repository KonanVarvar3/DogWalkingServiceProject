package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.impl.entities.IRequestWalk;
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

    public void toEntity(IRequestWalk entity){
        entity.setRequestPlace(this.getRequestPlace());
        entity.setTimeWalk(this.getTimeWalk());
        entity.setPet(this.getPet());
        entity.setWalkDuration(this.getWalkDuration());
        entity.setRequestWalkStatus(this.getRequestWalkStatus());
    }

    public static RequestWalk fromEntity(IRequestWalk entity){
        RequestWalk requestWalk = new RequestWalk();
        requestWalk.setRequestPlace(entity.getRequestPlace());
        requestWalk.setTimeWalk(entity.getTimeWalk());
        requestWalk.setPet(entity.getPet());
        requestWalk.setWalkDuration(entity.getWalkDuration());
        requestWalk.setRequestWalkStatus(entity.getRequestWalkStatus());
        return requestWalk;
    }
}
