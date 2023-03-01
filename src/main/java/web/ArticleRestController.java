package web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dao.DaoShoppingList;
import entities.Article;
import entities.ShoppingList;

@RestController
public class ArticleRestController {
	private DaoShoppingList daoShoppingList = DaoShoppingList.getInstance();

	@GetMapping("/shoppingLists/{id}/articles")
	public ResponseEntity<List<Article>> getAllArticle(@PathVariable Long id) {
		try {
			ShoppingList shoppingList = daoShoppingList.get(id);
			return new ResponseEntity<>(shoppingList.getDaoArticle().getAll(), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/shoppingLists/{id}/articles/{idArticle}")
	public ResponseEntity<Article> getArticle(@PathVariable Long id, @PathVariable long idArticle) {

		try {
			Article article = daoShoppingList.get(id).getDaoArticle().get(idArticle);
			return new ResponseEntity<>(article, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	/*
	 * @PutMapping("/articles/{id}") public ResponseEntity<Article>
	 * modifierArticle(@PathVariable Long id, @RequestBody Article articlemodifie){
	 * Article article = daoArticle.get(id); if(article != null) {
	 * article.setName(articlemodifie.getName());
	 * article.setQuantity(articlemodifie.getQuantity()); daoArticle.update(id,
	 * article); return new ResponseEntity<>(article, HttpStatus.OK); }else { return
	 * new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */

	@PostMapping("/shoppingLists/{id}/articles")
	public ResponseEntity<Article> addArticle(@PathVariable long id, @RequestBody Article article) {
		try {
			ShoppingList shoppingList = daoShoppingList.get(id);
			shoppingList.getDaoArticle().create(article);
			return new ResponseEntity<>(article, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/shoppingLists/{id}/articles/{idArticle}")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Void> deleteArticle(@PathVariable long id, @PathVariable long idArticle) {
		try {
			ShoppingList shoppingList = daoShoppingList.get(id);
			shoppingList.getDaoArticle().delete(idArticle);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
}
