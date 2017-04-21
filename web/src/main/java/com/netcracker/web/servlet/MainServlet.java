package com.netcracker.web.servlet;

import com.google.gson.Gson;
import com.netcracker.command.CommandFactory;
import com.netcracker.command.exception.CommandException;
import com.netcracker.command.factoryimpl.CommandFactoryMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MainServlet.class.getName());

    private void doRequest(HttpServletRequest req, HttpServletResponse resp){
        try {
            String page = req.getParameter("page");
            CommandFactory commandFactory = CommandFactoryMapper.getCommandFactory(req.getParameter("action"));
            Object result = commandFactory.createCommand().execute(req.getParameterMap());
            if(page != null) {
                req.setAttribute("items", result);
                req.getRequestDispatcher(page).forward(req,resp);
            }else {
                formJsonResponse(resp, result);
            }
        } catch (CommandException e) {
            logger.error(e);
            String message = e.getMessage();
            formJsonResponse(resp,message.substring(message.lastIndexOf(":")+1));
        }catch (IOException e){
            logger.error(e);
        }catch (ServletException e){
            logger.error(e);
        }
    }

    protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
        doRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse resp) {
        doRequest(req, resp);
    }

    protected void doDelete(HttpServletRequest req,HttpServletResponse resp) {
        doRequest(req, resp);
    }

    private void formJsonResponse(HttpServletResponse resp,Object result){
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Gson jsonConverter = new Gson();
            resp.getWriter().write(jsonConverter.toJson(result));
        }catch (IOException e){
            logger.error(e);
        }
    }
}
