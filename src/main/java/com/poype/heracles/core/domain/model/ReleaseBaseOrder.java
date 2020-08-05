package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.enums.ReleaseBaseOrderStatus;

import java.util.Date;
import java.util.List;

/**
 * 发布
 */
public class ReleaseBaseOrder {

    /**
     * 发布记录Id
     */
    private String baseOrderId;

    /**
     * 要发布的一组应用列表
     */
    private List<String> targetApplicationNames;

    /**
     * 发布环境
     */
    private Environment environment;

    /**
     * 发布分区
     */
    private String region;

    /**
     * 整体发布状态
     */
    private ReleaseBaseOrderStatus status;

    /**
     * 发布经理
     */
    private String releaseManager;

    /**
     * 发布日期
     */
    private Date releaseDate;
}
