package com.backend.model.validate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelSearchDTO {
    
    @NotBlank(message = "City cannot be empty")   
    @Pattern(regexp = "^(?!\\\\s*$)[a-zA-ZàáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵđÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶÈÉẺẼẸÊỀẾỂỄỆÌÍỈĨỊÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢÙÚỦŨỤƯỪỨỬỮỰỲÝỶỸỴĐ '-]+$", message = "City must only contain letters, apostrophes('), or hyphens(-)")
    private String city;
    
    @NotNull(message = "Check-in date cannot be empty")
    @FutureOrPresent(message = "Check-in date cannot be in the past") // để chỉ lấy giá trị ngày từ hôm nay cho đến tương lai
    private LocalDate checkinDate;


    @NotNull(message = "Check-out date cannot be empty")
    private LocalDate checkoutDate;
    
}
