package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Article;



public class DaoArticle implements DaoInterface<Article> {
	/* private static DaoArticle instance; */
	List<Article> articles = new ArrayList<>();
	
	public DaoArticle() {
	}

	/*
	 * private DaoArticle() { articles.add(new Article(1L , "Olives", 20));
	 * articles.add(new Article(2L , "Citron confit", 5)); articles.add(new
	 * Article(3L , "Côtelette d'Agneau",8 )); }
	 * 
	 * public static DaoArticle getInstance() { if(instance == null) { instance =
	 * new DaoArticle();
	 * 
	 * } return instance; }
	 */
	@Override
	public List<Article> getAll() {

		return articles;
	}

	@Override
	public Article get(long id) {
		for (Article article : articles) {
			if (article.getId().equals(id)) {
				return article;
			}
		}
		throw new IllegalArgumentException ("Article non trouvé");
	}

	@Override
	public void update(long id, Article objectUpdated) {
		Article existingArticle = get(id);
		if (existingArticle != null) {
			existingArticle.setName(objectUpdated.getName());
			existingArticle.setQuantity(objectUpdated.getQuantity());
		}
	}

	@Override
	public void create(Article newObject) {
		articles.add(newObject);

	}

	@Override
	public void delete(long id) {
		articles.removeIf(article -> article.getId().equals(id));

	}

}
