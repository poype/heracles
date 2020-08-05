package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.enums.ReleaseSerialOrderStatus;

/**
 * 发布流水单
 */
public class ReleaseSerialOrder {

    private String serialOrderNo;

    private String baseOrderId;

    private ReleaseSerialOrderStatus status;

    private String applicationName;

    private String environmentName;

    private String codeRepository;

    private String codeBranch;

    private String region;

}
