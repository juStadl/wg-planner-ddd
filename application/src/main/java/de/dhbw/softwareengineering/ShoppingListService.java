package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.exceptions.ShoppingListNotFoundException;
import de.dhbw.softwareengineering.repositories.ShoppingListRepository;
import de.dhbw.softwareengineering.values.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList create(UUID personUuid){
        ShoppingList shoppingList = new ShoppingList(personUuid);
        return shoppingListRepository.save(shoppingList);
    }

    public ShoppingList getShoppingList(UUID uuid) throws ShoppingListNotFoundException {
        return shoppingListRepository.findById(uuid)
                .orElseThrow(() -> new ShoppingListNotFoundException("No shopping list with such an UUID."));
    }

    public List<ShoppingItem> getShoppingItemList(UUID uuid){
        return getShoppingList(uuid).getShoppingItemList();
    }

    public ShoppingList addShoppingItem(UUID listUuid, ShoppingItem shoppingItem){
        ShoppingList shoppingList = getShoppingList(listUuid);
        shoppingList.getShoppingItemList().add(shoppingItem);

        return shoppingListRepository.save(shoppingList);
    }

    public ShoppingList deleteShoppingItem(UUID listUuid, UUID shoppingItemUuid){
        ShoppingList shoppingList = getShoppingList(listUuid);
        shoppingList.getShoppingItemList().removeIf(shoppingItem -> shoppingItem.getUuid().equals(shoppingItemUuid));

        return shoppingListRepository.save(shoppingList);
    }

    public void delete(UUID listUuid){
        shoppingListRepository.delete(listUuid);
    }
}
