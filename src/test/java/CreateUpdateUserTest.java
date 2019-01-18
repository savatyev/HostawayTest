import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateUpdateUserTest extends BaseTest {

    private static final String EMAIL = "demo@hostaway.com";
    private static final String PASSWORD = "1qaz2wsx3edc";
    private static final String TEST_USER_EMAIL = "test73456abc@gmail.com";

    @BeforeClass
    public void setUp() {
        loginPage.open();
        Assert.assertTrue(loginPage.isCurrent());
        loginPage.setEmail(EMAIL)
                .setPassword(PASSWORD)
                .submitLogin();
        dashboardPage.isCurrent();
        userManagementPage = dashboardPage.navigateToUserManagement();
        userManagementPage.deleteUserIfExists(TEST_USER_EMAIL);
        Assert.assertFalse(userManagementPage.isUserExist(TEST_USER_EMAIL));
    }

    @AfterClass(alwaysRun = true)
    public void cleanUpAfter() {
        dashboardPage.isCurrent();
        userManagementPage = dashboardPage.navigateToUserManagement();
        userManagementPage.deleteUserIfExists(TEST_USER_EMAIL);
        Assert.assertFalse(userManagementPage.isUserExist(TEST_USER_EMAIL));
    }

    @Test
    public void userCreationTest() {
        userManagementPage.addNewUserBtn.click();
        Assert.assertTrue(userProfilePage.isCurrent());
        userProfilePage
                .setEmail(TEST_USER_EMAIL)
                .setPassword("3uedfdgh#12D")
                .setFirstName("TestUser")
                .setIsAdmin(true)
                .setReceiveReservationNotificationEmail(true)
                .setAccessFinancialData(true)
                .setAccessContactData(true)
                .setReadListing(true)
                .unselectAllListings()
                .assignRandomListing()
                .saveUser()
                .confirmUserCreation();
        userManagementPage = userProfilePage.navigateToUserManagement();
        Assert.assertTrue(userManagementPage.isUserExist(TEST_USER_EMAIL));
    }

    @Test(dependsOnMethods = {"userCreationTest"})
    public void editUserTest() {
        userManagementPage.editUser(TEST_USER_EMAIL);
        Assert.assertTrue(userProfilePage.isCurrent());
        userProfilePage
                .setReceiveReservationNotificationEmail(false)
                .setAccessFinancialData(false)
                .setAccessContactData(true)
                .setHasAccessToNewListings(false)
                .setReadReservation(true)
                .setReadCalendar(true)
                .saveUser();
        Assert.assertTrue(dashboardPage.isCurrent());
    }
}