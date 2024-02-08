package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //UserRepository(JpaRepsository를 구현한 인터페이스)에 save함수를 사용해 객체(User)를 넣어주면 INSERT SQL이 자동으로 날라간다.
    //아래있는 함수가 시작될 떄 start transaction;을 해준다(트랜잭션을 시작!!)
    //함수가 예외 없이 잘 끝났다면 commit,
    //혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request){
        User u = userRepository.save(new User(request.getName(),request.getAge()));
        //레포지토리를 호출해서 새로운(new) 유저(User)를 저장해준다.
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user.getId(),user.getName(),user.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
       User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
       //findById : id기준으로 1개의 데이터를 가져옴
        //Optional의 orElseThrow를 사용해 user가 없다면 예외를 던진다
        //user가 있다면 아래 코드로 넘어간다.

       user.updateName(request.getName());
       //객체(User)를 변경해주고,
       userRepository.save(user);   //save메서드를 호출한다.
        //자동으로 UPDATE SQL이 날라가게된다.
    }

    @Transactional
    public void deleteUser(String name){
        //update는 findById라는 함수가 따로있지만, delete는 없으므로 인터페이스에 함수(findByName)을 만들어준다.
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }

}
