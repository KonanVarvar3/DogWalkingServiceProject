package com.dvoryanchikov.dogWalkingService.myPlugin.entities;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.enums.RequestWalkStatus;
import net.java.ao.Entity;

import java.util.Date;

public interface IRequestWalk extends Entity {
    public String getRequestPlace();

    public void setRequestPlace(String requestPlace);

    public Date getTimeWalk();

    public void setTimeWalk(Date timeWalk);

    public String getPet();

    public void setPet(String pet);

    public int getWalkDuration();

    public void setWalkDuration(int walkDuration);

    public RequestWalkStatus getRequestWalkStatus();

    public void setRequestWalkStatus(RequestWalkStatus requestWalkStatus);

    public String getUniqueId();

    public void setUniqueId(String uniqueId);
}
