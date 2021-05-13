package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.IssueInputParametersImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogWalkerManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;

public class DogWalkerIssueService {

    private ActiveObjects ao;
    private DogWalkerManager dogWalkerManager;

    public DogWalkerIssueService(ActiveObjects ao) {
        this.ao = ao;
        dogWalkerManager = new DogWalkerManager(ao);
    }

    private String findIssueId(String dogWalkerId) {
        DogWalker dogWalker = dogWalkerManager.getByUniqueId(dogWalkerId);
        return dogWalker.getIssueId();
    }

    public boolean updateIssue(DogWalker dogWalker) {
        try{
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            DogWalker updatedDogWalker = dogWalkerManager.getByUniqueId(dogWalker.getUniqueId());

            issueInputParameters.setSummary(dogWalker.getName() + " " + dogWalker.getLastName());

            issueInputParameters.addCustomFieldValue("customfield_10200",
                    updatedDogWalker.getName());
            issueInputParameters.addCustomFieldValue("customfield_10201",
                    updatedDogWalker.getLastName());
            issueInputParameters.addCustomFieldValue("customfield_10202",
                    updatedDogWalker.getMiddleName());
            issueInputParameters.addCustomFieldValue("customfield_10203",
                    updatedDogWalker.getBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10204",
                    updatedDogWalker.getPhoneNumber());
            issueInputParameters.addCustomFieldValue("customfield_10205",
                    updatedDogWalker.getEmail());
            issueInputParameters.addCustomFieldValue("customfield_10207",
                    updatedDogWalker.getDogWalkerStatus().toString());

            IssueService.UpdateValidationResult updateValidationResult = ComponentAccessor
                    .getIssueService().validateUpdate(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(updatedDogWalker.getIssueId()), issueInputParameters);

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

    public boolean deleteIssue(String dogWalkerId) {
        try {
            IssueService.DeleteValidationResult deleteValidationResult = ComponentAccessor
                    .getIssueService().validateDelete(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(findIssueId(dogWalkerId)));

            if(deleteValidationResult.isValid()){
                ComponentAccessor.getIssueService().delete(ComponentAccessor
                        .getJiraAuthenticationContext().getLoggedInUser(),
                        deleteValidationResult);
                return true;
            }

        }catch (Exception ex){
            String exs = ex.getMessage();
        }
        return false;
    }

    public Boolean changeIssueStatus(DogWalker dogWalker) {
        try {
            int transitionId = 21;

            if (dogWalker.getDogWalkerStatus().toString().equals("BUSY")) transitionId = 11;

            IssueService.TransitionValidationResult transitionValidationResult = ComponentAccessor
                    .getIssueService().validateTransition(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(findIssueId(dogWalker.getUniqueId())),
                            transitionId, new IssueInputParametersImpl());

            if (transitionValidationResult.isValid()) {
                ComponentAccessor.getIssueService().transition(ComponentAccessor
                        .getJiraAuthenticationContext().getLoggedInUser(), transitionValidationResult);
            }
            return true;

        } catch (Exception ex) {
            String exs = ex.getMessage();
        }
        return false;
    }

    public Issue create(DogWalker dogWalker) {
        try{
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            issueInputParameters.setProjectId(IssueConstants.PROJECT_ID.numId);
            issueInputParameters.setIssueTypeId(IssueConstants.ISSUE_TYPE_DOG_WALKER_ID.id);
            issueInputParameters.setSummary(dogWalker.getName() + " " + dogWalker.getLastName());

            issueInputParameters.addCustomFieldValue("customfield_10200",dogWalker.getName());
            issueInputParameters.addCustomFieldValue("customfield_10201",dogWalker.getLastName());
            issueInputParameters.addCustomFieldValue("customfield_10202", dogWalker.getMiddleName());
            issueInputParameters.addCustomFieldValue("customfield_10203", dogWalker.getBirthDate().toString());
            issueInputParameters.addCustomFieldValue("customfield_10204", dogWalker.getPhoneNumber());
            issueInputParameters.addCustomFieldValue("customfield_10205", dogWalker.getEmail());
            issueInputParameters.addCustomFieldValue("customfield_10207", dogWalker.getDogWalkerStatus().toString());

            IssueService.CreateValidationResult createValidationResult = issueService
                    .validateCreate(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            issueInputParameters);

            if(createValidationResult.isValid()){
                return issueService.create(ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                        createValidationResult).getIssue();
            }

        }catch (Exception ex){
            String exs = ex.getMessage();
        }
        return null;
    }
}
