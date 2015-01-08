package org.power.action;

import org.power.action.bean.Action;
import org.power.action.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ActionRepository extends PagingAndSortingRepository<Action, Long> {

  Page<Action> findByOwner(@Param("id") User owner, Pageable pageable);

  Page<Action> findByOwnerName(@Param("name") String name, Pageable pageable);

}
