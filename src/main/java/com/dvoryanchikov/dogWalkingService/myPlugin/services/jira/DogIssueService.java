package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.bc.ServiceResult;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.IssueInputParametersImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;

public class DogIssueService {

    private ActiveObjects ao;
    private ClientManager clientManager;
    private DogManager dogManager;

    public DogIssueService(ActiveObjects ao){
        this.ao = ao;
        clientManager = new ClientManager(ao);
        dogManager = new DogManager(ao);
    }

    private String findFullName(String id){
        Client client = clientManager.getByUniqueId(id);
        return client.getLastName() + " " + client.getLastName();
    }

    private String findIssueId(String dogId) {
        Dog dog = dogManager.getByUniqueId(dogId);
        return dog.getIssueId();
    }

    public boolean updateIssue(Dog dog) {
        try{
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            Dog updatedDog = dogManager.getByUniqueId(dog.getUniqueId());

            issueInputParameters.setSummary(dog.getBreed() + " " + dog.getDogName());

            issueInputParameters.addCustomFieldValue("customfield_10208", updatedDog.getDogName());
            issueInputParameters.addCustomFieldValue("customfield_10209", updatedDog.getGender());
            issueInputParameters.addCustomFieldValue("customfield_10210", updatedDog.getDogBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10211", updatedDog.getBreed());
            issueInputParameters.addCustomFieldValue("customfield_10212", updatedDog.getColor());
            issueInputParameters.addCustomFieldValue("customfield_10213", updatedDog.getDogCharacter());
            issueInputParameters.addCustomFieldValue("customfield_10215", updatedDog.getDogStatus().toString());
            issueInputParameters.addCustomFieldValue("customfield_10300", findFullName(updatedDog.getOwnerId()));

            IssueService.UpdateValidationResult updateValidationResult = ComponentAccessor
                    .getIssueService().validateUpdate(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(updatedDog.getIssueId()), issueInputParameters);

            if(updateValidationResult.isValid()) {
                ComponentAccessor.getIssueService().update(
                        ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        updateValidationResult);
            }

        }catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public boolean deleteIssue(String dogId) {
        try {
            IssueService.DeleteValidationResult deleteValidationResult = ComponentAccessor
                    .getIssueService().validateDelete(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(findIssueId(dogId)));

            if(deleteValidationResult.isValid()) {
                ComponentAccessor.getIssueService().delete(ComponentAccessor
                        .getJiraAuthenticationContext().getLoggedInUser(), deleteValidationResult);
                return true;
            }

        }catch (Exception ex){
            String exs = ex.getMessage();
        }
        return false;
    }

    public boolean updateChangeStatusIssue(Dog dog) {
        try {
            int transitionId = 21;

            if(dog.getDogStatus().toString().equals("WALKING")) {
                transitionId = 11;
            }

            IssueService.TransitionValidationResult transitionValidationResult = ComponentAccessor
                    .getIssueService().validateTransition(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(findIssueId(dog.getUniqueId())),
                            transitionId, new IssueInputParametersImpl());

            if(transitionValidationResult.isValid()) {
                ComponentAccessor.getIssueService().transition(
                        ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        transitionValidationResult);

                return true;
            }

        }catch (Exception ex){
            String exs = ex.getMessage();
        }
        return false;
    }

    public Issue create(Dog dog) {
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
            issueInputParameters.addCustomFieldValue("customfield_10300", findFullName(dog.getOwnerId()));

            IssueService.CreateValidationResult createValidationResult = issueService
                    .validateCreate(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            issueInputParameters);

            if(createValidationResult.isValid()){
                return issueService.create(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        createValidationResult).getIssue();
            }

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return null;
    }
}
