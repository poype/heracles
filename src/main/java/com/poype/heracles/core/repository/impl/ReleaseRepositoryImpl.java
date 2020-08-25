package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.ReleaseItem;
import com.poype.heracles.core.domain.model.ReleaseOrder;
import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;
import com.poype.heracles.core.domain.model.enums.ReleaseItemStatus;
import com.poype.heracles.core.domain.model.enums.ReleaseOrderStatus;
import com.poype.heracles.core.repository.ReleaseRepository;
import com.poype.heracles.core.repository.dao.ReleaseDAO;
import com.poype.heracles.core.repository.dao.model.ReleaseItemDO;
import com.poype.heracles.core.repository.dao.model.ReleaseOrderDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("releaseRepository")
public class ReleaseRepositoryImpl implements ReleaseRepository {

    @Resource
    private ReleaseDAO releaseDAO;

    @Transactional
    @Override
    public void saveReleaseOrder(ReleaseOrder releaseOrder) {
        ReleaseOrderDO releaseOrderDO = new ReleaseOrderDO(releaseOrder.getOrderId(),
                releaseOrder.getReleaseName(), releaseOrder.getDescription(), releaseOrder.getEnvName(),
                releaseOrder.getStatus().getCode(), releaseOrder.getOperator(), releaseOrder.getReleaseDate(),
                releaseOrder.getSprintId());

        releaseDAO.saveReleaseOrder(releaseOrderDO);
        for (ReleaseItem item : releaseOrder.getReleaseItems()) {
            ReleaseItemDO itemDO = new ReleaseItemDO(item.getItemId(), item.getOrderId(),
                    item.getStatus().getCode(), item.getAppName(), item.getCodeRepos(), item.getCodeBranch());
            releaseDAO.saveReleaseItem(itemDO);
        }
    }

    @Override
    public void updateReleaseOrderStatus(ReleaseOrder releaseOrder) {
        releaseDAO.updateReleaseOrderStatus(releaseOrder.getOrderId(), releaseOrder.getStatus().getCode());
        for (ReleaseItem item : releaseOrder.getReleaseItems()) {
            releaseDAO.updateReleaseItemStatus(item.getItemId(), item.getStatus().getCode());
        }
    }

    @Override
    public ReleaseOrder queryByOrderId(String orderId) {
        ReleaseOrderDO releaseOrderDO = releaseDAO.queryReleaseOrderById(orderId);
        List<ReleaseItemDO> itemDOList = releaseDAO.queryReleaseItemListByOrderId(orderId);

        List<ReleaseItem> itemList = new ArrayList<>();
        for (ReleaseItemDO itemDO : itemDOList) {
            ReleaseItem item = new ReleaseItem(itemDO.getOrderId(),
                    itemDO.getItemId(),
                    ReleaseItemStatus.getByCode(itemDO.getStatus()),
                    itemDO.getAppName(),
                    itemDO.getCodeRepos(),
                    itemDO.getCodeBranch());
            itemList.add(item);
        }
        ReleaseOrder releaseOrder = new ReleaseOrder(releaseOrderDO.getOrderId(),
                releaseOrderDO.getReleaseName(),
                releaseOrderDO.getDescription(),
                itemList,
                releaseOrderDO.getEnvName(),
                ReleaseOrderStatus.getByCode(releaseOrderDO.getStatus()),
                releaseOrderDO.getOperator(),
                releaseOrderDO.getReleaseDate());
        return releaseOrder;
    }

    @Override
    public List<SimpleReleaseOrderDto> queryReleaseOrderListBySprintId(String sprintId) {
        List<ReleaseOrderDO> releaseOrderDOList = releaseDAO.queryBySprintId(sprintId);
        return convertDtoList(releaseOrderDOList);
    }

    @Override
    public List<SimpleReleaseOrderDto> queryPageOfReleaseOrder(int pageNum) {
        int offset = (pageNum - 1) * 10;
        List<ReleaseOrderDO> releaseOrderDOList = releaseDAO.queryPage(offset);
        return convertDtoList(releaseOrderDOList);
    }

    @Override
    public int queryTotal() {
        return releaseDAO.queryTotal();
    }

    private List<SimpleReleaseOrderDto> convertDtoList(List<ReleaseOrderDO> releaseOrderDOList) {
        List<SimpleReleaseOrderDto> dtoList = new ArrayList<>();
        for (ReleaseOrderDO orderDO : releaseOrderDOList) {
            SimpleReleaseOrderDto dto = new SimpleReleaseOrderDto(
                    orderDO.getOrderId(),
                    orderDO.getReleaseName(),
                    orderDO.getDescription(),
                    orderDO.getEnvName(),
                    ReleaseOrderStatus.getByCode(orderDO.getStatus()).getName(),
                    orderDO.getOperator(),
                    orderDO.getReleaseDate()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }
}
