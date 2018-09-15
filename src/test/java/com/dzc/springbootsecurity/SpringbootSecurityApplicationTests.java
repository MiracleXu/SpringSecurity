package com.dzc.springbootsecurity;

import com.dzc.springbootsecurity.entity.User;
import com.dzc.springbootsecurity.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSecurityApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectByUserName() {
        User record = new User();
        record.setUsername("admin");
        User user = userMapper.selectOne(record);
        assertEquals(user.getPassword(), "admin");
    }

    @Test
    public void testSelectrolesByUserName() {
        User user = userMapper.findByUserName("admin");
        assertEquals(user.getRoles().size(), 1);
    }

}
