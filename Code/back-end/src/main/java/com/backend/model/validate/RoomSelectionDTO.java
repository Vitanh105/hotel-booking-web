package com.backend.model.validate;

import com.backend.model.enums.RoomType;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomSelectionDTO {
    private RoomType roomType;
    private int count;
}
