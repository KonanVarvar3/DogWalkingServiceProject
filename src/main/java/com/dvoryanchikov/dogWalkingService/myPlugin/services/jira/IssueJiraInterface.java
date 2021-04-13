package com.dvoryanchikov.dogWalkingService.myPlugin.services.jira;

import com.dvoryanchikov.dogWalkingService.myPlugin.models.Client;
import com.dvoryanchikov.dogWalkingService.myPlugin.models.UniqID;

public interface IssueJiraInterface {

    boolean create(UniqID model);
}
