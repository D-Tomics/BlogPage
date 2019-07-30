package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import db.Database;
import db.Table;

@WebServlet("/Upload")
@MultipartConfig
public class Upload extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        Database usersDb = Database.getDatabase("usersDB");
        Table test = usersDb.getTable("test");


        String id = req.getParameter("id");
        String name = req.getParameter("name");

        PrintWriter out =  res.getWriter();
        
        Part image = req.getPart("pic");
        if(image != null) {
            
        }
    }
}