package edu.home.rsmessage;

import java.util.List;

public class AddParameterMessage extends AddEntityMessage {

    private final InputType inputType;

    private final List<String> options;

    private final String eventName;

    private final String serviceName;

    private AddParameterMessage(Builder builder) {
        super(builder);
        this.inputType = builder.inputType;
        this.options = builder.options;
        this.eventName = builder.eventName;
        this.serviceName = builder.serviceName;
    }

    public enum InputType {
        INPUT, CHECKBOX, SELECT, MULTISELECT
    }

    public static class Builder extends AddEntityMessage.Builder<Builder> {

        private InputType inputType;

        private List<String> options;

        private String eventName;

        private String serviceName;

        public Builder setInputType(InputType inputType) {
            this.inputType = inputType;
            return self();
        }

        public Builder setOptions(List<String> options) {
            this.options = options;
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

    public List<String> getOptions() {
        return options;
    }

    public String getEventName() {
        return eventName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
