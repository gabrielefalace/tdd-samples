package user;

import org.junit.Before;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

@RunWith(Theories.class)
public class UserTestTheory {

    private User user;

    @DataPoint
    public static int newBorn = 0, underAge = 14, rightAge = 21, tooOld = 155;

    @DataPoint
    public static boolean badHealth = false, goodHealth = true;

    @Before
    public void init(){
        user = new User();
    }

    @Theory
    public void testUserCanDrive(int age, boolean health) {
        user.setAge(age);
        user.setGoodHealth(health);
        assumeTrue(user.getAge() >= 18);
        assumeTrue(user.getAge() <= 100);
        assumeTrue(user.isGoodHealth());
        assertTrue(user.canDrive());
    }

    @Theory
    public void testUserCannotDrive(int age) {
        user.setAge(age);
        assumeTrue(user.getAge() >= 18);
        assumeTrue(user.getAge() <= 100);
        assertFalse(user.canDrive());
    }

    @Theory
    public void testUserCannotDriveHealth(boolean health) {
        user.setGoodHealth(health);
        assumeFalse(user.isGoodHealth());
        assertFalse(user.canDrive());
    }

}