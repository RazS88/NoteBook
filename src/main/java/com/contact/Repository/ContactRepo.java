package com.contact.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contact.Entity.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {


	 @Query("FROM Contact as c where c.user.id =:userId")
	   List<Contact> findAllByUserId(@Param(value = "userId") long userId);
}
