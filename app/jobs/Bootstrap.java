package jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.Category;
import models.Post;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job {
    
    private static final String INITIAL_DATASET = "initial-dataset.yml";

	public void doJob() {
		Logger.info("Start bootstrap jobs");
		System.out.println("Why this damned class is not loaded ?");
		
		Category development = new Category();
		development.name = "Development";
		development.insert();
		
		Category misc = new Category();
		misc.name = "Miscellaneous";
		misc.insert();
		
		Post first = new Post();
		first.category = development;
		first.content = "Lorem ipsum";
		first.postedAt = new Date();
		first.title = "My first development post";
		first.insert();
		
		Post second = new Post();
		second.category = development;
		second.content = "Lorem ipsum";
		second.postedAt = new Date();
		second.title = "My second development post";
		second.insert();
		
		Post third = new Post();
		third.category = misc;
		third.content = "Lorem ipsum";
		third.postedAt = new Date();
		third.title = "My first miscellaneous post";
		third.insert();		
    }
    
}
