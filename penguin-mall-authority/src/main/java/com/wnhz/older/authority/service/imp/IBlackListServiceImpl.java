package com.wnhz.older.authority.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wnhz.older.authority.service.IBlackListService;
import com.wnhz.older.domain.dao.BlackListDao;
import com.wnhz.older.domain.entity.BlackList;
import com.wnhz.older.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IBlackListServiceImpl implements IBlackListService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private BlackListDao blackListDao;

    @Override
    public void warnUpBlackList() {
        LambdaQueryWrapper<BlackList> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(BlackList::getState,-1);
        List<BlackList> blackLists = blackListDao.selectList(lambdaQueryWrapper);
        for (BlackList blackList : blackLists) {
            stringRedisTemplate.opsForSet().add("blackList",blackList.getBlackListIp());
        }
    }
}
