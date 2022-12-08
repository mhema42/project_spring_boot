package service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.repository.ItemRepository;
import com.example.project_spring_boot.service.ItemService;
import com.example.project_spring_boot.service.ItemServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemServiceImpl;

    @Test
    public void getItemsFromRepoTest() {
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(
            new Item("Antikt Svärd", "Gammalt trasigt svärd"),
            new Item("Super Nintendo", "Komplett 16-bits konsol")
            ));

            List<Item> result = itemServiceImpl.getItems();

            assertEquals("Antikt Svärd", result.get(0).getName());
            assertEquals("Super Nintendo", result.get(1).getName());
    }

    @Test
    public void getItemByIdFromRepoTest() {
        Item item = new Item("Super Nintendo", "Komplett 16-bits konsol");
        Mockito.when(itemRepository.findById(0L)).thenReturn(item);

        Long id = item.getId();
        Item result = itemServiceImpl.getItem(id);
        assertEquals(item, result);

    
    }
    
}
