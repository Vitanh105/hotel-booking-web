package com.backend.model.validate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDTO {
    private Long id;
    private String name;
    private String type;
    private byte[] image;
}
