package com.global.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.Model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
