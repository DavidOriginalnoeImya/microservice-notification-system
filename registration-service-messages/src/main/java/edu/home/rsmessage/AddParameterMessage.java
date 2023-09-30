package edu.home.rsmessage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddParameterMessage.Builder.class)
public class AddParameterMessage extends AddEntityMessage {

    private final InputType inputType;

    private final String eventName;

    private final String serviceName;

    private AddParameterMessage(Builder builder) {
        super(builder);
        this.inputType = builder.inputType;
        this.eventName = builder.eventName;
        this.serviceName = builder.serviceName;
    }

    public enum InputType {
        INPUT, CHECKBOX, SELECT, MULTISELECT
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder extends AddEntityMessage.Builder<Builder> {

        private InputType inputType;

        private String eventName;

        private String serviceName;

        public Builder setInputType(InputType inputType) {
            this.inputType = inputType;
            return self();
        }

        public Builder setEventName(String eventName) {
            this.eventName = eventName;
            return self();
        }

        public Builder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return self();
        }

        @Override
        public AddEntityMessage build() {
            return new AddParameterMessage(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public InputType getInputType() {
        return inputType;
    }

    public String getEventName() {
        return eventName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
