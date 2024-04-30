package de.dhbw.softwareengineering;

import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.exceptions.ShoppingListNotFoundException;
import de.dhbw.softwareengineering.interfaces.ShoppingListServiceInterface;
import de.dhbw.softwareengineering.mappers.ShoppingListMapper;
import de.dhbw.softwareengineering.repositories.ShoppingListRepository;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import de.dhbw.softwareengineering.values.ShoppingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingListService implements ShoppingListServiceInterface {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListMapper shoppingListMapper;
    private static final String ERROR_MESSAGE = "No shoppinglist with such a UUID.";

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, ShoppingListMapper shoppingListMapper) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListMapper = shoppingListMapper;
    }

    @Override
    public ShoppingListRepresentation create(UUID personUuid){
        ShoppingList shoppingList = new ShoppingList(personUuid);
        return shoppingListMapper.toShoppingListRepresentation(shoppingListRepository.insert(shoppingList));
    }

    @Override
    public ShoppingListRepresentation getShoppingList(UUID uuid) throws ShoppingListNotFoundException {
        return shoppingListMapper.toShoppingListRepresentation(shoppingListRepository.findById(uuid)
                .orElseThrow(() -> new ShoppingListNotFoundException(ERROR_MESSAGE)));
    }

    @Override
    public List<ShoppingItem> getShoppingItemList(UUID uuid){
        return getShoppingList(uuid).getShoppingItemList();
    }

    @Override
    public ShoppingListRepresentation addShoppingItem(UUID listUuid, ShoppingItem shoppingItem){
        ShoppingList shoppingList = get(listUuid);
        ShoppingItem item = new ShoppingItem(shoppingItem.title(), shoppingItem.quantity(), shoppingItem.price());
        shoppingList.getShoppingItemList().add(item);

        return shoppingListMapper.toShoppingListRepresentation(shoppingListRepository.save(shoppingList));
    }

    @Override
    public void delete(UUID uuid) throws ShoppingListNotFoundException{
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(uuid);

        if(optionalShoppingList.isEmpty()){
            throw new ShoppingListNotFoundException(ERROR_MESSAGE);
        }

        shoppingListRepository.delete(uuid);
    }

    @Override
    public ShoppingList get(UUID uuid){
        return shoppingListRepository.findById(uuid)
                .orElseThrow(() -> new ShoppingListNotFoundException(ERROR_MESSAGE));
    }
}
