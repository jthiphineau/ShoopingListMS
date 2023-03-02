package dao;

import java.util.ArrayList;
import java.util.List;

import entities.Article;



public class DaoArticle implements DaoInterface<Article> {
	/* private static DaoArticle instance; */
	private List<Article> articles;
	
	public DaoArticle() {
		this.articles = new ArrayList<Article>();
	};
	
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
		throw new IllegalArgumentException ("Article not found");
	}

	@Override
	public void update(long id, Article objectUpdated) {
		Article existingArticle = get(id);
		if (existingArticle != null) {
			existingArticle.setName(objectUpdated.getName());
			existingArticle.setQuantity(objectUpdated.getQuantity());
		}else {
			throw new IllegalArgumentException("Article not found");
		}
	}

	@Override
	public void create(Article newObject) {
		articles.add(newObject);

	}

	@Override
	public void delete(long id) {
		Article existingArticle = get(id);
		if (existingArticle != null) {
			articles.removeIf(article -> article.getId().equals(id));
		}else {
			throw new IllegalArgumentException("Article not found");
		}

	}

}
