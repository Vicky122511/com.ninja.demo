package com.ninja.demo.testsuite;

import com.ninja.demo.customlisteners.CustomListeners;
import com.ninja.demo.pages.AccountRegisterPage;
import com.ninja.demo.pages.HomePage;
import com.ninja.demo.pages.MyAccountPage;
import com.ninja.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class AccountRegisterPageTest extends BaseTest {
    HomePage homePage;
    AccountRegisterPage accountRegisterPage;
    MyAccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        accountPage = new MyAccountPage();
        accountRegisterPage = new AccountRegisterPage();
    }

    @Test(groups = {"sanity","regression"})
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Register");
        Assert.assertEquals(accountRegisterPage.getRegisterAccountText(),
                "Register Account", "Register page not displayed");
    }

    @Test(groups = {"smoke","regression"})
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Register");
        accountRegisterPage.enterFirstName("abc");
        accountRegisterPage.enterLastName("xyz");
        accountRegisterPage.enterEmail("abc@gmail.com");
        accountRegisterPage.enterTelephone("0123456789");
        accountRegisterPage.enterPassword("abc@111");
        accountRegisterPage.enterConfirmPassword("abc@123");
        accountRegisterPage.selectSubscription("Yes");
        accountRegisterPage.clickOnPrivacyPolicyCheckBox();
        accountRegisterPage.clickOnContinueButton();
        Assert.assertEquals(accountPage.getYourAccountHasBeenCreatedText(), "Your Account Has Been Created!",
                "Account not created");
        accountPage.clickOnContinueButton();
        homePage.clickOnMyQAccountTab();
        homePage.selectMyAccountOptions("Logout");
        Assert.assertEquals(accountPage.getAccountLogoutText(), "Account Logout", "Not logged out");
        accountPage.clickOnContinueButton();
    }

}
