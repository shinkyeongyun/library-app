package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    //private final List<User> users = new ArrayList<>();

//    private final JdbcTemplate jdbcTemplate;
    private final UserServiceV2 userservice;

    public UserController(UserServiceV2 userservice){
//        this.jdbcTemplate = jdbcTemplate;
        this.userservice = userservice;
    }
    @PostMapping("/user")   // POST /user
    public void saveUser(@RequestBody UserCreateRequest request){   //@RequestBody : HTTP Body에서 들어오는 JSON을 Class로 바꾸기위해
//        //users.add(new User(request.getName(), request.getAge()));
//        String sql = "INSERT INTO user (name,age) VALUES (?,?)";
//        jdbcTemplate.update(sql,request.getName(),request.getAge());
        userservice.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
//        //데이터를 담아줄 dto 필요 -> UserResponse
//        /* List<UserResponse> responses = new ArrayList<>();//비어있는 리스트를 만들고
//        for(int i=0;i<users.size();i++){//users 리스트에 담겨있는 유저들을 하나씩 돌면서
//            responses.add(new UserResponse(i+1,users.get(i)));//UserResponse 형태로바꿔서 responses 리스트로 저장하고
//        }
//        return responses; */
//        String sql = "SELECT * FROM user";
//        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
//            @Override   //ctrl + o
//            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
//                long id = rs.getLong("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                return new UserResponse(id,name,age);
//            }
//        });
        return userservice.getUsers();
    }

    //**Clean코드 만들기 : 컨트롤러 함수 1개가 3가지의 역할을 한다.

    //1. API진입 지점으로써 HTTP Body를 객체로 변환하고있다.     --> Controller의 역할
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){

    /*
      //2. 현재 유저가 있는지, 없는지 등을 확인하고 예외처리를 해준다.--> Service의 역할
        String readSql = "SELECT * FROM user WHERE id = ?";

        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }

        //3. SQL을 사용하여 실제 DB와 통신을 담당한다.             --> Repository의 역할
        String sql = "UPDATE user SET name = ? WHERE id =?";
        jdbcTemplate.update(sql, request.getName(),request.getId());
    */
        userservice.updateUser(request);

    }


    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
//        String readSql = "SELECT * FROM user WHERE name = ?";
//        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
//        if(isUserNotExist){
//            throw new IllegalArgumentException();
//        }
//
//        String sql = "DELETE FROM user WHERE name = ?";
//        jdbcTemplate.update(sql,name);
        userservice.deleteUser(name);

    }
}
