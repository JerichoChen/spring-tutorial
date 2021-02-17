package org.example.spring.service.impl;

import lombok.AllArgsConstructor;
import org.example.spring.dao.UserDAO;
import org.example.spring.model.SearchUserDTO;
import org.example.spring.model.User;
import org.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> listAll() {
        return userDAO.listAll();
    }

    @Override
    public List<User> findByNameOrAge(SearchUserDTO search) {
        return userDAO.findByNameOrAge(search);
    }

    @Override
    public List<User> findByIds(List<Integer> ids) {
        return userDAO.findByIds(ids);
    }

    @Override
    public List<User> findByIdsOrNames(List<Integer> ids, List<String> names) {
        return userDAO.findByIdsOrNames(ids, names);
    }

    @Override
    public List<User> findByIdsOtherwiseNames(List<Integer> ids, List<String> names) {
        return userDAO.findByIdsOtherwiseNames(ids, names);
    }
}
