package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.ReleaseItemDO;
import com.poype.heracles.core.repository.dao.model.ReleaseOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReleaseDAO {

    void saveReleaseOrder(ReleaseOrderDO releaseOrderDO);

    void saveReleaseItem(ReleaseItemDO releaseItemDO);

    ReleaseOrderDO queryReleaseOrderById(@Param("orderId") String orderId);

    List<ReleaseItemDO> queryReleaseItemListByOrderId(@Param("orderId") String orderId);

    void updateReleaseOrderStatus(@Param("orderId") String orderId, @Param("status") int status);

    void updateReleaseItemStatus(@Param("itemId") String itemId, @Param("status") int status);

    List<ReleaseOrderDO> queryBySprintId(@Param("sprintId") String sprintId);

    List<ReleaseOrderDO> queryPage(@Param("offset") int offset);

    int queryTotal();
}
