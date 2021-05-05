package com.dvoryanchikov.dogWalkingService.myPlugin.entities;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.enums.DogWalkerStatus;
import net.java.ao.Entity;

import java.util.Date;

public interface IDogWalker extends Entity {

    String getLastName();

    void setLastName(String lastName);

    String getName();

    void setName(String name);

    String getMiddleName();

    void setMiddleName(String middleName);

    Date getBirthDate();

    void setBirthDate(Date birthDate);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getEmail();

    void setEmail(String email);

    DogWalkerStatus getDogWalkerStatus();

    void setDogWalkerStatus(DogWalkerStatus dogWalkerStatus);

    String getUniqueId();

    void setUniqueId(String uniqueId);

}
