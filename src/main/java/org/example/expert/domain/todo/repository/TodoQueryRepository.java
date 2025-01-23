package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Todo> findByIdWithUser(Long todoId) {

        return Optional.ofNullable(queryFactory.select(todo)
                .from(todo)
                .leftJoin(todo.user, user)
                .fetchJoin()
                .where(
                        eqId(todoId)
                )
                .fetchFirst());
    }

    private BooleanExpression eqId(Long id) {
        if (id == null) {
            return null;
        }

        return todo.id.eq(id);
    }
}
