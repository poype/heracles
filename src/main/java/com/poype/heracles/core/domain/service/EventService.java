package com.poype.heracles.core.domain.service;

public interface EventService {

    void sendReleaseOrderCreated(String releaseOrderId);

    void sendApplicationCreated(String applicationId);

    void sendSprintCreated(String sprintId);
}
