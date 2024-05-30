package com.app.alcala.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.alcala.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {



}
