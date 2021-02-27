package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.impl.models.enums.DogStatus;

import java.util.Date;

public class Dog {
    private String dogName;
    private String gender;
    private Date dogBirthDate;
    private String breed;
    private String color;
    private String dogCharacter;
    private DogStatus dogStatus;

    public Dog(){}

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
}
