package com.solides.tangerino.blog.repository.specification;

import com.solides.tangerino.blog.model.entity.Post;
import com.solides.tangerino.blog.specification.BaseSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostSpecification extends BaseSpecification<Post> {

    private String title;

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        _addLikeCondition(predicates, criteriaBuilder, root.get("title"), this.title);
        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }
}
