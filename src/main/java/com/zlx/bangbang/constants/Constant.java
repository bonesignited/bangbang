package com.zlx.bangbang.constants;

public interface Constant {
    int SYSTEM_BUSY_CODE = -1;
    int REQUEST_SUCCEED_CODE = 0;
    int INVALID_TOKEN = 40001;
    int INVALID_CERTIFICATE_TYPE = 40002;
    int INVALID_USER_ID = 40003;
    int INVALID_MSG_TYPE = 40008;

    String MASTER_TODAY_INCOME = "master_today_income";
    String TOKEN = "token";
    String INVALID_MESSAGE = "信息有误";

    Integer TOKEN_EXPIRED = 7200;

    // 10 天
    Integer REMEMBER_ME = 864000;

    String TEST_HOST = "http://127.0.0.1";

    int TEST_PORT = 8001;

    String MALE = "MALE";
    String FEMALE = "FEMALE";
    String NO_LIMITED = "NO_LIMITED";

    String WITHDRAW = "WITHDRAW";
}
