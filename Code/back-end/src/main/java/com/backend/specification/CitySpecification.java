package com.backend.specification;

import com.backend.entity.City;
import com.backend.entity.Hotel;
import com.backend.form.CityFilterForm;
import com.backend.form.HotelDetailFilterForm;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CitySpecification {
    public static Specification<City> buildSpec(CityFilterForm form) {
        return (root, query, builder) ->
        {
            List<Predicate> predicates = new ArrayList<>();

            if (form == null) {
                return null;
            }

            if (StringUtils.hasText(form.getSearch())) {
                String pattern = "%" + form.getSearch().trim() + "%";
                Path<String> fullName = root.get("fullName");
                Predicate hasFullNameLike = builder.like(fullName, pattern);
                Path<String> address = root.get("address");
                Predicate hasAddressLike = builder.like(address, pattern);

                predicates.add(builder.or(hasFullNameLike, hasAddressLike));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
