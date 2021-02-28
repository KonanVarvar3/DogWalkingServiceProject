package com.dvoryanchikov.dogWalkingService.myPlugin.impl.models;

import com.dvoryanchikov.dogWalkingService.myPlugin.impl.entities.IClient;

public class Client extends Human {
    private String address;

    public Client() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void toEntity(IClient entity) {
        entity.setLastName(this.getLastName());
        entity.setName(this.getName());
        entity.setMiddleName(this.getMiddleName());
        entity.setBirthDate(this.getBirthDate());
        entity.setPhoneNumber(this.getPhoneNumber());
        entity.setEmail(this.getEmail());
        entity.setAddress(this.getAddress());
    }

    public static Client formEntity(IClient entity) {
        Client client = new Client();
        client.setLastName(entity.getLastName());
        client.setName(entity.getName());
        client.setMiddleName(entity.getMiddleName());
        client.setBirthDate(entity.getBirthDate());
        client.setPhoneNumber(entity.getPhoneNumber());
        client.setEmail(entity.getEmail());
        client.setAddress(entity.getAddress());
        return client;
    }
}
