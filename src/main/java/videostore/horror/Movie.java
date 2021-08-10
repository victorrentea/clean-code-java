package videostore.horror;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Movie {
	enum Category  {
   	CHILDRENS,
   	REGULAR,
   	NEW_RELEASE
	}

   private final String title;
	private final Category category; // NOT NULL

   public Movie(String title, Category category) {
      this.title = title;
		this.category = requireNonNull(category);
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
      return title;
   }
}