package com.ninja.demo.testsuite;

import com.ninja.demo.customlisteners.CustomListeners;
import com.ninja.demo.pages.AccountLoginPage;
import com.ninja.demo.pages.HomePage;
import com.ninja.demo.pages.MyAccountPage;
import com.ninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class AccountLoginPageTest extends BaseTest {
    HomePage homePage;
    AccountLoginPage accountLoginPage;
    MyAccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        accountLoginPage = new AccountLoginPage();
        accountPage = new MyAccountPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Login");
        Assert.assertEquals(accountLoginPage.getReturningCustomerText(),
                "Returning Customer", "Login page not displayed");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Login");
        accountLoginPage.enterEmailAddress("abc111@gmail.com");
        accountLoginPage.enterPassword("abc@111");
        accountLoginPage.clickOnLoginButton();
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Logout");
        Assert.assertEquals(accountPage.getAccountLogoutText(), "Account Logout", "Not logged out");
    }

}
