package egov.legalModule.legal_persister.dtos;

import java.util.ArrayList;

public class Case{
    public String case_id;
    public String registration_date;
    public Advocate advocate;
    public UlbOfficer ulb_officer;
    public ArrayList<Document> documents;
    public HearingDetails hearing_details;
    public Judgment judgment;
    public Appeal appeal;
    public CounterAffidavit counter_affidavit;
    public ArrayList<ParaWiseRemark> para_wise_remarks;
    public InterimOrder interim_order;
    public CaseClosure case_closure;
    public AssignedAdvocate assigned_advocate;
}