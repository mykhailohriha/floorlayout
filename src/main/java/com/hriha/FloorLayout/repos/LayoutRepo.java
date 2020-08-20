package com.hriha.FloorLayout.repos;

import com.hriha.FloorLayout.domain.Layout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LayoutRepo extends CrudRepository<Layout, Long> {
    Layout findLayoutById(Integer id);
}
