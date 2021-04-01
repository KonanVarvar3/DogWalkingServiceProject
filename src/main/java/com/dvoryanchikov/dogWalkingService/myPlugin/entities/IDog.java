package com.dvoryanchikov.dogWalkingService.myPlugin.entities;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.enums.DogStatus;
import net.java.ao.Entity;

import java.util.Date;

public interface IDog extends Entity {

    public String getDogName();

    public void setDogName(String dogName);

    public String getGender();

    public void setGender(String gender);

    public Date getDogBirthDate();

    public void setDogBirthDate(Date dogBirthDate);

    public String getBreed();

    public void setBreed(String breed);

    public String getColor();

    public void setColor(String color);

    public String getDogCharacter();

    public void setDogCharacter(String dogCharacter);

    public DogStatus getDogStatus();

    public void setDogStatus(DogStatus dogStatus);

    public String getUniqueId();

    public void setUniqueId(String uniqueId);

    public String getOwnerId();

    public void setOwnerId(String ownerId);
}
