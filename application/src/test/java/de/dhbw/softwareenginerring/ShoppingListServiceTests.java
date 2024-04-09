package de.dhbw.softwareenginerring;

import de.dhbw.softwareengineering.ShoppingListService;
import de.dhbw.softwareengineering.mappers.ShoppingListMapper;
import de.dhbw.softwareengineering.repositories.ShoppingListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShoppingListServiceTests {

    @Mock
    ShoppingListRepository shoppingListRepository;

    @Mock
    ShoppingListMapper shoppingListMapper;

    @InjectMocks
    ShoppingListService shoppingListService;

    //TODO: add testcases for @ShoppingListService
}
