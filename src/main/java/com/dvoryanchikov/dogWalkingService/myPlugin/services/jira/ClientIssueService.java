package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;

public class ClientIssueService {

    private ActiveObjects ao;
    private ClientManager clientManager;

    public ClientIssueService(ActiveObjects ao){
        this.ao = ao;
        clientManager = new ClientManager(ao);
    }

    private String findIssue(String clientId) {
        Client client = clientManager.getByUniqueId(clientId);
        return client.getIssueId();
    }

    public boolean updateIssue(Client client) {
        try{
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            Client updatedClient = clientManager.getByUniqueId(client.getUniqueId());

            issueInputParameters.setSummary(updatedClient.getName() + " " + updatedClient.getLastName());

            issueInputParameters.addCustomFieldValue("customfield_10200", updatedClient.getName());
            issueInputParameters.addCustomFieldValue("customfield_10201", updatedClient.getLastName());
            issueInputParameters.addCustomFieldValue("customfield_10202", updatedClient.getMiddleName());
            issueInputParameters.addCustomFieldValue("customfield_10203", updatedClient.getBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10204", updatedClient.getPhoneNumber());
            issueInputParameters.addCustomFieldValue("customfield_10205", updatedClient.getEmail());
            issueInputParameters.addCustomFieldValue("customfield_10206", updatedClient.getAddress());

            IssueService.UpdateValidationResult updateValidationResult = ComponentAccessor
                    .getIssueService().validateUpdate(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(updatedClient.getIssueId()), issueInputParameters);

            if(updateValidationResult.isValid()) {
                ComponentAccessor.getIssueService().update(ComponentAccessor
                        .getJiraAuthenticationContext().getLoggedInUser(),
                        updateValidationResult);
            }

        }catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public boolean deleteIssue(String clientId) {
        try {
            IssueService.DeleteValidationResult deleteValidationResult = ComponentAccessor
                    .getIssueService().validateDelete(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(findIssue(clientId)));

            if (deleteValidationResult.isValid()) {
                ComponentAccessor.getIssueService().delete(
                        ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        deleteValidationResult);
            }
            return true;

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public Issue create(Client client) {
        try {
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
                return issueService.create(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        createValidationResult).getIssue();
            }

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return null;
    }
}
