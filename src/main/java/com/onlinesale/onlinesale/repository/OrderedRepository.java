package com.onlinesale.onlinesale.repository;


import com.onlinesale.onlinesale.model.data.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface OrderedRepository extends JpaRepository<Ordered, UUID> {
}