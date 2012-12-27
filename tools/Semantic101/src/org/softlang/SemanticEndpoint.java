package org.softlang;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.util.List;

@WebServlet(name = "SemanticEndpoint")
public class SemanticEndpoint extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        Message m;
        try {
            Gson gson = new Gson();
            m = gson.fromJson(jb.toString(), Message.class);
        } catch (ParseException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");

        List<String> params = m.getParams();

        if (m.getMethod() == "getResourceTriples"){
            writer.println("getResourceTriples");
            String res = org.softlang.Explorer.getResourceTriples("Language-3AHaskell");
            writer.write(res);
        }

        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String method = request.getParameter("method");
        doPost(request, response);
    }
}
