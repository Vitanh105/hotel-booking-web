package com.backend.specification;

import com.backend.entity.Comment;
import com.backend.form.CommentFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentSpecification {
    public static Specification<Comment> buildSpecCmt(CommentFilterForm form) {

        return form == null ? null : new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                String sch = form.getSearch();
                if (StringUtils.hasText(sch)) {
                    String pattern = "%" + sch.trim() + "%";
                    Predicate hasTitleLike = builder.like(root.get("body"), pattern);
                    predicates.add(hasTitleLike);

                }


//                sua laij phan nay
//                var postId=form.getPostId();
//                if (postId!=null){
//
//                    var predicate= builder.equal(root.get("post").get("id"),postId);
//                    // lay ra bang post trong root,roi sau do lay ra id trong bang post,roi so sanh voi postId ma minh truyen vao o khung tim kiem gia tri
//                    predicates.add(predicate);
//                }
//

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };

    }
}
