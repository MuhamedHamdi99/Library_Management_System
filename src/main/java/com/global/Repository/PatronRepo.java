package com.global.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.Model.Patron;

@Repository
public interface PatronRepo  extends JpaRepository<Patron,Long >{

}
