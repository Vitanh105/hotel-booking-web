package com.backend.model.validate;

import com.backend.model.enums.StarRating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRegistrationDTO {
    @NotBlank(message = "Hotel name cannot be empty")
    //@Pattern(regexp = "^(?!\\\\s*$)[a-zA-ZàáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵđÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶÈÉẺẼẸÊỀẾỂỄỆÌÍỈĨỊÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢÙÚỦŨỤƯỪỨỬỮỰỲÝỶỸỴĐ ]+$", message = "Hotel name must only contain letters and numbers")
    private String name;

    @Valid
    private AddressDTO addressDTO;


    @Valid
    private List<RoomDTO> roomDTOs = new ArrayList<>();
    @Valid
    private List<ImageDTO> imageDTOs = new ArrayList<>();

    private StarRating starRating;

    private String description ;
}
