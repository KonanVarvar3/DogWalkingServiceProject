package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueInputParameters;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;

public class DogWalkerIssueService {

    public boolean create(DogWalker dogWalker) {
        try{

            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            issueInputParameters.setProjectId(IssueConstants.PROJECT_ID.numId);
            issueInputParameters.setIssueTypeId(IssueConstants.ISSUE_TYPE_DOG_WALKER_ID.id);
            issueInputParameters.setSummary(dogWalker.getName() + " " + dogWalker.getLastName());

            issueInputParameters.addCustomFieldValue("customfield_10107",dogWalker.getName());
            issueInputParameters.addCustomFieldValue("customfield_10108",dogWalker.getLastName());
            issueInputParameters.addCustomFieldValue("customfield_10109", dogWalker.getMiddleName());
            issueInputParameters.addCustomFieldValue("customfield_10110", dogWalker.getBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10113", dogWalker.getPhoneNumber());
            issueInputParameters.addCustomFieldValue("customfield_10111", dogWalker.getEmail());
            issueInputParameters.addCustomFieldValue("customfield_10114", dogWalker.getDogWalkerStatus().toString());

            IssueService.CreateValidationResult createValidationResult = issueService
                    .validateCreate(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            issueInputParameters);

            if(createValidationResult.isValid()){

                issueService.create(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        createValidationResult);

                return true;
            }

        }catch (Exception ex){
            String exs = ex.getMessage();
        }
        return false;
    }
}
