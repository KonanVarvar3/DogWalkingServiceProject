package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

public enum IssueConstants {
    PROJECT_ID(10000L),
    ISSUE_TYPE_CLIENT_ID("10100"),
    ISSUE_TYPE_DOG_ID("10101"),
    ISSUE_TYPE_DOG_WALKER_ID("10102"),
    ISSUE_TYPE_REQUEST_WALK_ID("10103");

    String id;
    long numId;

    IssueConstants(String id){
        this.id = id;
    }

    IssueConstants(long numId){
        this.numId = numId;
    }
}
