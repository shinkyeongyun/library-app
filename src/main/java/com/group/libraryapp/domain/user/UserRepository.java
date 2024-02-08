package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository 를 사용안하더라도, JpaRepository를 상속받은걸로 스프링빈으로 관리가된다.
public interface UserRepository extends JpaRepository<User, Long> { //<entity객체, id의 type>
    Optional<User> findByName(String name);
    //find : 1개의 데이터만 가져온다.
    //By뒤에 붙는 이름으로 SELECT쿼리의 WHERE문이 작성된다.

    /*  BY 앞에 들어갈 수 있는 구절정리
    *
    *   find : 1건을 가져온다. 반환타입은 객체가 될 수 있고, Optional 타입이 될 수 있다.
    *   findAll : 쿼리의 결과물이 N개인 경우 사용. List<타입>반환
    *   exists : 쿼리결과가 존재하는지 확인. 반환타입은 boolean
    *   count : SQL결과 개수를 센다. 반환타입은 long
    *
    */

    /* BY 뒤에 들어갈 수 있는 구절정리
    *
    *   각 구절은 AND나 OR로 조합할 수 있다.
    *   ex)List<User> findAllByNameAndAge(String name, int age);
    *       == SELECT * FROM user WHERE name = ? AND age=?;
    *
    *   GreaterThan : 초과
    *   GreaterThanEqual : 이상
    *   LessThan : 미만
    *   LessThanEqual : 이하
    *   Between : 사이에
    *   StartsWith : ~로 시작하는
    *   Endswith : ~로 끝나는
    */
}
