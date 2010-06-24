package jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import models.Category;
import models.Post;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {
    
    private static final String INITIAL_DATASET = "initial-dataset.yml";

	public void doJob() {
		if (Post.count() == 0) {
			Logger.info("Load initial dataset ...");
			Fixtures.load(INITIAL_DATASET);
			Logger.info("Dataset is initialized");
		} else {
			Logger.info("Dataset is already initialized - skip data load");
		}
		
    }
    
}
