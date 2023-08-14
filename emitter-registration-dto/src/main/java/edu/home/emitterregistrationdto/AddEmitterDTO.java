package edu.home.emitterregistrationdto;

public class AddEmitterDTO {
    private String emitterCaption;

    private String emitterKafkaTopicName;

    public String getEmitterCaption() {
        return emitterCaption;
    }

    public void setEmitterCaption(String emitterCaption) {
        this.emitterCaption = emitterCaption;
    }

    public String getEmitterKafkaTopicName() {
        return emitterKafkaTopicName;
    }

    public void setEmitterKafkaTopicName(String emitterKafkaTopicName) {
        this.emitterKafkaTopicName = emitterKafkaTopicName;
    }
}
