package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueInputParameters;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;

public class DogIssueService {

    public boolean create(Dog dog) {
        try {

            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            issueInputParameters.setProjectId(IssueConstants.PROJECT_ID.numId);
            issueInputParameters.setIssueTypeId(IssueConstants.ISSUE_TYPE_DOG_ID.id);
            issueInputParameters.setSummary(dog.getBreed() + " " + dog.getDogName());

            issueInputParameters.addCustomFieldValue("customfield_10208", dog.getDogName());
            issueInputParameters.addCustomFieldValue("customfield_10209", dog.getGender());
            issueInputParameters.addCustomFieldValue("customfield_10210", dog.getDogBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10211", dog.getBreed());
            issueInputParameters.addCustomFieldValue("customfield_10212", dog.getColor());
            issueInputParameters.addCustomFieldValue("customfield_10213", dog.getDogCharacter());
            issueInputParameters.addCustomFieldValue("customfield_10215", dog.getDogStatus().toString());
            //issueInputParameters.addCustomFieldValue("",dog.getOwnerId());

            IssueService.CreateValidationResult createValidationResult = issueService
                    .validateCreate(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            issueInputParameters);

            if(createValidationResult.isValid()){

                issueService.create(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        createValidationResult);

                return true;
            }

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }
}
