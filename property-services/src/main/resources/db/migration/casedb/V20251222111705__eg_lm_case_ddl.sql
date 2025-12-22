
CREATE TABLE eg_lm_case (
    caseId               VARCHAR(64) PRIMARY KEY,
    tenantId             VARCHAR(64) NOT NULL,

    caseType             VARCHAR(64),
    caseCategory         VARCHAR(64),

    title                 VARCHAR(256),
    description           TEXT,
    department            VARCHAR(128),

    courtType             VARCHAR(64),
    courtName             VARCHAR(128),

    nextHearingDate      BIGINT,

    judgementId           VARCHAR(64),

    workflowId            VARCHAR(64) NOT NULL,

    additionalDetails     JSONB,

    createdBy             VARCHAR(64),
    createdTime           BIGINT,
    lastModifiedBy       VARCHAR(64),
    lastModifiedTime     BIGINT
);

CREATE INDEX IF NOT EXISTS  idx_lm_case_tenant ON eg_lm_case (tenantId,caseId);
CREATE INDEX IF NOT EXISTS  idx_lm_case_hearing ON eg_lm_case (nextHearingDate);



-->  advocate

CREATE TABLE eg_lm_case_advocate (
    id            VARCHAR(64) PRIMARY KEY,
    caseId       VARCHAR(64) NOT NULL,
    advocateId   VARCHAR(64) NOT NULL,
    role          VARCHAR(64),
    createdBy             VARCHAR(64),
    createdTime           BIGINT,
    lastModifiedBy       VARCHAR(64),
    lastModifiedTime     BIGINT,

    CONSTRAINT fk_case_advocate_case
        FOREIGN KEY (caseId) REFERENCES eg_lm_case(caseId)
           ON DELETE CASCADE
);



CREATE TABLE eg_lm_case_petitioner (
    id            VARCHAR(64) PRIMARY KEY,
    caseId       VARCHAR(64) NOT NULL,
    name          VARCHAR(128),
    address       TEXT,
    contactNo    VARCHAR(32),
    createdBy             VARCHAR(64),
    createdTime           BIGINT,
    lastModifiedBy       VARCHAR(64),
    lastModifiedTime     BIGINT,

    CONSTRAINT fk_case_petitioner_case
        FOREIGN KEY (caseId) REFERENCES eg_lm_case(caseId)
        ON DELETE CASCADE
);




CREATE TABLE eg_lm_case_respondent (
    id            VARCHAR(64) PRIMARY KEY,
    caseId       VARCHAR(64) NOT NULL,
    name          VARCHAR(128),
    address       TEXT,
    contactNo    VARCHAR(32),
    createdBy             VARCHAR(64),
    createdTime           BIGINT,
    lastModifiedBy       VARCHAR(64),
    lastModifiedTime     BIGINT,

    CONSTRAINT fk_case_respondent_case
        FOREIGN KEY (caseId) REFERENCES eg_lm_case(caseId)
        ON DELETE CASCADE
);



CREATE TABLE eg_lm_case_document (
    id               VARCHAR(64) PRIMARY KEY,
    caseId          VARCHAR(64) NOT NULL,
    documentType    VARCHAR(64),
    fileStoreId    VARCHAR(128),
    documentUid     VARCHAR(64),
    createdBy             VARCHAR(64),
    createdTime           BIGINT,
    lastModifiedBy       VARCHAR(64),
    lastModifiedTime     BIGINT,

    CONSTRAINT fk_case_document_case
        FOREIGN KEY (caseId) REFERENCES eg_lm_case(caseId)
        ON DELETE CASCADE
);



CREATE TABLE eg_lm_judgement (
    judgementId     VARCHAR(64) PRIMARY KEY,
    caseId          VARCHAR(64) NOT NULL,
    judgementType   VARCHAR(64),
    judgementDate   BIGINT,
    remarks          TEXT,
    createdBy             VARCHAR(64),
    createdTime           BIGINT,
    lastModifiedBy       VARCHAR(64),
    lastModifiedTime     BIGINT,

    CONSTRAINT fk_judgement_case
        FOREIGN KEY (caseId) REFERENCES eg_lm_case(caseId)
        ON DELETE CASCADE
);

