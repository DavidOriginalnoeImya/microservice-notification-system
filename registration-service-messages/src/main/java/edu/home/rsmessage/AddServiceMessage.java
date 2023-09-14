package edu.home.rsmessage;

public class AddServiceMessage extends AddEntityMessage {

    private AddServiceMessage(Builder builder) {
        super(builder);
    }

    public static class Builder extends AddEntityMessage.Builder<Builder> {

        @Override
        public AddEntityMessage build() {
            return new AddServiceMessage(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
