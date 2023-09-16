package edu.home.rsmessage;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AddServiceMessage.Builder.class)
public class AddServiceMessage extends AddEntityMessage {

    private AddServiceMessage(Builder builder) {
        super(builder);
    }

    @JsonPOJOBuilder(withPrefix = "set")
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
