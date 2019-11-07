package com.contact.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String email , String password);

    User findByEmail(String username);
	

	
}
