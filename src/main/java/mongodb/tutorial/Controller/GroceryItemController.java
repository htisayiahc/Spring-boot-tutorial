package mongodb.tutorial.Controller;

import lombok.RequiredArgsConstructor;
import mongodb.tutorial.dto.CreateGroceryRequestDto;
import jakarta.websocket.server.PathParam;
import lombok.NoArgsConstructor;
import mongodb.tutorial.dto.CreateGroceryResponseDto;
import mongodb.tutorial.model.GroceryItem;
import mongodb.tutorial.service.GroceryItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/grocery")
@RequiredArgsConstructor
public class GroceryItemController {

    private final GroceryItemService groceryItemService;

    @GetMapping
    public Optional<GroceryItem> getGroceryItemById(@PathParam("id") String id) {
        return groceryItemService.findAllByItemId(id);
    }

    @PostMapping
    public ResponseEntity<CreateGroceryResponseDto> createGrocery(@RequestBody CreateGroceryRequestDto groceryItem) {
        CreateGroceryResponseDto responseDto = groceryItemService.createGroceryItem(groceryItem);
        return ResponseEntity.ok(responseDto);
    }


}
