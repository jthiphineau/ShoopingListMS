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

import dao.DaoShoppingList;
import entities.Article;
import entities.ShoppingList;

@RestController
public class ShoppingListRestController {
	private DaoShoppingList daoShoppingList = DaoShoppingList.getInstance();

	@GetMapping("/shoppingLists")
	public List<ShoppingList> getAllLists() {
		System.out.println(" vous etes dans le GET");
		return daoShoppingList.getAll();
	}

	@GetMapping("/shoppingLists/{id}")
	public ResponseEntity<ShoppingList> getShoppingList(@PathVariable Long id) {
		ShoppingList shoppingList = daoShoppingList.get(id);
		if (shoppingList != null) {
			return new ResponseEntity<>(shoppingList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/shoppingLists")
	public void createList(@RequestBody ShoppingList shoppingList) {
		daoShoppingList.create(shoppingList);
	}

	@PutMapping("/shoppingLists/{id}")
	public void updateList(@PathVariable long id, @RequestBody ShoppingList shoppingList) {
		daoShoppingList.update(id, shoppingList);
	}

	@DeleteMapping("/shoppingLists/{id}")
	public void deleteList(@PathVariable long id) {
		daoShoppingList.delete(id);
	}

	

}
