package com.dvoryanchikov.dogWalkingService.myPlugin.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
//import com.atlassian.jira.component.pico.ComponentManager;
//import com.atlassian.plugin.PluginAccessor;
import com.atlassian.jira.component.pico.ComponentManager;
import com.atlassian.modzdetector.IOUtils;
import com.atlassian.plugin.PluginAccessor;
import com.atlassian.templaterenderer.TemplateRenderer;

//@WebServlet("/servlet-inf")
public class ServletDogWalking extends HttpServlet {

    private TemplateRenderer templateRenderer;

    public ServletDogWalking(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String requestUri = req.getRequestURI(); // /plugins/servlet/servlet-inf/
        String path = requestUri.replace("/plugins/servlet/servlet-inf/", "");
        if (path.startsWith("site/")) {
            if (path.endsWith(".html")) {
                templateRenderer.render("site/infClient.html", new HashMap(), resp.getWriter());
            }
        }
        if (path.startsWith("js/")) {
            resp.setContentType("text/javascript");
//            if (path.endsWith(".js")) {
//                templateRenderer.render("js/scriptInfClient.js", new HashMap(), resp.getWriter());
//            }
        }

        if (path.startsWith("css/")) {
            resp.setContentType("text/css");
//            if (path.endsWith(".css")) {
//                templateRenderer.render("css/myPlugin.css", new HashMap(), resp.getWriter());
//            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
