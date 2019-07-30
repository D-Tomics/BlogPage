package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.BlogPage;
import db.Database;
import db.Table;

@WebServlet("/Blog")
public class Blog extends HttpServlet {

    private static final String DB_NAME = "usersDB";
    private static final String TABLE_NAME = "userLog";

    private Database usersDB;

    public void init() {
        usersDB = Database.getDatabase(DB_NAME);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
        
        String blog = req.getParameter("blog");
        String username = (String)req.getSession().getAttribute("username");

        if(username == null) {
            res.sendRedirect("index.jsp");
            return;
        }
        
        PrintWriter out = res.getWriter();
        usersDB.out = out;

        BlogPage page = new BlogPage(blog,getServletContext());
        req.setAttribute("head", page.getHeader());
        req.setAttribute("content", page.getContent());
        

        Table userLog = usersDB.getTable(TABLE_NAME);        //username, blog, visits

        String sql = "username='"+username+"' && blog='"+blog+"'";
        if(userLog.containsString("username='"+username+"'", "blog", blog)) {
            int blogVisits = userLog.getInt(sql, "visits");
            userLog.updateColumn(sql, "visits", blogVisits+1);
        } else {
            userLog.insert(username, blog, "1");
        }
        req.getRequestDispatcher("./blog.jsp").forward(req, res);
    }

}