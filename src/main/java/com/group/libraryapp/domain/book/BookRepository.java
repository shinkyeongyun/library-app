package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    //메서드가 반환할 결과값이 ‘없음’을 명백하게 표현할 필요가 있고,
    // null을 반환하면 에러를 유발할 가능성이 높은 상황에서 메서드의 반환 타입으로 Optional을 사용하는 목적입니다.
    Optional<Book> findByName(String name);
}
