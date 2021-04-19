package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

public enum IssueConstants {
    PROJECT_ID(10000L),
    ISSUE_TYPE_CLIENT_ID("10005"),
    ISSUE_TYPE_DOG_ID("10006"),
    ISSUE_TYPE_DOG_WALKER_ID("10007");

    String id;
    long numId;

    IssueConstants(String id){
        this.id = id;
    }

    IssueConstants(long numId){
        this.numId = numId;
    }
}
