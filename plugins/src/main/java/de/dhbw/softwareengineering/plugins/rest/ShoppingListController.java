package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ShoppingListService;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import de.dhbw.softwareengineering.values.ShoppingItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListRepresentation create(
            @RequestBody UUID personUuid)
    {
        return service.create(personUuid);
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListRepresentation get(
            @PathVariable UUID uuid)
    {
        return service.getShoppingList(uuid);
    }

    @GetMapping(value = "/{uuid}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingItem> getList(
            @PathVariable UUID uuid)
    {
        return service.getShoppingItemList(uuid);
    }

    @PostMapping(path = "/{uuid}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ShoppingListRepresentation addItem(
            @PathVariable UUID uuid,
            @RequestBody @Valid ShoppingItem item)
    {
        return service.addShoppingItem(uuid, item);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @PathVariable UUID uuid)
    {
        service.delete(uuid);
    }

}
