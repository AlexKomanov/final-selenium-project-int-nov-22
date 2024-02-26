package tests.utils;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "incorrect_password", TestUtils.NO_MATCH_CREDENTIALS_ERROR_MESSAGE},
                {"incorrect_username", "secret_sauce", TestUtils.NO_MATCH_CREDENTIALS_ERROR_MESSAGE},
                {"incorrect_username", "incorrect_password", TestUtils.NO_MATCH_CREDENTIALS_ERROR_MESSAGE},
                {"", "secret_sauce", TestUtils.EMPTY_USERNAME_ERROR_MESSAGE},
                {"standard_user", "", TestUtils.EMPTY_PASSWORD_ERROR_MESSAGE},
                {"", "", TestUtils.EMPTY_USERNAME_ERROR_MESSAGE}
        };
    }
    @DataProvider(name = "userCredentials")
    public static Object[][] userCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"visual_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
        };
    }
}