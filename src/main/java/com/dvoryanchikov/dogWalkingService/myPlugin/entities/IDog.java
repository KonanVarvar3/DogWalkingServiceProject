package com.dvoryanchikov.dogWalkingService.myPlugin.entities;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.enums.DogStatus;
import net.java.ao.Entity;

import java.util.Date;

public interface IDog extends Entity {

    String getDogName();

    void setDogName(String dogName);

    String getGender();

    void setGender(String gender);

    Date getDogBirthDate();

    void setDogBirthDate(Date dogBirthDate);

    String getBreed();

    void setBreed(String breed);

    String getColor();

    void setColor(String color);

    String getDogCharacter();

    void setDogCharacter(String dogCharacter);

    DogStatus getDogStatus();

    void setDogStatus(DogStatus dogStatus);

    String getUniqueId();

    void setUniqueId(String uniqueId);

    String getOwnerId();

    void setOwnerId(String ownerId);
}
