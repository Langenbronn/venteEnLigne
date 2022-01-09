package com.venteEnLigne.venteEnLigne.repository;


import com.venteEnLigne.venteEnLigne.model.data.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderedRepository extends JpaRepository<Ordered, Long> {
}