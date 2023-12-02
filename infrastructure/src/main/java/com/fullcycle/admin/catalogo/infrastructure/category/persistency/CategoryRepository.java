package com.fullcycle.admin.catalogo.infrastructure.category.persistency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, String> {
}
