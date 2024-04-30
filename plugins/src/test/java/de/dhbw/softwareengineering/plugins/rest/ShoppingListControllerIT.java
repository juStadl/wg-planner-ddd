package de.dhbw.softwareengineering.plugins.rest;

import de.dhbw.softwareengineering.ShoppingListService;
import de.dhbw.softwareengineering.representations.ShoppingListRepresentation;
import de.dhbw.softwareengineering.values.ShoppingItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShoppingListController.class)
class ShoppingListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingListService shoppingListService;

    @Test
    void ShoppingListController_create_ReturnsShoppingListRepresentation() throws Exception{
        ShoppingListRepresentation createdShoppingList = ShoppingListRepresentation.builder()
                .id(UUID.randomUUID())
                .shoppingItemList(new ArrayList<>())
                .personUUID(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
                .build();

        when(shoppingListService.create(any(UUID.class))).thenReturn(createdShoppingList);

        mockMvc.perform(post("/shoppingList")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("\"3fa85f64-5717-4562-b3fc-2c963f66afa6\""))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.shoppingItemList").exists())
                .andExpect(jsonPath("$.personUuid").value("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
                .andReturn();
    }

    @Test
    void ShoppingListController_get_ReturnsShoppingListRepresentationWithGivenUuid() throws Exception{
        UUID uuid = UUID.randomUUID();
        UUID personUuid = UUID.randomUUID();
        ShoppingListRepresentation shoppingList = ShoppingListRepresentation.builder()
                .id(uuid)
                .shoppingItemList(new ArrayList<>())
                .personUUID(personUuid)
                .build();

        when(shoppingListService.getShoppingList(any(UUID.class))).thenReturn(shoppingList);

        mockMvc.perform(get("/shoppingList/{uuid}", uuid))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.shoppingItemList").exists())
                .andExpect(jsonPath("$.personUuid").value(personUuid.toString()))
                .andReturn();
    }

    @Test
    void ShoppingListController_getList_ReturnsListOfShoppingItemsOfShoppingListWithGivenId() throws Exception{
        UUID shoppingListUuid = UUID.randomUUID();
        List<ShoppingItem> list = List.of(new ShoppingItem("title", 10, 20.99));

        when(shoppingListService.getShoppingItemList(any(UUID.class))).thenReturn(list);

        mockMvc.perform(get("/shoppingList/{uuid}/list", shoppingListUuid))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("title"))
                .andExpect(jsonPath("$.[0].quantity").value(10))
                .andExpect(jsonPath("$.[0].price").value(20.99))
                .andReturn();
    }

    @Test
    void ShoppingListController_addItem_ReturnsUpdatedShoppingListRepresentation() throws Exception{
        UUID uuid = UUID.randomUUID();
        UUID personUuid = UUID.randomUUID();
        ShoppingListRepresentation existingShoppingList = ShoppingListRepresentation.builder()
                .id(uuid)
                .shoppingItemList(new ArrayList<>())
                .personUUID(personUuid)
                .build();

        ShoppingItem item = new ShoppingItem("title", 1, 1.99);
        existingShoppingList.getShoppingItemList().add(item);

        ShoppingListRepresentation updatedShoppingList = ShoppingListRepresentation.builder()
                .id(existingShoppingList.getId())
                .shoppingItemList(existingShoppingList.getShoppingItemList())
                .personUUID(existingShoppingList.getPersonUUID())
                .build();

        when(shoppingListService.addShoppingItem(any(UUID.class), any(ShoppingItem.class))).thenReturn(updatedShoppingList);

        mockMvc.perform(post("/shoppingList/{uuid}/add", uuid)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"title\":\"title\",\"quantity\":1,\"price\":1.99}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.shoppingItemList.size()").value(1))
                .andExpect(jsonPath("$.shoppingItemList.[0].title").value("title"))
                .andExpect(jsonPath("$.shoppingItemList.[0].quantity").value(1))
                .andExpect(jsonPath("$.personUuid").value(personUuid.toString()))
                .andReturn();

    }

    @Test
    void ShoppingListController_delete_DeletesShoppingListWithGivenId() throws Exception{
        UUID uuid = UUID.randomUUID();
        doNothing().when(shoppingListService).delete(any(UUID.class));

        mockMvc.perform(delete("/shoppingList/{uuid}", uuid))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();
    }


}
