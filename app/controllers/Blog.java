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
	 * Displays all posts of a specific category.
	 */
	public static void category(String name) {
		Category category = Category.getByName(name);
		
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
	public static void showPost(String url) {
		Post post = Post.getByUrl(url);
		renderArgs.put("post", post);
		
		render();
	}
	
	/**
	 * Displays a form for inserting a new post.
	 */
	public static void showWriteForm() {
		render();
	}
	
	public static void insert(String title, String content, String category) {
		// check if form was filled with correct values
		validation.required(title);
		validation.required(content);
		
		Category cat = Category.get(Long.parseLong(category));
		validation.required(cat);
		
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			showWriteForm();
		} else {
			Post post = Post.insertPost(title, content, cat);
			showPost(post.getUrl());
		}
	}
	
}
