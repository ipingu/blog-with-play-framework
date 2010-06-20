package controllers;

import java.util.Date;

import models.Category;
import models.Post;
import play.Logger;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
    	System.out.println("App launched");
    	
        render();
    }

}