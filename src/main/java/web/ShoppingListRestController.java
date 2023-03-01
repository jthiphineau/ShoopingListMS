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
public class ShoppingListRestController {
	private DaoShoppingList daoShoppingList = DaoShoppingList.getInstance();

	@GetMapping("/shoppingLists")
	public List<ShoppingList> getAllLists() {
		return daoShoppingList.getAll();
	}

	@GetMapping("/shoppingLists/{id}")
	public ResponseEntity<ShoppingList> getShoppingList(@PathVariable Long id) {
		try {
			ShoppingList shoppingList = daoShoppingList.get(id);
			return new ResponseEntity<>(shoppingList, HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/shoppingLists")
	public ResponseEntity<ShoppingList> createList(@RequestBody ShoppingList shoppingList) {
		daoShoppingList.create(shoppingList);
		return new ResponseEntity<ShoppingList>(daoShoppingList.get(shoppingList.getId()), HttpStatus.CREATED);
	}

	@PutMapping("/shoppingLists/{id}")
	public ResponseEntity<ShoppingList> updateList(@PathVariable long id, @RequestBody ShoppingList shoppingList) {
		daoShoppingList.update(id, shoppingList);
		return new ResponseEntity<ShoppingList>(daoShoppingList.get(id), HttpStatus.OK);
	}

	@DeleteMapping("/shoppingLists/{id}")
	public void deleteList(@PathVariable long id) {
		daoShoppingList.delete(id);
	}
}
