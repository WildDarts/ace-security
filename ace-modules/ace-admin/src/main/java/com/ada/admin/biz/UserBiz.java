package com.ada.admin.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.ada.admin.entity.User;
import com.ada.admin.mapper.MenuMapper;
import com.ada.admin.mapper.UserMapper;
import com.ada.common.biz.BaseBiz;
import com.ada.common.constant.UserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ada
 * @ClassName :UserBiz
 * @date 2019/10/5 16:41
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper, User> {

    @Autowired
    private MenuMapper menuMapper;

   /* @Autowired
    private UserAuthUtil userAuthUtil;*/

    @Override
    public void insertSelective(User user) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(user.getPassword());
        user.setPassword(password);
        super.insertSelective(user);
    }

    @Override
    @CacheClear(pre = "user{1.username}")
    public void updateSelectiveById(User user) {
        super.updateSelectiveById(user);
    }

    /**
     * 根据用户名获取用户信息
     */
    @Cache(key = "user{1}")
    public User getUserByUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        return mapper.selectOne(user);
    }
}
