package com.venteEnLigne.venteEnLigne.repository;


import com.venteEnLigne.venteEnLigne.model.data.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderedRepository extends JpaRepository<Ordered, UUID> {
}