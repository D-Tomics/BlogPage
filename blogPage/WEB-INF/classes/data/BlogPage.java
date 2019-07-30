package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import utils.Maths;

public class BlogPage {

    private String name;
    private String data;
    private String header;
    private String content;
    private ServletContext context;

    public BlogPage(String name,ServletContext context) {
        this.name = name;
        this.context = context;
        readContentFromFile(false);
    }

    public String getContent() {
        this.content = Maths.subStringBetween(data, "<start>", "<end>");
        return content;
    }

    public String getHeader() {
        this.header = Maths.subStringBetween(data, "<header>", "</header>");
        return header;
    }

    private void readContentFromFile(boolean append) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.getResourceAsStream("./blogs/"+name+".data")));
            String line = null;

            StringBuilder sb = null;
            if(append) sb = new StringBuilder(this.data);
            else sb = new StringBuilder();
            while((line = reader.readLine()) != null ) {
                sb.append(line);
            }
            this.data = sb.toString();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}