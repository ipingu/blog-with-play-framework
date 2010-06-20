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
	
	@Override
	public String toString() {
		return id.toString();
	}
	
}
