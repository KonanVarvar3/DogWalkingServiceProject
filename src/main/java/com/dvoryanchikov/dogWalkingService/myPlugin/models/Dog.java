package com.dvoryanchikov.dogWalkingService.myPlugin.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.entities.IDog;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.enums.DogStatus;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class Dog extends UniqID{

    @JsonProperty("dogName")
    private String dogName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("dogBirthDate")
    private Date dogBirthDate;

    @JsonProperty("breed")
    private String breed;

    @JsonProperty("color")
    private String color;

    @JsonProperty("dogCharacter")
    private String dogCharacter;

    @JsonProperty("dogStatus")
    private DogStatus dogStatus;

    @JsonProperty("ownerId")
    private String ownerId;

    public Dog() {
        dogStatus = DogStatus.AT_HOME;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDogBirthDate() {
        return dogBirthDate;
    }

    public void setDogBirthDate(Date dogBirthDate) {
        this.dogBirthDate = dogBirthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDogCharacter() {
        return dogCharacter;
    }

    public void setDogCharacter(String dogCharacter) {
        this.dogCharacter = dogCharacter;
    }

    public DogStatus getDogStatus() {
        return dogStatus;
    }

    public void setDogStatus(DogStatus dogStatus) {
        this.dogStatus = dogStatus;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void toEntity(IDog entity) {
        entity.setDogName(this.getDogName());
        entity.setGender(this.getGender());
        entity.setDogBirthDate(this.getDogBirthDate());
        entity.setBreed(this.getBreed());
        entity.setColor(this.getColor());
        entity.setDogCharacter(this.getDogCharacter());
        entity.setDogStatus(this.getDogStatus());
        entity.setUniqueId(this.getUniqueId());
        entity.setOwnerId(this.getOwnerId());
    }

    public static Dog fromEntity(IDog entity) {
        Dog dog = new Dog();
        dog.setDogName(entity.getDogName());
        dog.setGender(entity.getGender());
        dog.setDogBirthDate(entity.getDogBirthDate());
        dog.setBreed(entity.getBreed());
        dog.setColor(entity.getColor());
        dog.setDogCharacter(entity.getDogCharacter());
        dog.setDogStatus(entity.getDogStatus());
        dog.setUniqueId(entity.getUniqueId());
        dog.setOwnerId(entity.getOwnerId());
        return dog;
    }
}
