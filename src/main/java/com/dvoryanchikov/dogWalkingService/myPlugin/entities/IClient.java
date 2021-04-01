package com.dvoryanchikov.dogWalkingService.myPlugin.entities;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import net.java.ao.Entity;

import java.util.Date;


public interface IClient extends Entity {

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

    String getAddress();

    void setAddress(String address);

    String getUniqueId();

    void setUniqueId(String uniqueId);

}
