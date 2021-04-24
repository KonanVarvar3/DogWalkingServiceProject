package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueInputParameters;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;

public class ClientIssueService{

    public boolean create(Client client) {

        try{
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            issueInputParameters.setProjectId(IssueConstants.PROJECT_ID.numId);
            issueInputParameters.setIssueTypeId(IssueConstants.ISSUE_TYPE_CLIENT_ID.id);
            issueInputParameters.setSummary(client.getName() + " " + client.getLastName());

            issueInputParameters.addCustomFieldValue("customfield_10200", client.getName());
            issueInputParameters.addCustomFieldValue("customfield_10201", client.getLastName());
            issueInputParameters.addCustomFieldValue("customfield_10202", client.getMiddleName());
            issueInputParameters.addCustomFieldValue("customfield_10203", client.getBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10204", client.getPhoneNumber());
            issueInputParameters.addCustomFieldValue("customfield_10205", client.getEmail());
            issueInputParameters.addCustomFieldValue("customfield_10206", client.getAddress());

            IssueService.CreateValidationResult createValidationResult = issueService
                    .validateCreate(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            issueInputParameters);

            if (createValidationResult.isValid()) {

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
