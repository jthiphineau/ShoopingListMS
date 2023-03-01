package web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	
	@PutMapping("/shoppingLists/{id}/articles/{idArticle}") 
  	public ResponseEntity<Article> updateArticle(@PathVariable Long id, @PathVariable long idArticle, @RequestBody Article articleUpdated){
	  try {
		  daoShoppingList.get(id).getDaoArticle().update(idArticle, articleUpdated);
		  return new ResponseEntity<>(daoShoppingList.get(id).getDaoArticle().get(idArticle), HttpStatus.OK);
	  }catch(IllegalArgumentException e){ 
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
	  } 
  }
	 
	@PostMapping("/shoppingLists/{id}/articles")
	public ResponseEntity<Article> addArticle(@PathVariable long id, @RequestBody Article article) {
		try {
			ShoppingList shoppingList = daoShoppingList.get(id);
			shoppingList.getDaoArticle().create(article);
			return new ResponseEntity<>(article, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/shoppingLists/{id}/articles/{idArticle}")
	public ResponseEntity<Void> deleteArticle(@PathVariable long id, @PathVariable long idArticle) {
		try {
			ShoppingList shoppingList = daoShoppingList.get(id);
			shoppingList.getDaoArticle().delete(idArticle);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
