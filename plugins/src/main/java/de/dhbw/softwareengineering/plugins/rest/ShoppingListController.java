package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ShoppingListService;
import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.values.ShoppingItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("shoppingList")
public class ShoppingListController {
    private final ShoppingListService service;

    @Autowired
    public ShoppingListController(ShoppingListService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList create(
            @RequestBody UUID personUuid)
    {
        return service.create(personUuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList get(
            @PathVariable UUID uuid)
    {
        return service.getShoppingList(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{uuid}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShoppingItem> getList(
            @PathVariable UUID uuid)
    {
        return service.getShoppingItemList(uuid);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/{uuid}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList addItem(
            @PathVariable UUID uuid,
            @RequestBody @Valid ShoppingItem item)
    {
        return service.addShoppingItem(uuid, item);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{uuid}/delete")
    public ShoppingList deleteItem(
            @PathVariable UUID uuid,
            @RequestBody @Valid UUID shoppingItemUuid)
    {
        return service.deleteShoppingItem(uuid, shoppingItemUuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{uuid}")
    public void delete(
            @PathVariable UUID uuid)
    {
        service.delete(uuid);
    }

}
