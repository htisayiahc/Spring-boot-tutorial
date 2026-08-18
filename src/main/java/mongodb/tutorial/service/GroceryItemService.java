package mongodb.tutorial.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mongodb.tutorial.dto.CreateGroceryRequestDto;
import mongodb.tutorial.dto.CreateGroceryResponseDto;
import mongodb.tutorial.model.GroceryItem;
import mongodb.tutorial.repository.GroceryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    public CreateGroceryResponseDto createGroceryItem(CreateGroceryRequestDto createGroceryRequestDto) {
        try{
            GroceryItem groceryItem = new GroceryItem();

            groceryItem.setName(createGroceryRequestDto.getName());
            groceryItem.setCategory(createGroceryRequestDto.getCategory());
            groceryItem.setQuantity(createGroceryRequestDto.getQuantity());

            GroceryItem savedItem = groceryItemRepository.save(groceryItem);
            CreateGroceryResponseDto responseDto = new CreateGroceryResponseDto();
            responseDto.setItemId(savedItem.getId());
            return responseDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<GroceryItem> findAllByItemId(String id) {
        return groceryItemRepository.findById(id);
    }
}
