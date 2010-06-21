package controllers;

import java.util.List;

import models.Category;
import models.Post;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;
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
	
	/**
	 * Displays one unique blog post. It uses the url title after slugification
	 *  (replacing spaces with "-" )
	 */
	public static void showPost(String urlTitle) {
		Post post = Post.all().filter("urlTitle", urlTitle).get();
		renderArgs.put("post", post);
		
		render();
	}
	
	/**
	 * Displays a form for inserting a new post.
	 */
	public static void showWriteForm() {
		render();
	}
	
	public static void insert(String title, String content) {
		// check if form was filled with correct values
		validation.required(title);
		validation.required(content);
		
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			showWriteForm();
		} else {
			Post post = Post.insertPost(title, content);
			showPost(title);
		}
	}
	
}
