package com.dvoryanchikov.dogWalkingService.myPlugin.entities;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.enums.RequestWalkStatus;
import net.java.ao.Entity;

import java.util.Date;

public interface IRequestWalk extends Entity {
    String getRequestPlace();

    void setRequestPlace(String requestPlace);

    String getTimeWalk();

    void setTimeWalk(String timeWalk);

    String getPetId();

    void setPetId(String petId);

    int getWalkDuration();

    void setWalkDuration(int walkDuration);

    RequestWalkStatus getRequestWalkStatus();

    void setRequestWalkStatus(RequestWalkStatus requestWalkStatus);

    void setRequestWalkStatus();

    String getUniqueId();

    void setUniqueId(String uniqueId);

    void setUniqueId();

    String getClientId();

    void setClientId(String clientId);

    String getDogWalkerId();

    void setDogWalkerId(String dogWalkerId);
}
