package user;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserTestClassic {

    private User user;

    private int[] allowed = new int[]{21};

    private int[] notAllowed = new int[]{0, 14, 155};

    private boolean[] healthStatuses = new boolean[]{true, false};

    @Before
    public void init(){
        user = new User();
    }


    @Test
    public void testUserCanDrive(){
        for(int age: allowed){
            for (boolean healthStatus : healthStatuses) {
                user.setAge(age);
                user.setGoodHealth(healthStatus);
                if(healthStatus) {
                    assertTrue(user.canDrive());
                } else {
                    assertFalse(user.canDrive());
                }
            }
        }
    }

    @Test
    public void testUserCannotDriveAge(){
        for(int age: notAllowed){
            user.setAge(age);
            assertFalse(user.canDrive());
        }
    }

    @Test
    public void testUserCannotDriveHealth(){
        user.setGoodHealth(false);
        assertFalse(user.canDrive());
    }
}