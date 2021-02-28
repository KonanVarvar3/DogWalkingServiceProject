package com.dvoryanchikov.dogWalkingService.myPlugin.impl.entities;


import net.java.ao.Entity;

import java.util.Date;

public interface IClient extends Entity{

    public String getLastName();

    public void setLastName(String lastName);

    public String getName();

    public void setName(String name);

    public String getMiddleName();

    public void setMiddleName(String middleName);

    public Date getBirthDate();

    public void setBirthDate(Date birthDate);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public String getEmail();

    public void setEmail(String email);

    public String getAddress();

    public void setAddress(String address);

}
