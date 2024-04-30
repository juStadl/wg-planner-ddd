package de.dhbw.softwareenginerring;

import de.dhbw.softwareengineering.ShoppingListService;
import de.dhbw.softwareengineering.entities.ShoppingList;
import de.dhbw.softwareengineering.exceptions.ShoppingListNotFoundException;
import de.dhbw.softwareengineering.mappers.ShoppingListMapper;
import de.dhbw.softwareengineering.repositories.ShoppingListRepository;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import de.dhbw.softwareengineering.values.ShoppingItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceTests {

    @Mock
    ShoppingListRepository shoppingListRepository;

    @Mock
    ShoppingListMapper shoppingListMapper;

    @InjectMocks
    ShoppingListService shoppingListService;

    @Test
    void ShoppingListService_Create_ReturnsShoppingsListRepresentation(){
        UUID uuid = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(uuid);

        ShoppingListRepresentation shoppingListRepresentation = ShoppingListRepresentation.builder()
                .id(uuid)
                .shoppingItemList(shoppingList.getShoppingItemList())
                .personUUID(shoppingList.getPersonUUID())
                .build();

        when(shoppingListRepository.insert(Mockito.any())).thenReturn(shoppingList);
        when(shoppingListMapper.toShoppingListRepresentation(shoppingList)).thenReturn(shoppingListRepresentation);

        ShoppingListRepresentation savedShoppingList = shoppingListService.create(uuid);

        Assertions.assertThat(savedShoppingList).isNotNull();
        Assertions.assertThat(savedShoppingList.getId()).isNotNull();
        Assertions.assertThat(savedShoppingList.getPersonUUID()).isEqualTo(uuid);
    }

    @Test
    void ShoppingListService_GetShoppingList_ReturnsShoppingListRepresentation()
        throws ShoppingListNotFoundException {
        UUID personUuid = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(personUuid);
        UUID uuid = shoppingList.getId();

        when(shoppingListRepository.findById(uuid)).thenReturn(Optional.of(shoppingList));

        ShoppingListRepresentation shoppingListRepresentation = ShoppingListRepresentation.builder()
                .id(uuid)
                .personUUID(shoppingList.getPersonUUID())
                .shoppingItemList(shoppingList.getShoppingItemList())
                .build();

        when(shoppingListMapper.toShoppingListRepresentation(shoppingList)).thenReturn(shoppingListRepresentation);

        ShoppingListRepresentation retrievedShoppingList = shoppingListService.getShoppingList(uuid);

        Assertions.assertThat(retrievedShoppingList).isNotNull();
        Assertions.assertThat(retrievedShoppingList.getId()).isEqualTo(uuid);
    }

    @Test
    void ShoppingListService_GetShoppingList_ThrowsException(){
        UUID notExistingUuid = UUID.randomUUID();

        when(shoppingListRepository.findById(notExistingUuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> shoppingListService.getShoppingList(notExistingUuid))
                .isInstanceOf(ShoppingListNotFoundException.class)
                .hasMessageContaining("No shoppinglist with such a UUID.");
    }

    @Test
    void ShoppingListService_GetShoppingItemList_ReturnsListOfShoppingItems() throws ShoppingListNotFoundException {
        UUID personUuid = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(personUuid);
        UUID uuid = shoppingList.getId();

        List<ShoppingItem> expectedItems = new ArrayList<>();
        expectedItems.add(new ShoppingItem("Item 1", 1, 2.99));
        expectedItems.add(new ShoppingItem("Item 2", 2, 10.99));
        shoppingList.setShoppingItemList(expectedItems);

        ShoppingListRepresentation shoppingListRepresentation = ShoppingListRepresentation.builder()
                .id(uuid)
                .shoppingItemList(shoppingList.getShoppingItemList())
                .personUUID(shoppingList.getPersonUUID())
                .build();

        when(shoppingListRepository.findById(uuid)).thenReturn(Optional.of(shoppingList));
        when(shoppingListMapper.toShoppingListRepresentation(shoppingList)).thenReturn(shoppingListRepresentation);

        List<ShoppingItem> result = shoppingListService.getShoppingItemList(uuid);

        Assertions.assertThat(result).isNotNull().isEqualTo(expectedItems);
    }

    @Test
    void ShoppingListService_AddShoppingItem_ReturnsShoppingListRepresentationWithNewShoppingItems() {
        UUID personUuid = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(personUuid);
        ShoppingItem shoppingItem = new ShoppingItem("test", 1, 1.00);
        UUID uuid = shoppingList.getId();

        ShoppingListRepresentation shoppingListRepresentation = ShoppingListRepresentation.builder()
                .id(uuid)
                .shoppingItemList(shoppingList.getShoppingItemList())
                .personUUID(shoppingList.getPersonUUID())
                .build();

        when(shoppingListRepository.findById(uuid)).thenReturn(Optional.of(shoppingList));
        when(shoppingListRepository.save(Mockito.any())).thenReturn(shoppingList);
        when(shoppingListMapper.toShoppingListRepresentation(shoppingList)).thenReturn(shoppingListRepresentation);

        ShoppingListRepresentation result = shoppingListService.addShoppingItem(uuid, shoppingItem);

        Assertions.assertThat(result.getShoppingItemList()).hasSize(1);
        Assertions.assertThat(result.getShoppingItemList().get(0)).isEqualTo(shoppingList.getShoppingItemList().get(0));
        Assertions.assertThat(result.getShoppingItemList()).isEqualTo(shoppingList.getShoppingItemList());
        verify(shoppingListRepository).save(shoppingList);
    }

    @Test
    void ShoppingListService_Delete_ExistingShoppingList_DeletesShoppingList(){
        UUID uuid = UUID.randomUUID();
        UUID personUuid = UUID.randomUUID();
        ShoppingList shoppingList = new ShoppingList(personUuid);

        when(shoppingListRepository.findById(uuid)).thenReturn(Optional.of(shoppingList));

        shoppingListService.delete(uuid);

        verify(shoppingListRepository).delete(uuid);
    }

    @Test
    void ShoppingListService_Delete_NonExistingShoppingList_ThrowsException(){
        UUID uuid = UUID.randomUUID();

        when(shoppingListRepository.findById(uuid)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> shoppingListService.delete(uuid))
                .isInstanceOf(ShoppingListNotFoundException.class)
                .hasMessageContaining("No shoppinglist with such a UUID.");
    }

}
