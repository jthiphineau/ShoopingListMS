package dao;

import java.util.ArrayList;
import java.util.List;


import entities.ShoppingList;

public class DaoShoppingList implements DaoInterface<ShoppingList> {
	private static DaoShoppingList instance;
	List<ShoppingList> shoppingLists = new ArrayList<>();
	
	private DaoShoppingList() {
		shoppingLists.add(new ShoppingList(10L,"TheFirst"));
		
	}

	public static DaoShoppingList getInstance() {
		if(instance == null) {
			instance = new DaoShoppingList();
			
		}
		return instance;
	}
	
	@Override
	public List<ShoppingList> getAll() {
		return shoppingLists;
	}

	@Override
    public ShoppingList get(long id) {
        for (ShoppingList list : shoppingLists) {
            if (list.getId().equals(id)) {
                return list;
            }
        }
        throw new IllegalArgumentException("ShoppingList not found");
    }

	@Override
    public void update(long id, ShoppingList objectUpdated) {
        ShoppingList existingShoppingList = get(objectUpdated.getId());
        if (existingShoppingList != null && objectUpdated != null) {
        	existingShoppingList.setName(objectUpdated.getName());
        } else {
            throw new IllegalArgumentException("ShoppingList not found");
        }
    }
    
	@Override
    public void create(ShoppingList newObject) {
        shoppingLists.add(newObject);
    }

	
	
	@Override
    public void delete(long id) {
        ShoppingList existingShoppingList = get(id);
        if (existingShoppingList != null) {
			shoppingLists.removeIf(shoppingList -> shoppingList.getId().equals(id));
		}else {
	        throw new IllegalArgumentException("ShoppingList not found");
		}
    }

	
	
	
}
