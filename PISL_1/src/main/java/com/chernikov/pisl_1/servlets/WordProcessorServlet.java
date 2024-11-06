package com.chernikov.pisl_1.servlets;

import java.io.*;
import java.util.List;
import java.util.Map;

import com.chernikov.pisl_1.service.WordsService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import com.google.gson.Gson;

@WebServlet(value = "/post-words")
public class WordProcessorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map<String, List<String>> categorizedWords = WordsService
                    .processWords(request.getParameter("word"));

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResponse = new Gson().toJson(categorizedWords);
            response.getWriter().write(jsonResponse);

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Internal server error.\"}");
        }
    }

    public void destroy() {
    }
}