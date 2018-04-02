package com.dibya.event.jms.factory;

public enum MQDestination {
    CONFIMATION_PAGE("conf-page", "queue"),
    CONFIMATION_EMAIL("conf-email", "queue");

    private String queueName;
    private String type;

    MQDestination(String queueName, String type) {
        this.queueName = queueName;
        this.type = type;
    }

    public String getName() {
        return queueName;
    }

    public String getType() {
        return type;
    }
}
