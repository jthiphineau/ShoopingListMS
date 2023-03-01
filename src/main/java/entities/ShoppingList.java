package entities;

import java.util.ArrayList;

import dao.DaoArticle;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoppingList {
	private Long id;
	private DaoArticle daoArticle;
	private String name;

	public ShoppingList(Long id, String name) {
		this.id = id;
		this.name = name;
		this.daoArticle = new DaoArticle();
	}

}
