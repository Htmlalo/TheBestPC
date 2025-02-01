package com.thebest.thebestpc.repository;

import com.thebest.thebestpc.model.OderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderItemRepository extends JpaRepository<OderItem, String> {
}
