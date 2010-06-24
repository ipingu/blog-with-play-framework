package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity(name="categories")
public class Category extends Model {

	@Id
	public Long id;
	
	public String name;
	
	@OneToMany
	public List<Post> posts;
	
	@Override
	public String toString() {
		return name;
	}

	public static Category getByName(String name) {
		return find("byName", name).first();
	}
	
}
