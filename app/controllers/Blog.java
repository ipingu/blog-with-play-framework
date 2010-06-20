package controllers;

import java.util.List;

import models.Category;
import models.Post;
import play.mvc.Controller;
import siena.Model;
import siena.Query;

public class Blog extends Controller {

	/**
	 * Returns all posts to the view.
	 */
	public static void all() {
		List<Post> allPosts = Post.all().fetch();
		renderArgs.put("posts", allPosts);
		
		render();
	}
	
	/**
	 * Displays all post of a specific category.
	 */
	public static void category(Long categoryId) {
		Category category = Category.get(categoryId);
		
		if (category != null) {
			List<Post> postsFromCategory = Post.all().filter("category", category).fetch();
			renderArgs.put("posts", postsFromCategory);
			
			render("Blog/all.html");
		} else {
			notFound();
		}
		
	}
	
}
