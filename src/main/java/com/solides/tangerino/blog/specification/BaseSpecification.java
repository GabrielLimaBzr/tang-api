package com.solides.tangerino.blog.specification;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
public abstract class BaseSpecification<T> implements Specification<T> {

    protected void _addEqualCondition(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Expression<?> expression, Object value) {
        if (Objects.nonNull(value)) {
            predicates.add(criteriaBuilder.equal(expression, value));
        }
    }

    protected void _addLikeCondition(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Expression<String> expression, String value) {
        if (StringUtils.isNotEmpty(value)) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(expression), "%" + value.toLowerCase() + "%"));
        }
    }

    protected void _addGreaterThanOrEqualCondition(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Expression<BigDecimal> expression, BigDecimal value) {
        if (Objects.nonNull(value)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(expression, value));
        }
    }

    protected void _addLessThanOrEqualCondition(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Expression<BigDecimal> expression, BigDecimal value) {
        if (Objects.nonNull(value)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(expression, value));
        }
    }
}