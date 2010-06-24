package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity(name="posts")
public class Post extends Model {

	@Id()
	public Long id;
	
	@ManyToOne
	public Category category;
	
	public Date postedAt;
	
	public String title;
	
	@Lob
	public String content;
		
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
		return Post.findById(Long.parseLong(id));
	}
	
	public static Post insertPost(String title, String content, Category category) {
		Post post = new Post();
		post.title = title;
		post.content = content;
		post.category = category;
		post.save();
		
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
