package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueInputParameters;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.ClientManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.managers.DogWalkerManager;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.Dog;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.DogWalker;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.RequestWalk;


public class RequestWalkIssueService {

    private ActiveObjects ao;
    private ClientManager clientManager;
    private DogManager dogManager;
    private DogWalkerManager dogWalkerManager;

    public RequestWalkIssueService(ActiveObjects ao){
        this.ao = ao;
        clientManager = new ClientManager(ao);
        dogManager = new DogManager(ao);
        dogWalkerManager = new DogWalkerManager(ao);
    }

    private String findDogWalker(String id){
        DogWalker dogWalker = dogWalkerManager.getByUniqueId(id);
        return dogWalker.getLastName() + " " + dogWalker.getName();
    }

    private String findFullName(String id){
        Client client = clientManager.getByUniqueId(id);
        return client.getLastName() + " " + client.getName();
    }

    private String findDogNameAndBreed(String id){
        Dog dog = dogManager.getByUniqueId(id);
        return dog.getBreed() + " " + dog.getDogName();
    }

    public boolean create(RequestWalk requestWalk){
        try{
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
