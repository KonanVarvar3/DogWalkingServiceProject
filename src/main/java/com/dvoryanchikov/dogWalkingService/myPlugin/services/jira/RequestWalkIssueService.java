package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.IssueInputParametersImpl;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogWalkerManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.RequestWalkManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;

public class RequestWalkIssueService {

    private ActiveObjects ao;
    private ClientManager clientManager;
    private DogManager dogManager;
    private DogWalkerManager dogWalkerManager;
    private RequestWalkManager requestWalkManager;

    public RequestWalkIssueService(ActiveObjects ao) {
        this.ao = ao;
        clientManager = new ClientManager(ao);
        dogManager = new DogManager(ao);
        dogWalkerManager = new DogWalkerManager(ao);
        requestWalkManager = new RequestWalkManager(ao);
    }

    private String findDogWalker(String id) {
        if (!id.equals("No dog walker")) {
            DogWalker dogWalker = dogWalkerManager.getByUniqueId(id);
            return dogWalker.getLastName() + " " + dogWalker.getName();
        } else {
            return "No dog walker";
        }
    }

    private String findFullName(String id) {
        Client client = clientManager.getByUniqueId(id);
        return client.getLastName() + " " + client.getName();
    }

    private String findDogNameAndBreed(String id) {
        Dog dog = dogManager.getByUniqueId(id);
        return dog.getBreed() + " " + dog.getDogName();
    }

    private String findIdIssue(String id) {
        RequestWalk requestWalk = requestWalkManager.getByUniqueId(id);
        return requestWalk.getIssueId();
    }

    public boolean updateIssue(RequestWalk requestWalk) {
        try{
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            RequestWalk updatedRequestWalk = requestWalkManager.getByUniqueId(requestWalk.getUniqueId());

            issueInputParameters.addCustomFieldValue("customfield_10220",
                    updatedRequestWalk.getRequestWalkStatus().toString());
            issueInputParameters.addCustomFieldValue("customfield_10400",
                    findDogWalker(updatedRequestWalk.getDogWalkerId()));

            IssueService.UpdateValidationResult updateValidationResult = ComponentAccessor
                    .getIssueService().validateUpdate(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(updatedRequestWalk.getIssueId()), issueInputParameters);

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

    public Boolean changeIssueStatus(RequestWalk requestWalk) {
        try {
            int transitionId = 21;

            if (requestWalk.getRequestWalkStatus().toString().equals("WALKING")) transitionId = 11;

            IssueService.TransitionValidationResult transitionValidationResult = ComponentAccessor
                    .getIssueService().validateTransition(
                            ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser(),
                            Long.parseLong(findIdIssue(requestWalk.getUniqueId())),
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

    public Issue create(RequestWalk requestWalk) {
        try {
            IssueService issueService = ComponentAccessor.getIssueService();
            IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();

            issueInputParameters.setProjectId(IssueConstants.PROJECT_ID.numId);
            issueInputParameters.setIssueTypeId(IssueConstants.ISSUE_TYPE_REQUEST_WALK_ID.id);
            issueInputParameters.setSummary("Owner: " + findFullName(requestWalk.getClientId())
                    + " Pet: " + findDogNameAndBreed(requestWalk.getPetId()));

            String walkDuration = Integer.toString(requestWalk.getWalkDuration());

            issueInputParameters.addCustomFieldValue("customfield_10216",
                    requestWalk.getRequestPlace());
            issueInputParameters.addCustomFieldValue("customfield_10217",
                    requestWalk.getTimeWalk());
            issueInputParameters.addCustomFieldValue("customfield_10218",
                    findDogNameAndBreed(requestWalk.getPetId()));
            issueInputParameters.addCustomFieldValue("customfield_10219",
                    walkDuration);
            issueInputParameters.addCustomFieldValue("customfield_10220",
                    requestWalk.getRequestWalkStatus().toString());
            issueInputParameters.addCustomFieldValue("customfield_10300",
                    findFullName(requestWalk.getClientId()));
            issueInputParameters.addCustomFieldValue("customfield_10400",
                    findDogWalker(requestWalk.getDogWalkerId()));

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
