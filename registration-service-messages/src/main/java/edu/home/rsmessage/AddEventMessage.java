package edu.home.rsmessage;

public class AddEventMessage extends AddEntityMessage {

    private String serviceName;

    private AddEventMessage(Builder builder) {
        super(builder);
        this.serviceName = builder.serviceName;
    }

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
