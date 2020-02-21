import com.tiger.consumer.redis.Application;
import com.tiger.consumer.redis.entity.User;
import com.tiger.consumer.redis.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.Object2Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class Test {

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void test() throws IllegalAccessException {
        User user = new User();
        Object2Map.object2Map(user);
    }

    @org.junit.Test
   public void insertUser(){
        User user = new User();
        user.setName("test");
        user.setSalery(1000.0D);
        user.setAge(23);
        userService.addUser(user);
   }

   @org.junit.Test
   public void getUser(){
       User user = userService.getUser("test");
       System.out.println(user.getName());
       System.out.println(user.getSalery());
       System.out.println(user.getAge());
   }

}
