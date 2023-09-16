package edu.home.rsmessage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddEventMessage.Builder.class)
public class AddEventMessage extends AddEntityMessage {

    private String serviceName;

    private AddEventMessage(Builder builder) {
        super(builder);
        this.serviceName = builder.serviceName;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder extends AddEntityMessage.Builder<Builder> {

        private String serviceName;

        public Builder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return self();
        }

        @Override
        public AddEntityMessage build() {
            return new AddEventMessage(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public String getServiceName() {
        return serviceName;
    }
}
