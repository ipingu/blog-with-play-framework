package models;

import siena.Filter;
import siena.Id;
import siena.Max;
import siena.Model;
import siena.NotNull;
import siena.Query;
import siena.Table;

@Table("categories")
public class Category extends Model {

	@Id
	public Long id;
	
	@NotNull @Max(100)
	public String name;
	
	@Filter("category")
	public Query<Post> posts;
	
	@Override
	public String toString() {
		return name;
	}
	
	public static Query<Category> all() {
		return Model.all(Category.class);
	}
	
	public static Category get(long id) {
		return all().filter("id", id).get();
	}
	
}
