package com.fsm.backend.Objects.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Command {
    String commandType;

    @JsonCreator
    public Command(@JsonProperty("commandType") String commandType) {
        this.commandType = commandType;
    }

    public String getCommandType() {
        return commandType;
    }

}
