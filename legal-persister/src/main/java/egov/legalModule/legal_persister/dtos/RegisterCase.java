package egov.legalModule.legal_persister.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterCase{
    @JsonProperty("case") 
    public Case mycase;
}