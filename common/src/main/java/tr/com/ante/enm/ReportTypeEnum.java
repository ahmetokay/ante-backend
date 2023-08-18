package tr.com.ante.enm;

public enum ReportTypeEnum {

    USER_MANAGEMENT_DISA_AKTAR("UserManagement_", "userManagement"),
    ROLE_MANAGEMENT_DISA_AKTAR("RoleManagement_", "roleManagement"),

    ACCESS_LOG_DISA_AKTAR("AccessLog_", "accessLog"),
    TEST_DISA_AKTAR("Test_", "test")
    ;

    private String sourceName;
    private String name;

    ReportTypeEnum(String sourceName, String name) {
        this.sourceName = sourceName;
        this.name = name;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getName() {
        return name;
    }
}