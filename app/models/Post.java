package models;

import java.util.Date;

import javax.persistence.Column;

import siena.Generator;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;
import siena.Query;
import siena.Table;
import siena.Time;

@Table("posts")
public class Post extends Model {

	@Id(Generator.AUTO_INCREMENT)
	public Long id;
	
	public Category category;
	
	@NotNull @Time
	public Date postedAt;
	
	@NotNull @Max(255)
	public String title;
	
	@NotNull @Max(0)
	public String content;

	public static Query<Post> all() {
		return Model.all(Post.class);
	}
	
	public static int count() {
		return Post.all().count();
	}
	
	public static Post getById(Long id) {
		return all().filter("id", id).get();
	}
	
	/**
	 * Returns a post by its slugified title.
	 * 
	 * @param title the slugified title is completed by the post id
	 * @return
	 */
	public static Post getByUrl(String title) {
		int tokenIndex = title.lastIndexOf("-");
		
		if (tokenIndex < 0) {
			return null;
		}
		
		String id = title.substring(tokenIndex + 1, title.length());
		return Post.getById(Long.parseLong(id));
	}
	
	public static Post insertPost(String title, String content, Category category) {
		Post post = new Post();
		post.title = title;
		post.content = content;
		post.category = category;
		post.insert();
		
		return post;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
	
	public String getUrl() {
		return play.templates.JavaExtensions.slugify(title) + "-" + this.id;
	}
	
}
