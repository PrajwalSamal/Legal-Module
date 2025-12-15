/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.22.595 on 2025-12-15 12:52:07.

export namespace Digit {

    interface MdmsCriteriaReq {
        RequestInfo: RequestInfo;
        MdmsCriteria: MdmsCriteria;
    }

    interface MdmsResponse {
        ResponseInfo: ResponseInfo;
        MdmsRes: { [index: string]: { [index: string]: any[] } };
    }

    interface MdmsCriteria {
        tenantId: string;
        moduleDetails: ModuleDetail[];
    }

    interface ModuleDetail {
        moduleName: string;
        masterDetails: MasterDetail[];
    }

    interface MasterDetail {
        name: string;
        filter: string;
    }

}
