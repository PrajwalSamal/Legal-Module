package egov.legalModule.legal_persister.dtos;

import java.util.ArrayList;

public class CaseRegister{
    private String tenantId;
    private String caseType;
    private String caseCategory;
    private String courtType;
    private String courtCode;
    private CaseDetails caseDetails;
    private ArrayList<Advocate> advocate;
    private UlbOfficer ulbOfficer;
    private Petitioner petitioner;
    private ArrayList<Document> documents;
}