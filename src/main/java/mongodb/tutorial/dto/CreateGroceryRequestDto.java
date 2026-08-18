package mongodb.tutorial.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateGroceryRequestDto {
    private String name;
    private int quantity;
    private String category;
}
