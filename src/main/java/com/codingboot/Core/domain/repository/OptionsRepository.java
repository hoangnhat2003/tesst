package com.codingboot.Core.domain.repository;

import com.codingboot.Core.domain.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsRepository extends JpaRepository<Options, Long> {

        Options findByName(String name);
}
