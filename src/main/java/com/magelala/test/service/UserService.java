package com.magelala.test.service;

import com.magelala.test.entity.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface UserService {

    int add(Author author);

    int delete(int id);

    Author find(int id);

    void update(Author author);

    List<Author> all();

}
