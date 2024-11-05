package com.example.Instargram.user.repository;

import com.example.Instargram.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
public class UserMemoryRepository /* implements UserRepository*/ {

    private final AtomicLong userIdxGenerator=new AtomicLong(0);
    private final Map<Long, User> userMap=new HashMap<Long, User>();

    //저장 및 업데이트
    public User save(User user) {
        if(user.getId()==null){
            Long id=userIdxGenerator.incrementAndGet();
            user.setId(id);
            userMap.put(id, user);
            return user;
        }else {
            userMap.replace(user.getId(), user);
            return user;
            //이미 service에서 처리했기떄문에 상관 커스텀 에러 안던져줘도됨
        }

    }

    //이메일로 조회
//    public Boolean findByEmail(String email) {
//        return userMap.values().stream()
//                .anyMatch(data -> data.getEmail().equals(email));
//    }


}
