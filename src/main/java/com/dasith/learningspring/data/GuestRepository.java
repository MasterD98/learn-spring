package com.dasith.learningspring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
